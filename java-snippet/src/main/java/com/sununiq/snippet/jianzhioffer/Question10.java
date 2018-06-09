package com.sununiq.snippet.jianzhioffer;

/**
 * 位运算：http://www.matrix67.com/blog/archives/122
 */
public class Question10 {
    public static void main(String[] args) {
        System.out.println(numberOf1(7));
        System.out.println(numberOf1_2(7));
        System.out.println(numberOf1_3(7));
    }

    /**
     * 死循环风险，对于0x80000000
     */
    static int numberOf1(int n) {
        int count = 0;
        while (n != 0) {
            if((n & 1) != 0) {
                count++;
            }
            n = n >> 1;
        }
        return count;
    }

    /**
     * 避免死循环，移动标志位，不改变原来的数字
     */
    static int numberOf1_2(int n) {
        int count = 0;
        int flag = 1;
        while (flag != 0) {
            if((n & flag) != 0) {
                count++;
            }
            flag = flag << 1;
        }
        return count;
    }

    /**
     *  通过利用 n & (n - 1) 运算来去掉一个1的方式计算1的个数
     */
    static int numberOf1_3(int n) {
        int count = 0;

        while (n != 0) {
            count++;
            n = (n - 1) & n;  // 去掉最右边的一个1
        }

        return count;
    }
}
