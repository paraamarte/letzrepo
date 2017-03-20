package com.letz.euler;

import org.springframework.util.StopWatch;

/**
 * 세 자연수 a, b, c 가 피타고라스 정리 a2 + b2 = c2 를 만족하면 피타고라스 수라고 부릅니다 (여기서 a < b < c ).
 * 예를 들면 32 + 42 = 9 + 16 = 25 = 52이므로 3, 4, 5는 피타고라스 수입니다.
 * a + b + c = 1000 인 피타고라스 수 a, b, c는 한 가지 뿐입니다. 이 때, a × b × c 는 얼마입니까?
 *
 */
public class Test_009 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        StopWatch sw = new StopWatch();
        sw.start();
        int result = 1000;
        for (int a = 1; a < 1000; a++) {
            for (int b = a + 1; b < 1000; b++) {
                for (int c = b + 1; c < 1000; c++) {
//                    System.out.println("a =>" + a + " b =>" + b + " c =>" + c);
                    if (Math.pow(a, 2) + Math.pow(b, 2) == Math.pow(c, 2)) {
//                        System.out.println("a =>" + a + " b =>" + b + " c =>" + c);
                        if ((a + b + c) == result) {
//                            System.out.println("a =>" + a + " b =>" + b + " c =>" + c);
                            System.out.println("a * b * c = "+(a*b*c));
                            sw.stop();
                        }
                    }
                }
            }
        }
        System.out.println(sw.prettyPrint());
    }
}