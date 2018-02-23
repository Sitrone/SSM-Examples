package io.sununiq.conf.util;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class FileUtil {
    private static String classpath = null;

    public static String getClasspath() {
        if(classpath != null) {
            return classpath;
        }

        try {
            Path path = Paths.get(Thread.currentThread().getContextClassLoader().getResource("").toURI());
            classpath = path.toAbsolutePath().toString();
            return classpath;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }

    }
}
