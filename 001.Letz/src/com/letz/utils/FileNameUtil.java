package com.letz.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.codehaus.plexus.util.FileUtils;

import com.sivillage.constant.Constants;
import com.sivillage.product.vo.ProductImageVO;
import com.sivillage.utils.message.MessageUtil;

/**
 * FileNameUtil : �뙆�씪�씠由꾩쓣 愿�由ы븯湲� �쐞�븳 �쑀�떥由ы떚
 * �뙆�씪�쓽 �솗�옣�옄瑜� 遺꾨━�븳�떎�뜕媛�, �솗�옣�옄 �궗�씠�뿉 媛믪쓣 �꽔�뒗�떎�뜕媛� �븯�뒗 臾몄옄�뿴 議곗젙 �븿�닔瑜� 愿�由ы븳�떎.
 * 
 * @author mass / 2014-04-28
 */
public class FileNameUtil {

    /*
     * �뙆�씪�씠由� �솗�옣�옄�뿉 timestamp insert
     */
    public static String getNewFileName(String fileName) {
        long timestamp = System.currentTimeMillis();

        int i = fileName.contains(".") ? fileName.lastIndexOf('.') : fileName.length();
        String dstName = fileName.substring(0, i) + "_" + timestamp + fileName.substring(i);
        return dstName;
    }

    /*
     * �뙆�씪�씠由� �솗�옣�옄�뿉 parameterized timestamp insert
     */
    public static String getNewFileName(String fileName, long timestamp) {
        int i = fileName.contains(".") ? fileName.lastIndexOf('.') : fileName.length();
        String dstName = fileName.substring(0, i) + timestamp + fileName.substring(i);
        return dstName;
    }

    /*
     * �뙆�씪�씠由� �솗�옣�옄 �솗�씤
     */
    public static String getExtenstionFromFileName(String fileName) {
        int i = fileName.contains(".") ? fileName.lastIndexOf('.') : fileName.length();
        try
        {
            return fileName.substring(i + 1);
        } catch (Exception e)
        {
            return "";
        }
    }

    /*
     * �빐�떦 臾몄옄�뿴�씠 List�븞�뿉 �엳�뒗吏� �솗�씤�븯�뒗 �븿�닔 / ���냼臾몄옄 媛�由ъ� �븡�뒗�떎.
     */
    public static boolean isIncludeString(List<String> requiredExtensionList,
            String extension) {

        for (String oneString : requiredExtensionList)
        {
            if (oneString.equalsIgnoreCase(extension))
            {
                return true;
            }
        }

        return false;
    }

    /*
     * �뵒�젆�넗由ъ뿉 �궇吏� �씠由꾩쓣 遺숈뿬二쇰뒗 �븿�닔. �빐�떦 �뵒�젆�넗由щ룄 �깮�꽦�빐 以��떎.
     */
    public static String addDateMaskLocation(String uploadDirectory)
    {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String resultDirectory = uploadDirectory + sdf.format(now) + "/";

        try
        {
            File targetDirectory = new File(resultDirectory);

            if (targetDirectory.exists())
            {
                return resultDirectory;
            }

            boolean result = targetDirectory.mkdirs();
            if (!result)
            {
                return null;
            } else
            {
                return resultDirectory;
            }
        } catch (Exception e)
        {
            // �쇅遺��슂�씤�뿉 �쓽�븯�뿬 �깮�꽦�뿉 �떎�뙣�븷 �닔 �엳�쓬
            return null;
        }
    }

    /*
     * �뙆�씪�씠由� �븵�뿉 �삤�뒛�궇吏쒕�� 遺숈뿬�꽌 Mask 留뚮뱶�뒗 �븿�닔 (�씠誘� �뵒�젆�넗由щ뒗 �깮�꽦�릺�뿀�떎怨� 媛��젙)
     */
    public static String addDateMaskForFileName(String fileName)
    {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(now) + "/" + fileName;
    }

    /*
     * getAbsoluteUrlHeadPathFromRequest : Request濡쒕��꽣 Context瑜� 異붿텧�븯�뒗 �븿�닔
     */
    public static String getAbsoluteUrlHeadPathFromRequest(HttpServletRequest request) {

        String hostInfo = request.getServerName();
        int hostPort = request.getServerPort();
        String uri = request.getContextPath();

        String protocolType = "http://";

        if (request.isSecure())
        {
            protocolType = "https://";
        }

        return protocolType + hostInfo + ":" + hostPort + uri;
    }

