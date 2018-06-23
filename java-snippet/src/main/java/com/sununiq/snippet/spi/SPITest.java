package com.sununiq.snippet.spi;

import com.sununiq.snippet.spi.service.Animal;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

/**
 * @program: java-snippet
 *
 * @description: spi
 *
 * @author: sununiq
 *
 * @create: 2018-06-23 13:13
 **/
public class SPITest {
    private static final String PREFIX = "META-INF/services/";

    public static void main(String[] args) throws Exception{
//        testJavaSPI();
//        testLoadAllServiceConfigs();

        testCustomServiceLoader();

    }

    /**
     * 测试自定义的ServiceLoader
     * @throws Exception
     */
    private static void testCustomServiceLoader() throws Exception {
        List<Animal> animals = CustomServiceLoader.load(Animal.class);
        for (Animal animal : animals){
            animal.eat();
        }
    }

    /**
     *  加载所有jar包下面指定路径的文件
     */
    private static void testLoadAllServiceConfigs() throws ClassNotFoundException, IOException {
        // 加载所有的jar包下面的这个路径下的这个约定文件
        // 然后加载文件中的类全名
        Class<?> service = Class.forName("com.sununiq.snippet.spi.service.Animal");
        String fullName = PREFIX + service.getName();
        Enumeration<URL> configs = ClassLoader.getSystemResources(fullName);
        while (configs.hasMoreElements()) {
            System.out.println(configs.nextElement());
        }
    }

    /**
     * 测试Java spi机制
     */
    private static void testJavaSPI() {
        // serviceLoader:首先根据约定的包获取到对应的接口文件，接着解析出文件中的所有服务实现类并加载实例化。
        ServiceLoader<Animal> load = ServiceLoader.load(Animal.class);
        Iterator<Animal> iterator = load.iterator();
        while (iterator.hasNext()) {
            Animal animal = iterator.next();
            animal.eat();
        }
    }
}
