package person.man.zhouyuqing;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class H2O<valite> {

    private valite int bitmap = 0;;
    private Lock lock = new ReentrantLock();
    public H2O() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {

        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        if()
            releaseHydrogen.run();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {

        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
    }
}
