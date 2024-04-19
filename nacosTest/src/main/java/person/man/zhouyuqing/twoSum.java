package person.man.zhouyuqing;


import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;


//有序数组的和 (Two Sum II - Input array is sorted)
//
//题目描述： 给定一个已按照升序排列的整数数组 numbers 和一个目标值 target，请你在数组中找出和为目标值的那两个整数，并返回他们的数组下标（下标从1开始计数）。
public class twoSum {//1,3,4,5,5,7  7
    public static void main(String[]args){
        ThreadLocal<String> a = new ThreadLocal<>();
        a.set("nihao");
        a.get();
  int[] res =   new twoSum().twoSum(new int[]{1,3,4,5,5,7},7);
        System.out.println("finish"+res[0]+res[1]);
        Boolean e =  null;
        if(e ==true){
            System.out.println("你好呀！！");
        }
    }
    public  int[] twoSum(int [] nums ,int targer){
        synchronized (new String()){
            System.out.println("");
        }
        int res;
       HashMap<Integer,Integer> map = new HashMap();
        for(int i = 0;i<nums.length;i++){

            if(map.get(targer-nums[i])!=null){

                    return new int[]{i+1,map.get(targer-nums[i])+1};


            }else {
                map.put(nums[i],i);
            }

        }
        return new int[]{-1,-1};
    }
}
