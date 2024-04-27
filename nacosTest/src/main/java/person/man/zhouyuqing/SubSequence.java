package person.man.zhouyuqing;

public class SubSequence {
    public static void main(String[] args) {
         boolean res = new SubSequence().isSubsequence("abc","ahbgdc");
        System.out.println(res+"    ----------");
    }
    public boolean isSubsequence(String s, String t) {
        if("".equals(t)&&"".equals(s)){
            return true;
        }
        if("".equals(t)){
            return false;
        }
        if("".equals(s)){
            return true;
        }
        String tmp = "";
        tmp = s;
        s = t;
        t =tmp;
     int i_s = 0;
     int i_t = 0;
     while (i_s<s.length()){
         if(s.charAt(i_s)==t.charAt(i_t)){
             i_t++;
             i_s++;
             if(i_t == t.length()){
                 return  true;
             };
         }else{
             if(i_t==t.length()){
                  return true;
             }
             i_s++;
         }
     }

        return false;
    }
}
