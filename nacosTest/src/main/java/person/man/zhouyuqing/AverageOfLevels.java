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
}
