package persong.yuqing.zhou;

public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello(String name) {
        System.out.println("Hello, " + name);
    }
    public  void  selfMethod(){
        System.out.println("this is my self method!!");
    }
}

