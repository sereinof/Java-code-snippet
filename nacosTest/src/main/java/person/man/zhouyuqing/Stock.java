package person.man.zhouyuqing;

public class Stock {
    public static void main(String[] args) {
        int res =  new Stock().getMax(new int[]{7,1,5,3,6,4});
        System.out.println("finish" +res);
    }
public  int getMax(int[] prices){
 int len = prices.length;
 int min =Integer.MAX_VALUE;
 int max = Integer.MIN_VALUE;
 int index = 0;
   for(int i=0;i<len;i++){
       if(prices[i]<min){
           min = prices[i];
           index = i;
       }
   }
    for(int j = index;j<len;j++){
        if(prices[j]>max){
            max = prices[j];
        }
    }

    return  max-min;
}}
