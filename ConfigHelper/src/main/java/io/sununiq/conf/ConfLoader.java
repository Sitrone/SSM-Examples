package io.sununiq.conf;

import io.sununiq.conf.impl.BaseConf;
import io.sununiq.conf.impl.PropertyConf;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class ConfLoader {
    private static final Map<String, Conf> confMap = new ConcurrentHashMap<>();

    public static void load(String conf) {
        load(conf, PropertyConf.class);
    }

    public static void load(String conf, Class<? extends BaseConf> aClass) {
        if(null == conf || conf.equals("")) {
            throw new IllegalArgumentException("the config file name is null");
        }

        if(null == aClass) {
            throw new IllegalArgumentException("the config aClass class is null");
        }

        try {
            BaseConf baseConf = aClass.newInstance();
            Conf loadConf = baseConf.load(conf);
            confMap.put(conf, loadConf);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }

    public static Conf getConf(String conf) {
        if(!confMap.containsKey(conf)) {
            load(conf);
        }
        return confMap.get(conf);
    }
}
