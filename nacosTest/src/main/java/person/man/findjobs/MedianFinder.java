package person.man.findjobs;

import java.util.PriorityQueue;

class MedianFinder {
    PriorityQueue<Integer> small = new PriorityQueue<>();
    PriorityQueue<Integer> big = new PriorityQueue<>((a, b) -> b - a);
    int size = 0;

    public MedianFinder() {
    }
    public void addNum(int num) {
        if (small.size() == big.size()) {
            big.offer(num);
            small.offer(big.poll());
        } else {
            small.offer(num);
            big.offer(small.poll());
        }
        size++;
    }
    public double findMedian() {
        if (size % 2 == 0) {
       return  0.5*(big.peek()+small.peek());
        }else {
            return small.peek();
        }
    }
}



