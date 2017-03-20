package com.letz.utils.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.sivillage.base.vo.ComboVO;
import com.sivillage.base.vo.GroupCodeVO;
import com.sivillage.cmcd.service.CodeUtilService;
import com.sivillage.cmcd.vo.ColorChipSC;
import com.sivillage.cmcd.vo.ColorChipVO;
import com.sivillage.cmcd.vo.CommonCodeSC;
import com.sivillage.cmcd.vo.CommonCodeVO;

public class CodeUtil implements InitializingBean {

    @Autowired
    static CodeUtilService codeUtilService;
    
    public static void setCodeUtilService(CodeUtilService codeUtilService) {
        CodeUtil.codeUtilService = codeUtilService;
    }

    private static List<CommonCodeVO> codeList;
    private static Map<String, List<CommonCodeVO>> codeMap;
    private static List<GroupCodeVO> grpCodeList;
    private static Logger log = LoggerFactory.getLogger(CodeUtil.class);
    
    private static List<ColorChipVO> colorList;

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    /**
     * 二쇱뼱吏� GroupCode �뿉 留ㅼ묶�릺�뒗 detail code list瑜� 諛섑솚�븳�떎.
     * 
     * @param grpCode
     *            寃��깋�븯怨좎옄 �븯�뒗 group code
     * @return grpCode�뿉 �빐�떦�븯�뒗 detail code 紐⑸줉. 留ㅼ묶�릺吏� �븡�쑝硫� null �쓣 諛섑솚�븳�떎.
     */
    public static List<CommonCodeVO> getDtlCodeList(String grpCode) {
        List<CommonCodeVO> owner = codeMap.get(grpCode);
        if (owner == null) {
            return null;
        }
        List<CommonCodeVO> newList = new ArrayList<CommonCodeVO>();
        newList.addAll(owner);
        return newList;
    }

    /**
     * 二쇱뼱吏� GroupCode �뿉 留ㅼ묶�릺�뒗 combo list瑜� 諛섑솚�븳�떎.
     * 
     * @param grpCode
     *            寃��깋�븯怨좎옄 �븯�뒗 group code
     * @return grpCode�뿉 �빐�떦�븯�뒗 combo list. 留ㅼ묶�릺吏� �븡�쑝硫� null �쓣 諛섑솚�븳�떎.
     */
    public static List<ComboVO> getComboList(String grpCode) {
        List<CommonCodeVO> owner = codeMap.get(grpCode);
        if (owner == null) {
            return null;
        }

        List<ComboVO> newList = new ArrayList<ComboVO>();
        for (CommonCodeVO commonVO : owner) {
            ComboVO comboVO = new ComboVO();
            
            comboVO.setCd(commonVO.getCd());
            comboVO.setCdNm(commonVO.getCdNm());
            comboVO.setGrpCd(commonVO.getGrpCd());
            comboVO.setOpt1(commonVO.getOpt1());
            comboVO.setOpt2(commonVO.getOpt2());
            comboVO.setOpt3(commonVO.getOpt3());
            comboVO.setOpt4(commonVO.getOpt4());
            comboVO.setOpt5(commonVO.getOpt5());

            newList.add(comboVO);
        }

        return newList;
    }

