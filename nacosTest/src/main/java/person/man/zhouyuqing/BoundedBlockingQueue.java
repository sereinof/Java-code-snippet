package person.man.zhouyuqing;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Semaphore;

public class BoundedBlockingQueue {
    private Semaphore s1;
    private Semaphore s2;
    private Semaphore mutex;
    private volatile int size = 0;
    private Deque<Integer> dq = new ArrayDeque<Integer>();

    public BoundedBlockingQueue(int capacity) {
        s1 = new Semaphore(capacity);
        s2 = new Semaphore(0);
        mutex = new Semaphore(1);

    }

    public void enqueue(int element) throws InterruptedException {
        s1.acquire(1);
        mutex.acquire(1);
        dq.addFirst(element);
        size++;
        mutex.release(1);
        s2.release(1);
    }

    public int dequeue() throws InterruptedException {
        s2.acquire(1);
        ;
        mutex.acquire(1);
        int e = dq.removeLast();
        size--;
        s1.release();
        return e;
    }

    public int size() {
  return this.size;
    }
}
