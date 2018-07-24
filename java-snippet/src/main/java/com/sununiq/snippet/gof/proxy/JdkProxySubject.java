package com.sununiq.snippet.gof.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * aspect
 */
public class JdkProxySubject implements InvocationHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(JdkProxySubject.class);

    private Subject target;

    public JdkProxySubject(Subject target) {
        this.target = target;
    }

    @SuppressWarnings("unchecked")
    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object result = null;
        try {
            LOGGER.info("Before");
            result = method.invoke(target, args);
            LOGGER.info("After returning");
        } catch (Exception e) {
            LOGGER.info("Ex {}", e.getMessage());
            throw e;
        } finally {
            LOGGER.info("After");
        }
        return result;
    }
}
