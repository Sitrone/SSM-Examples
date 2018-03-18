package com.github.sununiq.simplehttpserver;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * 相对路径测试
 */
public class RelativeTest {

    @Test
    public void testRelative() throws IOException {
        String file = "";
        Path path = Paths.get(file);
        System.out.println(Files.isDirectory(path));

//        Files.list(path).forEach(path1 -> System.out.println(path1));
//
//        File file1 = new File(file);
//        for (File file2 : file1.getCanonicalFile().listFiles()) {
//            System.out.println(file2.getName());
//        }
    }
}
