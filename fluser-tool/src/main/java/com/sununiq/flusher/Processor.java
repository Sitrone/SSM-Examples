package com.sununiq.flusher;

import java.util.List;

public interface Processor<T> {

    void process(List<T> list);

}
