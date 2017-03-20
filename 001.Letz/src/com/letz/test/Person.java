/*
 * This software is the confidential and proprietary information of JAJU
 * Shinsegae Internatinal Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with JAJU.
 */
package com.letz.test;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @Class Name : Person.java
 * @Description : 자세한 클래스 설명
 * @author UZEN / 금지현
 * @since 2016. 3. 15.
 * @version 1.0
 * @see
 *      Copyright(c) 2015 JAJU. All rights reserved
 */
public class Person {
    private final Date birthDate;

    public Person(Date birthDate) {
        // Defensive copy - see Item 39
        this.birthDate = new Date(birthDate.getTime());
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    public boolean isBabyBoomer() {
        Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
        Date boomSt = gmtCal.getTime();
        gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
        Date boomend = gmtCal.getTime();
        return birthDate.compareTo(boomSt) >= 0 && birthDate.compareTo(boomend) < 0;
    }
}