    /*
     * getFileNameFromPath : FullPath�뿉�꽌 �뙆�씪�씠由꾩쓣 異붿텧�븯�뒗 �븿�닔
     */
    public static String getFileNameFromPath(String path)
    {
        File file = new File(path);
        return file.getName();
    }

    /*
     * �뙆�씪�씠由� �솗�옣�옄�뿉 timestamp insert
     */
    public static String getRandomFileName(String fileName, long timestamp) {
        int i = fileName.contains(".") ? fileName.lastIndexOf('.') : fileName.length();
        String dstName = timestamp + fileName.substring(i);
        return dstName;
    }

    public static List<String> getFileUrlListExist(String productNo, int size) {
        List<String> fileList = new ArrayList<String>();

        String baseUrl = MessageUtil.getMessage("config.files.cdn.url");
        String realPath = MessageUtil.getMessage("config.files.real.path");
        String rootPath = MessageUtil.getMessage("config.files.root.path");
        String productPath = MessageUtil.getMessage("config.files.product.path");

        String outputPath = realPath + rootPath + productPath + FileNameUtil.getPathFromProductNo(productNo);

        for (int i = 0; i < 11; i++) {
            String fileNameWithPath = "";
            String tailPath = "";
            if (i == 0) {
                if (size > 0)
                    tailPath = "/" + productNo + "_" + size + ".jpg";
                else
                    tailPath = "/" + productNo + ".jpg";
            } else {
                if (size > 0)
                    tailPath = "/" + productNo + "_" + i + "_" + size + ".jpg";
                else
                    tailPath = "/" + productNo + "_" + i + ".jpg";

            }

            fileNameWithPath = outputPath + tailPath;

            // 議댁옱�븯硫� 由ъ뒪�듃 異붽�
            File checkFile = new File(fileNameWithPath);
            if (checkFile.exists()) {
                fileList.add(baseUrl + rootPath + productPath + FileNameUtil.getPathFromProductNo(productNo) + tailPath);
            }
        }

        return fileList;
    }

