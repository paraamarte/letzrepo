package com.letz.euler;

import org.springframework.util.StopWatch;

/** 1 ~ 10 사이의 어떤 수로도 나누어 떨어지는 가장 작은 수는 2520입니다. 그러면 1 ~ 20 사이의 어떤 수로도 나누어 떨어지는 가장 작은 수는 얼마입니까? */
public class Test_005 {
    public static void main(String args[]) {
        StopWatch sw = new StopWatch();
        sw.start();
        int rangeLimit = 20;
        try {
            for (int i = rangeLimit + 1; i < i + 1; i++) {
                // System.out.println(i);
                for (int j = 1; j <= rangeLimit; j++) {
                    // System.out.println(i+"/"+j );
                    if (i % j == 0) {
                        if (j == rangeLimit) {
                            sw.stop();
                            System.out.println(sw.prettyPrint());
                            System.out.println(i);
                            throw new Exception();
                        }
                        continue;
                    } else {
                        break;
                    }
                }
            }
        } catch (Exception e) {

        }
    }
}
