package person.man.zhouyuqing;

public class friendCircus {
    public int findCircleNum(int[][] isConnected) {
        int x= isConnected.length;
        int y = isConnected[0].length;
        UnionFind union = new UnionFind(x+y);
        for(int i =0;i<x;i++){
            for(int j = i+1;j<y;j++){
                if(isConnected[i][j] ==1){
                    union.union(new int[]{i,j});
                }
            }
        }
        return union.size;
    }

    class UnionFind {
        public int size;
        public int[] parent;

        public UnionFind(int n) {
            parent = new int[n];
            size = 0;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public void union(int[] p){
            int Rootx = find(p[0]);
            int Rooty = find(p[1]);
            if(Rootx==Rooty){
                size--;
                parent[p[1]] = Rootx;
                return;//这里不用维护权重
            }else{
                size++;
            }
        }

        public int find(int x){
            if(x != parent[x]){
                int Root = find(parent[x]);
                  parent[x] = Root;
                  return  Root;
            }else{
                return parent[x];
            }
        }


    }
}
