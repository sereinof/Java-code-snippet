package com.test.reflection;

import java.lang.reflect.Field;

public class mainClass {
    public static void main(String[] args) throws ClassNotFoundException {
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
    }
}
