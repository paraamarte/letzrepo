/**
 * Copyright (c) 2006 Samsung Electronics, Inc.
 * All right reserved.
 * This software is the confidential and proprietary information of Samsung
 * Electronics, Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with Samsung Electronics.
 * Revision History
 * Author Date Description
 * ---------- ----- ------------
 * KangSeungil 2012. 5. 30 DefaultConfig.java created
 */
package com.sivillage.common.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.configuration.CombinedConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.codehaus.plexus.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigUtil {

    private static String serverType;

    private static Logger LOG = LoggerFactory.getLogger(ConfigUtil.class);

    private static ConcurrentHashMap<String, CombinedConfiguration> configurationMap = new ConcurrentHashMap<String, CombinedConfiguration>();

    public static ConcurrentHashMap<Thread, String> contextPathMap;

    private static XMLConfiguration applicationConf;

    private static XMLConfiguration channelConf;

    public static String channelPrefix = "SIVILLAGE";
    public static String servletPath;

    protected static Map<String, CombinedConfiguration> getConfigurationMap() {
        return configurationMap;
    }

    /**
     * Refresh the configuration .
     */
    public static void refreshConfiguration(String channelPrefix, String servletPath) {
        // ConfigUtil.channelPrefix = channelPrefix;
        ConfigUtil.servletPath = servletPath;
        CombinedConfiguration config = init(channelPrefix);
        setConfigurationMap(channelPrefix, config);
    }

    /**
     * Flush the configuration.
     */
    public static void flushConfiguration() {
        if (configurationMap != null && !configurationMap.isEmpty()) {
            configurationMap.remove(channelPrefix);
        }
    }

    /**
     * configuration 을 map 에 담는다.<br/>
     * key = contextPath
     * 
     * @param contextPath
     * @return
     */
    public static void setConfigurationMap(String contextPath, CombinedConfiguration config) {
        // if (configurationMap == null) {
        // configurationMap = new ConcurrentHashMap<String, CombinedConfiguration>();
        // configurationMap = new HashMap<String, CombinedConfiguration>();
        // }
        configurationMap.put(contextPath, config);
    }

    /**
     * Initializations the configuration.
     * 
     * <pre>
     * 1. systemConf 로딩
     * 2. channelConf 로딩
     * 
     * * 자동 reload 안되도록 처리되어 있음.
     * </pre>
     * 
     * @author KangSeungil
     * @param
     * @return the combined configuration
     */
    private static CombinedConfiguration init(String channelPrefix) {

        CombinedConfiguration config = new CombinedConfiguration();

        serverType = StringUtils.defaultString(System.getProperty("spring.profiles.active"), System.getenv("spring.profiles.active"));
        LOG.debug("serverType = " + serverType);

        try {
            /* applicationConf.xml 서버 type 에 따라 파일이 분리 되어 있음 */
            applicationConf = new XMLConfiguration(getConfigFileName("applicationConf", serverType));

            /* channelConf.xml */
            channelConf = new XMLConfiguration(getConfigFileName("channelConf", serverType));

            /* configuration files combine */
            config.addConfiguration(applicationConf);
            config.addConfiguration(channelConf);
            config.setForceReloadCheck(true);

            if (LOG.isDebugEnabled()) { // 설정정보 1건씩 로깅
                @SuppressWarnings("rawtypes")
                Iterator iterator = applicationConf.getKeys();
                while (iterator.hasNext()) {
                    String key = (String) iterator.next();
                    LOG.debug("applicationConf {} = \"{}\";", key.toUpperCase().replaceAll("\\.", "_"), key);
                    break;
                }

                iterator = channelConf.getKeys();
                while (iterator.hasNext()) {
                    String key = (String) iterator.next();
                    LOG.debug("channelConf {} = \"{}\";", key.toUpperCase().replaceAll("\\.", "_"), key);
                    break;
                }
            }
        } catch (ConfigurationException e) {
            config = null;
            LOG.error(e.toString());
        }

        return config;
    }

    /**
     * Gets the config file name.
     * 
     * @param fileName
     *            the file name
     * @return the config file name
     */
    public static String getConfigFileName(String fileName) {
        return getConfigFileName(fileName, "");
    }

    /**
     * Gets the config file name.<br/>
     * configuration file is distributed by ServerType.
     * 
     * @param fileName
     *            the file name
     * @param serverType
     *            the server type
     * @return the config file name
     */
    private static String getConfigFileName(String fileName, String serverType) {
        String path = String.format("../config/%s_%s.xml", fileName, serverType);

        LOG.debug("--------------------------------------------------------------------");
        LOG.debug("getConfigFileName - " + path);
        LOG.debug("--------------------------------------------------------------------");

        return path;

    }

    /**
     * 
     */
    private static CombinedConfiguration getProperConfig() {
        CombinedConfiguration config = null;

        if (configurationMap != null && !configurationMap.isEmpty()) {
            config = configurationMap.get(channelPrefix);
        } else {
            refreshConfiguration(channelPrefix, servletPath);
            config = configurationMap.get(channelPrefix);
        }
        return config;
    }

    /**
     * Adds the property.
     * 
     * @param key
     *            the key
     * @param value
     *            the value
     */
    public static void addProperty(String key, Object value) {
        CombinedConfiguration config = getProperConfig();
        if (config != null) {
            config.addProperty(key, value);
        }
    }

    /**
     * Clear.
     * configuration 자원을 해제한다.
     */
    public static void clear() {
        CombinedConfiguration config = getProperConfig();
        if (config != null) {
            config.clear();
        }
    }

    /**
     * Clear property.
     * 
     * @param key
     *            the key
     */
    public static void clearProperty(String key) {
        CombinedConfiguration config = getProperConfig();
        if (config != null) {
            config.clearProperty(key);
        }
    }

    /**
     * Contains key.
     * 해당 키 value 가 존재하는지 확인한다.
     * 
     * @param key
     *            the key
     * @return true, if successful
     */
    public static boolean containsKey(String key) {
        CombinedConfiguration config = getProperConfig();
        if (config != null) {
            return config.containsKey(key);
        }

        return false;
    }

    /**
     * Checks if is empty.
     * configuration
     * 
     * @return true, if is empty
     */
    public static boolean isEmpty() {
        CombinedConfiguration config = getProperConfig();
        if (config != null) {
            return config.isEmpty();
        }

        return true;
    }

    /**
     * Sets the property.
     * 
     * @param key
     *            the key
     * @param value
     *            the value
     */
    public static void setProperty(String key, Object value) {
        CombinedConfiguration config = getProperConfig();
        if (config != null) {
            config.setProperty(key, value);
        }
    }

    /**
     * Gets the keys.
     * 
     * @return the keys
     */
    @SuppressWarnings("unchecked")
    public static Iterator<String> getKeys() {
        CombinedConfiguration config = getProperConfig();
        if (config != null) {
            return config.getKeys();
        }

        return null;
    }

    /**
     * Gets the keys.
     * 
     * @param prefix
     *            the prefix
     * @return the keys
     */
    @SuppressWarnings("unchecked")
    public static Iterator<String> getKeys(String prefix) {
        CombinedConfiguration config = getProperConfig();
        if (config != null) {
            return config.getKeys(prefix);
        }

        return null;
    }

    /**
     * Gets the list.
     * 
     * @param key
     *            the key
     * @return the list
     */
    @SuppressWarnings("unchecked")
    public static List<String> getList(String key) {
        CombinedConfiguration config = getProperConfig();
        if (config != null) {
            List<String> list = config.getList(key);
            if (list != null && list.size() > 0) {
                if (list.get(0) instanceof String) {
                    String s;
                    for (int i = 0; i < list.size(); i++) {
                        s = (String) list.get(i);
                        list.set(i, s.replace("#comma#", ","));
                    }
                }
            }
            return config.getList(key);
        }

        return null;
    }

    /**
     * Gets the list.
     * 
     * @param key
     *            the key
     * @param defaultValue
     *            the default value
     * @return the list
     */
    @SuppressWarnings("unchecked")
    public static List<String> getList(String key, List<String> defaultValue) {
        CombinedConfiguration config = getProperConfig();
        if (config != null) {
            return config.getList(key, defaultValue);
        }

        return null;
    }

    /**
     * Gets the properties.
     * 
     * @param key
     *            the key
     * @return the properties
     */
    public static Properties getProperties(String key) {
        CombinedConfiguration config = getProperConfig();
        if (config != null) {
            return config.getProperties(key);
        }

        return null;
    }

    /**
     * Gets the property.
     * 
     * @param key
     *            the key
     * @return the property
     */
    public static Object getProperty(String key) {
        CombinedConfiguration config = getProperConfig();
        if (config != null) {
            return config.getProperty(key);
        }

        return null;
    }

    /**
     * Gets the big decimal.
     * 
     * @param key
     *            the key
     * @return the big decimal
     */
    public static BigDecimal getBigDecimal(String key) {
        CombinedConfiguration config = getProperConfig();
        if (config != null) {
            return config.getBigDecimal(key);
        }

        return null;
    }

    /**
     * Gets the big decimal.
     * 
     * @param key
     *            the key
     * @param defaultValue
     *            the default value
     * @return the big decimal
     */
    public static BigDecimal getBigDecimal(String key, BigDecimal defaultValue) {
        CombinedConfiguration config = getProperConfig();
        if (config != null) {
            return config.getBigDecimal(key, defaultValue);
        }

        return defaultValue;
    }

    /**
     * Gets the big integer.
     * 
     * @param key
     *            the key
     * @return the big integer
     */
    public static BigInteger getBigInteger(String key) {
        CombinedConfiguration config = getProperConfig();
        if (config != null) {
            return config.getBigInteger(key);
        }

        return null;
    }

    /**
     * Gets the big integer.
     * 
     * @param key
     *            the key
     * @param defaultValue
     *            the default value
     * @return the big integer
     */
    public static BigInteger getBigInteger(String key, BigInteger defaultValue) {
        CombinedConfiguration config = getProperConfig();
        if (config != null) {
            return config.getBigInteger(key, defaultValue);
        }

        return defaultValue;
    }

    /**
     * Gets the boolean.
     * 
     * @param key
     *            the key
     * @return the boolean
     * @throws java.util.NoSuchElementException
     */
    public static boolean getBoolean(String key) {
        CombinedConfiguration config = getProperConfig();
        if (config != null) {
            return config.getBoolean(key);
        }

        return false;
    }

    /**
     * Gets the boolean.
     * 
     * @param key
     *            the key
     * @param defaultValue
     *            the default value
     * @return the boolean
     */
    public static boolean getBoolean(String key, boolean defaultValue) {
        CombinedConfiguration config = getProperConfig();
        if (config != null) {
            return config.getBoolean(key, defaultValue);
        }

        return defaultValue;
    }

    /**
     * Gets the boolean.
     * 
     * @param key
     *            the key
     * @param defaultValue
     *            the default value
     * @return the boolean
     */
    public static Boolean getBoolean(String key, Boolean defaultValue) {
        CombinedConfiguration config = getProperConfig();
        if (config != null) {
            return config.getBoolean(key, defaultValue);
        }

        return defaultValue;
    }

    /**
     * Gets the byte.
     * 
     * @param key
     *            the key
     * @return the byte
     * @throws java.util.NoSuchElementException
     */
    public static byte getByte(String key) {
        CombinedConfiguration config = getProperConfig();
        if (config != null) {
            return config.getByte(key);
        }

        return 0;
    }

    /**
     * Gets the byte.
     * 
     * @param key
     *            the key
     * @param defaultValue
     *            the default value
     * @return the byte
     */
    public static byte getByte(String key, byte defaultValue) {
        CombinedConfiguration config = getProperConfig();
        if (config != null) {
            return config.getByte(key, defaultValue);
        }

        return defaultValue;
    }

    /**
     * Gets the byte.
     * 
     * @param key
     *            the key
     * @param defaultValue
     *            the default value
     * @return the byte
     */
    public static Byte getByte(String key, Byte defaultValue) {
        CombinedConfiguration config = getProperConfig();
        if (config != null) {
            return config.getByte(key, defaultValue);
        }

        return defaultValue;
    }

    /**
     * Gets the double.
     * 
     * @param key
     *            the key
     * @return the double
     * @throws java.util.NoSuchElementException
     */
    public static double getDouble(String key) {
        CombinedConfiguration config = getProperConfig();
        if (config != null) {
            return config.getDouble(key);
        }

        return 0.0D;
    }

    /**
     * Gets the double.
     * 
     * @param key
     *            the key
     * @param defaultValue
     *            the default value
     * @return the double
     */
    public static double getDouble(String key, double defaultValue) {
        CombinedConfiguration config = getProperConfig();
        if (config != null) {
            return config.getDouble(key, defaultValue);
        }

        return defaultValue;
    }

    /**
     * Gets the double.
     * 
     * @param key
     *            the key
     * @param defaultValue
     *            the default value
     * @return the double
     */
    public static Double getDouble(String key, Double defaultValue) {
        CombinedConfiguration config = getProperConfig();
        if (config != null) {
            return config.getDouble(key, defaultValue);
        }

        return defaultValue;
    }

    /**
     * Gets the float.
     * 
     * @param key
     *            the key
     * @return the float
     * @throws java.util.NoSuchElementException
     */
    public static float getFloat(String key) {
        CombinedConfiguration config = getProperConfig();
        if (config != null) {
            return config.getFloat(key);
        }

        return 0.0F;
    }

    /**
     * Gets the float.
     * 
     * @param key
     *            the key
     * @param defaultValue
     *            the default value
     * @return the float
     */
    public static float getFloat(String key, float defaultValue) {
        CombinedConfiguration config = getProperConfig();
        if (config != null) {
            return config.getFloat(key, defaultValue);
        }

        return defaultValue;
    }

    /**
     * Gets the float.
     * 
     * @param key
     *            the key
     * @param defaultValue
     *            the default value
     * @return the float
     */
    public static Float getFloat(String key, Float defaultValue) {
        CombinedConfiguration config = getProperConfig();
        if (config != null) {
            return config.getFloat(key, defaultValue);
        }

        return defaultValue;
    }

    /**
     * Gets the int.
     * 
     * @param key
     *            the key
     * @return the int
     * @throws java.util.NoSuchElementException
     */
    public static int getInt(String key) {
        CombinedConfiguration config = getProperConfig();
        if (config != null) {
            return config.getInt(key);
        }

        return 0;
    }

    /**
     * Gets the int.
     * 
     * @param key
     *            the key
     * @param defaultValue
     *            the default value
     * @return the int
     */
    public static int getInt(String key, int defaultValue) {
        CombinedConfiguration config = getProperConfig();
        if (config != null) {
            return config.getInt(key, defaultValue);
        }

        return defaultValue;
    }

    /**
     * Gets the integer.
     * 
     * @param key
     *            the key
     * @param defaultValue
     *            the default value
     * @return the integer
     */
    public static Integer getInteger(String key, Integer defaultValue) {
        CombinedConfiguration config = getProperConfig();
        if (config != null) {
            return config.getInteger(key, defaultValue);
        }

        return defaultValue;
    }

    /**
     * Gets the long.
     * 
     * @param key
     *            the key
     * @return the long
     * @throws java.util.NoSuchElementException
     */
    public static long getLong(String key) {
        CombinedConfiguration config = getProperConfig();
        if (config != null) {
            return config.getLong(key);
        }

        return 0L;
    }

    /**
     * Gets the long.
     * 
     * @param key
     *            the key
     * @param defaultValue
     *            the default value
     * @return the long
     */
    public static long getLong(String key, long defaultValue) {
        CombinedConfiguration config = getProperConfig();
        if (config != null) {
            return config.getLong(key, defaultValue);
        }

        return defaultValue;
    }

    /**
     * Gets the long.
     * 
     * @param key
     *            the key
     * @param defaultValue
     *            the default value
     * @return the long
     */
    public static Long getLong(String key, Long defaultValue) {
        CombinedConfiguration config = getProperConfig();
        if (config != null) {
            return config.getLong(key, defaultValue);
        }

        return defaultValue;
    }

    /**
     * Gets the short.
     * 
     * @param key
     *            the key
     * @return the short
     * @throws java.util.NoSuchElementException
     */
    public static short getShort(String key) {
        CombinedConfiguration config = getProperConfig();
        if (config != null) {
            return config.getShort(key);
        }

        return 0;
    }

    /**
     * Gets the short.
     * 
     * @param key
     *            the key
     * @param defaultValue
     *            the default value
     * @return the short
     */
    public static short getShort(String key, short defaultValue) {
        CombinedConfiguration config = getProperConfig();
        if (config != null) {
            return config.getShort(key, defaultValue);
        }

        return defaultValue;
    }

    /**
     * Gets the short.
     * 
     * @param key
     *            the key
     * @param defaultValue
     *            the default value
     * @return the short
     */
    public static Short getShort(String key, Short defaultValue) {
        CombinedConfiguration config = getProperConfig();
        if (config != null) {
            return config.getShort(key, defaultValue);
        }

        return defaultValue;
    }

    /**
     * Gets the string.
     * 
     * @param key
     *            the key
     * @return the string
     */
    public static String getString(String key) {
        /*
         * Iterator keys = systemConf.getKeys();
         * while (keys.hasNext()) {
         * LOG.debug("key = " + keys.next());
         * }
         */

        CombinedConfiguration config = getProperConfig();
        if (config != null) {
            return config.getString(key);
        }

        return null;
    }

    /**
     * Gets the string.
     * 
     * @param key
     *            the key
     * @param defaultValue
     *            the default value
     * @return the string
     */
    public static String getString(String key, String defaultValue) {
        CombinedConfiguration config = getProperConfig();
        if (config != null) {
            return config.getString(key, defaultValue);
        }

        return defaultValue;
    }

    /**
     * Gets the string array.
     * 
     * @param key
     *            the key
     * @return the string array
     */
    public static String[] getStringArray(String key) {
        CombinedConfiguration config = getProperConfig();
        if (config != null) {
            return config.getStringArray(key);
        }

        return null;
    }
}
