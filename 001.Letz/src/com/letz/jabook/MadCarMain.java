package com.letz.jabook;

interface IFork{
    void dig();
}

interface ITank{
    void shoot();
}

abstract class AutoCar{
    public abstract void drive();
}

class MadCar extends AutoCar implements IFork, ITank{
    public void dig(){
        System.out.println("땅을판다");
    }
    public void shoot(){
        System.out.println("포를쏜다");
    }
    public void drive(){
        System.out.println("운전한다");
    }
    public void soundHorn(){
        System.out.println("빵빵");
    }
}

public class MadCarMain{
    public static void main(String[] args){
        MadCar m = new MadCar();
        m.dig();
        m.shoot();
        m.drive();
        m.soundHorn();
        
        IFork f = m;
        f.dig();
        
        ITank t = m;
        t.shoot();
        
        AutoCar c = (AutoCar) t;
        c.drive();
    }
}
