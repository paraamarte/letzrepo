package com.sivillage.common.util;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import com.sivillage.common.constant.ConfigKeys;
import com.sivillage.common.constant.Constants;
import com.sivillage.oldproduct.vo.ProductImageVO;

/**
 * @Class Name : FileNameUtil.java
 * @Description : 파일이름을 관리하기 위한 유틸리티
 *              파일의 확장자를 분리한다던가, 확장자 사이에 값을 넣는다던가 하는 문자열 조정 함수를 관리한다.
 * @author UZEN / BILL
 * @since 2016. 4. 20.
 * @version 1.0
 * @see Copyright(c) 2016 SHINSEGAE INTERNATIONAL. All rights reserved
 */
public class FileNameUtil implements ConfigKeys {

    private static final Logger log = LoggerFactory.getLogger(FileNameUtil.class);

    /*
     * 파일이름 확장자에 timestamp insert
     */
    public static String getNewFileName(String fileName) {
        long timestamp = System.currentTimeMillis();

        int i = fileName.contains(".") ? fileName.lastIndexOf('.') : fileName.length();
        String dstName = fileName.substring(0, i) + "_" + timestamp + fileName.substring(i);
        return dstName;
    }

    /*
     * 파일이름 확장자에 parameterized timestamp insert
     */
    public static String getNewFileName(String fileName, long timestamp) {
        int i = fileName.contains(".") ? fileName.lastIndexOf('.') : fileName.length();
        String dstName = fileName.substring(0, i) + timestamp + fileName.substring(i);
        return dstName;
    }

    /*
     * 파일이름 확장자 확인
     */
    public static String getExtenstionFromFileName(String fileName) {
        int i = fileName.contains(".") ? fileName.lastIndexOf('.') : fileName.length();

        try {
            return fileName.substring(i + 1);
        } catch (Exception e) {
            log.error(e.toString());
            return "";
        }
    }

    /*
     * 해당 문자열이 List안에 있는지 확인하는 함수 / 대소문자 가리지 않는다.
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
     * 디렉토리에 날짜 이름을 붙여주는 함수. 해당 디렉토리도 생성해 준다.
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
            log.error(e.toString());
            // 외부요인에 의하여 생성에 실패할 수 있음
            return null;
        }
    }

    /*
     * 파일이름 앞에 오늘날짜를 붙여서 Mask 만드는 함수 (이미 디렉토리는 생성되었다고 가정)
     */
    public static String addDateMaskForFileName(String fileName)
    {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(now) + "/" + fileName;
    }

    /*
     * getAbsoluteUrlHeadPathFromRequest : Request로부터 Context를 추출하는 함수
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
     * getFileNameFromPath : FullPath에서 파일이름을 추출하는 함수
     */
    public static String getFileNameFromPath(String path)
    {
        File file = new File(path);
        return file.getName();
    }

    /*
     * 파일이름 확장자에 timestamp insert
     */
    public static String getRandomFileName(String fileName, long timestamp) {
        int i = fileName.contains(".") ? fileName.lastIndexOf('.') : fileName.length();
        String dstName = timestamp + fileName.substring(i);
        return dstName;
    }

    public static List<String> getFileUrlListExist(String productNo, int size, String imgId, String scheme) {
        List<String> fileList = new ArrayList<String>();

        String baseUrl = ConfigUtil.getString(FILE_URL_CDN);
        String realPath = ConfigUtil.getString(FILE_PATH_REAL);
        String rootPath = ConfigUtil.getString(FILE_PATH_ROOT);
        String productPath = ConfigUtil.getString(FILE_PATH_PRODUCT);

        // String outputPath = scheme + realPath + rootPath + productPath +
        // FileNameUtil.getPathFromProductNo(productNo);
        String outputPath = realPath + rootPath + productPath + FileNameUtil.getPathFromProductNo(productNo);
        // 최대 노출 이미지 20개로 수정
        for (int i = 0; i < 20; i++) {
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

            // 존재하면 리스트 추가
            File checkFile = new File(fileNameWithPath);
            if (checkFile.exists()) {
                fileList.add(scheme + baseUrl + rootPath + productPath + FileNameUtil.getPathFromProductNo(productNo) + tailPath);
            }
        }

        return fileList;
    }

