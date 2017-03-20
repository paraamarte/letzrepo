package com.letz.utils.common.advice;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.codehaus.plexus.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.MethodBeforeAdvice;

import com.sivillage.common.constant.Constants;
import com.sivillage.common.util.CommonUtils;

public class BeforeLoggingAdvice implements MethodBeforeAdvice {

    private static final Logger log = LoggerFactory.getLogger(BeforeLoggingAdvice.class);

    public BeforeLoggingAdvice() {
    }

    public void before(Method method, Object args[], Object target) throws Throwable {

        String className = target.getClass().getInterfaces()[0].getName();

        if (className.indexOf("Mapper") < 0) {
            className = target.getClass().getName();
        }

        StringBuilder logSb = new StringBuilder(String.format("%s.%s Arguments\n", className, method.getName()));

        if (Constants.LOGGING_PROFILES.contains(CommonUtils.getProfile())) {
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    if (args[i] instanceof java.util.HashMap) {
                        logSb.append(String.format("Argument[%s] : %s\n", i, args[i]));
                    } else {
                        logSb.append(String.format("Argument[%s] : %s\n", i, toString(args[i])));
                    }
                }
            }

            //log.debug("{}", logSb.toString());
        }
    }

    private String toString(Object object) {
        List<String> blankList = new ArrayList<String>();
        List<String> notBlankList = new ArrayList<String>();

        PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(object);
        for (PropertyDescriptor pd : pds) {
            try {
                String property = pd.getName();
                Object propertyValue = PropertyUtils.getSimpleProperty(object, property);

                String tmpStr = String.format("%s", propertyValue);

                if (StringUtils.isBlank(tmpStr) || "null".equals(tmpStr)) {
                    blankList.add(String.format("%s=%s", property, propertyValue));
                } else {
                    notBlankList.add(String.format("%s=%s", property, propertyValue));
                }

            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        Collections.sort(blankList);
        Collections.sort(notBlankList);

        // log.debug("blanList = {}", blankList);
        // log.debug("notBlankList = {}", notBlankList);

        String blank = blankList.toString().replaceAll("\r\n|\n", "");
        String notBlank = notBlankList.toString().replaceAll("[,]", ",\n\t");

        return String.format("%s\n\tBlankValues%s", notBlank, blank);
    }
}
