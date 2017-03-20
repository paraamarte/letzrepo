/*
 * This software is the confidential and proprietary information of
 * Shinsegae Internatinal Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Shinsegae International.
 */
package com.letz.utils;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sivillage.core.vo.NexacroConstant;

/**
 * @Class Name : ObjectUtil.java
 * @Description : Object Util
 * @Modification Information
 *               @ * @ �닔�젙�씪 �닔�젙�옄 �닔�젙�궡�슜
 *               @ --------- --------- -------------------------------
 *               @ 2015.10.13 理쒖큹�깮�꽦
 * @author Hubert
 * @since 2015.10.13
 * @version 1.0
 * @see
 *      Copyright(c) 2016 SHINSEGAE INTERNATIONAL. All rights reserved
 */
public class ObjectUtil {

    /**
     * �럹�씠吏� �젙蹂대�� �꽕�젙�븳�떎.
     * 
     * @param req
     *            : HttpServletRequest 媛앹껜
     * @param dsNm
     *            : �럹�씠吏뺥븷 �뜲�씠�꽣�뀑 紐�(ds_test:P �뿉�꽌 'ds_test')
     * @param obj
     *            : VO Class
     * @param totCnt
     *            : �럹�씠吏뺥븷 �뜲�씠�꽣 珥� 移댁슫�듃
     * @return
     */
    public static void setPaging(HttpServletRequest req, String dsNm, int totCnt) {
        req.setAttribute(dsNm + "_page_totCnt", totCnt);
    }

