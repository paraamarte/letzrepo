package com.letz.euler;

import org.springframework.util.StopWatch;

/**
 * 1부터 10까지 자연수를 각각 제곱해 더하면 다음과 같습니다 (제곱의 합).
 * 12 + 22 + ... + 102 = 385
 * 1부터 10을 먼저 더한 다음에 그 결과를 제곱하면 다음과 같습니다 (합의 제곱).
 * (1 + 2 + ... + 10)2 = 552 = 3025
 * 따라서 1부터 10까지 자연수에 대해 "합의 제곱"과 "제곱의 합" 의 차이는 3025 - 385 = 2640 이 됩니다.
 * 그러면 1부터 100까지 자연수에 대해 "합의 제곱"과 "제곱의 합"의 차이는 얼마입니까?
 */
public class Test_006 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        StopWatch sw = new StopWatch();
        sw.start();
        int rangeLimit = 100;
        long squareAtSum = 0;
        long sumAtSquare = 0;
        long sum = 0; // ��
        for (int i = 1; i <= rangeLimit; i++) {
            squareAtSum += i * i;
            sum += i;
        }
        sumAtSquare = (int) Math.pow(sum, 2);
        System.out.println(sumAtSquare + " - " + squareAtSum + " = " + (sumAtSquare - squareAtSum));
        sw.stop();
        System.out.println(sw.prettyPrint());
    }
}
