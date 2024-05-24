package person.man.zhou;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class May_23 {
    int prev = 0;
    boolean flag = false;
    int max = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) throws InterruptedException {
        dfs(root);
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        lock.tryLock(100, TimeUnit.MINUTES);
        new Semaphore(2);
        return max;
    }

    public void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        if (flag == false) {
            flag = true;
        }
        if (flag) {
            max = Math.min(max, Math.abs(node.val - prev));
        }
        prev = node.val;

        dfs(node.right);
    }

    public int longestEqualSubarray(List<Integer> nums, int k) {
        int n = nums.size();
        List<Integer>[] pos = new ArrayList[n + 1];
        Arrays.setAll(pos, a -> new ArrayList<>());
        for (int i = 0; i < nums.size(); i++) {
            int x = nums.get(i);
            pos[x].add(i);
        }
        int max = 0;
        for (List<Integer> arr : pos) {
            if (arr.size() <= max) continue;

            for (int j = 0; j < arr.size(); j++) {
                //这里可以进行二分了 意思是这里面可以二分 草 都到这里了 谁还去二分吖！！！
                int left = 0;
                //  arr[j]- arr[left] +1  删除有多少元素
                //    j - left +1
                while ((arr.get(j) - arr.get(left) - (j - left)) > k) {
                    left++;
                }
                max = Math.max(max, j - left + 1);
            }

        }
        return max;
    }
}
