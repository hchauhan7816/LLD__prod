package semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {

    private static final Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> accessCriticalResource("t1"));
        Thread t2 = new Thread(() -> accessCriticalResource("t2"));
        Thread t3 = new Thread(() -> accessCriticalResource("t3"));

        t1.start();
        t2.start();
        t3.start();
    }

    private static void accessCriticalResource(String threadName) {
        System.out.println(threadName + " - WAITING");
        try {
            semaphore.acquire();
            System.out.println(threadName + " - ACCESSING");

            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            System.out.println(threadName + " - RELEASED");
            semaphore.release();
        }
    }
}