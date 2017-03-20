/*
 * This software is the confidential and proprietary information of
 * Shinsegae Internatinal Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Shinsegae International.
 */
package com.letz.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @Class Name : NumUtils.java
 * @Description : �닽�옄 怨꾩궛 Utils
 * @author UZEN / �씠�슜�꽑
 * @since 2015. 12. 16.
 * @version 1.0
 * @see
 *      Copyright(c) 2016 SHINSEGAE INTERNATIONAL. All rights reserved
 */
public class NumUtils {

    /**
     * �엯�젰�맂 媛�寃⑹뿉�꽌 �샃�뀡�뿉 �뵲�씪 �븷�씤�맂 媛�寃⑹쓣 諛섑솚
     * 
     * <pre>
     * �엯�젰�맂 媛�寃⑹뿉�꽌 �샃�뀡�뿉 �뵲�씪 �븷�씤�맂 媛�寃⑹쓣 諛섑솚�븳�떎.
     * </pre>
     * 
     * @param price: �븷�씤 �쟾 媛�寃�
     * @param option: �븷�씤 �샃�뀡(R: �젙瑜�, A:�젙�븸)
     * @param value: �븷�씤 媛�(�븷�씤�샃�뀡 R�씪 寃쎌슦 percentage, �븷�씤�샃�뀡 A�씪 寃쎌슦: won)
     * @return
     */
    public static BigDecimal dcApplyAmount(int price, String option, int value) {
        BigDecimal retValue = null;
        BigDecimal decPrice = new BigDecimal(String.valueOf(price));
        decPrice = decPrice.setScale(-1, RoundingMode.DOWN);  // �썝 �떒�쐞 �젅�궘
        if("R".equals(option)) {  // �젙瑜�            
            BigDecimal decDivValue = new BigDecimal("1").subtract(new BigDecimal(String.valueOf(value)).movePointLeft(2));
            retValue = decPrice.multiply(decDivValue);
        } else { // �젙�븸
            BigDecimal decValue = new BigDecimal(String.valueOf(value));
            retValue = decPrice.subtract(decValue);
        }
        retValue = retValue.setScale(-1, RoundingMode.DOWN);  // �썝 �떒�쐞 �젅�궘
        
        return new BigDecimal(String.valueOf(retValue.toBigInteger()));
    }
    
    public static void main(String[] args) {
        System.out.println(NumUtils.dcApplyAmount(7111, "R", 7));
        System.out.println(NumUtils.dcApplyAmount(10000, "A", 2000));
    }
}
