package com.letz.test;

public class StringFormatTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // String tmp = "aaaa";
        // String tmp2 = "bbbb";
        // try {
        // System.out.println(String.format("%s/%s", tmp, tmp2));
        // } catch (Exception e) {
        //
        // }
        // try {
        // System.out.println(String.format("%s\\/%s", tmp, tmp2));
        // } catch (Exception e) {
        //
        // }
        // try {
        // System.out.println(String.format("%s\\%s", tmp, tmp2));
        // } catch (Exception e) {
        //
        // }
        // try {
        // System.out.println(String.format("%s//%s", tmp, tmp2));
        // } catch (Exception e) {
        //
        // }
        // String telNo = "01098767338";
        // String tmp3 = telNo.substring(0, 3) + "-" + telNo.substring(3, 7) + "-" + telNo.substring(7);
        // System.out.println(tmp3);
        // System.out.println(System.currentTimeMillis());
        // System.out.println((System.currentTimeMillis() / 1000));

        // String tmp = "서울특별시 강남구 선릉로 762 (청담동, 영종빌딩) 123";
        // String chk = "(";
        //
        // if (tmp.indexOf(chk) > -1) {
        // System.out.println("=============");
        // System.out.println(tmp.replaceAll("\\" + chk, ""));
        // }
        String tmp = null;
        String tmpe = "";
        System.out.println("".toUpperCase());
        // long pv = Long.parseLong(tmpe.replaceAll(",", ""));
        StringBuilder sb = new StringBuilder();
        sb.append("slakdfj, sldkfjslad,lsdkfjlsa,asd;lfkjsadl,");
        System.out.println(((sb.toString()).substring(0, (sb.toString().length()) - 1)).getClass());
        System.out.println((sb.toString()).substring(0, (sb.toString().length()) - 1));
    }
}
