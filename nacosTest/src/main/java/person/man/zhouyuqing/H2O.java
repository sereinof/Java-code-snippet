package person.man.zhouyuqing;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class H2O {

    private  int state = 0;;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private Semaphore hsem = new Semaphore(2);
    private Semaphore osem = new Semaphore(0);
    public H2O() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {

        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
      hsem.acquire();
      releaseHydrogen.run();
      osem.acquire();


    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {

        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        osem.acquire(2);
        releaseOxygen.run();
        hsem.release(2);
    }
}
