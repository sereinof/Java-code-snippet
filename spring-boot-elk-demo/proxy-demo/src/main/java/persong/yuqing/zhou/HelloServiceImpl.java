package persong.yuqing.zhou;

public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello(String name) {
        System.out.println("Hello, " + name);
    }
    @Override
    public  void  sayBye(String name){
        System.out.println("this is Bye method!!");
    }
    public  void  self(String name){
        System.out.println("this is self method!!");
    }
}

