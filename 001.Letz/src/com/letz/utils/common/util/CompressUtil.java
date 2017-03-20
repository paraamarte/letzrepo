package com.sivillage.common.util;

import jaju.utils.StringUtils;

import java.util.Arrays;
import java.util.List;

public class CompressUtil {

    private static final List<String> TARGET_PROFILES = Arrays.asList("none");

    private static final String PROP_NAME = "spring.profiles.active";

    public static boolean isToBeCompressed() {

        return TARGET_PROFILES.contains(StringUtils.nullToStr(System.getenv(PROP_NAME), System.getProperty(PROP_NAME)));

    }
}
