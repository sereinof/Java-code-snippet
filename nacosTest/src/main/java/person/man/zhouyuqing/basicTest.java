package person.man.zhouyuqing;

public class basicTest {
    public static void main(String[] args) {
        String res = new son("jjj").say();
        System.out.println("结束了");
    }
    public basicTest(
    ){
        System.out.println("nihao");
    }
    public basicTest( String s
    ){
        System.out.println("有参构造方法");
    }
}
class  son extends  basicTest{
    public son(String s){
        //super(s);
        System.out.println("son儿子");
    }
    public son(String s,String b){
        //super(s);
        System.out.println("son儿子");
    }
     public   String  say(){
        try {
            System.out.println("hgeggg");
            int a = 6/0;
            return "你好"+a;
        }catch (Exception e){

            System.out.println("catch");
            return "catch";
        }finally {
            int a = 6+6;
            System.out.println("a");
            System.out.println("nihao");
           // return  "finally";
        }
     }
}