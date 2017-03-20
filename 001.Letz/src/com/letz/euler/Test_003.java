package com.letz.euler;

import org.springframework.util.StopWatch;

/**
 * 어떤 수를 소수의 곱으로만 나타내는 것을 소인수분해라 하고, 이 소수들을 그 수의 소인수라고 합니다.
 * 예를 들면 13195의 소인수는 5, 7, 13, 29 입니다.
 * 600851475143의 소인수 중에서 가장 큰 수를 구하세요.
 **/
public class Test_003 {
    public long target = 600851475143L;
    public long tmp;

    public static void main(String args[]) {
        Test_003 t = new Test_003();
        try {
             t.answer();

            t.division(t.target);
            
        } catch (Exception exception) {

        }
        System.out.println(String.format("가장 큰 인수 => %d", t.tmp));
    }

    public void division(long target)
            throws Exception {

        for (int i = 2; i <= target; i++)
            if (target % i == 0) {
                System.out.println(String.format("\uB300\uC0C1 %d \uC778\uC218 [%d]", Long.valueOf(target), Long.valueOf(i)));
                if (tmp < i)
                    tmp = i;
                if (i == target)
                    throw new Exception();
                division(target / i);
            }

    }

    public void answer() {
        StopWatch sw = new StopWatch();
        sw.start();
        int per = 0;
        for (per = 2; (long) per < target; per++)
            if (target % (long) per == 0L)
                target /= per;

        System.out.println((new StringBuilder(String.valueOf(per))).toString());
        sw.stop();
        System.out.println(sw.prettyPrint());
    }
}
