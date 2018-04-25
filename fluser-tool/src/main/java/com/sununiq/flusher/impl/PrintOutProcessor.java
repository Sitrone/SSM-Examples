package com.sununiq.flusher.impl;

import com.sununiq.flusher.Processor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class PrintOutProcessor implements Processor<String> {
    @Override
    public void process(List<String> list) {

        log.info("start flush");

        list.forEach(System.out::println);

        log.info("end flush");
    }
}
