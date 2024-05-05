package person.man.zhouyuqing;

import java.util.Stack;

public class BSTIterator {
    public TreeNode cur = null;
    public Stack<TreeNode> stack = new Stack();

    public BSTIterator(TreeNode root) {
        this.cur = root;
    }

    public int next() {
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        cur = stack.pop();
        int ret = cur.val;
        cur = cur.right;
        return ret;
    }

    public boolean hasNext() {
  return cur!=null||!stack.isEmpty();
    }
}
