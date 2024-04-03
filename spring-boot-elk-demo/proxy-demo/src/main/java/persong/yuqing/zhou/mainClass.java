package persong.yuqing.zhou;

import java.lang.reflect.Proxy;

public class mainClass {

    public static void main1(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        HelloServiceStaticProxy proxy = new HelloServiceStaticProxy(helloService);
        proxy.sayHello("world");
    }
    public static void main2(String[] args) {
        // 创建目标对象
        HelloService target = new HelloServiceImpl();
        // 创建调用处理器对象
        HelloServiceHandler handler = new HelloServiceHandler(target);
        // 创建代理对象
        //下面这里如何强转为实现类的话会报错了 运行的时候
        HelloService proxyInstance = (HelloService) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                handler);
        // 通过代理对象调用方法
        proxyInstance.sayHello("World");
        proxyInstance.sayBye("zhouyuqing");
       // proxyInstance.self("nihao");
        //this delegated object  can  only call method that belongs to thenslves
       // proxyInstance.selfMethod();
    }
    public static void main(String[] args) {
        CglibObject target = new CglibObject();
        HelloServiceCglib cglib = new HelloServiceCglib();
        CglibObject proxy = (CglibObject) cglib.getInstance(target);
        proxy.sayHello("World");
    }
}
