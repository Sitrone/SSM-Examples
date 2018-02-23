package io.sununiq.conf;

import io.sununiq.conf.impl.BaseConf;
import io.sununiq.conf.impl.PropertyConf;

public abstract class ConfLoader {

    public static Conf load(String conf) {
        return load(conf, PropertyConf.class);
    }

    public static Conf load(String conf, Class<? extends BaseConf> aClass) {

        if(null == conf || conf.equals("")) {
            throw new IllegalArgumentException("the config file name is null");
        }

        if(null == aClass) {
            throw new IllegalArgumentException("the config aClass class is null");
        }

        try {
            BaseConf baseConf = aClass.newInstance();
            return baseConf.load(conf);
        } catch (ReflectiveOperationException e) {
            return null;
        }
    }
}
