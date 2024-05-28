package person.man.zhou;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReentrantLock;

class Foo {
    CountDownLatch latch = new CountDownLatch(1);
    CountDownLatch latch2 = new CountDownLatch(1);

    public Foo() {
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList();
        if(nums.length==0){
            return res;
        }
        int i = 0;
        while (i <= nums.length - 1) {
            while (nums[i] != i + 1) {
                int exactN = nums[i];//将该值放到 exactN
                swap(nums, i, nums[i] - 1);
            }
            i++;
        }
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != j + 1) {
                res.add(j + 1);
            }
        }
        return res;
    }

    public void swap(int[] nums, int index1, int index2) {
        int tmp = nums[index2];
        nums[index2] = nums[index1];
        nums[index1] = tmp;
    }

    public int maxProfit(int[] prices) {
        int min = prices[0];
        int res = Integer.MIN_VALUE;
        for (int i = 1; i < prices.length; i++) {
            int profix = prices[i] - min;
            res = Math.max(profix, res);
            min = Math.min(min, prices[i]);
            new ReentrantLock();
            // new ThreadPoolExecutor();
            // CompletableFuture.supplyAsync();
        }
        return res < 0 ? 0 : res;
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
