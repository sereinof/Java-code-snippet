package person.man.zhouyuqing;

import java.util.Arrays;

public class Sort {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people,(a,b)->{
            if(a[0]==b[0]){
                 return b[1]-b[1];
            }else {
                return a[0] - b[0];
            }
        });

        for(int i =0;i<people.length;i++){
            if(people[i][1]==i){
                continue;
            }else if(people[i][1]<i){
                int[] tmp = people[i];
                for (int j =i; j>people[i][1];j--){
                    people[j] = people[j-1];
                }
                people[i] = tmp;
            }
        }
        return  people;
    }
}
