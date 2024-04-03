package persong.yuqing.zhou;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
//下面是jdk动态代理
public class HelloServiceHandler implements InvocationHandler {
    //目标对象
    private Object target;

    public HelloServiceHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before sayHello");
        // 执行目标对象的方法
        Object result = method.invoke(target, args);
        System.out.println("After sayHello");
        return result;
    }
}
