package com.letz.jabook;

public class VideoShop extends Shop implements IQueue{
    public void enQueue(String video){
        System.out.println(video + " 반납");
        this.store.addElement(video);
    }
    public String deQueue(){
        return (String)this.store.remove(0);
    }
}