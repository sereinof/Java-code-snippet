package person.man.zhou;


import javax.print.DocFlavor;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root==null) return "[]";
        StringBuilder string = new StringBuilder("[");
        Queue<TreeNode> quene = new LinkedList<>();
        quene.add(root);
        while (!quene.isEmpty()) {
            TreeNode node = quene.poll();
            if (node != null) {
                string.append(node.val).append(",");
                quene.add(node.left);
                quene.add(node.right);
            } else {
                string.append("null,");
            }
        }

        string.deleteCharAt(string.length() - 1);
        string.append("]");
        return string.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if("[]".equals(data)) return null;
        String real = data.substring(1, data.length() - 1);
        String[] arr = real.split(",");
        int index = 1;
        Queue<TreeNode> queue = new ArrayDeque<>();
        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if("null".equals(arr[index])){
                node.left=null;
                index++;
            }else{
                TreeNode node1 = new TreeNode(Integer.parseInt(arr[index]));
                queue.add(node1);
                node.left = node1;
                index++;
            }
            if("null".equals(arr[index])){
                node.right=null;
                index++;
            }else{
                TreeNode node1 = new TreeNode(Integer.parseInt(arr[index]));
                queue.add(node1);
                node.right = node1;
                index++;
            }


        }
        return root;
    }


}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}