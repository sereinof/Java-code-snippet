package person.man.zhouyuqing;

import java.util.concurrent.CountDownLatch;

public class TheadSequence {
   CountDownLatch c1  = new CountDownLatch(1);
CountDownLatch c2 = new CountDownLatch(1);
    public void first(Runnable printFirst) throws InterruptedException {
c1.countDown();
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
    }

    public void second(Runnable printSecond) throws InterruptedException {
 c1.await();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        c2.countDown();

    }

    public void third(Runnable printThird) throws InterruptedException {
c2.await();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
