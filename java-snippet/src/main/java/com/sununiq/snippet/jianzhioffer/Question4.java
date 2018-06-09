package com.sununiq.snippet.jianzhioffer;

import lombok.extern.slf4j.Slf4j;

/**
 * 替换空格, java里面使用StringBuilder可以解决
 * 为避免StringBuilder扩容带来的损耗，可以先遍历字符串计算总的大小
 */
@Slf4j
public class Question4 {
    public static void main(String[] args) {

        log.info(replaceBlank("We are happy."));
    }

    public static String replaceBlank(String input) {
        String target = "%20";

        StringBuilder builder = new StringBuilder(calLen(input));
        for (char c : input.toCharArray()) {
            if(Character.isWhitespace(c)) {
                builder.append(target);
            } else {
                builder.append(c);
            }
        }

        return builder.toString();
    }

    private static int calLen(String input) {
        int sum = 0;
        for (char c : input.toCharArray()) {
            if(Character.isWhitespace(c)) {
                sum += 3;
            }
        }
        return sum + input.length();
    }
}
