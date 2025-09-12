package semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;

public class BarrierSynchronizationPatternSemaphore {

    static class SemaphoreBarrier {
        Semaphore mutex = new Semaphore(1);
        Semaphore barrier = new Semaphore(0);
        Integer counter;
        Integer noOfThreads;

        public SemaphoreBarrier(Integer noOfThread) {
            counter = noOfThread;
            noOfThreads = noOfThread;
        }

        public void await() {
            try {
                mutex.acquire();
                counter--;

                if (counter == 0) {
                    counter = noOfThreads;
                    System.out.println("Thread " + Thread.currentThread().getName()
                            + " - All threads have arrived at the barrier");
                    barrier.release(noOfThreads - 1);
                    System.out.println(
                            "Thread " + Thread.currentThread().getName() + " - All threads have passed the barrier");
                    mutex.release();
                    System.out.println("Thread " + Thread.currentThread().getName() + " - Mutex released");
                } else {
                    mutex.release();
                    System.out.println("Thread " + Thread.currentThread().getName() + " - Mutex released");
                    barrier.acquire();
                    System.out.println("Thread " + Thread.currentThread().getName() + " - Barrier acquired");
                }

            } catch (Exception e) {
                System.out.println("Exception: " + e.getMessage());
            }
        }

    }

    public static void main(String[] args) {

        int noOfThreads = 3;
        SemaphoreBarrier barrier = new SemaphoreBarrier(noOfThreads);

        ExecutorService executorService = Executors.newFixedThreadPool(noOfThreads, new ThreadFactory() {
            private int counter = 1;

            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r, "Worker-" + counter);
                counter++;
                return t;
            }
        });

        for (int i = 0; i < noOfThreads; i++) {
            executorService.submit(() -> {
                try {
                    System.out.println("Thread " + Thread.currentThread().getName() + " - PROCESSING");
                    Thread.sleep((long) (Math.random() * 1000));
                    System.out.println("Thread " + Thread.currentThread().getName()
                            + " - PROCESSING COMPLETED & WAITING AT THE BARRIER");

                    barrier.await();

                    System.out.println("Thread " + Thread.currentThread().getName() + " - PROCESSING 2");
                    Thread.sleep((long) (Math.random() * 1000));
                    System.out.println("Thread " + Thread.currentThread().getName()
                            + " - PROCESSING 2 COMPLETED & WAITING AT THE BARRIER");

                    barrier.await();

                    System.out.println("Thread " + Thread.currentThread().getName() + " - COMPLETED");

                } catch (Exception e) {
                    System.out.println("Exception: " + e.getMessage());
                }
            });
        }
    }
}
