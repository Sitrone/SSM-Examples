package com.sununiq.snippet.jianzhioffer;

/**
 * 数值的整数次方
 */
public class Question11 {
    public static void main(String[] args) {
        System.out.println(power(5, 5));
        System.out.println(power1(5, 5));
    }

    static double power(double base, int exp) {
        if(exp == 0) {
            return 1;
        }
        return (exp > 0) ? doPower(base, exp) : 1 / doPower(base, Math.abs(exp));
    }

    private static double doPower(double base, int exp) {
        double result = 1.0;
        for (int i = 0; i < exp; i++) {
            result *= base;
        }
        return result;
    }


    static double power1(double base, int exp) {
        if(exp == 0) {
            return 1;
        }

        return (exp > 0) ? helper(base, exp, 1.0) : 1 / helper(base, Math.abs(exp), 1.0);
    }

    /**
     *   尾递归，使用公式
     */
    private static double helper(double base, int exp, double result) {
        if(exp == 1) {
            return result;
        }
        if((exp & 1) == 0) {
            return helper(base, exp >> 1, result * base * base);
        } else {
            return helper(base, exp - 1, result * base);
        }
    }
}
