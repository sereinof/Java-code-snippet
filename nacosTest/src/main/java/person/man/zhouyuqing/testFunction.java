package person.man.zhouyuqing;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class testFunction {
    public static void main(String[] args) {
        // 定义一个顾客（Consumer），用于消费食物
        Consumer<String> customer = food -> System.out.println("吃掉了：" + food);

// 厨师准备了一份美食
        String food = "美味牛排";

// 顾客吃掉厨师准备的食物
        customer.accept(food);

        Consumer<String> printConsumer = System.out::println;
        printConsumer.accept("我是聪啊");

        Supplier<String> chef = () -> "美味牛排";

// 厨师提供食物
        String foodq = (String) chef.get();
        System.out.println("厨师准备了：" + foodq);

        // 定义一个服务员（Function），用于加工食物
      //  Function<String, String> waiter = food -> "加工后的" + food;

// 厨师提供食物
        Supplier<String> chefw = () -> "美味牛排";
        String foodw = chef.get();

// 服务员加工食物后送到顾客手中
    //    String processedFood = waiter.apply(foodw);

    }
}
