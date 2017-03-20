package com.letz.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;


import org.springframework.util.StopWatch;

public class FileMake {
    public long maxFileSize = 1024 * 1024 * 38; // 38MB
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        FileMake fm = new FileMake();
        fm.makeFile1();
//        fm.makeFile2();
    }
    public void makeFile1(){
        String tmp = "R";
        for(int i=0;i<=maxFileSize;i++){
           tmp +=tmp;
        }
        File f = new File("test2.txt");
        try {
            BufferedWriter fw = new BufferedWriter(new FileWriter(f, true));
            fw.write(tmp);

            fw.flush();
            fw.close();
        } catch (Exception e) {

        }
    }
    public void makeFile2(){
        StopWatch sw = new StopWatch();
        sw.start();
        File f = new File("test1.txt");
        
        String txt = "t\r\n";
        try {
            BufferedWriter fw = new BufferedWriter(new FileWriter(f, true));
            while (f.length() <= maxFileSize) {
                fw.write(txt);
            }
            fw.flush();
            fw.close();
        } catch (Exception e) {

        }
        sw.stop();
        System.out.println(sw.prettyPrint());
    }
}