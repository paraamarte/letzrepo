/*
 * This software is the confidential and proprietary information of JAJU
 * Shinsegae Internatinal Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with JAJU.
 */
package com.letz.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @Class Name : DateUtils.java
 * @Description : �옄�꽭�븳 �겢�옒�뒪 �꽕紐�
 * @author UZEN / KEVIN
 * @since 2016. 4. 21.
 * @version 1.0
 * @see Copyright(c) 2016 SHINSEGAE INTERNATIONAL. All rights reserved
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    /**
     * TODO : Should set these static final variables into Common table or Constant Variables Class Set Static final
     * variables :
     * BASE_DATE_FORMAT.
     */
    private static final String BASE_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String BIGINT_DATE_FORMAT = "yyyyMMddHHmmssSSS";

    public static final String DATE_FORMAT14 = "yyyyMMddHHmmss";

    /**
     * get Current DateTime with BASE_DATE_FORMAT.
     * 
     * @return the String
     */
    public static String getDate() {

        return getDate(DateUtils.BASE_DATE_FORMAT);
    }

    /**
     * <pre>
     * get Current DateTime with specific Date Format.
     * </pre>
     *
     * @param dateFormat
     *            dateFormat
     * @return String
     */
    public static String getDate(String dateFormat) {

        return DateUtils.convertDateFormat(new Date(), new SimpleDateFormat(dateFormat));
    }

    /**
     * <pre>
     * nextDays�씪 �씠�썑�쓽 �씪�옄�젙蹂대�� �뼸�뒗�떎.
     * </pre>
     *
     * @param nextDays
     *            nextDays
     * @param dateFormat
     *            dateFormat
     * @return String
     */
    public static String getNextDate(Calendar cal, int nextDays, String dateFormat) {

        if (cal == null) {
            cal = Calendar.getInstance();
        }
        cal.add(Calendar.DATE, nextDays);
        return DateUtils.convertDateFormat(cal.getTime(), new SimpleDateFormat(dateFormat));
    }

    /**
     * <pre>
     * nextDays�씪 �씠�썑�쓽 �씪�옄�젙蹂대�� �뼸�뒗�떎.
     * </pre>
     *
     * @param nextDays
     *            nextDays
     * @param dateFormat
     *            dateFormat
     * @return String
     */
    public static String getNextDate(int nextDays, String dateFormat) {

        return getNextDate(Calendar.getInstance(), nextDays, dateFormat);
    }

    /**
     * <pre>
     * �씪�옄 �젙蹂�(yyyyMMddHHmmss)瑜� 吏��젙�맂 �룷硫㏃쑝濡� 蹂�寃�.
     * </pre>
     * 
     * @param sourceString
     *            sourceString
     * @param dateFormat
     *            dateFormat
     * @return the String
     */
    public static String convertDateFormat(String sourceString, String dateFormat) {

        Date sourceDate = null;

        try {
            sourceDate = new SimpleDateFormat("yyyyMMddHHmmss").parse(sourceString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return DateUtils.convertDateFormat(sourceDate, new SimpleDateFormat(dateFormat));
    }

    /**
     * <pre>
     * �씪�옄 �젙蹂�(yyyyMMddHHmmss)瑜� 吏��젙�맂 �룷硫㏃쑝濡� 蹂�寃�.
     * </pre>
     * 
     * @param sourceDate
     *            sourceDate
     * @param dateFormat
     *            dateFormat
     * @return the String
     */
    public static String convertDateFormat(Date sourceDate, String dateFormat) {

        return DateUtils.convertDateFormat(sourceDate, new SimpleDateFormat(dateFormat));
    }

    /**
     * <pre>
     * get format converted Date string with specific Date Format.
     * </pre>
     * 
     * @param sourceDate
     *            sourceDate
     * @param dateForm
     *            dateForm
     * @return the String
     */
    public static String convertDateFormat(Date sourceDate, SimpleDateFormat dateForm) {

        return dateForm.format(sourceDate);
    }

    /**
     * <pre>
     * get time stamp.
     * </pre>
     *
     * @return the String
     * @author Choo Kyoungil
     */
    public static String getTimeStamp() {

        return Long.toString(System.currentTimeMillis());
    }

    /**
     * getDayOfTheWeek.
     *
     * @return
     * @author Kim Donghyeong
     */
    public static int getDayOfTheWeek() {

        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * changeTimeStamp
     *
     * @return
     * @author kim doo
     */
    public static String changeTimeStamp(String getDate) {

        SimpleDateFormat curFormat = null;

        if (getDate.trim().length() == 14) {
            curFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        } else {
            curFormat = new SimpleDateFormat("yyyyMMddHHmm");
        }

        long lMsec = 0;

        try {
            Date date = curFormat.parse(getDate);
            lMsec = date.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return lMsec + "";
    }

    /**
     * GMT �떆媛꾩쓣 Locale �떆媛꾩쑝濡� 蹂�寃�
     *
     * @param date
     *            GMT�떆媛�
     * @param hour
     * @param minute
     * @param dateFormat
     * @return
     * @author wonju choi
     */
    public static String convertToDispalyTimeZone(String date, int hour, int minute, String dateFormat) {

        Calendar calendar = Calendar.getInstance();
        try {
            Date sourceDate = new SimpleDateFormat(dateFormat).parse(date);
            calendar.setTime(sourceDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hour);
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + minute);

        return convertDateFormat(calendar.getTime(), new SimpleDateFormat(dateFormat));
    }

    /**
     * GMT �떆媛꾩쓣 Locale �떆媛꾩쑝濡� 蹂�寃�
     *
     * @param date
     * @param hour
     * @param minute
     * @param dateFormat
     * @return
     * @author wonju choi
     */
    public static String convertToDispalyTimeZone(Date date, int hour, int minute, String dateFormat) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hour);
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + minute);

        return convertDateFormat(calendar.getTime(), new SimpleDateFormat(dateFormat));
    }

    /************************************************
     * 硫붿꽌�뱶紐� : getCalsDate
     * <p>
     * 硫붿꽌�뱶 湲곕뒫 : �썝�븯�뒗 �떆�젏�쓽 �궇吏쒕�� 李얜뒗�떎.
     * <p>
     * PARAM : string
     * <p>
     * getCalsDate(0,1) :�삤�뒛
     * <p>
     * getCalsDate(1,1) :�뀈, -1(1�뀈�쟾 �삤�뒛),-2(2�뀈�쟾 �삤�뒛)
     * <p>
     * getCalsDate(2,1) :媛쒖썡, -1(1媛쒖썡�쟾 �삤�뒛),-2(2媛쒖썡�쟾 �삤�뒛), 1(1媛쒖썡�썑 �삤�뒛)
     * <p>
     * getCalsDate(3 or 4 or 8,1) :二�, -1(�씪二쇱씪�쟾 媛숈��슂�씪), 1(1二쇱씪�썑 媛숈��슂�씪)
     * <p>
     * getCalsDate(5 or 6 or 7,1) :�븯猷�, -1(�삤�뒛遺��꽣 �븯猷⑥쟾), 1(�삤�뒛遺��꽣 �븯猷⑦썑)
     * <p>
     * getCalsDate(9,1) :12�떆媛�, -1(12�떆媛꾩쟾) 1(12�떆媛꾪썑) 2(24�떆媛꾪썑)
     * <p>
     * PARAM date_type : 異쒕젰�쓣 �썝�븯�뒗 �궇吏쒗삎�떇
     * <p>
     * RETURN VALUE : string
     * <p>
     *************************************************/
    public static String getCalsDate(int y, int z, String date_type) {

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
