package person.man.zhouyuqing;

public class recoverTree {
    public static int[] preorder;
    public static int[] inorder;
    public HashMap map;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;
        for (int i = 0; i < inorder.length; i++) {
            this.map.put(inorder[i],i);
        }
        TreeNode res = new TreeNode();
        res.val = preorder[0];
        recursive(res, 0, preorder.length - 1, 0, inorder.length - 1);
        return res;
    }

    public TreeNode recursive(TreeNode node,int index1 ,int idnex2 ,int index3,int index4){

        node.val = preorder[index1];
        int indexRootinoreder = map.get(preorder[index1]);
        int lengthOfSubpre= indexRootinoreder - index3;
        int lengthRight = index4- indexRootinoreder;
        if(lengthOfSubpre>0){
            node.left =new TreeNode();
            recursive(node.left,index1+1,index1+1+lengthOfSubpre,index3,indexRootinoreder-1);
        }
        if(lengthRight>0){
            node.right =new TreeNode();
            recursive(node.right,index1+1+lengthOfSubpre,idnex2+lengthOfSubpre+1,index4);
        }
    }
}