    /**
     * variable�쓣 VO �삎�깭濡� 蹂��솚�븯怨� �럹�씠吏� �젙蹂대�� �꽕�젙�븳�떎.
     * 
     * @param req
     *            : HttpServletRequest 媛앹껜
     * @param obj
     *            : VO Class
     */
    @SuppressWarnings("unchecked")
    public static Object getVariable(HttpServletRequest req, Object obj, String dsNm) { // clazz: Map
        Map<String, Object> params = (Map<String, Object>) req.getAttribute("params");
        Map<String, Object> variableMap = (Map<String, Object>) params.get(NexacroConstant.IN_VARIABLES_ATT_NAME);

        Object vo = new Object();
        try {
            String classNm = obj.toString().substring(6);
            vo = Class.forName(classNm).newInstance();

            convertMapToObject(variableMap, vo, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // �럹�씠吏� �젙蹂� �꽕�젙
        setPagingInfoToObject(req, vo, dsNm);

        return vo;
    }

    /**
     * variable�쓣 VO �삎�깭濡� 蹂��솚�븳�떎.
     * 
     * @param req
     *            : HttpServletRequest 媛앹껜
     * @param obj
     *            : VO Class
     */
    @SuppressWarnings("unchecked")
    public static Object getVariable(HttpServletRequest req, Object obj) { // clazz: Map
        Map<String, Object> params = (Map<String, Object>) req.getAttribute("params");
        Map<String, Object> variableMap = (Map<String, Object>) params.get(NexacroConstant.IN_VARIABLES_ATT_NAME);

        Object vo = new Object();
        try {
            String classNm = obj.toString().substring(6);
            vo = Class.forName(classNm).newInstance();

            // variable�씠 �뾾�떎硫� 鍮� VO瑜� 由ы꽩�븳�떎.
            if (variableMap == null || variableMap.size() == 0) {
                return vo;
            }

            convertMapToObject(variableMap, vo, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return vo;
    }

    /**
     * variable�쓣 Map �삎�깭濡� 蹂��솚�븳�떎.
     * 
     * @param req
     *            : HttpServletRequest 媛앹껜
     * @param obj
     *            : VO Class
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getVariableMap(HttpServletRequest req) { // clazz: Map
        Map<String, Object> params = (Map<String, Object>) req.getAttribute("params");
        Map<String, Object> variableMap = (Map<String, Object>) params.get(NexacroConstant.IN_VARIABLES_ATT_NAME);

        return variableMap;
    }

    /**
     * dataset�쓣 VO �삎�깭濡� 蹂��솚�븳�떎.
     * 
     * @param req
     *            : HttpServletRequest 媛앹껜
     * @param obj
     *            : VO Class
     * @param dsNm
     *            : �뙆�씪誘명꽣濡� 諛쏆븘�삱 �뜲�씠�꽣�뀑 紐�
     */
    @SuppressWarnings("unchecked")
    public static List<?> getDataset(HttpServletRequest req, Object obj, String dsNm) {
        Map<String, Object> params = (Map<String, Object>) req.getAttribute("params");
        Map<String, Object> datasetMap = (Map<String, Object>) params.get(NexacroConstant.IN_DATASET_ATT_NAME);

        // �빐�떦 dataset�씠 �뾾�쑝硫� 由ы꽩
        List<Map<String, Object>> list = (List<Map<String, Object>>) datasetMap.get(dsNm);
        if (list == null || list.size() == 0) {
            return new ArrayList<Object>();
        }

        List<Object> voList = new ArrayList<Object>();
        for (Map<String, Object> map : list) {
            Object vo = new Object();
            try {
                String classNm = obj.toString().substring(6);
                vo = Class.forName(classNm).newInstance();

                convertMapToObject(map, vo, true);
                voList.add(vo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return voList;
    }

    /**
     * @param map
     * @param obj
     * @param dataType
     *            �뜲�씠�꽣���엯�뿉 �뵲�씪 蹂��솚�븷 寃껋씤吏� �뿬遺�
     */
    @SuppressWarnings("rawtypes")
    public static void convertMapToObject(Map map, Object obj, Boolean dataType) {
        String key = null;
        String methodName = null;

        Iterator itr = map.keySet().iterator();
        while (itr.hasNext()) {

            key = (String) itr.next();
            /*
             * InfoBaseVO�뿉�꽌 List ���엯�쓽 蹂��닔媛� 議댁옱�븯�뒗�뜲 bo > �궗�씠�듃 �슫�쁺 愿�由� > �뼱�뱶誘� 怨듭��궗�빆 �쓽 �닔�젙 湲곕뒫�뿉�꽌�뒗
             * �뜲�씠�꽣媛� List濡� �꽆�뼱���꽌 �븘�옒 invoke �떆 coldataType�� String�씠�굹 obj媛� list�뿬�꽌 illeagalAgumentException 諛쒖깮�븯�뿬
             * �듅�젙 Class�쓽 �듅�젙 蹂��닔留� continue 泥섎━ �븿.
             * 2016.05.12 uzen_letz
             */
            if (obj.getClass().getCanonicalName().equals("com.sivillage.general.information.vo.InfoBaseVO") && key.endsWith("ntcTgtTpList") ||
                    obj.getClass().getCanonicalName().equals("com.sivillage.general.popup.vo.PopupVO") && key.endsWith("popupBulHallList")) {
                continue;
            }
            methodName = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);

            Class<?> voClass = obj.getClass();

            try {
                Method[] methods = voClass.getMethods();
                for (Method method : methods) {
                    if (methodName.equals(method.getName())) {
                        Object colData = map.get(key);
                        if (StringUtils.isNotBlank(String.valueOf(colData))) {
                            if (dataType) {
                                String colDataType = colData.getClass().getSimpleName();
                                if ("String".equals(colDataType)) {
                                    method.invoke(obj, (String) map.get(key));
                                } else if ("Integer".equals(colDataType)) {
                                    method.invoke(obj, (Integer) map.get(key));
                                } else if ("Float".equals(colDataType)) {
                                    method.invoke(obj, (Float) map.get(key));
                                } else if ("Double".equals(colDataType)) {
                                    method.invoke(obj, (Float) map.get(key));
                                } else if ("BigDecimal".equals(colDataType)) {
                                    BigDecimal val = new BigDecimal(map.get(key).toString());
                                    method.invoke(obj, val);
                                } else if ("Date".equals(colDataType)) {
                                    method.invoke(obj, (Date) map.get(key));
                                }
                            } else {
                                method.invoke(obj, map.get(key));
                            }
                        }

                    }
                }
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static void setPagingInfoToObject(HttpServletRequest req, Object obj, String dsNm) {

        Class<?> clazz = null;

        Map<String, Object> params = (Map<String, Object>) req.getAttribute("params");
        Map<String, Object> datasetMap = (Map<String, Object>) params.get(NexacroConstant.IN_DATASET_ATT_NAME);
        List<Map<String, Object>> dsPage = (List<Map<String, Object>>) datasetMap.get(dsNm + "_page");
        if (dsPage == null) {
            return;
        }

        try {
            // �럹�씠吏� �젙蹂�
            Integer currentPage = (Integer) dsPage.get(0).get("currPage");
            int perPage = (Integer) dsPage.get(0).get("pageListCnt");

            clazz = obj.getClass().getSuperclass();

            Method method = null;
            method = clazz.getDeclaredMethod("setCurrentPage", Integer.class);
            method.invoke(obj, currentPage);
            method = clazz.getDeclaredMethod("setPerPage", int.class);
            method.invoke(obj, perPage);

            // Method[] methods = obj.getClass().getSuperclass().getDeclaredMethods();
            // for (Method method : methods) {
            // if ("setCurrentPage".equals(method.getName())) {
            // method.invoke(obj, currentPage);
            // } else if ("setPerPage".equals(method.getName())) {
            // method.invoke(obj, perPage);
            // }
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}