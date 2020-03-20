package com.example.studynote.DesignPatterns;

import androidx.core.app.NavUtils;

/*
   单例模式
 */
public class Singleton {

    /*饿汉模式*/
    private static Singleton singleton = new Singleton();

    public static Singleton getSingleton() {
        return singleton;
    }


    /*懒汉模式-线程不安全模式*/
    private static Singleton singleton2;

    public static Singleton getSingleton2() {
        if (singleton2 == null) {
            singleton2 = new Singleton();
        }
        return singleton2;
    }

    /* 懒汉模式-线程安全模式
     * 增加synchronized实现实例同步
     * */
    private static Singleton singleton3;

    public synchronized static Singleton getSingleton3() {
        if (singleton3 == null) {
            singleton3 = new Singleton();
        }
        return singleton3;
    }

    /* 双重加锁-线程安全同时提高性能模式
     * 1、synchronized加锁使得同一时间只有一个线程能进入
     * 2、两个if判空因为防止第二个线程进入时候重复实例化
     * 3、volatile修饰符为了让singleton4实例在变化后立即写入主存，方便其他线程读取，否则有可能造成空指针
     * */
    private volatile static Singleton singleton4;

    public static Singleton getSingleton4() {
        if (singleton4 == null) {
            synchronized (Singleton.class) {
                if (singleton4 == null) {
                    singleton4 = new Singleton();
                }
            }

        }
        return singleton4;
    }
}
