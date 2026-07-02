package person.man.findjobs;

public class Hindex {
    public static void main(String[] args) {
        int[] citaioms = new int[]{3, 0, 6, 1, 5};
        int res = new Hindex().hIndex(citaioms);
        System.out.println(res);
    }

    public int hIndexI(int[] citations) {
        if (citations.length == 0) {
            return 0;
        }
//理解一下 h 个大于或等于h的 数 求这个h的最大值
        //h最大也就 数组的长度了 先暴力一下吧
        int maxHindex = 0;
        for (int i = 1; i < citations.length + 1; i++) {
            int count = 0;
            for (int j = 0; j < citations.length; j++) {
                if (citations[j] >= i) {
                    count++;
                    if (count >= i) {
                        maxHindex = i;
                        break;
                    }
                }
            }
        }
        return maxHindex;
    }

    public int hIndex(int[] citations) {
        //使用计数排序写
        int[] arr = new int[citations.length + 1];//零处不用
        int hidex = 0;
        int publicS = 0;//这部分是所有都可以用的
        for (int i = 0; i < citations.length; i++) {
            if (citations[i] > citations.length) {
                publicS++;
            } else {
                arr[citations[i]]++;
            }

        }
        for (int i = arr.length-1; i >0; i--) {
            if(publicS+arr[i]>=i){
                hidex = i;
                break;
            }else {
                publicS=publicS+ arr[i];
            }
        }
        return hidex;
    }
}
