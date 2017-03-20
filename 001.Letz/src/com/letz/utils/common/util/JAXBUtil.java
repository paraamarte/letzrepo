package com.sivillage.common.util;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sivillage.legacy.vo.BizSpringDataVO;
import com.sivillage.legacy.vo.BizSpringHeaderVO;
import com.sivillage.legacy.vo.BizSpringReportVO;

public class JAXBUtil {

    private static final Logger log = LoggerFactory.getLogger(JAXBUtil.class);

    @SuppressWarnings("unchecked")
    public static <T> T xmlToObject(String xml, Class<T> clazz) {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller un = context.createUnmarshaller();
            T obj = (T) un.unmarshal(new StringReader(xml));
            return obj;
        } catch (JAXBException e) {
            log.error(e.toString());
        }
        return null;
    }

    public static String objectToXml(Object obj) {
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            StringWriter sw = new StringWriter();
            m.marshal(obj, sw);

            return sw.toString();
        } catch (JAXBException e) {
            log.error(e.toString());
        }

        return null;
    }

    public static void main(String[] args) {
        BizSpringReportVO report = new BizSpringReportVO();
        report.setPROFILE("JAJU(Web)");
        report.setREPORT("페이지뷰 & 방문자수");
        report.setSEARCH_DATA_START("2016-06-05");
        report.setSEARCH_DATA_END("2016-06-09");

        List<BizSpringHeaderVO> metricsList = new ArrayList<BizSpringHeaderVO>();

        int i = 0;
        for (String column : new String[] { "pv", "vt", "du_vt", "vs", "g1" }) {
            BizSpringHeaderVO metrics = new BizSpringHeaderVO();
            metrics.setCode(column);
            metrics.setValue("Column" + i++);
            metricsList.add(metrics);
        }

        report.setMETRICS_INFO(metricsList);

        List<BizSpringDataVO> data = new ArrayList<BizSpringDataVO>();
        BizSpringDataVO itemVO = new BizSpringDataVO();
        itemVO.setITEM_NAME("2016-06-05");
        itemVO.setPV("10,460");
        itemVO.setVT("951");
        itemVO.setDU_VT("854");
        itemVO.setVS("104:01:19");
        itemVO.setG1("22");
        data.add(itemVO);

        itemVO = new BizSpringDataVO();
        itemVO.setITEM_NAME("2016-06-05");
        itemVO.setPV("10,460");
        itemVO.setVT("951");
        itemVO.setDU_VT("854");
        itemVO.setVS("104:01:19");
        itemVO.setG1("22");
        data.add(itemVO);

        report.setDATA(data);

        itemVO = new BizSpringDataVO();
        itemVO.setPV("84,644");
        itemVO.setVT("8,564");
        itemVO.setDU_VT("7,443");
        itemVO.setVS("887:01:27");
        itemVO.setG1("178");

        List<BizSpringDataVO> summary = new ArrayList<BizSpringDataVO>();
        report.setSUMMARY(summary);

        String xml = objectToXml(report);
        log.debug(xml);

        // ----------------------------------------------------------------

        BizSpringReportVO report2 = xmlToObject(xml, BizSpringReportVO.class);

        List<BizSpringHeaderVO> header2 = report2.getMETRICS_INFO();
        for (BizSpringHeaderVO vo : header2) {
            log.debug("{}", vo.getValue());
        }

        List<BizSpringDataVO> data2 = report2.getDATA();
        for (BizSpringDataVO vo : data2) {
            log.debug("ITEM_NAME = {}", vo.getITEM_NAME());
            log.debug("G1 = {}", vo.getG1());
        }
    }
}
