package com.letz.jabook;

class Top implements Runnable {
    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.print(i + "\t");
        }
    }
}

public class ThreadMain {
    public static void main(String[] args) {
        System.out.println("start");
        Top t = new Top();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        t1.start();
        t2.start();
        System.out.println("stop");
    }
}
