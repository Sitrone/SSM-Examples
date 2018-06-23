package com.sununiq.snippet.spi;

import org.apache.commons.io.IOUtils;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: java-snippet
 *
 * @description: 自定义ServiceLoader
 *
 * @author: sununiq
 *
 * @create: 2018-06-23 14:26
 **/
public class CustomServiceLoader {
    private static final String MAPPING_CONFIG_PREFIX = "META-INF/services";

    public static <S> List<S> load(Class<S> service) throws Exception {
        String mappingConfigFile = MAPPING_CONFIG_PREFIX + "/" + service.getName();
        //由于一个接口的实现类可能存在多个jar包中的META-INF目录下，所以下面使用getResources返回一个URL数组
        Enumeration<URL> configFileUrls = CustomServiceLoader.class.getClassLoader().getResources(mappingConfigFile);
        if (configFileUrls == null) {
            return Collections.EMPTY_LIST;
        }

        List<S> services = new LinkedList<>();
        while (configFileUrls.hasMoreElements()) {
            URL configFileUrl = configFileUrls.nextElement();
            String configContent = IOUtils.toString(configFileUrl.openStream(), StandardCharsets.UTF_8);
            String[] serviceNames = configContent.split("\n");
            for (String serviceName : serviceNames) {
                Class serviceClass = CustomServiceLoader.class.getClassLoader().loadClass(serviceName);
                Object serviceInstance = serviceClass.newInstance();
                services.add((S) serviceInstance);
            }
        }
        return services;
    }

}
