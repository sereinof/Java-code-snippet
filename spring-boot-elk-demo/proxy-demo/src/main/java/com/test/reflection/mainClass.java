package com.test.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class mainClass {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        // 1.通过字符串获取Class对象，这个字符串必须带上完整路径名
        Class studentClass = Class.forName("com.test.reflection.Student");
// 2.通过类的class属性
        Class studentClass2 = Student.class;
// 3.通过对象的getClass()函数
        Student studentObject = new Student();
        Class studentClass3 = studentObject.getClass();
        System.out.println("class1 = " + studentClass + "\n" +
                "class2 = " + studentClass2 + "\n" +
                "class3 = " + studentClass3 + "\n" +
                "class1 == class2 ? " + (studentClass == studentClass2) + "\n" +
                "class2 == class3 ? " + (studentClass2 == studentClass3));
//获取所有申明的字段？
        Field[] declaredFieldList = studentClass.getDeclaredFields();
        for (Field declaredField : declaredFieldList) {
            System.out.println("declared Field: " + declaredField);
        }
// 2.获取所有公有的字段
        Field[] fieldList = studentClass.getFields();
        for (Field field : fieldList) {
            System.out.println("field: " + field);
        }

        // 1.获取所有声明的构造方法
        Constructor[] declaredConstructorList = studentClass.getDeclaredConstructors();
        for (Constructor declaredConstructor : declaredConstructorList) {
            System.out.println("declared Constructor: " + declaredConstructor);
        }
// 2.获取所有公有的构造方法
        Constructor[] constructorList = studentClass.getConstructors();
        for (Constructor constructor : constructorList) {
            System.out.println("constructor: " + constructor);
        }

        Method[] declaredMethodList = studentClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethodList) {
            System.out.println("declared Method: " + declaredMethod);
        }
// 2.获取所有公有的函数
        Method[] methodList = studentClass.getMethods();
        for (Method method : methodList) {
            System.out.println("method: " + method);
        }

        // 1.通过字符串获取Class对象，这个字符串必须带上完整路径名
       // Class studentClass = Class.forName("com.test.reflection.Student");
// 2.获取声明的构造方法，传入所需参数的类名，如果有多个参数，用','连接即可
        Constructor studentConstructor = studentClass.getDeclaredConstructor(String.class);
// 如果是私有的构造方法，需要调用下面这一行代码使其可使用，公有的构造方法则不需要下面这一行代码
        studentConstructor.setAccessible(true);
// 使用构造方法的newInstance方法创建对象，传入构造方法所需参数，如果有多个参数，用','连接即可
        Object student = studentConstructor.newInstance("NameA");
// 3.获取声明的字段，传入字段名
        Field studentAgeField = studentClass.getDeclaredField("studentAge");
// 如果是私有的字段，需要调用下面这一行代码使其可使用，公有的字段则不需要下面这一行代码
// studentAgeField.setAccessible(true);
// 使用字段的set方法设置字段值，传入此对象以及参数值
        studentAgeField.set(student,10);
// 4.获取声明的函数，传入所需参数的类名，如果有多个参数，用','连接即可
        Method studentShowMethod = studentClass.getDeclaredMethod("show",String.class);
// 如果是私有的函数，需要调用下面这一行代码使其可使用，公有的函数则不需要下面这一行代码
        studentShowMethod.setAccessible(true);
// 使用函数的invoke方法调用此函数，传入此对象以及函数所需参数，如果有多个参数，用','连接即可。函数会返回一个Object对象，使用强制类型转换转成实际类型即可
        Object result = studentShowMethod.invoke(student,"message");
        System.out.println("result: " + result);
    }
}
