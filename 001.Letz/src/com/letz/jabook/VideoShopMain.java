package com.letz.jabook;

public class VideoShopMain{
    public static void main(String[] args){
        String tmp;
        VideoShop vs = new VideoShop();
        System.out.println("111 "+vs.getCount());
        
        vs.enQueue("a");
        vs.enQueue("b");
        
        System.out.println("1111 "+vs.getCount());
        
        tmp = vs.deQueue();
        System.out.println(tmp +" 1");
        
        tmp = vs.deQueue();
        System.out.println(tmp +" 1");
        
        System.out.println("111111 "+vs.getCount());
        
    }
}