    /**
     * 洹몃９肄붾뱶�쓽 肄붾뱶 由ъ뒪�듃 以� 遺�媛� 肄붾뱶�� 留ㅼ묶�릺�뒗 ComboVO 媛앹껜 由ъ뒪�듃瑜� 諛섑솚
     * 
     * <pre>
     * 洹몃９肄붾뱶�쓽 肄붾뱶 由ъ뒪�듃 以� 遺�媛� 肄붾뱶�� 留ㅼ묶�릺�뒗 ComboVO 媛앹껜 由ъ뒪�듃瑜� 諛섑솚�븳�떎.
     * </pre>
     * 
     * @param grpCd
     * @param adtnCd
     * @return
     */
    public static List<ComboVO> getComboList(String grpCode, String adtnCd) {
        List<CommonCodeVO> owner = codeMap.get(grpCode);
        if (owner == null) {
            return null;
        }

        List<ComboVO> newList = new ArrayList<ComboVO>();
        for (CommonCodeVO commonVO : owner) {
            if (adtnCd.equals(commonVO.getAdtnCdNm())) {
                ComboVO comboVO = new ComboVO();
                comboVO.setCd(commonVO.getCd());
                comboVO.setCdNm(commonVO.getCdNm());
                comboVO.setGrpCd(commonVO.getGrpCd());
                newList.add(comboVO);
            }
        }

        return newList;
    }

    /**
     * 怨듯넻肄붾뱶媛� 珥덇린�솕.
     * CodeUtil �뿉�꽌 �궗�슜�맆 肄붾뱶�뜲�씠�꽣 珥덇린�솕.
     */
    public static void init() {
        CommonCodeSC param = new CommonCodeSC();
        param.setSearchUseYn("Y");
        List<CommonCodeVO> list = codeUtilService.getCommonCodeList(param);
        if (list == null) {
            throw new IllegalStateException("not found codes");
        }
        if (codeList == null) {
            codeList = list;
        }
        else {
            // codeList �� list(cache) 媛� 媛숈� instance �씠硫� �븘臾닿쾬�룄 �븯吏� �븡�쓬.
            if (codeList == list) {
                return;
            }
        }
        // 珥덇린�솕
        codeMap = new HashMap<String, List<CommonCodeVO>>();
        grpCodeList = new ArrayList<GroupCodeVO>();
        for (CommonCodeVO code : list) {
            String curGrpCd = code.getGrpCd();
            List<CommonCodeVO> curCodeList = codeMap.get(curGrpCd);
            if (curCodeList == null) { // map�뿉 grpCd媛� 議댁옱 �븯吏� �븡�쓣 �븣
                curCodeList = new ArrayList<CommonCodeVO>();
                codeMap.put(curGrpCd, curCodeList);

                // 洹몃９肄붾뱶 list�뿉 異붽�
                GroupCodeVO grpCdmd = new GroupCodeVO();
                grpCdmd.setGrpCd(curGrpCd);
                grpCdmd.setGrpCdNm(code.getGrpCdNm());
                grpCodeList.add(grpCdmd);
            }
            curCodeList.add(code);
        }
        
        
        ColorChipSC ccsc = new ColorChipSC();
        ccsc.setUseYn("Y");
        List<ColorChipVO> colorlist = codeUtilService.getPdColorChipList(ccsc);
        if (colorlist == null) {
            throw new IllegalStateException("not found colorChips");
        }
        if (colorList == null) {
            colorList = colorlist;
        }
        else {
            // codeList �� list(cache) 媛� 媛숈� instance �씠硫� �븘臾닿쾬�룄 �븯吏� �븡�쓬.
            if (colorList == colorlist) {
                return;
            }
        }
    }

    /**
     * group code list瑜� 諛섑솚�븳�떎
     */
    public static List<GroupCodeVO> getGrpCode() {
        return grpCodeList;
    }

    /**
     * code �뿉 留ㅼ묶�릺�뒗 CommonDtlCode 媛앹껜瑜� 諛섑솚�븳�떎.
     * 
     * @param strCd
     *            寃��깋�븯怨좎옄 �븯�뒗 code
     * @return 留ㅼ묶�릺�뒗 CommonDtlCode. 留ㅼ묶�릺吏� �븡��硫� null �쓣 諛섑솚�븳�떎.
     */
    public static CommonCodeVO getCode(String grpCode, String strCd) {
        if (grpCode == null || strCd == null) {
            return null;
        }
        List<CommonCodeVO> dtlCodeList = getDtlCodeList(grpCode);
        if (dtlCodeList == null) {
            return null;
        }
        for (CommonCodeVO dtlCode : dtlCodeList) {
            if (strCd.equals(dtlCode.getCd())) {
                return dtlCode;
            }
        }
        return null;
    }

