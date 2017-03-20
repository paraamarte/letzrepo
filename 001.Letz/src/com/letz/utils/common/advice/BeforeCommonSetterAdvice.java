package com.letz.utils.common.advice;

import java.lang.reflect.Method;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.MethodBeforeAdvice;

import com.sivillage.common.util.SessionUtil;
import com.sivillage.member.vo.FrontUserInfo;

/**
 * @Class Name : BeforeCommonSetterAdvice.java
 * @Description : DB TABLES > REGR_NO, MODR_NO�뿉 留ㅽ븨�맆 Object�쓽 member field媛믪쓣 �꽭�뀡�젙蹂대�� �씠�슜�븯�뿬 �꽕�젙
 * @author UZEN / BILL
 * @since 2016. 4. 25.
 * @version 1.0
 * @see Copyright(c) 2016 SHINSEGAE INTERNATIONAL. All rights reserved
 */
public class BeforeCommonSetterAdvice implements MethodBeforeAdvice {

    private static final Logger log = LoggerFactory.getLogger(BeforeCommonSetterAdvice.class);

    private static final String[] GET_METHOD_PREFIX_LIST = { "get", "select", "retrieve", "list" };
    private static final String[] SET_PROPERTY_LIST = { "regrNo", "modrNo" };

    public BeforeCommonSetterAdvice() {
    }

    public void before(Method method, Object args[], Object target) throws Throwable {

        if (isGetTypeMethod(method.getName())) { // get type�씤 寃쎌슦 return
            return;
        }

        if (args == null || args.length < 1) { // arguments媛� 議댁옱�븯吏� �븡�뒗 寃쎌슦
            return;
        }

        String sessionMbrNo = "";
        FrontUserInfo userInfo = SessionUtil.getFrontLoginUserInfo();

        if (userInfo == null) { // 鍮꾨줈洹몄씤 �긽�깭
            log.debug("userInfo is null");
            return;
        }

        sessionMbrNo = StringUtils.defaultString(userInfo.getMbrNo());
        if (StringUtils.isBlank(sessionMbrNo)) { // �꽭�뀡�뿉 �쉶�썝踰덊샇媛� �뾾�뒗 寃쎌슦
            log.debug("sessionMbrNo is null");
            return;
        }

        for (int i = 0; i < args.length; i++) {
            for (String propertyName : SET_PROPERTY_LIST) {
                String propertyValue = getPropertyValue(args[i], propertyName);
                if (StringUtils.isNotBlank(propertyValue)) { // 媛믪씠 議댁옱�븷 寃쎌슦 skip
                    continue;
                }

                // 媛믪씠 議댁옱�븯吏� �븡�쑝硫� sessionMbrNo濡� �꽕�젙
                setPropertyValue(args[i], propertyName, sessionMbrNo);
                log.debug("{}", String.format("%s:sessionMbrNo = %s:%s", propertyName, propertyValue, sessionMbrNo));
            }
        }
    }

    private String getPropertyValue(Object arg, String property) {
        String value = null;

        if (PropertyUtils.isReadable(arg, property)) { // getter媛� 議댁옱�븯�뒗 寃쎌슦
            try {
                value = (String) PropertyUtils.getProperty(arg, property);
            } catch (Exception e) {
                log.error("argument type:errmsg = {}:{}" + Object.class.getName(), e.getMessage());
            }
        }

        return value;
    }

    private void setPropertyValue(Object arg, String propertyName, String mbrNo) {

        if (PropertyUtils.isWriteable(arg, propertyName)) { // setter媛� 議댁옱�븯�뒗 寃쎌슦
            try {
                PropertyUtils.setProperty(arg, propertyName, mbrNo);
                log.debug("set regrNo as {}", mbrNo);
            } catch (Exception e) {
                log.error("argument type:errmsg = {}:{}" + Object.class.getName(), e.getMessage());
            }
        }
    }

    /**
     * <pre>
     * get�삎�떇�쓽 method �뿬遺� �뙋�떒.
     * </pre>
     * 
     * @param methodName
     * @return true-get�삎�떇�씠硫�, false-get�삎�떇�씠 �븘�땶寃쎌슦(create, save, update, merge,,)
     */
    private boolean isGetTypeMethod(String methodName) {
        for (String prefix : GET_METHOD_PREFIX_LIST) {
            if (methodName.startsWith(prefix)) {
                return true;
            }
        }

        return false;
    }
}
