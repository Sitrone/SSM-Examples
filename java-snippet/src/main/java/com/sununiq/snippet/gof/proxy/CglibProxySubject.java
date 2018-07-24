package com.sununiq.snippet.gof.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

import static net.sf.cglib.proxy.Enhancer.create;

public class CglibProxySubject implements MethodInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(CglibProxySubject.class);

    public <T> T getProxy(Class<T> cls) {
        return (T) create(cls, this);
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        LOGGER.info("Cglib before");
        Object result = methodProxy.invokeSuper(o, objects);
        LOGGER.info("Cglib after");
        return result;
    }
}
