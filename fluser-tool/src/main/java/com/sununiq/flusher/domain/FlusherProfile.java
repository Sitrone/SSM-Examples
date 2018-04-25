package com.sununiq.flusher.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlusherProfile {
    private String threadName;

    private int bufferSize = 5;

    private long intervalInMillis = 2000;

    private int threadNum = 1;

    private int queueSize = 30;

    public static FlusherProfile defaultProfile() {
        return new FlusherProfile();
    }
}
