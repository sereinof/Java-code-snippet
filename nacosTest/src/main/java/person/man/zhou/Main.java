package person.man.zhou;

import java.util.Scanner;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] s = in.nextLine().split(" ");
        int target = in.nextInt();
        int[] arr =  new int[s.length];
        for(int i=0;i<s.length;i++){
            arr[i] = Integer.valueOf(s[i]);
        }
        HashMap  map= new HashMap();
        for(int i=0;i<arr.length;i++){
            if(map.containsKey(target-arr[i])){
                System.out.print(i );
                System.out.print("  ");
                System.out.print(map.get(target-arr[i]));
                return;
            }
            map.put(arr[i],i);
        }

        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int a = in.nextInt();
            int b = in.nextInt();
            System.out.println(a + b);

        }
    }
}
