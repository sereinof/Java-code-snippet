package person.man.zhouyuqing;

public class GasCost {
    public static void main(String[] args) {
        int res =   new GasCost().canCompleteCircuit(new int[]{1,2,3,4,5},new int[]{3,4,5,1,2});
        System.out.println(res);
    }
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int l = 0;
        boolean falg = false;
        while (l < n) {
            int num = n;
            int start = l;
            int remain = 0;
            while (num > 0) {
                remain = remain + gas[start] - cost[start];
                if (remain < 0) {
                    l = start + 1;
                    if(start<l&&falg){
                        return -1;
                    }
                    num--;
                    break;
                } else {
                    if (start + 1 > n - 1) {
                        falg=true;
                        start = 0;
                    } else {
                        start++;
                    }
                    num--;
                }
            }
            if(num==0){
               if(remain>=0){
                   return l;
               }else {
                   return -1;
               }
            }
        }
        return -1;
    }
}
