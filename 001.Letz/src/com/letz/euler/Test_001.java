package com.letz.euler;

/**
 * 10보다 작은 자연수 중에서 3 또는 5의 배수는 3, 5, 6, 9 이고, 이것을 모두 더하면 23입니다.
 * 1000보다 작은 자연수 중에서 3 또는 5의 배수를 모두 더하면 얼마일까요?
 */

public class Test_001 {
    public static void main(String args[]) {
        int resultSum = 0;
        int limitNumber = 2000000;
        for (int i = 1; i <= limitNumber; i++) {
            if (i % 3 == 0 || i % 5 == 0)
                resultSum += i;
        }
        System.out.println(resultSum);
    }
}