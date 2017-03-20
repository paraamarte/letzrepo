/*
 * This software is the confidential and proprietary information of JAJU
 * Shinsegae Internatinal Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with JAJU.
 */
package com.sivillage.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @Class Name : DateUtils.java
 * @Description : 자세한 클래스 설명
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
     * nextDays일 이후의 일자정보를 얻는다.
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
     * nextDays일 이후의 일자정보를 얻는다.
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
     * 일자 정보(yyyyMMddHHmmss)를 지정된 포멧으로 변경.
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
     * 일자 정보(yyyyMMddHHmmss)를 지정된 포멧으로 변경.
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
     * GMT 시간을 Locale 시간으로 변경
     *
     * @param date
     *            GMT시간
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
     * GMT 시간을 Locale 시간으로 변경
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
     * 메서드명 : getCalsDate
     * <p>
     * 메서드 기능 : 원하는 시점의 날짜를 찾는다.
     * <p>
     * PARAM : string
     * <p>
     * getCalsDate(0,1) :오늘
     * <p>
     * getCalsDate(1,1) :년, -1(1년전 오늘),-2(2년전 오늘)
     * <p>
     * getCalsDate(2,1) :개월, -1(1개월전 오늘),-2(2개월전 오늘), 1(1개월후 오늘)
     * <p>
     * getCalsDate(3 or 4 or 8,1) :주, -1(일주일전 같은요일), 1(1주일후 같은요일)
     * <p>
     * getCalsDate(5 or 6 or 7,1) :하루, -1(오늘부터 하루전), 1(오늘부터 하루후)
     * <p>
     * getCalsDate(9,1) :12시간, -1(12시간전) 1(12시간후) 2(24시간후)
     * <p>
     * PARAM date_type : 출력을 원하는 날짜형식
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
