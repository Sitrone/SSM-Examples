package io.sununiq.conf.impl;

import io.sununiq.conf.AppConf;
import io.sununiq.conf.Conf;
import io.sununiq.conf.ConfLoader;
import org.junit.After;
import org.junit.Before;
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

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: load(String conf)
     */
    @Test
    public void testLoad() throws Exception {
        Conf conf = ConfLoader.load(conf_name);
        AppConf appConf = conf.get(AppConf.class);

        System.out.println(appConf.name());
        System.out.println(appConf.age());
        System.out.println(appConf.address());
    }

} 
