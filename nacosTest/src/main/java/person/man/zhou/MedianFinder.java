package person.man.zhou;

import java.util.*;

public class MedianFinder {

    PriorityQueue container = new PriorityQueue();
    private double midian = 0;
    private volatile int count = 0;
    public MedianFinder() {
    }
    public void addNum(int num) {
        count++;
        container.add(num);
        Iterator<Integer> iterator = container.iterator();
        if (count % 2 == 0) {
            int count1 = -1;
            int nu1 = 0;
            int nu2 = 0;
            while (iterator.hasNext()) {
                count1++;
                Integer nu3 = iterator.next();
                if (count1 == count / 2) {
                    nu1 = nu3;
                }
                if (count1 == count / 2 - 1) {
                    nu2 = nu3;
                };
            }
            midian = (nu1 + nu2) / 2.0;
        } else {
            int count1 = 0;
            int nu1 = 0;
            while (iterator.hasNext()) {
                Integer nu3 = iterator.next();
                if (count1 == count / 2 ) {
                    nu1 = nu3;
                }
                count1++;
            }
            midian = nu1;
        }
    }

    public double findMedian() {
        return midian;
    }
}
