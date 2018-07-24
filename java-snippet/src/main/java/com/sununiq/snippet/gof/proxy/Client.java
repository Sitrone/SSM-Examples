package com.sununiq.snippet.gof.proxy;

public class Client {
    public static void main(String[] args) {
        Subject subject = new ProxySubject(new RealSubject());
        subject.sayHello("world!");

        JdkProxySubject jdkProxySubject = new JdkProxySubject(new RealSubject());
        Subject subject1 = jdkProxySubject.getProxy();
        subject1.sayHello("world! again!");
        subject1.request();

        CglibProxySubject cglibProxySubject = new CglibProxySubject();
        RealSubject realSubject = cglibProxySubject.getProxy(RealSubject.class);
        realSubject.request();
    }
}
