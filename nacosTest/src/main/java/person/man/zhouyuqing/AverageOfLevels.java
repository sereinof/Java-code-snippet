package person.man.zhouyuqing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AverageOfLevels {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        LinkedList<TreeNode> dqueue = new LinkedList<>();
        dqueue.offer(root);
        while (!dqueue.isEmpty()) {
            double sum = 0;
            int size = dqueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode now = dqueue.poll();
                sum += now.val;
                if (now.left != null) {
                    dqueue.offer(now.left);
                }
                if (now.right != null) {
                    dqueue.offer(now.right);
                }
            }
            res.add(sum / size);
        }
        return res;
    }

    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        int left = getMinimumDifference(root.left);
        int right = getMinimumDifference(root.right);
        int dir_left = Integer.MAX_VALUE;
        int dir_right = Integer.MAX_VALUE;
        if (root.left != null) {
            dir_left = Math.abs(findMax(root.left) - root.val);
        }
        if (root.right != null) {
            dir_right = Math.abs(findMin(root.right) - root.val);
        }

        return Math.min(left, Math.min(right, Math.min(dir_left, dir_right)));
    }
    public  int findMax(TreeNode root){
         if(root.right==null){
             return  root.val;
         }else {
             return findMax(root.right);
         }
    }
    public  int findMin(TreeNode root){
        if(root.left==null){
            return  root.val;
        }else {
            return findMin(root.left);
        }
    }
}