    /**
     * code �뿉 留ㅼ묶�릺�뒗 怨듯넻肄붾뱶�쓽 紐낆쓣 諛섑솚�븳�떎.
     * 
     * @param strCd
     *            寃��깋�븯怨좎옄 �븯�뒗 code
     * @return 留ㅼ묶�릺�뒗 CommonDtlCode. 留ㅼ묶�릺吏� �븡��硫� null �쓣 諛섑솚�븳�떎.
     */
    public static String getCodeNm(String grpCode, String strCd) {
        if (grpCode == null || strCd == null) {
            return null;
        }
        List<CommonCodeVO> dtlCodeList = getDtlCodeList(grpCode);
        if (dtlCodeList == null) {
            return null;
        }
        for (CommonCodeVO dtlCode : dtlCodeList) {
            if (strCd.equals(dtlCode.getCd())) {
                return dtlCode.getCdNm();
            }
        }
        return null;
    }

    /**
     * 怨듯넻 肄붾뱶瑜� option tag �삎�깭濡� 諛섑솚�븳�떎.
     * 
     * @param grpCd
     *            怨듯넻洹몃９ 肄붾뱶
     * @param selecCd
     *            湲곕낯 �꽑�깮 肄붾뱶
     * @param cdb
     *            opt1 ~ opt5 異붽� condition
     * @return
     */
    public static String getDtlCodeListOptionHtml(String grpCd, String selectCd, CommonCodeVO cdb) {
        List<CommonCodeVO> codeList = getDtlCodeList(grpCd);

        StringBuilder retStr = new StringBuilder();
        if (codeList != null) {
            for (CommonCodeVO code : codeList) {
                boolean checkFlag = true;
                if (StringUtils.isNotBlank(cdb.getOpt1()) && !StringUtils.defaultString(code.getOpt1()).equals(StringUtils.defaultString(cdb.getOpt1()))) {
                    checkFlag = false;
                }
                if (StringUtils.isNotBlank(cdb.getOpt2()) && !StringUtils.defaultString(code.getOpt2()).equals(StringUtils.defaultString(cdb.getOpt2()))) {
                    checkFlag = false;
                }
                if (StringUtils.isNotBlank(cdb.getOpt3()) && !StringUtils.defaultString(code.getOpt3()).equals(StringUtils.defaultString(cdb.getOpt3()))) {
                    checkFlag = false;
                }
                if (StringUtils.isNotBlank(cdb.getOpt4()) && !StringUtils.defaultString(code.getOpt4()).equals(StringUtils.defaultString(cdb.getOpt4()))) {
                    checkFlag = false;
                }
                if (StringUtils.isNotBlank(cdb.getOpt5()) && !StringUtils.defaultString(code.getOpt5()).equals(StringUtils.defaultString(cdb.getOpt5()))) {
                    checkFlag = false;
                }
                if (checkFlag) {
                    retStr.append("<option");
                    retStr.append(" value=\"" + code.getCd() + "\"");
                    retStr.append(" opt1=\"" + StringUtils.defaultString(code.getOpt1()) + "\"");
                    retStr.append(" opt2=\"" + StringUtils.defaultString(code.getOpt2()) + "\"");
                    retStr.append(" opt3=\"" + StringUtils.defaultString(code.getOpt3()) + "\"");
                    retStr.append(" opt4=\"" + StringUtils.defaultString(code.getOpt4()) + "\"");
                    retStr.append(" opt5=\"" + StringUtils.defaultString(code.getOpt5()) + "\"");
                    retStr.append(code.getCd().equals(selectCd) ? " selected=\"selected\"" : "");
                    retStr.append(">");
                    retStr.append(code.getCdNm());
                    retStr.append("</option>");
                }
            }
        }

        return retStr.toString();
    }

