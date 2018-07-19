package io.sununiq.conf.util;

import org.apache.commons.beanutils.ConvertUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * PropertyConverter Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>二月 23, 2018</pre>
 */
public class PropertyConverterTest {

    enum Gender {
        MALE, FEMALE
    }


    @interface Foo {
        int id() default 1;

        String name() default "";

        Gender sex();
    }

    @BeforeClass
    public static void beforeClass() {
        ConvertUtils.register(new EnumConverter(), Gender.class);

    }


    /**
     * Method: to(Class<T> targetType)
     */
    @Test
    public void testTo() throws Exception {

        Map<String, String> map = new HashMap<>();
        map.put("id", "123");
        map.put("name", "Tom");
        map.put("sex", Gender.MALE.toString());

        Foo foo = PropertyConverter.to(Foo.class).from(map);
//        System.out.println(foo.id());
//        System.out.println(foo.name());
        System.out.println(foo.sex());
    }

    /**
     * Method: from(final Map<String, String> map)
     */
    @Test
    public void testFrom() throws Exception {
//TODO: Test goes here... 
    }


} 
