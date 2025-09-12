import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Semaphore;

/**
 * BoundedBlockingQueue implementation using semaphores for thread-safe operations.
 * This queue has a fixed capacity and blocks producers when full and consumers when empty.
 */
public class BoundedBlockingQueue {

    // Semaphore to track available empty slots in the queue
    Semaphore emptySlotsSemaphore;
    // Semaphore to track available filled slots in the queue  
    Semaphore filledSlotsSemaphore;
    // Thread-safe deque to store the actual data
    Deque<Integer> dequeue;

    /**
     * Adds an element to the queue. Blocks if the queue is full.
     * @param x the integer value to add to the queue
     */
    public void enqueue(Integer x) {
        try {
            String threadName = Thread.currentThread().getName();
            System.out.println("[" + threadName + "] WAITING to add " + x + " (Empty slots: " + emptySlotsSemaphore.availablePermits() + ")");
            
            // Wait for an empty slot to become available
            emptySlotsSemaphore.acquire();

            System.out.println("[" + threadName + "] ACQUIRED empty slot, adding " + x + " to queue");
            dequeue.addLast(x);
            System.out.println("[" + threadName + "] SUCCESSFULLY added " + x + ". Queue: " + dequeue + " (Size: " + dequeue.size() + ")");

            // Signal that a filled slot is now available
            filledSlotsSemaphore.release();
            System.out.println("[" + threadName + "] RELEASED filled slot (Filled slots now: " + filledSlotsSemaphore.availablePermits() + ")");
        } catch (Exception e) {
            System.out.println("[" + Thread.currentThread().getName() + "] ERROR in enqueue: " + e);
        }
    }

    /**
     * Removes and returns an element from the queue. Blocks if the queue is empty.
     */
    public void dequeue() {
        try {
            String threadName = Thread.currentThread().getName();
            System.out.println("[" + threadName + "] WAITING to remove element (Filled slots: " + filledSlotsSemaphore.availablePermits() + ")");
            
            // Wait for a filled slot to become available
            filledSlotsSemaphore.acquire();

            System.out.println("[" + threadName + "] ACQUIRED filled slot, removing from queue: " + dequeue);
            Integer removedValue = dequeue.removeFirst();
            System.out.println("[" + threadName + "] SUCCESSFULLY removed " + removedValue + ". Queue: " + dequeue + " (Size: " + dequeue.size() + ")");

            // Signal that an empty slot is now available
            emptySlotsSemaphore.release();
            System.out.println("[" + threadName + "] RELEASED empty slot (Empty slots now: " + emptySlotsSemaphore.availablePermits() + ")");
        } catch (Exception e) {
            System.out.println("[" + Thread.currentThread().getName() + "] ERROR in dequeue: " + e);
        }
    }

    /**
     * Demonstrates the bounded blocking queue functionality by creating producer and consumer threads.
     * @param n the maximum capacity of the queue
     */
    public void solve(Integer n) {
        // Initialize semaphores: n empty slots available, 0 filled slots
        emptySlotsSemaphore = new Semaphore(n);
        filledSlotsSemaphore = new Semaphore(0);
        dequeue = new ConcurrentLinkedDeque<Integer>();

        System.out.println("========================================");
        System.out.println("BOUNDED BLOCKING QUEUE - INITIAL STATE");
        System.out.println("Queue Capacity: " + n);
        System.out.println("Empty slots available: " + emptySlotsSemaphore.availablePermits());
        System.out.println("Filled slots available: " + filledSlotsSemaphore.availablePermits());
        System.out.println("Queue contents: " + dequeue);
        System.out.println("========================================" + "\n");

        // Create 10 producer threads to add elements to the queue
        System.out.println("🟢 STARTING PRODUCER THREADS...\n");
        for (int i = 1; i <= 5; i++) {
            Integer value = i;
            String threadName = "PRODUCER-" + i;

            System.out.println("🚀 Creating " + threadName + " to add value: " + value);
            System.out.println("   Current state - Empty slots: " + emptySlotsSemaphore.availablePermits() + 
                             ", Filled slots: " + filledSlotsSemaphore.availablePermits() + 
                             ", Queue size: " + dequeue.size());
            System.out.println();

            // Create and start a producer thread
            Thread t = new Thread(() -> {
                enqueue(value);
            }, threadName);
            t.start();
        }

        // Create 10 consumer threads to remove elements from the queue
        System.out.println("\n🔴 STARTING CONSUMER THREADS...\n");
        for (int i = 1; i <= 5; i++) {
            String threadName = "CONSUMER-" + i;
            
            System.out.println("🔥 Creating " + threadName + " to remove element");
            System.out.println("   Current state - Empty slots: " + emptySlotsSemaphore.availablePermits() + 
                             ", Filled slots: " + filledSlotsSemaphore.availablePermits() + 
                             ", Queue size: " + dequeue.size());
            System.out.println();

            // Create and start a consumer thread
            Thread t = new Thread(() -> {
                dequeue();
            }, threadName);
            t.start();
        }

        /* Alternative implementation using ExecutorService (commented out):
         * 
         * ExecutorService readerExecutorService = Executors.newFixedThreadPool(10, new
         * ThreadFactory() {
         *     private Integer counter = 1;
         * 
         *     @Override
         *     public Thread newThread(Runnable r) {
         *         return new Thread("Thread R" + 1);
         *     }
         * });
         * 
         * ExecutorService writerExecutorService = Executors.newFixedThreadPool(10, new
         * ThreadFactory() {
         *     private Integer counter = 1;
         * 
         *     @Override
         *     public Thread newThread(Runnable r) {
         *         return new Thread("Thread W" + 1);
         *     }
         * });
         */
    }
}