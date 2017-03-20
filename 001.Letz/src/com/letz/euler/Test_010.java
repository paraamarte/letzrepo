package com.letz.euler;


import org.springframework.util.StopWatch;

public class Test_010 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        long primeSum = 0L;// 소수의 합
        int maxNum = 0;
        StopWatch sw = new StopWatch();
        sw.start();
        sw.stop();
        System.out.println(sw.prettyPrint());
        System.out.println(maxNum + "이하의 모든 소수의 합은 " + primeSum);
    }
}
