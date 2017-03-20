package com.letz.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // Calendar cal = Calendar.getInstance();
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        // System.out.println(sdf.format(cal.getTime()));
        // String tmp = "";
        // System.out.println(Integer.parseInt((StringUtils.isNotBlank(tmp) ? tmp : "0")));
        //
        // String tmp1 = "6214932001700|S";
        // String[] tmpSaleStat = tmp1.split("\\|");
        // for (int i = 0; i < tmpSaleStat.length; i++) {
        // System.out.println(tmpSaleStat[i]);
        // }
        try {
            String searchStart1Date = getCalsDate(2, -11, "yyyyMM");
            System.out.println(searchStart1Date);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static String getCalsDate(int y, int z, String date_type) throws Exception {

        Calendar cal = Calendar.getInstance();
        // TimeZone timezone = cal.getTimeZone();
        // timezone = timezone.getTimeZone("Asia/Seoul");

        cal.add(y, z);
        Date currentTime = cal.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(date_type, Locale.KOREAN);
        String timestr = formatter.format(currentTime);

        return timestr;
    }
}
