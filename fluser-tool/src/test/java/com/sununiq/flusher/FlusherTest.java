package com.sununiq.flusher;

import com.sununiq.flusher.domain.FlusherProfile;
import com.sununiq.flusher.impl.PrintOutProcessor;
import org.junit.Test;

public class FlusherTest {

    @Test
    public void testFlusher() throws InterruptedException {
        FlusherProfile flusherProfile = FlusherProfile.defaultProfile();
        flusherProfile.setThreadName("flusherWorker");

        Flusher<String> stringFlusher = new Flusher<>(flusherProfile, new PrintOutProcessor());

        int index = 1;
        while (true) {
            stringFlusher.add(String.valueOf(index++));
            Thread.sleep(1000);
        }
    }
}
