package com.letz.euler;

import org.springframework.util.StopWatch;

/**
 * 피보나치 수열의 각 항은 바로 앞의 항 두 개를 더한 것이 됩니다. 1과 2로 시작하는 경우 이 수열은 아래와 같습니다.
 * 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...
 * 짝수이면서 4백만 이하인 모든 항을 더하면 얼마가 됩니까?
 */

public class Test_002 {

    public static void main(String args[]) {
        Test_002 t = new Test_002();
        StopWatch sw = new StopWatch();
        sw.start("test002");
        t.test002();
        sw.stop();

        System.out.println(sw.prettyPrint());
    }

    public void test002() {
        int limitNumber = 0x3d0900;
        int n1Number = 0;
        int n2Number = 1;
        int resultNumber = 0;
        boolean isCheck = true;
        while (isCheck) {
            int n3Number = n1Number + n2Number;
            if (n3Number > limitNumber) {
                isCheck = false;
            } else {
                if (n3Number % 2 == 0)
                    resultNumber += n3Number;
                n1Number = n2Number;
                n2Number = n3Number;
            }
        }
        System.out.println(String.format("sum => %d",Integer.valueOf(resultNumber)));
    }
}
