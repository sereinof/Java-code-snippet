package person.man.zhouyuqing;

public class LengthOfLastWord {

    public  int  lengthOfLastWordth(String s) {


        int res = 0;
        int i = s.length() - 1;
        while (s.charAt(i) != ' ') {
            res++;
            i--;
        }
        return res;
    }
    public String longestCommonPrefix(String[] strs) {
        String a = "ggg";

       int  minLength = Integer.MAX_VALUE;
        for(int i =0;i<strs.length;i++){
            minLength = Math.min(minLength,strs[i].length());
        }

       for (int i =0;i<minLength;i++){
          for(int j= 1;j<strs.length;j++){
              String s1 = strs[j].substring(0,i);
              String s2 = strs[j-1].substring(0,i);
               if(!s1.equals(s2)){
                   return strs[i].substring(0,i-1);
               }
           }
       }

          return strs[0].substring(minLength);
        }
}
