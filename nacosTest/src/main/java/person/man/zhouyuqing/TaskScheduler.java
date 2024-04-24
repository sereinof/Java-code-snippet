package person.man.zhouyuqing;

import java.util.Arrays;

public class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
  //任务调度器 这个题目应该算是一个脑筋急转弯的题目了
        int [] hash = new int[26];
        for(int i=0;i<tasks.length;i++){
            hash[tasks[i]-'A'] +=1;
        }
        Arrays.sort(hash);
        int max= 0;
        for(int i=25;i>=0;i--){
            if(hash[i]==hash[25]){
                max++;
            }else{
                break;
            }
        }
        return  Math.max((hash[25])*(n+1)+max,tasks.length);
    }
}
