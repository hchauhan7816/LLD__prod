package semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SemaphoreVsLockExample {
    private static final Semaphore semaphore = new Semaphore(3);
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        // Thread t1 = new Thread(() -> accessUsingSemaphore("t1"));
        // Thread t2 = new Thread(() -> accessUsingSemaphore("t2"));
        // Thread t3 = new Thread(() -> accessUsingSemaphore("t3"));
        // Thread t4 = new Thread(() -> accessUsingSemaphore("t4"));

        Thread t1 = new Thread(() -> accessUsingLock("t1"));
        Thread t2 = new Thread(() -> accessUsingLock("t2"));
        Thread t3 = new Thread(() -> accessUsingLock("t3"));
        Thread t4 = new Thread(() -> accessUsingLock("t4"));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

    private static void accessUsingSemaphore(String threadName) {
        try {
            System.out.println(threadName + " - WAITING");
            semaphore.acquire();
            System.out.println(threadName + " - ACQUIRED");
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(threadName + " - RELEASED");
            semaphore.release();
        }
    }

    private static void accessUsingLock(String threadName) {
        try {
            System.out.println(threadName + " - LOCK WAITING");
            lock.lock();
            System.out.println(threadName + " - LOCK ACQUIRED");
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(threadName + " - LOCK RELEASED");
            lock.unlock();
        }

    }
}