package com.letz.euler;
/**
 * 소수를 크기 순으로 나열하면 2, 3, 5, 7, 11, 13, ... 과 같이 됩니다.
 * 이 때 10,001번째의 소수를 구하세요.
 */

import org.springframework.util.StopWatch;

public class Test_007 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        StopWatch sw = new StopWatch();
        sw.start();
        int i = 2;
        int chk = 1;
        while (chk < 10002) {
            for (int j = 2; j <= i; j++) {
                if (i % j == 0) {
                    if (i == j) {
                        if (chk == 10001) {
                            System.out.println(i);
                        }
                        chk++;
                    } else {
                        break;
                    }
                }
            }
            i++;
        }
        sw.stop();
        System.out.println(sw.prettyPrint());
    }
}