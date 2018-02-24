package io.sununiq.conf.impl;

import io.sununiq.conf.AppConf;
import io.sununiq.conf.ConfLoader;
import org.junit.Test;

/**
 * PropertyConf Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>二月 23, 2018</pre>
 */
public class PropertyConfTest {
    private static final String conf_name = "appconf.properties";

    /**
     * Method: load(String conf)
     */
    @Test
    public void testLoad() throws Exception {
        ConfLoader.load(conf_name);
        AppConf appConf = ConfLoader.getConf(conf_name).get(AppConf.class);

        System.out.println(appConf.name());
        System.out.println(appConf.age());
        System.out.println(appConf.address());
        System.out.println(appConf.secondNickname());
    }

} 
