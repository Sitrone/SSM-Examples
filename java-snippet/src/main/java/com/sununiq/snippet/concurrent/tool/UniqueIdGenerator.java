package com.sununiq.snippet.concurrent.tool;

import com.sununiq.snippet.util.BytesHelper;
import com.sununiq.snippet.util.Helper;

import java.util.UUID;

/**
 * @program: java-snippet
 *
 * @description: 唯一性ID生成, hibernate方法
 *
 * 时间戳(6bytes, 48bit) + 顺序号(2bytes, 16bit, 最大值65535) + 机器标识(4bytes 32bit) + 进程标识(4bytes 32bit)
 *
 * @author: sununiq
 *
 * @create: 2018-06-23 15:47
 * ref:http://calvin1978.blogcn.com/articles/uuid.html
 **/
public class UniqueIdGenerator {
    public int getGeneratedVersion() {
        return 1;
    }

    private final long mostSignificantBits;

    public UniqueIdGenerator() {
        // generate the "most significant bits" ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        byte[] hiBits = new byte[8];
        // use address as first 32 bits (8 * 4 bytes)
        System.arraycopy(Helper.getAddressBytes(), 0, hiBits, 0, 4);
        // use the "jvm identifier" as the next 32 bits
        System.arraycopy(Helper.getJvmIdentifierBytes(), 0, hiBits, 4, 4);
        // set the version (rfc term) appropriately
        hiBits[6] &= 0x0f;
        hiBits[6] |= 0x10;

        mostSignificantBits = BytesHelper.asLong(hiBits);
    }

    public UUID generateUUID() {
        long leastSignificantBits = generateLeastSignificantBits(System.currentTimeMillis());
        return new UUID(mostSignificantBits, leastSignificantBits);
    }

    public long getMostSignificantBits() {
        return mostSignificantBits;
    }

    public static long generateLeastSignificantBits(long seed) {
        byte[] loBits = new byte[8];

        short hiTime = (short) (seed >>> 32);
        int loTime = (int) seed;
        System.arraycopy(BytesHelper.fromShort(hiTime), 0, loBits, 0, 2);
        System.arraycopy(BytesHelper.fromInt(loTime), 0, loBits, 2, 4);
        System.arraycopy(Helper.getCountBytes(), 0, loBits, 6, 2);
        loBits[0] &= 0x3f;
        loBits[0] |= ((byte) 2 << (byte) 6);

        return BytesHelper.asLong(loBits);
    }

    public static void main(String[] args) {
        UniqueIdGenerator strategy = new UniqueIdGenerator();

        for (int i = 0; i < 1000; i++) {
            System.out.println("Generation # " + i + " ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            byte[] loBits = new byte[8];

            long sysTime = System.currentTimeMillis();
            short hiTime = (short) (System.currentTimeMillis() >>> 32);
            int loTime = (int) sysTime;
            System.arraycopy(BytesHelper.fromShort(hiTime), 0, loBits, 0, 2);
            System.arraycopy(BytesHelper.fromInt(loTime), 0, loBits, 2, 4);
            System.arraycopy(Helper.getCountBytes(), 0, loBits, 6, 2);

            System.out.println("    before bit setting ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("       loBits[0] : " + BytesHelper.toBinaryString(loBits[0]));
            System.out.println("             lsb : " + BytesHelper.toBinaryString(BytesHelper.asLong(loBits)));
            System.out.println("    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            loBits[0] &= 0x3f;
            loBits[0] |= ((byte) 2 << (byte) 6);

            System.out.println("    after bit setting ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("       loBits[0] : " + BytesHelper.toBinaryString(loBits[0]));
            long leastSignificantBits = BytesHelper.asLong(loBits);
            System.out.println("             lsb : " + BytesHelper.toBinaryString(leastSignificantBits));
            System.out.println("    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");


            UUID uuid = new UUID(strategy.mostSignificantBits, leastSignificantBits);
            System.out.println("  uuid : " + uuid.toString());
            System.out.println("  variant : " + uuid.variant());
            System.out.println("  version : " + uuid.version());
            if (uuid.variant() != 2) {
                throw new RuntimeException("bad variant");
            }
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
    }
}
