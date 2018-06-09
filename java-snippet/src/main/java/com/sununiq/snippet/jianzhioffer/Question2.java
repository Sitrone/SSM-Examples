package com.sununiq.snippet.jianzhioffer;

/**
 * 实现单例模式
 */
public class Question2 {

    /**
     *  饿汉，多线程安全
     */
    public static class Singleton1 {
        private Singleton1(){}

        private static final Singleton1 instance = new Singleton1();

        public static Singleton1 getInstance() {
            return instance;
        }
    }

    /**
     * double lock check，多线程安全，懒加载
     */
    public static class Singleton2 {
        private Singleton2(){}

        private volatile static Singleton2 instance;

        public static Singleton2 getInstance() {
            if(instance != null) {
                synchronized (Singleton2.class) {
                    if(instance != null) {
                        instance = new Singleton2();
                    }
                }
            }
            return instance;
        }
    }


    /**
     * 枚举类，天然的多线程安全单例类
     */
    public enum Singleton3 {
        instance;
    }

    /**
     * 静态内部类，多线程安全，懒加载
     */
    public static class Singleton4 {
        private Singleton4(){}

        static class InnerHolder {
            static final Singleton4 instance = new Singleton4();
        }

        public Singleton4 getInstance() {
            return InnerHolder.instance;
        }

    }
}


