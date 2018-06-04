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

    public long getLength() {
        return endPos - startPos + 1;
    }

    public long getRemaining() {
        return endPos - curPos + 1;
    }

    public Slice copy() {
        Slice copy = new Slice();
        copy.setStartPos(this.startPos);
        copy.setCurPos(this.curPos);
        copy.setEndPos(this.endPos);
        return copy;
    }
}
