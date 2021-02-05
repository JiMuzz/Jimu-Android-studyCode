package com.example.studynote.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Test {

    public static void main(String[] args) {
        setUser();

//        try {
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        }
    }

    public static void setUser() {
        User user = new User();
        Class clz = User.class;
        Field field1 = null;
        Field field2 = null;
        try {
//            System.out.println("修改前"+user.getStudent());
//            field1 = clz.getDeclaredField("student");
//            field1.setAccessible(true);
//            field1.set(user, new Student());
//            System.out.println("修改后"+user.getStudent());

            field2 = clz.getDeclaredField("name");
            field2.setAccessible(true);

            //去掉final
            Field modifiers = field2.getClass().getDeclaredField("modifiers");
            modifiers.setAccessible(true);
            modifiers.setInt(field2, field2.getModifiers() & ~Modifier.FINAL);

            field2.set(user, "xixi");

            //恢复final
            modifiers.setInt(field2, field2.getModifiers() & ~Modifier.FINAL);

            System.out.println("修改后"+user.name);
            System.out.println("修改后"+field2.get(user));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public static void setUser2() {
//        Class clz = User.class;
//        Field field2 = null;
//        try {
//            field2 = clz.getDeclaredField("name");
//            field2.setAccessible(true);
//            Object getname=field2.get(null);
//            System.out.println("修改前"+getname);
//            field2.set(null, "xixi");
//            System.out.println("修改后"+User.name);
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }

    }


    public static void getNewUser() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        long startTime = System.currentTimeMillis();
        Class clz = Class.forName("com.example.studynote.reflection.User");
        User user;
        int i = 0;
        while (i < 1000000) {
            i++;
            //方法1，直接实例化
//            user = new User();
//            //方法2，每次都通过反射获取class，然后实例化
            user = (User) Class.forName("com.example.studynote.reflection.User").newInstance();
//            //方法3，通过之前反射得到的class进行实例化
//            user = (User) clz.newInstance();
        }

        System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
    }
}

//public void test(){
//
//}



