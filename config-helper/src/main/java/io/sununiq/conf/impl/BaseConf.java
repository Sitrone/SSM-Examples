package io.sununiq.conf.impl;

import io.sununiq.conf.Conf;
import org.apache.commons.beanutils.ConvertUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public abstract class BaseConf implements Conf {
    protected Properties properties = new Properties();
    protected Map<String, List<String>> multiValueMap = new HashMap<>();

    public abstract Conf load(String conf);

    public String getString(String key) {
        return properties.getProperty(key);
    }

    public Integer getInt(String key) {
        String obj = properties.getProperty(key);
        if(obj != null) {
            return Integer.parseInt(obj);
        }
        return null;
    }

    public Long getLong(String key) {
        String obj = properties.getProperty(key);
        if(obj != null) {
            return Long.parseLong(obj);
        }
        return null;
    }

    public Boolean getBoolean(String key) {
        String obj = properties.getProperty(key);
        if(obj != null) {
            return Boolean.parseBoolean(obj);
        }
        return null;
    }

    public Double getDouble(String key) {
        String obj = properties.getProperty(key);
        if(obj != null) {
            return Double.parseDouble(obj);
        }
        return null;
    }

    public List<String> getMultiValue(String key, String sep) {
        String obj = properties.getProperty(key);
        if(multiValueMap.containsKey(key)) {
            return multiValueMap.get(key);
        }

        List<String> values = new ArrayList<>();
        if(obj != null) {
            String[] strings = obj.split(sep);
            values.addAll(Arrays.asList(strings));
            multiValueMap.put(key, values);
        }
        return values;
    }

    public <T extends Annotation> T get(Class<T> type) {
        @SuppressWarnings("unchecked")
        T obj = (T) Proxy.newProxyInstance(type.getClassLoader(), new Class[]{type}, (proxy, method, args) -> {
            String value = properties.getProperty(camel2Dot(method.getName()));
            if(value == null) {
                Object defaultValue = method.getDefaultValue();
                if(defaultValue == null) {
                    // 必须提供默认值
                    throw new IllegalArgumentException("Failed to get property " + method.getName() + " from property file");
                }
                return defaultValue;
            }

            return ConvertUtils.convert(value, method.getReturnType());
        });
        return obj;
    }

    /**
     * 驼峰命名方式转换成properties文件中的dot连接方式
     * @param methodName
     * @return
     */
    private String camel2Dot(String methodName) {
        StringBuilder builder = new StringBuilder();
        for (char c :methodName.toCharArray()){
            if(Character.isUpperCase(c)) {
                builder.append('.');
            }
            builder.append(Character.toLowerCase(c));
        }
        return builder.toString();
    }
}
