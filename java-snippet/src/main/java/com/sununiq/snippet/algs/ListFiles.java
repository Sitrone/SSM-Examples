package com.sununiq.snippet.algs;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 打印目录及子目录
 * bfs or dfs
 */
@Slf4j
public class ListFiles {

    /**
     * 递归dfs打印全部文件及子文件
     *
     * @param startPath
     */
    public static void dfsListFiles(File startPath) {
        // 递归的终止条件，是当前路径是文件
        if(startPath.isFile()) {
            log.info(startPath.getAbsolutePath());
        } else {
            File[] files = startPath.listFiles();
            if(files == null) {
                return;
            }
            for (File file : files) {
                dfsListFiles(file);
            }
        }
    }

    /**
     * bfs 非递归解法
     *
     * @param startPath
     */
    public static void bfsListFiles1(File startPath) {
        Queue<File> fileQueue = new LinkedList<>();

        fileQueue.offer(startPath);
        while (!fileQueue.isEmpty()) {
            File path = fileQueue.poll();
            File[] files = path.listFiles();
            if(files == null) {
                return;
            }
            for (File file : files) {
                if(file.isFile()) {
                    log.info(file.getAbsolutePath());
                } else {
                    fileQueue.offer(file);
                }
            }
        }

    }

    public static void dfsListFiles1(File startPath) {
        Deque<File> fileStack = new ArrayDeque<>();
        fileStack.push(startPath);

        while (!fileStack.isEmpty()) {
            File path = fileStack.pop();
            File[] files = path.listFiles();
            if(files == null) {
                return;
            }
            for (File file : files) {
                if(file.isFile()) {
                    log.info(file.getAbsolutePath());
                } else {
                    fileStack.push(file);
                }
            }
        }
    }

    public static void main(String[] args) {
//        bfsListFiles(new File("/Users/sunzhe/github/SSM-Examples"));
//        bfsListFiles1(new File("/Users/sunzhe/github/SSM-Examples"));
        dfsListFiles1(new File("/Users/sunzhe/github/SSM-Examples"));
    }
}
