package persong.yuqing.zhou;

public class HelloServiceStaticProxy implements HelloService {
    private HelloService helloService;

    public HelloServiceStaticProxy(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    public void sayBye(String name) {

    };

    @Override
    public void sayHello(String name) {
        System.out.println("Before sayHello"); // 前置增强
        helloService.sayHello(name);
        System.out.println("After sayHello"); // 后置增强
    }
}
