package person.man.zhouyuqing;

import org.checkerframework.checker.units.qual.K;

public class KMP {
    public static void main(String[] args) {
        for(int i=0 ,q=0;i<5;i++){
            q++;
            System.out.println(i);
            System.out.println(q);
        }
        int[] res = new KMP().findNextNums(new String("a"));
        int res1 =new KMP().strStr("sbdbutsad","sad");
        System.out.println("res1");
        System.out.println("finish--");
    }
    public int []  findNextNums(String patter){
 if(patter.length()==1){
     return new int[]{-1};
 }
        int[]   next = new int[patter.length()];
        //aabaabd
        next[0] = -1;
        next[1] = 0;
        int j = 0;
        for(int i = 2;i<patter.length();i++){//找出每个位置的那个值
            //输入是0-i的闭区间的字符串
            while (j>0&&patter.charAt(i-1)!=patter.charAt(j)){
                j = next[j];
            }
            if(patter.charAt(i-1)==patter.charAt(j)){
                 j++;
            }
            next[i]=j;

        }
        return next;
    }

    public  int  strStr(String haystack,String needle){
        int res = -1;
        int[] next = findNextNums(needle);

       int i = 0;
       int j = 0;
       while (i<haystack.length()&&  j+next.length<haystack.length()+1){
           if(haystack.charAt(i)==needle.charAt(i-j)){
                i++;
               if(i-j==next.length){
                     return j;
               }
           }else if(i-j==0){
                i++;j++;
           }else{
               j = i- next[i-j];
           }
       }
        return  res;
    }
}
