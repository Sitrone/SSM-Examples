package io.sununiq.conf.impl;

import io.sununiq.conf.ConfLoader;
import io.sununiq.conf.monitor.ConfListener;
import io.sununiq.conf.util.FileUtil;

import java.nio.file.Path;

/**
 * properties类型配置文件监控类
 */
public class PropertiesConfListener implements ConfListener {

    @Override
    public void doAction(Path path) {
        String fileName = path.toAbsolutePath().normalize().toString();
        if(fileName.endsWith("properties")) {
            ConfLoader.load(fileName.replace(FileUtil.getClasspath(), ""));
        }
    }
}
