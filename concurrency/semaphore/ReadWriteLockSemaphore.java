package semaphore;

import java.util.concurrent.Semaphore;

public class ReadWriteLockSemaphore {

    static class ReadWriteLock {
        Integer readerCnt = 0;
        Integer writerCnt = 0;

        Semaphore lockRow = new Semaphore(1);
        Semaphore mutex = new Semaphore(1);

        public void lockRead() throws InterruptedException {
            System.out.println("Thread " + Thread.currentThread().getName() + " - READ WAITING");
            mutex.acquire();

            readerCnt++;

            if (readerCnt == 1) {  // First reader blocks writers
                lockRow.acquire();
            }

            mutex.release();
            System.out.println("Thread " + Thread.currentThread().getName() + " - READ ACQUIRED");
        }

        public void unlockRead() throws InterruptedException {
            System.out.println("Thread " + Thread.currentThread().getName() + " - READ RELEASING");
            mutex.acquire();

            readerCnt--;

            if (readerCnt == 0) {  // Last reader unblocks writers
                lockRow.release();
            }

            mutex.release();
            System.out.println("Thread " + Thread.currentThread().getName() + " - READ RELEASED");
        }

        public void lockWrite() throws InterruptedException {
            System.out.println("Thread " + Thread.currentThread().getName() + " - WRITE WAITING");
            lockRow.acquire();
            System.out.println("Thread " + Thread.currentThread().getName() + " - WRITE ACQUIRED");
        }

        public void unlockWrite() {
            System.out.println("Thread " + Thread.currentThread().getName() + " - WRITE RELEASING");
            lockRow.release();
            System.out.println("Thread " + Thread.currentThread().getName() + " - WRITE RELEASED");
        }

    }

    public static void main(String[] arg) {

        ReadWriteLock readWriteLock = new ReadWriteLock();

        Runnable read = () -> {
            try {
                readWriteLock.lockRead();
                System.out.println("Thread " + Thread.currentThread().getName() + " - READING DATA");
                Thread.sleep(500);
                readWriteLock.unlockRead();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        Runnable write = () -> {
            try {
                readWriteLock.lockWrite();
                System.out.println("Thread " + Thread.currentThread().getName() + " - WRITING DATA");
                Thread.sleep(500);
                readWriteLock.unlockWrite();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        Thread t1 = new Thread(read);
        Thread t2 = new Thread(read);
        Thread t3 = new Thread(write);
        Thread t4 = new Thread(read);
        Thread t5 = new Thread(read);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
