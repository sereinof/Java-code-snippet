package person.man.zhouyuqing;

public class CanConstruct {
    public boolean canConstruct(String ransomNote, String magazine) {
    int[] hshamap = new int[26];
    for(int i=0;i<magazine.length();i++){
        hshamap[magazine.charAt(i) -97] ++;
    }
    for(int j =0;j<ransomNote.length();j++){
        if(hshamap[ransomNote.charAt(j) -97]==0){
            return false;

        }else{
            hshamap[ransomNote.charAt(j) -97]--;
        }
    }
     return true;
    }
}
