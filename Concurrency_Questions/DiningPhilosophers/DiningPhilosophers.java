package DiningPhilosophers;

import java.util.concurrent.Semaphore;

public class DiningPhilosophers {

    Semaphore tableAccess;
    Semaphore[] forkAccess;
    Integer n;

    private void eat(Integer leftForkId, Integer rightForkId) {
        try {
            System.out.println("Philosopher " + (leftForkId) + " is getting forks" + " with fork " + leftForkId + " and fork " + rightForkId);
            forkAccess[leftForkId].acquire();
            forkAccess[rightForkId].acquire();
            System.out.println("Philosopher " + (leftForkId) + " is getting forks" + " with fork " + leftForkId + " and fork " + rightForkId);

            System.out.println("Philosopher " + (leftForkId) + " is eating" + " with fork " + leftForkId + " and fork " + rightForkId);

            System.out.println("Philosopher " + (leftForkId) + " is done eating" + " with fork " + leftForkId + " and fork " + rightForkId);
            forkAccess[leftForkId].release();
            forkAccess[rightForkId].release();
            System.out.println("Philosopher " + (leftForkId) + " is done eating" + " with fork " + leftForkId + " and fork " + rightForkId);
        } catch (Exception e) {
            System.out.println();
        }
    }

    private void accessTable(Integer philosopherId) {
        try {
            Thread.sleep(1000);

            tableAccess.acquire();
            System.out.println("Philosopher " + (philosopherId) + " is getting table");

            eat(philosopherId, (philosopherId + 1) % n);

            System.out.println("Philosopher " + (philosopherId) + " is leaving table");
            tableAccess.release();
        } catch (Exception e) {
            System.out.println();
        }
    }

    public void solve(Integer n) {

        tableAccess = new Semaphore(n - 1);
        forkAccess = new Semaphore[n];
        this.n = n;

        for (int i = 0; i < n; i++) {
            forkAccess[i] = new Semaphore(1);
        }

        for (int i = 0; i < n; i++) {
            Integer temp = i;
            System.out.println("Starting philosopher " + (i + 1));
            Thread t = new Thread(() -> {
                accessTable(temp);
            }, "Thread T" + (i + 1));
            t.start();
            System.out.println("Philosopher " + (i + 1) + " started");
        }

    }
}
