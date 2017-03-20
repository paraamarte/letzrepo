package com.letz.dev.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class PickNumber {
    public int maxItemCnt = 6;
    public int maxSetCnt = 10;
    public int loopMaxCnt = 100000;
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        PickNumber pn = new PickNumber();
        List<Set<Integer>> ss = new ArrayList<Set<Integer>>();
        while(pn.maxSetCnt > ss.size()){
            System.out.println(pn.getNumberSet().toString());
            ss.add(pn.getNumberSet());
        }
//        System.out.println(ss.toString());
        
        
    }
    public Set<Integer> getNumberSet(){
        Set<Integer> set = new TreeSet<Integer>();
        while(maxItemCnt > set.size()){
            set.add((int)(Math.random()*45)+1);
        }
        return set;
    }
    public int getNumber(){
        return (int)(Math.random()*45)+1;
    }
}