    /**
     * imgId 유무에 따라 존재하는 이미지 목록을 생성한다
     * 
     * <pre>
     * 1. 대표 사이즈 이미지 정보 생성 (필수 전달 값 : productNo, size)
     * 2. 대표 사이즈 이미지 ID 별, 이미지 사이즈 정보 전체 생성 (필수 전달 값 : productNo, imgId)
     * </pre>
     * 
     * @param productNo
     * @param size
     * @param imgId
     * @return
     */
    public static List<ProductImageVO> getFileUrlListExist(String productNo, int size, String imgId) {
        List<ProductImageVO> productImageList = new ArrayList<ProductImageVO>();

        String baseUrl = ConfigUtil.getString(FILE_URL_CDN);
        String realPath = ConfigUtil.getString(FILE_PATH_REAL);
        String rootPath = ConfigUtil.getString(FILE_PATH_ROOT);
        String productPath = ConfigUtil.getString(FILE_PATH_PRODUCT);

        String productSubPath = FileNameUtil.getPathFromProductNo(productNo);
        String outputPath = realPath + rootPath + productPath + productSubPath;
        String fileType = "." + Constants.IMAGE_FILE_TYPE_PNG;

        // 리사이징 사이즈 정보 리스트 추출
        String[] sizeList = getProductResizeList();

        if (StringUtils.isEmpty(imgId)) {
            /* ***************************************************************************************
             * 대표 사이즈 이미지 정보 생성
             * **************************************************************************************
             */
            for (int i = 0; i < 20; i++) {
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

                // 존재하면 리스트 추가
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
             * 대표 사이즈 이미지 ID 별, 이미지 사이즈 정보 전체 생성
             * **************************************************************************************
             */
            // 확장자를 제외한 이미지 Id 추출
            imgId = imgId.substring(0, imgId.lastIndexOf("."));
            imgId = imgId.substring(0, imgId.lastIndexOf("_"));

            int idx = 0;
            for (int i = 0; i < sizeList.length + 1; i++) {
                String fileNameWithPath = "";
                String tailPath = "";
                String imgNm = "";
                String imgSize = "";

                if (i == 0) {
                    // 이미지 default (2000*2000)
                    imgNm = imgId + fileType;
                    imgSize = Constants.PRODUCT_IMAGE_SIZE_2000x2000;
                    ;

                } else {
                    // 리사이징 이미지 정보
                    imgSize = sizeList[idx];
                    imgNm = imgId + "_" + imgSize + fileType;
                    idx++;
                }

                tailPath = "/" + imgNm;
                fileNameWithPath = outputPath + tailPath; // 존재 유무 체크 파일 정보

                // 존재하면 리스트 추가
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

            // 이미지 사이즈 별로 오름차순 정렬
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
     * 상품 기본이미지 존재 여부
     * 
     * @param productNo
     * @param size
     * @return
     */
    public static String isProductDefaultImageExist(String productNo, String size) {

        String realPath = ConfigUtil.getString(FILE_PATH_REAL);
        String rootPath = ConfigUtil.getString(FILE_PATH_ROOT);
        String productPath = ConfigUtil.getString(FILE_PATH_PRODUCT);

        String outputPath = realPath + rootPath + productPath + FileNameUtil.getPathFromProductNo(productNo);

        String tailPath = "/" + productNo + "_" + size + ".jpg";
        String fileNameWithPath = outputPath + tailPath;

        File checkFile = new File(fileNameWithPath);

        if (checkFile.exists()) {
            return fileNameWithPath;
        } else {
            return "";
        }
    }

    /**
     * product no로 이미지 full 경로 반환
     * 
     * @param productNo
     * @param imgPreFix
     * @return
     */
    public static String getProductDefaultImageUrl(String productNo, String imgPreFix) {
        // TODO: no image url 및 파일명 결정 필요

        String baseUrl = ConfigUtil.getString(FILE_URL_CDN);
        String rootPath = ConfigUtil.getString(FILE_PATH_ROOT); // files
        String productPath = ConfigUtil.getString(FILE_PATH_PRODUCT); // product
        String[] imgSizes = ConfigUtil.getString(FILE_RESIZE_PRODUCT).split(","); // 이미지 사이즈

        String noImgNm = "bg_noimage.png"; // 대체이미지 파일명
        String noImgUrl = baseUrl + rootPath + productPath + "/" + noImgNm;// 대체 이미지 경로
        String productImgUrl = ""; // 상품 default 이미지 full 경로

        // config에 설정된 이미지 사이즈와 param으로 받은 이미지 사이즈와 비교하여 없으면 default로 122 설정
        if (imgPreFix == null || "".equals(imgPreFix) || ArrayUtils.indexOf(imgSizes, imgPreFix) < 0) {
            imgPreFix = "122";
        }

        StopWatch sw = new StopWatch();
        sw.start("make image path and exit check");
        if (StringUtils.isEmpty(productNo)) {
            return noImgUrl;
        } else {
            productImgUrl = baseUrl + rootPath + productPath + getPathFromProductNo(productNo) + "/" + productNo + getProductImgPreFix(imgPreFix) + ".png";
        }
        try {
            URL url = new URL(productImgUrl);
            URLConnection con = url.openConnection();
            HttpURLConnection exitCode = (HttpURLConnection) con;
            if (exitCode.getResponseCode() != 200) { // 존재
                productImgUrl = noImgUrl;
            }
        } catch (Exception e) {
            log.error(e.toString());
            return noImgUrl;
        }

        sw.stop();
        log.debug(sw.prettyPrint());

        return productImgUrl;
    }

    /**
     * 이미지 prefix 반환(사이즈)
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

    public static void main(String[] args) {
        log.debug(">>> " + FileNameUtil.getNewFileName("test.xml"));

    }

    private static String[] getProductResizeList() {
        String propResize = ConfigUtil.getString(FILE_RESIZE_PRODUCT);
        return propResize.split(",");
    }

    /**
     * ImgSize 오름차순
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
     * ImgSize 내림차순
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