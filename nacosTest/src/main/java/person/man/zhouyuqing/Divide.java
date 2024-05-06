package person.man.zhouyuqing;

public class Divide {
    public Node construct(int[][] grid) {
        return doConstruct(grid, 0, 0, grid.length);
    }

    public Node doConstruct(int[][] grid, int index1, int indey1, int offset) {
        if (offset==0) {
            return new Node(grid[index1][indey1] == 1 ? true : false, true);
        }
        //把坐标算出来
        Node node1 = doConstruct(grid,index1,indey1,offset/2);
        Node node2 = doConstruct(grid,index1,indey1+offset/2,offset/2);
        Node node3 = doConstruct(grid,index1+offset/2,indey1,offset/2);
        Node node4 = doConstruct(grid,index1+offset/2,indey1+offset/2,offset/2);
        if(node1.isLeaf&&node2.isLeaf&&node3.isLeaf&&node4.isLeaf&&node1.val==node2.val&&node2.val==node3.val&&node3.val==node4.val){
                return new Node(node1.val,true);
        }else{
            return new Node(true,false,node1,node2,node3,node4);
        }
    }

     class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }
}
