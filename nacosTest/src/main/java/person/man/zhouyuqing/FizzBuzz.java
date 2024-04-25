package person.man.zhouyuqing;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FizzBuzz {
    private int n;
    private volatile int i = 0;
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
            while (i <= n) {
                if (i % 3 == 0 && i%5!=0) {
                    printFizz.run();
                    i++;
                    c4.signal();
                    c1.await();
                }else{
                    c1.await();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        try {
            lock.lock();
            while (i <= n) {
                if (i % 5 == 0&& i%3!=0) {
                    printBuzz.run();
                    i++;
                    c4.signal();
                    c2.await();
                }else{
                    c2.await();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        try {
            lock.lock();
            while (i <= n) {
                if (i % 3 == 0 && i % 5 == 0) {
                    printFizzBuzz.run();
                    i++;
                    c4.signal();
                    c3.await();
                }else{
                    c3.await();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        try {
            lock.lock();

            while (i < n) {
               if(i%3!=0 &i%5!=0){
                   printNumber.accept(i++);
                   c1.signal();
                   c2.signal();
                   c3.signal();
                   c4.await();
               }
            }


        } finally {
            lock.unlock();
        }
    }
}

