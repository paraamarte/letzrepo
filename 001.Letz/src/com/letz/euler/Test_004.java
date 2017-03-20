package com.letz.euler;

import org.springframework.util.StopWatch;

/*
 * 앞에서부터 읽을 때나 뒤에서부터 읽을 때나 모양이 같은 수를 대칭수(palindrome)라고 부릅니다.
 * 두 자리 수를 곱해 만들 수 있는 대칭수 중 가장 큰 수는 9009 (= 91 × 99) 입니다.
 * 세 자리 수를 곱해 만들 수 있는 가장 큰 대칭수는 얼마입니까?
 */
public class Test_004 {
    public static void main(String args[]) {
        StopWatch sw = new StopWatch("\uB300\uCE6D \uC218 \uAD6C\uD558\uAE30");
        sw.start();
        int b = 0;
        int chkNum = 999;
        int sNum = 100;
        int result = 0;
        boolean same = false;
        int compareNum = 0;
        for (int a = sNum; a <= chkNum; a++)
            for (b = sNum; b <= chkNum; b++) {
                result = a * b;
                String tmp = String.valueOf(result);
                int tmpLength = tmp.length();
                int tmpSize = tmpLength / 2;
                for (int j = 0; j < tmpSize; j++) {
                    char sStr = tmp.charAt(j);
                    char eStr = tmp.charAt(tmpLength - (j + 1));
                    if (sStr != eStr) {
                        same = false;
                        break;
                    }
                    same = true;
                }

                if (same && compareNum < result)
                    compareNum = result;
            }

        System.out.println((new StringBuilder("\uAC00\uC7A5 \uD070 \uB300\uCE6D \uC218\uB294 =>")).append(compareNum).toString());
        sw.stop();
        System.out.println(sw.prettyPrint());
    }
}
