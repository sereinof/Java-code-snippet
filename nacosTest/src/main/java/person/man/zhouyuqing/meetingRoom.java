package person.man.zhouyuqing;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class meetingRoom {
    public int minMeetingRooms(int[][] intervals) {
        if(intervals.length==0){
            return 0;
        }
        Arrays.sort(
                intervals,
                (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> queue = new PriorityQueue<>(intervals.length,(a,b) -> a - b);
        queue.add(intervals[0][1]);
        for(int i=1;i<intervals.length;i++){
            if(intervals[i][0]>=queue.peek()){
                queue.poll();
            }else{
                queue.add(intervals[i][1]);
            }
        }
        return  queue.size();
    }
}
