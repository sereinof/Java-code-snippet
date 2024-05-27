package person.man.zhou;

import java.util.concurrent.CountDownLatch;

class Foo {
    CountDownLatch latch = new CountDownLatch(1);
    CountDownLatch latch2 = new CountDownLatch(1);

    public Foo() {
    }

    public int maxProfit(int[] prices) {
        int min =prices[0];
        int res =
    }
    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        latch.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        latch.await();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        latch2.countDown();

    }

    public void third(Runnable printThird) throws InterruptedException {
        latch2.await();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
