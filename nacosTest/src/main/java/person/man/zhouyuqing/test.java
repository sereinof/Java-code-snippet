package person.man.zhouyuqing;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@SpringBootApplication
public class test {
    public static void main(String[] args) {
        List res =  subsets(new int[]{1, 2, 3});
        System.out.println(res);
        SpringApplication.run(test.class, args);
    }


    public static List<List<Integer>> subsets(int[] nums) {
        int[] ints = {};
        ArrayList res = new ArrayList<List<Integer>>();
        ArrayList subset = new ArrayList();
        travse(false, subset, 0, res, nums);
        travse(true, subset, 0, res, nums);
        return res;
    }
    public boolean isPalindrome(int x) {
        int reversed = 0;
        int init = x;
        while (x>0) {
            int temp = x%10;
            reversed = reversed * 10 + temp;
            x = x / 10;
        }
        return init == reversed;
    }
    public static void travse(Boolean isChooes, ArrayList subset, int level, ArrayList res, int[] nums) {
        if (level > nums.length - 1) {
            return;
        }
        if (isChooes == true) {
            //  ArrayList temp = new ArrayList();
            subset.add(nums[level]);
            res.add(subset.toArray());
        }
        travse(false, subset, level + 1, res, nums);
        travse(true, subset, level + 1, res, nums);
        if (subset.size() > 0&&isChooes) {
            subset.remove(subset.size() - 1);
        }
    }

}
