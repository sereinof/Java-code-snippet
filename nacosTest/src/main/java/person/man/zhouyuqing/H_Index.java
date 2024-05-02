package person.man.zhouyuqing;

public class H_Index {
    public static void main(String[] args) {
        new H_Index().hIndex(new int[]{1,3,1});
    }
    public int hIndex(int[] citations) {
        int n = citations.length;//论文数量
        int h = 0;
        int[] arr = new int[n + 1];//该数组含义是i应用次数的论文有arr[i]篇
        for (int i = 0; i < citations.length; i++) {
            if (citations[i] >= n) {
                arr[n]++;
            } else {
                arr[citations[i]]++;
            }
        }
        int rfrence = 0;
        for (int i = n; i >= 1; i--) {
            rfrence+=arr[i];
            if(rfrence>=i){
              h = Math.min(rfrence,i);
              break;
            }

        }
        return h;
    }
}
