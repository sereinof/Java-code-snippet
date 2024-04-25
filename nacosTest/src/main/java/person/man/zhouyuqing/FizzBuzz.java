package person.man.zhouyuqing;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FizzBuzz {

    public static void main(String[] args) {
        Runnable printFizz = () -> {
            System.out.printf("%s", "fizz");
        };
        Runnable printBuzz = () -> {
            System.out.printf("%s", "buzz");
        };
        Runnable printFizzBuzz = () -> {
            System.out.printf("%s", "fizzbuzz");
        };
        IntConsumer intConsumer = new IntConsumer();
        FizzBuzz fb = new FizzBuzz(15);
        new Thread(() -> {
            try {
                fb.fizz(printFizz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fb.buzz(printBuzz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fb.fizzbuzz(printFizzBuzz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fb.number(intConsumer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }






    private int n;
    private volatile int i = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();
    private Condition c4 = lock.newCondition();

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        try {
            lock.lock();
            c1.await();
            while (i <= n) {
                if(i>n){
                    break;
                }
                    printFizz.run();
                    i++;
                    c4.signal();
                    c1.await();

            }
        } finally {
            lock.unlock();
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        try {
            lock.lock();
            c2.await();
            while (i <= n) {
                if(i>n){
                    break;
                }
                printBuzz.run();
                i++;
                c4.signal();
                c2.await();

            }
        } finally {
            lock.unlock();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        try {
            lock.lock();
            c3.await();
            while (i <= n) {
                if(i>n){
                    break;
                }
                printFizzBuzz.run();
                i++;
                c4.signal();

                c3.await();

            }
        } finally {
            lock.unlock();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        try {
            lock.lock();

            while (i <= n) {
                if (i % 3 != 0 && i % 5 != 0) {
                    printNumber.accept(i++);
                   continue;
                }
                if (i % 3 == 0 && i % 5 != 0) {
                    c1.signal();

                    c4.await();
                    continue;
                }

                if (i % 5 == 0 && i % 3 != 0) {

                    c2.signal();

                    c4.await();
                    continue;
                }
                if(i % 5 == 0 && i % 3 == 0) {

                    c3.signal();
                    c4.await();
                    continue;
                }
                }
            c1.signal();
            c2.signal();
            c3.signal();

            } finally{
                lock.unlock();
            }
        }
    }
class IntConsumer {
    public void accept(int i) {
        System.out.printf("%d", i);
    }
}
