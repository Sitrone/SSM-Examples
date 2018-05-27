package com.sununiq.downloader.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 读取的分片信息
 */
@Setter
@Getter
@ToString
public class Slice {
    private long startPos;
    private long endPos;
    private long curPos;
}
