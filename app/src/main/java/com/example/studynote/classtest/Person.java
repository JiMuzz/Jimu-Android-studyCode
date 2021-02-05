package com.example.studynote.classtest;

class Person {

    public static void main(String[] args) {
        staticFunction();
    }

    static Person person = new Person();

    static {
        System.out.println("1");
    }

    {
        System.out.println("2");
    }


    Person() {
        System.out.println("3");
        System.out.println("a=" + a + ",b=" + b);
    }

    public static void staticFunction() {
        System.out.println("4");
    }

    int a = 100;
    static int b = 200;

}

