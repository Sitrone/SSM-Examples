package io.sununiq.conf.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

import static org.apache.commons.beanutils.ConvertUtils.convert;

/**
 * Ref: http://ajoo.iteye.com/blog/190440?page=3#comments
 * 动态读取map中的key到注解中
 *
 * @param <T>
 */
public class PropertyConverter<T> {
    private final Class<T> targetType;

    public PropertyConverter(Class<T> targetType) {
        this.targetType = targetType;
    }

    public static <T> PropertyConverter<T> to(Class<T> targetType) {
        return new PropertyConverter<T>(targetType);
    }

    @SuppressWarnings("unchecked")
    public T from(final Map<String, String> map) {
        return (T) Proxy.newProxyInstance(
                targetType.getClassLoader(), new Class[]{targetType}, new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) {
                        String value = map.get(method.getName());
                        if(value == null) {
                            Object defaultValue = method.getDefaultValue();
                            if(defaultValue == null) {
                                throw new IllegalArgumentException("Failed to provide default value");
                            }
                            return defaultValue;
                        }
                        return convert(value, method.getReturnType());
                    }
                });
    }
}