    /**
     * 怨듯넻 肄붾뱶瑜� input radio tag �삎�깭濡� 諛섑솚�븳�떎.
     * 
     * @param name
     *            input name
     * @param grpCd
     *            怨듯넻洹몃９ 肄붾뱶
     * @param selecCd
     *            湲곕낯 �꽑�깮 肄붾뱶
     * @param cdb
     *            opt1 ~ opt5 異붽� condition
     * @return
     */
    public static String getDtlCodeListRadioHtml(String name, String grpCd, String selectCd, CommonCodeVO cdb) {
        List<CommonCodeVO> codeList = getDtlCodeList(grpCd);

        StringBuilder retStr = new StringBuilder();
        if (codeList != null) {
            for (CommonCodeVO code : codeList) {
                boolean checkFlag = true;
                if (StringUtils.isNotBlank(cdb.getOpt1()) && !StringUtils.defaultString(code.getOpt1()).equals(StringUtils.defaultString(cdb.getOpt1()))) {
                    checkFlag = false;
                }
                if (StringUtils.isNotBlank(cdb.getOpt2()) && !StringUtils.defaultString(code.getOpt2()).equals(StringUtils.defaultString(cdb.getOpt2()))) {
                    checkFlag = false;
                }
                if (StringUtils.isNotBlank(cdb.getOpt3()) && !StringUtils.defaultString(code.getOpt3()).equals(StringUtils.defaultString(cdb.getOpt3()))) {
                    checkFlag = false;
                }
                if (StringUtils.isNotBlank(cdb.getOpt4()) && !StringUtils.defaultString(code.getOpt4()).equals(StringUtils.defaultString(cdb.getOpt4()))) {
                    checkFlag = false;
                }
                if (StringUtils.isNotBlank(cdb.getOpt5()) && !StringUtils.defaultString(code.getOpt5()).equals(StringUtils.defaultString(cdb.getOpt5()))) {
                    checkFlag = false;
                }
                if (checkFlag) {
                    retStr.append("<label class=\"mr16\"><input type=\"radio\"");
                    retStr.append(" name=\"" + name + "\"");
                    retStr.append(" value=\"" + code.getCd() + "\"");
                    retStr.append(" opt1=\"" + StringUtils.defaultString(code.getOpt1()) + "\"");
                    retStr.append(" opt2=\"" + StringUtils.defaultString(code.getOpt2()) + "\"");
                    retStr.append(" opt3=\"" + StringUtils.defaultString(code.getOpt3()) + "\"");
                    retStr.append(" opt4=\"" + StringUtils.defaultString(code.getOpt4()) + "\"");
                    retStr.append(" opt5=\"" + StringUtils.defaultString(code.getOpt5()) + "\"");
                    if (StringUtils.isNotBlank("selectCd") && code.getCd().equals(selectCd)) {
                        retStr.append(" checked=\"checked\"");
                    }
                    retStr.append(" /> ");
                    retStr.append(code.getCdNm() + "</label>");
                }
            }
        }

        return retStr.toString();
    }

