package com.sununiq.snippet.util;

import lombok.extern.slf4j.Slf4j;

/**
 * 掩码工具类
 */
@Slf4j
public class MaskUtils {

    public static void main(String[] args) {
        log.info(mask(""));
        log.info(mask("1"));
        log.info(mask("12"));
        log.info(mask("123"));
        log.info(mask("1234"));
        log.info(mask("12345"));
        log.info(mask("123456"));
        log.info(mask("1234567"));
    }

    /**
     * 时间复杂度O(n)，空间复杂度O(n)
     */
    public static String mask(String input) {
        if(input == null || input.trim().equals("")) {
            return "";
        }

        char[] elements = input.toCharArray();
        int start = input.length() / 3;
        int length = (input.length() % 3 == 0) ? input.length() / 3 : (input.length() / 3 + 1);

        for (int i = start; i < start + length; i++) {
            elements[i] = '*';
        }
        return new String(elements);
    }

    /**
     * 掩码策略
     */
    public static enum MaskStrategy {
        ALL, FIRST, MIDDLE, LAST;
    }
}
