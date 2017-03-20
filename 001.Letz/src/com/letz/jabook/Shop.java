package com.letz.jabook;

import java.util.Vector;

public class Shop{
    protected Vector<String> store = new Vector<String>();
    public int getCount(){
        return store.size();
    }
}