    /**
     * 怨듯넻 肄붾뱶瑜� input checkbox tag �삎�깭濡� 諛섑솚�븳�떎.
     * 
     * @param name
     *            input name
     * @param grpCd
     *            怨듯넻洹몃９ 肄붾뱶
     * @param selecCd
     *            湲곕낯 �꽑�깮 肄붾뱶
     * @param defaultAllCheckYn
     *            �깮�꽦�떆 �쟾遺� check �뿬遺�
     * @param cdb
     *            opt1 ~ opt5 異붽� condition
     * @return
     */
    public static String getDtlCodeListCheckboxHtml(String name, String grpCd, String selectCd,
            String defaultAllCheckYn, CommonCodeVO cdb) {
        List<CommonCodeVO> codeList = getDtlCodeList(grpCd);
        StringBuilder retStr = new StringBuilder();
        if (codeList != null) {
            for (CommonCodeVO code : codeList) {
                boolean checkFlag = true;
                if (StringUtils.isNotBlank(cdb.getOpt1()) && !StringUtils.defaultString(code.getOpt1()).equals(StringUtils.defaultString(cdb.getOpt1()))) {
                    checkFlag = false;
                }
                if (StringUtils.isNotBlank(cdb.getOpt2()) && !StringUtils.defaultString(code.getOpt2()).equals(StringUtils.defaultString(cdb.getOpt2()))) {
                    checkFlag = false;
                }
                if (StringUtils.isNotBlank(cdb.getOpt3()) && !StringUtils.defaultString(code.getOpt3()).equals(StringUtils.defaultString(cdb.getOpt3()))) {
                    checkFlag = false;
                }
                if (StringUtils.isNotBlank(cdb.getOpt4()) && !StringUtils.defaultString(code.getOpt4()).equals(StringUtils.defaultString(cdb.getOpt4()))) {
                    checkFlag = false;
                }
                if (StringUtils.isNotBlank(cdb.getOpt5()) && !StringUtils.defaultString(code.getOpt5()).equals(StringUtils.defaultString(cdb.getOpt5()))) {
                    checkFlag = false;
                }
                if (checkFlag) {
                    retStr.append("<label class=\"mr16\"><input type=\"checkbox\"");
                    retStr.append(" name=\"" + name + "\"");
                    retStr.append(" value=\"" + code.getCd() + "\"");
                    retStr.append(" opt1=\"" + StringUtils.defaultString(code.getOpt1()) + "\"");
                    retStr.append(" opt2=\"" + StringUtils.defaultString(code.getOpt2()) + "\"");
                    retStr.append(" opt3=\"" + StringUtils.defaultString(code.getOpt3()) + "\"");
                    retStr.append(" opt4=\"" + StringUtils.defaultString(code.getOpt4()) + "\"");
                    retStr.append(" opt5=\"" + StringUtils.defaultString(code.getOpt5()) + "\"");
                    if ("Y".equals(StringUtils.defaultString(defaultAllCheckYn, "N"))) {
                        retStr.append(" checked=\"checked\"");
                    } else if (StringUtils.isNotBlank("selectCd") && code.getCd().equals(selectCd)) {
                        retStr.append(" checked=\"checked\"");
                    }
                    retStr.append(" /> ");
                    retStr.append(code.getCdNm() + "</label>");
                    log.debug(code.getGrpCd() + ":" + retStr.toString());
                }
            }
        }

        return retStr.toString();
    }
    
    
    /**
     * code �뿉 留ㅼ묶�릺�뒗 怨듯넻肄붾뱶 而щ윭移⑹뿉�꽌 而щ윭紐낆쓣 媛��졇�삩�떎.
     * 
     * @param strCd
     *            寃��깋�븯怨좎옄 �븯�뒗 code
     * @return 留ㅼ묶�릺�뒗 CommonDtlCode. 留ㅼ묶�릺吏� �븡��硫� null �쓣 諛섑솚�븳�떎.
     */
    public static String getColorNm(String grpCode, String strCd) {
        if (grpCode == null || strCd == null) {
            return "";
        }
        
        List<CommonCodeVO> dtlCodeList = CodeUtil.getDtlCodeList(grpCode);
        if (dtlCodeList == null) {
            return "";
        }
        
        try {
            
            for (CommonCodeVO dtlCode : dtlCodeList) {
                if (strCd.equals(dtlCode.getCd())) {
                    for (ColorChipVO colorVO : colorList) {
                        if (dtlCode.getOpt1() != null && dtlCode.getOpt1().equals(colorVO.getColorChipNo().toString())) {
                            return colorVO.getColorChipNm();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        
        return "";
    }
}