    /**
     * �긽�뭹 湲곕낯�씠誘몄� 議댁옱 �뿬遺�
     * 
     * @param productNo
     * @param size
     * @return
     */
    public static boolean isProductDefaultImageExist(String productNo, String size) {

        String realPath = MessageUtil.getMessage("config.files.real.path");
        String rootPath = MessageUtil.getMessage("config.files.root.path");
        String productPath = MessageUtil.getMessage("config.files.product.path");

        String outputPath = realPath + rootPath + productPath + FileNameUtil.getPathFromProductNo(productNo);

        String tailPath = "/" + productNo + "_" + size + ".jpg";
        String fileNameWithPath = outputPath + tailPath;

        File checkFile = new File(fileNameWithPath);

        if (checkFile.exists()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * imgId �쑀臾댁뿉 �뵲�씪 議댁옱�븯�뒗 �씠誘몄� 紐⑸줉�쓣 �깮�꽦�븳�떎
     * 
     * <pre>
     * 1. ���몴 �궗�씠利� �씠誘몄� �젙蹂� �깮�꽦 (�븘�닔 �쟾�떖 媛� : productNo, size)
     * 2. ���몴 �궗�씠利� �씠誘몄� ID 蹂�, �씠誘몄� �궗�씠利� �젙蹂� �쟾泥� �깮�꽦 (�븘�닔 �쟾�떖 媛� : productNo, imgId)
     * </pre>
     * 
     * @param productNo
     * @param size
     * @param imgId
     * @return
     */
    public static List<ProductImageVO> getFileUrlListExist(String productNo, int size, String imgId) {
        List<ProductImageVO> productImageList = new ArrayList<ProductImageVO>();

        String baseUrl = MessageUtil.getMessage("config.files.cdn.url");
        String realPath = MessageUtil.getMessage("config.files.real.path");
        String rootPath = MessageUtil.getMessage("config.files.root.path");
        String productPath = MessageUtil.getMessage("config.files.product.path");

        String productSubPath = FileNameUtil.getPathFromProductNo(productNo);
        String outputPath = realPath + rootPath + productPath + productSubPath;
        String fileType = "." + Constants.IMAGE_FILE_TYPE_JPG;

        String NU = MessageUtil.getMessage("config.files.product.nucut.path");
        String MO = MessageUtil.getMessage("config.files.product.mocut.path");

        // 由ъ궗�씠吏� �궗�씠利� �젙蹂� 由ъ뒪�듃 異붿텧
        String[] sizeList = getProductResizeList();

        if (StringUtils.isNull(imgId)) {
            /* ***************************************************************************************
             * ���몴 �궗�씠利� �씠誘몄� �젙蹂� �깮�꽦
             * **************************************************************************************
             */

            // �늻�겮而� 議댁옱�븯硫� 由ъ뒪�듃 異붽� **************************************************************************************
            String imgNm_nu = productNo + "_NU.jpg";
            String tailPath_nu = "/" + imgNm_nu;
            String fileNameWithPath_nu = outputPath + NU + tailPath_nu;

            File checkFile1 = new File(fileNameWithPath_nu);
            if (checkFile1.exists()) {
                ProductImageVO productImageVO = new ProductImageVO();
                String imgPath_nu = productPath + productSubPath + NU + tailPath_nu;
                String imgUrl_nu = baseUrl + rootPath + productPath + productSubPath + NU + tailPath_nu;

                productImageVO.setImgId(imgNm_nu);
                productImageVO.setImgNm(imgNm_nu);
                productImageVO.setImgPath(imgPath_nu);
                productImageVO.setImgSize("");
                productImageVO.setImgUrl(imgUrl_nu);

                productImageList.add(productImageVO);
            }

            // 紐⑤뜽而� 議댁옱�븯硫� 由ъ뒪�듃 異붽� **************************************************************************************
            String imgNm_mo = productNo + "_MO.jpg";
            String tailPath_mo = "/" + imgNm_mo;
            String fileNameWithPath_mo = outputPath + MO + tailPath_mo;

            File checkFile2 = new File(fileNameWithPath_mo);
            if (checkFile2.exists()) {
                ProductImageVO productImageVO = new ProductImageVO();
                String imgPath_mo = productPath + productSubPath + MO + tailPath_mo;
                String imgUrl_mo = baseUrl + rootPath + productPath + productSubPath + MO + tailPath_mo;

                productImageVO.setImgId(imgNm_mo);
                productImageVO.setImgNm(imgNm_mo);
                productImageVO.setImgPath(imgPath_mo);
                productImageVO.setImgSize("");
                productImageVO.setImgUrl(imgUrl_mo);

                productImageList.add(productImageVO);
            }

            for (int i = 0; i < 11; i++) {
                String fileNameWithPath = "";
                String tailPath = "";
                String imgNm = "";

                if (i == 0) {
                    if (size > 0)
                        imgNm = productNo + "_" + size + fileType;
                    else
                        imgNm = productNo + fileType;
                } else {
                    if (size > 0)
                        imgNm = productNo + "_" + i + "_" + size + fileType;
                    else
                        imgNm = productNo + "_" + i + fileType;
                }

                tailPath = "/" + imgNm;
                fileNameWithPath = outputPath + tailPath;

                // 議댁옱�븯硫� 由ъ뒪�듃 異붽�
                File checkFile = new File(fileNameWithPath);
                if (checkFile.exists()) {
                    ProductImageVO productImageVO = new ProductImageVO();
                    String imgPath = productPath + productSubPath + tailPath;
                    String imgUrl = baseUrl + rootPath + productPath + productSubPath + tailPath;

                    productImageVO.setImgId(imgNm);
                    productImageVO.setImgNm(imgNm);
                    productImageVO.setImgPath(imgPath);
                    productImageVO.setImgSize(String.valueOf(size));
                    productImageVO.setImgUrl(imgUrl);

                    productImageList.add(productImageVO);
                }
            }

        } else {
            /* ***************************************************************************************
             * ���몴 �궗�씠利� �씠誘몄� ID 蹂�, �씠誘몄� �궗�씠利� �젙蹂� �쟾泥� �깮�꽦
             * **************************************************************************************
             */
            // �솗�옣�옄瑜� �젣�쇅�븳 �씠誘몄� Id 異붿텧
            imgId = imgId.substring(0, imgId.lastIndexOf("."));
            imgId = imgId.substring(0, imgId.lastIndexOf("_"));

            // �늻�겮而� 議댁옱�븯硫� 由ъ뒪�듃 異붽� **************************************************************************************
            String imgNm_nu = imgId + "_NU.jpg";
            String tailPath_nu = "/" + imgNm_nu;
            String fileNameWithPath_nu = outputPath + NU + tailPath_nu;

            File checkFile1 = new File(fileNameWithPath_nu);
            if (checkFile1.exists()) {
                ProductImageVO productImageVO = new ProductImageVO();
                String imgPath_nu = productPath + productSubPath + NU + tailPath_nu;
                String imgUrl_nu = baseUrl + rootPath + productPath + productSubPath + NU + tailPath_nu;

                productImageVO.setImgId(imgNm_nu);
                productImageVO.setImgNm(imgNm_nu);
                productImageVO.setImgPath(imgPath_nu);
                productImageVO.setImgSize("");
                productImageVO.setImgUrl(imgUrl_nu);

                productImageList.add(productImageVO);
            }

            // 紐⑤뜽而� 議댁옱�븯硫� 由ъ뒪�듃 異붽� **************************************************************************************
            String imgNm_mo = imgId + "_MO.jpg";
            String tailPath_mo = "/" + imgNm_mo;
            String fileNameWithPath_mo = outputPath + MO + tailPath_mo;

            File checkFile2 = new File(fileNameWithPath_mo);
            if (checkFile2.exists()) {
                ProductImageVO productImageVO = new ProductImageVO();
                String imgPath_mo = productPath + productSubPath + MO + tailPath_mo;
                String imgUrl_mo = baseUrl + rootPath + productPath + productSubPath + MO + tailPath_mo;

                productImageVO.setImgId(imgNm_mo);
                productImageVO.setImgNm(imgNm_mo);
                productImageVO.setImgPath(imgPath_mo);
                productImageVO.setImgSize("");
                productImageVO.setImgUrl(imgUrl_mo);

                productImageList.add(productImageVO);
            }

            int idx = 0;
            for (int i = 0; i < sizeList.length + 1; i++) {
                String fileNameWithPath = "";
                String tailPath = "";
                String imgNm = "";
                String imgSize = "";

                if (i == 0) {
                    // �씠誘몄� default (2000*2000)
                    imgNm = imgId + fileType;
                    imgSize = Constants.PRODUCT_IMAGE_SIZE_2000x2000;
                    ;

                } else {
                    // 由ъ궗�씠吏� �씠誘몄� �젙蹂�
                    imgSize = sizeList[idx];
                    imgNm = imgId + "_" + imgSize + fileType;
                    idx++;
                }

                tailPath = "/" + imgNm;
                fileNameWithPath = outputPath + tailPath; // 議댁옱 �쑀臾� 泥댄겕 �뙆�씪 �젙蹂�

                // 議댁옱�븯硫� 由ъ뒪�듃 異붽�
                File checkFile = new File(fileNameWithPath);
                if (checkFile.exists()) {
                    ProductImageVO productImageVO = new ProductImageVO();
                    String imgPath = productPath + productSubPath + tailPath;
                    String imgUrl = baseUrl + rootPath + productPath + productSubPath + tailPath;

                    productImageVO.setImgId(imgNm);
                    productImageVO.setImgNm(imgNm);
                    productImageVO.setImgPath(imgPath);
                    productImageVO.setImgSize(String.valueOf(imgSize));
                    productImageVO.setImgUrl(imgUrl);

                    productImageList.add(productImageVO);
                }
            }

            // �씠誘몄� �궗�씠利� 蹂꾨줈 �삤由꾩감�닚 �젙�젹
            if (productImageList.size() > 1) {
                Collections.sort(productImageList, new ImgSizeAscCompare());
            }

        }

        return productImageList;
    }

    public static String getPathFromProductNo(String productNo) {
        StringBuilder path = new StringBuilder();

        path.append("/");
        path.append(productNo.substring(0, 2));
        path.append("/");
        path.append(productNo.substring(2, 7));
        path.append("/");
        path.append(productNo.substring(7, 9));
        path.append("/");
        path.append(productNo.substring(9, 11));
        path.append("/");
        path.append(productNo.substring(11));

        return path.toString();
    }

    public static String getPathFromFreebNo(String freebNo) {
        StringBuilder path = new StringBuilder();

        path.append("/");
        path.append(freebNo.substring(0, 2));
        path.append("/");
        path.append(freebNo.substring(2, 3));
        path.append("/");
        path.append(freebNo.substring(3, 5));
        path.append("/");
        if (freebNo.length() > 7) {
            path.append(freebNo.substring(5, 7));
        } else {
            path.append(freebNo.substring(5));
        }

        return path.toString();
    }

    /**
     * product no濡� �씠誘몄� full 寃쎈줈 諛섑솚
     * 
     * @param productNo
     * @param imgPreFix
     * @return
     */
    public static String getProductDefaultImageUrl(String productNo, String imgPreFix) {
        // TODO: no image url 諛� �뙆�씪紐� 寃곗젙 �븘�슂

        String baseUrl = MessageUtil.getMessage("config.files.cdn.url");
        String rootPath = MessageUtil.getMessage("config.files.root.path"); // files
        String productPath = MessageUtil.getMessage("config.files.product.path"); // product
        String[] imgSizes = MessageUtil.getMessage("config.files.product.resize").split(","); // �씠誘몄� �궗�씠利�

        String noImgNm = "bg_noimage.png"; // ��泥댁씠誘몄� �뙆�씪紐�
        String noImgUrl = baseUrl + rootPath + productPath + "/" + noImgNm;// ��泥� �씠誘몄� 寃쎈줈
        String productImgUrl = ""; // �긽�뭹 default �씠誘몄� full 寃쎈줈

        // config�뿉 �꽕�젙�맂 �씠誘몄� �궗�씠利덉� param�쑝濡� 諛쏆� �씠誘몄� �궗�씠利덉� 鍮꾧탳�븯�뿬 �뾾�쑝硫� default濡� 122 �꽕�젙
        if (imgPreFix == null || "".equals(imgPreFix) || ArrayUtils.indexOf(imgSizes, imgPreFix) < 0) {
            imgPreFix = "122";
        }

        // StopWatch sw = new StopWatch();
        // sw.start("make image path and exit check");
        if (StringUtils.isNull(productNo)) {
            return noImgUrl;
        } else {
            productImgUrl = baseUrl + rootPath + productPath + getPathFromProductNo(productNo) + "/" + productNo + getProductImgPreFix(imgPreFix) + ".jpg";
        }
        // try {
        // URL url = new URL(productImgUrl);
        // URLConnection con = url.openConnection();
        // HttpURLConnection exitCode = (HttpURLConnection) con;
        // if (exitCode.getResponseCode() != 200) { // 議댁옱
        // productImgUrl = noImgUrl;
        // }
        // // sw.stop();
        // } catch (Exception e) {
        // System.out.print(e);
        // return noImgUrl;
        // }
        // System.out.println(sw.prettyPrint());
        return productImgUrl;
    }

    /**
     * �씠誘몄� prefix 諛섑솚(�궗�씠利�)
     * 
     * @param imgPreFix
     * @return
     */
    public static String getProductImgPreFix(String imgPreFix) {
        int _imgPreFix = 0;
        try {
            _imgPreFix = Integer.parseInt(imgPreFix);
        } catch (Exception e) {
            _imgPreFix = 0;
        }
        switch (_imgPreFix) {
        case 0:
            return "";
        case 50:
            return "_50";
        case 60:
            return "_60";
        case 72:
            return "_72";
        case 80:
            return "_80";
        case 122:
            return "_122";
        case 150:
            return "_150";
        case 160:
            return "_160";
        case 224:
            return "_224";
        case 260:
            return "_260";
        case 274:
            return "_274";
        case 300:
            return "_300";
        case 480:
            return "_480";
        case 500:
            return "_500";
        case 2000:
            return "_2000";
        default:
            return "_122";
        }
    }

    public static List<String> getPhysicalImageFilePath(String productNo) {
        String filesRealPath = MessageUtil.getMessage("config.files.real.path"); // => /nas_img/sivillage
        String filesRootPath = MessageUtil.getMessage("config.files.root.path"); // => /files
        String filesProductPath = MessageUtil.getMessage("config.files.product.path"); // => /product
        String nuPath = MessageUtil.getMessage("config.files.product.nucut.path"); // => /NU
        String moPath = MessageUtil.getMessage("config.files.product.mocut.path");// => /MO
        List<String> physicalImageFilePathList = new ArrayList<String>();

        if (StringUtils.isBlank(productNo)) {
            return physicalImageFilePathList;
        }

        String productNoPath = getPathFromProductNo(productNo);

        if (StringUtils.isBlank(productNoPath)) {
            return physicalImageFilePathList;
        }
        // 1.�씪諛� �씠誘몄� 寃쎈줈
        physicalImageFilePathList.add(String.format("%s%s%s%s/%s.jpg", filesRealPath, filesRootPath, filesProductPath, productNoPath, productNo));
        // 2.detail 而� �씠誘몄� 寃쎈줈(�떎�젣 �뙆�씪 寃쎈줈 �쑀臾대�� �븣吏� 紐삵븯湲� �븣臾몄뿉 理쒖냼 10媛쒖젙�룄�쓽 detail 而� 寃쎈줈瑜� 誘몃━ �깮�꽦)
        for (int i = 1; i <= 10; i++) {
            physicalImageFilePathList.add(String.format("%s%s%s%s/%s_%d.jpg", filesRealPath, filesRootPath, filesProductPath, productNoPath, productNo, i));
        }
        // 3.紐⑤뜽而� �씠誘몄� 寃쎈줈 �깮�꽦
        physicalImageFilePathList.add(String.format("%s%s%s%s%s/%s_MO.jpg", filesRealPath, filesRootPath, filesProductPath, productNoPath, moPath, productNo));
        // 4.�늻�겮而� �씠誘몄� 寃쎈줈 �깮�꽦
        physicalImageFilePathList.add(String.format("%s%s%s%s%s/%s_NU.jpg", filesRealPath, filesRootPath, filesProductPath, productNoPath, nuPath, productNo));

        return physicalImageFilePathList;
    }

    public static String getTemporaryZipFilePath(String pathPostfix) {
        String filesTempPath = MessageUtil.getMessage("config.files.temp.path"); // => /data/sivillagebo
        String filesRootPath = MessageUtil.getMessage("config.files.root.path"); // => /files
        String filesProductPath = MessageUtil.getMessage("config.files.product.path"); // => /product

        FileUtils.mkdir(filesTempPath + filesRootPath + filesProductPath);
        String tempPath = String.format("%s%s%s/%s_%s.zip", filesTempPath, filesRootPath, filesProductPath, DateUtils.getDate(DateUtils.DATE_FORMAT14).substring(4), pathPostfix);
        // FileUtils.mkdir(tempPath);

        return tempPath;
    }

    public static String getTempProductBasePath() {
        String filesTempPath = MessageUtil.getMessage("config.files.temp.path"); // => /data/sivillagebo
        String filesRootPath = MessageUtil.getMessage("config.files.root.path"); // => /files
        String filesProductPath = MessageUtil.getMessage("config.files.product.path"); // => /product

        return filesTempPath + filesRootPath + filesProductPath;
    }

    public static void main(String[] args) {
        System.out.println(">>> " + FileNameUtil.getNewFileName("test.xml"));

    }

    private static String[] getProductResizeList() {
        String propResize = MessageUtil.getMessage("config.files.product.resize");
        return propResize.split(",");
    }

    /**
     * ImgSize �삤由꾩감�닚
     */
    static class ImgSizeAscCompare implements Comparator<ProductImageVO> {
        @Override
        public int compare(ProductImageVO arg0, ProductImageVO arg1) {
            int imgSize0 = Integer.parseInt(arg0.getImgSize());
            int imgSize1 = Integer.parseInt(arg1.getImgSize());

            return imgSize0 < imgSize1 ? -1 : imgSize0 > imgSize1 ? 1 : 0;
        }
    }

    /**
     * ImgSize �궡由쇱감�닚
     */
    static class ImgSizeDescCompare implements Comparator<ProductImageVO> {
        @Override
        public int compare(ProductImageVO arg0, ProductImageVO arg1) {
            int imgSize0 = Integer.parseInt(arg0.getImgSize());
            int imgSize1 = Integer.parseInt(arg1.getImgSize());

            return imgSize0 > imgSize1 ? -1 : imgSize0 < imgSize1 ? 1 : 0;
        }
    }

}