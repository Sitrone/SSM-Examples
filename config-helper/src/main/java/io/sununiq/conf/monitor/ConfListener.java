package io.sununiq.conf.monitor;

import java.nio.file.Path;

public interface ConfListener {
    void doAction(Path path);
}
