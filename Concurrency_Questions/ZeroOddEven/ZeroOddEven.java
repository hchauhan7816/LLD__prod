import java.util.concurrent.Semaphore;

public class ZeroOddEven {

    Semaphore zeroSemaphore = new Semaphore(1);
    Semaphore oddSemaphore = new Semaphore(0);
    Semaphore evenSemaphore = new Semaphore(0);

    private void printZero(Integer n) {
        try {
            for (int i = 1; i <= n; i++) {
                zeroSemaphore.acquire();
                System.out.print("0");

                if (i % 2 == 1) {
                    oddSemaphore.release();
                } else {
                    evenSemaphore.release();
                }
            }
        } catch (Exception e) {
            System.out.println();
        }
    }

    private void printOdd(Integer n) {
        try {
            for (int i = 1; i <= n; i += 2) {
                oddSemaphore.acquire();
                System.out.print(i);
                zeroSemaphore.release();
            }
        } catch (Exception e) {
            System.out.println();
        }
    }

    private void printEven(Integer n) {
        try {
            for (int i = 2; i <= n; i += 2) {
                evenSemaphore.acquire();
                System.out.print(i);
                zeroSemaphore.release();
            }
        } catch (Exception e) {
            System.out.println();
        }
    }

    public void solve(Integer n) {

        Thread zero = new Thread(() -> printZero(n));
        Thread odd = new Thread(() -> printOdd(n));
        Thread even = new Thread(() -> printEven(n));

        zero.start();
        odd.start();
        even.start();
    }
}
