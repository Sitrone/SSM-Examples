package io.sununiq.conf.monitor;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

/**
 * 监控配置文件修改
 */
public class ConfigWatcher implements Runnable {
    private String dir;

    private ConfListener confListener;

    public ConfigWatcher(String dir, ConfListener confListener) {
        this.dir = dir;
        this.confListener = confListener;
    }

    @Override
    public void run() {
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Paths.get(dir).register(watchService, ENTRY_MODIFY);

            while (true) {
                WatchKey watchKey = watchService.take();
                watchKey.pollEvents().stream()
                        .filter(watchEvent -> watchEvent.kind() == ENTRY_MODIFY)
                        .forEach(watchEvent -> {
                            Path changed = (Path) watchEvent.context();
                            confListener.doAction(changed);
                        });

                watchKey.reset();
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Unexpected error occurs while watch config state.");
            e.printStackTrace();
        }
    }
}
