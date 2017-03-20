package com.sivillage.common.util;

import java.io.UnsupportedEncodingException;
import java.lang.Character.UnicodeBlock;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Data;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sivillage.common.exception.MallException;

public class HFormatUtil {
    private static Logger log = LoggerFactory.getLogger(HFormatUtil.class);

    public static final Format STORE_UPLOAD_PATH = new Format("/contents/system/storeimage/");

    public static final Format BANK_UPLOAD_PATH = new Format("/contents/system/bankimage/");

    public static final Format CHANNEL_UPLOAD_PATH = new Format("/contents/system/channelimage/");

    public static final Format CREDITCARD_UPLOAD_PATH = new Format("/contents/system/creditcardimage/");

    public static final Format GUIDECONTENTS_UPLOAD_PATH = new Format("/contents/system/guidecontents/");

    public static final Format HTML_NOTICE_ATTACH = new Format("/contents/content/html/notice/attach/");
    public static final Format HTML_CUSTOMER_ATTACH_UPLOAD_PATH = new Format("/contents/content/html/customer/attach/%s/");
    public static final Format HTML_NOTICE_ATTACH_UPLOAD_PATH = new Format("/contents/content/html/notice/attach/%s/");
    public static final Format HTML_ENGINE_PAGE = new Format("/contents/content/html/ep/%s/");
    public static final Format FAQ_ATTACH = new Format("/contents/content/system/faq/%s/"); // 확인필요

    public static final Format GOODS = new Format("/contents/goods/%s/");

    public static final Format GOODS_IMG = new Format("/contents/goods/%s/%s__%s__%s_%s_%s.%s");
    public static final Format DAEBAK_GOODS_IMG = new Format("/contents/goods/%s/%s__%s__%s.%s");
    public static final Format GOODS_HISIS = new Format("/contents/goods_hisis/");

    public static final Format GOODS_RESIZEREQ = new Format("/contents/goods/resizereq/");
    public static final Format GOODS_ORIIMAGE = new Format("/contents/goods/oriimage/");
    public static final Format GOODS_TEMPLATE = new Format("/contents/goods/Template/");

    public static final Format GOODS_ATTACH = new Format("/contents/goods/%s/attach/"); // 설명서
    public static final Format GOODS_DETAIL = new Format("/contents/goods/%s/detail/"); // 상세
                                                                                        // 이미지

    public static final Format GOODS_SIZETABLE = new Format("/contents/goods/%s/sizeTable/");
    public static final Format GOODS_CHIP = new Format("/contents/goods/%s/chip/");

    // public static final Format GOODSPROP = new Format("/contents/goods/goodsProp/"); // 'goodsProp'
    // public static final Format GOODSPROP_ADDITION_INFO = new Format("/contents/goods/goodsProp/additionInfo/%s/");
    // public static final Format GOODSPROP_GOODSADDITIONCONTENTS = new
    // Format("/contents/goods/goodsProp/GoodsAdditionContents/");

    public static final Format GOODS_ARTC_SIZELOOKTABLE = new Format("/contents/goods/goodsProp/sizeTable/Article"); // 기준정보관리
                                                                                                                     // >
                                                                                                                     // 품목군상세
                                                                                                                     // -
                                                                                                                     // 사이즈
                                                                                                                     // 조견표
    public static final Format BRAND_IMG_PATH = new Format("/contents/brand/");

    public static final Format DISPLAY = new Format("/contents/content/display/");
    public static final Format DISPLAY_SPECIAL = new Format("/contents/content/display/special/%s/");
    public static final Format DISPLAY_SPEICAL_MC = new Format("/contents/content/display/specialMC/%s/");
    public static final Format DISPLAY_SHOP = new Format("/contents/content/display/shop/%s/");
    public static final Format DISPLAY_SHOP_GNB = new Format("/contents/content/display/shop/menu/%s/");
    public static final Format DISPLAY_SHOP_SUB = new Format("/contents/content/display/sub/%s/");
    public static final Format DISPLAY_CORNER = new Format("/contents/content/display/corner/%s/");
    public static final Format DISPLAY_CORNER_IMAGE = new Format("/contents/content/display/cornerImage/%s/");
    public static final Format DISPLAY_CORNER_CONTENTS_IMAGE_BANNER =
            new Format("/contents/content/display/cornerContents/imageBanner/%s/");
    public static final Format DISPLAY_CORNER_CONTENTS_HTML = new Format("/contents/content/display/cornerContents/html/");
    public static final Format DISPLAY_SUBSTITUTION_TEXT = new Format("/contents/content/display/substitution/");
    public static final Format DISPLAY_MEMBER_COMPANY_AUTH_UPLOAD_PATH = new Format("/contents/content/display/companyAuth/%s/");
    public static final Format UPLOAD_GOODS_COMMENT = new Format("/contents/content/upload/goodsComments/%s/");
    public static final Format UPLOAD_EVENT = new Format("/contents/content/upload/event/%s/");
    public static final Format UPLOAD_GOODS_CHEDITOR = new Format("/contents/content/upload/goods/%s/");
    public static final Format UPLOAD_CATALOG = new Format("/contents/content/upload/catalog/");
    public static final Format UPLOAD_PROMOTION = new Format("/contents/content/upload/promotion/%s/");
    public static final Format UPLOAD_POPUP = new Format("/contents/content/upload/popup/%s/");
    public static final Format DISPLAY_REG_EXCEL_TEMPLATE = new Format("/contents/display/excelTemplate/");

    public static final Format PROMOTION_TEMPLATE = new Format("/contents/promotion/Template/");
    public static final Format EVENT_TEMPLATE = new Format("/contents/event/Template/");

    public static final Format SHOPLINKER_SAVE_REQ = new Format("/shoplinker/%s/req/");
    public static final Format SHOPLINKER_SAVE_RES = new Format("/shoplinker/%s/res/");
    public static final Format SHOPLINKER_WEB_REQ = new Format("/contents/shoplinker/%s/");
    public static final Format EZWEL_SAVE_REQ = new Format("/ezwel/%s/req/");
    public static final Format EZWEL_SAVE_RES = new Format("/ezwel/%s/res/");
    public static final Format EZWEL_WEB_REQ = new Format("/contents/ezwel/%s/");
    public static final Format ALLIANCE_FILE_ORDER = new Format("/alliance/order/");
    public static final Format ALLIANCE_FILE_ADJ = new Format("/alliance/adj/");

    public static final Format UPLOAD_ASSESSMENT = new Format("/contents/content/upload/review/%s/");

    public static final Format DISPLAY_CORNER_HTML_IMAGE_PATH = new Format("/contents/content/upload/display/%s/");
    public static final Format SPECIAL_DISPLAY_HTML_IMAGE_PATH = new Format("/contents/content/upload/special/%s/");
    public static final Format SPECIAL_DISPLAY_HTML_FRONT_IMAGE_PATH = new Format("/contents/content/upload/special/%s/ec/");
    public static final Format SPECIAL_DISPLAY_HTML_MOBILE_IMAGE_PATH = new Format("/contents/content/upload/special/%s/mc/");

    final static String[] SIZE_UNIT = { "B", "KB", "MB", "GB" };
    final static int SIEZ_LEVEL = 1024;

    /**
     * 주어진 템플릿 문자열에 상품번호를 넣는다.
     * 
     * @param template
     * @param goodsId
     * @return
     */
    public static String replaceGoodsNo(String template, String goodsId) {

        return String.format(template, goodsId);
    }

    /**
     * 상품번호에 대한 File Path 를 만들어준다. <br/>
     * 상품번호가 1234567890 일때 2자리 단위로 / 를 붙여 반환하여 12/34/56/78/90/ 을 반환한다.
     * 상품번호는 주어진 객체의 프로터지중 goodsNo, 또는 standardGoodsNo,targetGoodsNo 프로터티를 읽어서 사용한다. 해당 프로퍼티중 여러개게 매칭된다면 뒤쪽에 제시된 프로퍼티를
     * 우선하여 사용한다.
     * 
     * @param goods
     *            상품번호를 가지는 Bean
     * @return 상품번호에 대한 Path
     */

    public static String goodsPath(Object goodsNoValue) {

        String paddedGoodsNo = paddedGoodsNo(goodsNoValue);
        String[] splited = splitInto2Chars(paddedGoodsNo);
        return StringUtils.join(splited, '/');
    }

    /**
     * 주어진 문자열을 2글자 단위로 잘라서 문자열 배열로 반환한다.
     * 
     * @param str
     * @return
     */
    public static String[] splitInto2Chars(String str) {

        String[] splited = str.split("(?<=\\G.{2})");
        return splited;
    }

    final static int GOODS_NO_LENGTH = 10;

    /**
     * 주어진 상품번호(숫자)를 왼쪽이 0으로 채워진(padded) 10글자 짜리 문자열로 반환한다.
     * 
     * @param goodsNoValue
     * @return
     */
    public static String paddedGoodsNo(Object goodsNoValue) {

        long goodsNo = Long.parseLong(String.valueOf(goodsNoValue));
        String paddedGoodsNo = String.format("%0" + GOODS_NO_LENGTH + "d", goodsNo);
        return paddedGoodsNo;
    }

    /**
     * 파일 용량을 단위(Unit) 를 붙인 문자열로 변환하여 반환한다.
     * 
     * @param size
     *            변환할 용량
     * @param points
     *            소숫점 이하 자리수
     * @return
     */
    public static String formatFileSize(long size, int points) {

        float v = size;
        int count = 0;
        while (true) {
            if (v / SIEZ_LEVEL < 1) {
                break;
            }
            count++;
            v = v / SIEZ_LEVEL;
        }
        String unit = SIZE_UNIT[count];
        int multi = 1;
        for (int i = 0; i < points; i++) {
            multi = multi * 10;
        }

        double d = Double.parseDouble(String.format("%." + points + "f", v));
        float result = ((float) ((int) (d * multi))) / multi;
        if (points > 0) {
            return result + unit;
        }
        return ((int) result) + unit;
    }

    public static String splitToSmallPath(String goodsNo) {

        try {
            goodsNo = goodsNo.trim().substring(2);
            StringBuffer buf = new StringBuffer("/");
            buf.append(goodsNo.substring(0, 2)).append("/");
            buf.append(goodsNo.substring(2, 4)).append("/");
            buf.append(goodsNo.substring(4, 6)).append("/");
            buf.append(goodsNo.substring(6, 8)).append("/");
            buf.append(goodsNo.substring(8));

            String GOODS_NO_DIR = buf.toString();
            return GOODS_NO_DIR;
        } catch (Exception e) {
            log.warn("Unable to split into small paths: {}", goodsNo);
            // e.printStackTrace();
            return null;
        }
    }

    public static String removeTrippleSlash(String url) {

        if (url.contains("://")) {
            String scheme = url.substring(0, url.indexOf("://") + 3);
            String remains = url.substring(url.indexOf("://") + 3);
            String removed = scheme + removeDoubleSlash(remains.replaceAll("//", "/"));
            return removed;
        }

        return url.replaceAll("///", "/").replaceAll("//", "/");
    }

    public static String removeDoubleSlash(String url) {

        return url.replaceAll("//", "/");
    }

    /**
     * 겹치지 않는 랜덤한 파일 이름을 생성한다.
     * 
     * @param extension
     *            사용할 확장자
     * @return 파일 명
     */
    public static String randomFileName(String extension) {

        UUID uuid = UUID.randomUUID();
        String name = uuid.toString().replaceAll("-", "");
        return name + extension;
    }

    /**
     * 한글을 2, 기타를 1로 길이를 계산하여 반환한다.
     * 예들들어 "가나다라ABCD" 에서 lengh 4 를 주면 "가나"를 반환한다.
     * 
     * @param src
     * @param length
     * @return
     */
    public static String splitText(String src, int length) {

        StringBuffer buf = new StringBuffer();
        int index = 0;
        for (int i = 0; i < length; i++) {
            if (index >= src.length()) {
                break;
            }
            char ch = src.charAt(index);
            buf.append(ch);
            Character.UnicodeBlock unicodeBlock = Character.UnicodeBlock.of(ch);
            if (UnicodeBlock.HANGUL_SYLLABLES.equals(unicodeBlock) || UnicodeBlock.HANGUL_COMPATIBILITY_JAMO.equals(unicodeBlock)
                    || UnicodeBlock.HANGUL_JAMO.equals(unicodeBlock)) {
                i++;
            }
            index++;

        }

        return buf.toString();
    }

    /**
     * 주어진 문자열을 주어진 maxBytesSize 를 넘지 않는 크기만큼 잘라서 문자열 배열로 반환한다.
     * 
     * @param src
     * @param maxByteSize
     * @param bytesPerCharacter
     * @return
     */
    public static String[] splitUnicodeToBytes(String src, final int maxByteSize, final int bytesPerCharacter) {

        List<String> res = new ArrayList<String>();

        StringBuffer buf = new StringBuffer();
        int index = 0;
        for (int i = 0; i < src.length(); i++) {

            char ch = src.charAt(i);
            int appendix = 1;
            Character.UnicodeBlock unicodeBlock = Character.UnicodeBlock.of(ch);
            if (UnicodeBlock.HANGUL_SYLLABLES.equals(unicodeBlock) || UnicodeBlock.HANGUL_COMPATIBILITY_JAMO.equals(unicodeBlock)
                    || UnicodeBlock.HANGUL_JAMO.equals(unicodeBlock) || UnicodeBlock.HANGUL_JAMO_EXTENDED_A.equals(unicodeBlock)
                    || UnicodeBlock.HANGUL_JAMO_EXTENDED_B.equals(unicodeBlock)) {
                appendix = bytesPerCharacter;
            }
            index += appendix;

            if (index > maxByteSize) {
                res.add(buf.toString());
                index = appendix;
                buf = new StringBuffer();
            }
            buf.append(ch);

        }
        if (buf.length() > 0) {
            res.add(buf.toString());
        }

        return res.toArray(new String[res.size()]);
    }

    /**
     * 한글을 2, 기타를 1로 길이를 계산하여 문자열의 길이를 반환한다.
     * 
     * @param src
     * @return
     */
    public static int getTextLengthKoreanDoubleByte(String src) {

        int length = 0;
        for (int index = 0; index < src.length(); index++) {
            char ch = src.charAt(index);
            Character.UnicodeBlock unicodeBlock = Character.UnicodeBlock.of(ch);
            if (UnicodeBlock.HANGUL_SYLLABLES.equals(unicodeBlock) || UnicodeBlock.HANGUL_COMPATIBILITY_JAMO.equals(unicodeBlock)
                    || UnicodeBlock.HANGUL_JAMO.equals(unicodeBlock)) {
                length++;
            }
            length++;
        }
        return length;
    }

    /**
     * 임시 파일들을 저장하기 위한 임피 파일 폴더 위치를 반환
     * 
     * @return
     */
    public static String pathTempFileFolder() {

        return System.getProperty("java.io.tmpdir");
    }

    /**
     * <신규> 경로 치환 후 tripple slash 제거
     * 
     * @param eventNo
     * @return
     */
    public static String commPathTransposition(String pathString, String substitutionString) {

        return removeTrippleSlash(String.format(pathString, substitutionString));
    }

    /**
     * 자바스크립트에서 만들어내는 Url Encoded String 도 동일한 형태를 만들어 낸다.
     * 
     * @param s
     * @return
     */
    public static String encodeURIComponent(String s) {

        String encodeString = null;
        try {
            // 자바스크립
            encodeString =
                    URLEncoder.encode(s, "UTF-8").replaceAll("\\+", "%20").replaceAll("\\%21", "!").replaceAll("\\%27", "'")
                            .replaceAll("\\%28", "(").replaceAll("\\%29", ")").replaceAll("\\%7E", "~");

        } catch (UnsupportedEncodingException e) {
            throw new MallException(e);
        }

        return encodeString;
    }

    /**
     * 자바스크립트에서 만들어내는 Url Encoded String 도 동일한 형태를 만들어 낸다.
     * 
     * @param s
     * @return
     */
    public static String decodeURIComponent(String s) {

        try {
            return URLDecoder.decode(s, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            throw new MallException(e);
        }

    }

    /**
     * 리사이징된 상품 이미지 경로명을 생성한다.
     * 
     * @param 부가번호2
     */
    public static String goodsResizePath(String 상품번호, String 모델번호, String 부가번호, int width, int height, String 확장자) {

        // 대박딜 상품 이미지는 한장만 사용하며 리사이즈 하지 않음
        if ("D".equals(부가번호)) {
            // log.debug("=====================================================");
            // log.debug("=====================================================");
            // log.debug("상품번호  : {}", 상품번호);
            // log.debug("goodsPath(상품번호)  : {}", goodsPath(상품번호));
            // log.debug("normalizeModelNo(모델번호)  : {}", normalizeModelNo(모델번호));
            // log.debug("부가번호  : {}", 부가번호);
            // log.debug("확장자  : {}", 확장자);
            // log.debug("=====================================================");
            // log.debug("=====================================================");

            return DAEBAK_GOODS_IMG.format(goodsPath(상품번호), 상품번호, normalizeModelNo(모델번호), 부가번호, 확장자);
        } else {
            return GOODS_IMG.format(goodsPath(상품번호), 상품번호, normalizeModelNo(모델번호), 부가번호, width, height, 확장자);
        }

    }

    /*
     * public static String goodsResizePath(GoodsImage img, int w, int h) {
     * return goodsResizePath(img.getGoodsNo(), img.getModelNo(), img.getSubNo(), w, h, img.getExt());
     * }
     */

    /**
     * 모델명을 파일명에 사용할수 있는 문자열로 일반화( normalize) 한다.
     * 
     * @param origModelNo
     * @return
     */
    public static String normalizeModelNo(String origModelNo) {

        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < origModelNo.length(); i++) {
            char ch = origModelNo.charAt(i);
            // 2015.09.07 파일명 구성중 파일명으로 사용불가한 문자열을 제외하고 모두 허용하기로 함. 하이마트의 모델명을 확인한 결과 / 만 있는 것으로 확인됬음(확인자 arc)
            if (ch != '/' && ch != '\\') {
                buf.append(ch);
            }

            // 2015.09.07 이전에는 허용가능한 문자열만 지원했음.
            // if (Pattern.matches("[0-9A-Za-z-_]", String.valueOf(ch))) {
            // buf.append(ch);
            // }
        }
        // 연속된 언더바는 다른곳에서 구분자로 사용하기 때문에 하나의 언더바로 치환한다.
        return buf.toString().replaceAll("_+", "_");
    }

    /**
     * 파일명에서 상품 이미지 리사이즈 규격을 분석해 낸다.
     * 
     * @param fileName
     * @return 규격에 맞지 않는 파일명일 경우 null 을 반환한다.
     */
    public static GoodsImage parseOriginalGoodsImageName(String fileName) {

        Matcher m = originalGoodsImgPattern.matcher(fileName);
        if (m.find() == false) {
            log.warn("Invalid Goods Image name: {}", fileName);
            return null;
        }
        GoodsImage img = new GoodsImage();
        /*
         * img.setGoodsNo(m.group(1));
         * img.setModelNo(m.group(2));
         * img.setSubNo(m.group(3));
         * img.setExt(m.group(4));
         */

        return img;
    }

    @Data
    public static class GoodsImage {

        String goodsNo;
        String modelNo;
        String subNo;
        String ext;
    }

    static Pattern originalGoodsImgPattern = Pattern.compile("([^_]+)__(.+)__(.+)\\.(.+)");

    /**
     * 모델명 추출하기
     * 
     * @param filename
     * @return filename
     */
    public static String getModelName(String filename) {

        String s = "";

        int idx = filename.lastIndexOf("_");

        if (idx > 0) {
            s = filename.substring(0, idx);
        } else {
            s = filename;
        }

        return s;
    }

    /**
     * HISISID 추출하기
     * 
     * @param filename
     * @return filename
     */
    public static String getHisisID(String filename) {

        String s = "";

        int idx = filename.indexOf("_");

        s = filename.substring(0, idx);

        return s;
    }

    /**
     * 이미지 구분 코드 가져오기
     * 
     * @param filename
     * @return filename
     */
    public static String getImageType(String filename, String splitStr) {

        String s = "";

        int idx = filename.lastIndexOf(".");
        s = filename.substring(0, idx);
        int idx2 = s.lastIndexOf(splitStr);
        s = s.substring(idx2 + 1);

        return s;
    }

    /**
     * 이미지 확장자 가져오기
     * 
     * @param filename
     * @return filename
     */
    public static String getFileExt(String filename) {

        String s = "";

        int idx = filename.lastIndexOf(".");
        s = filename.substring(idx + 1);

        return s;
    }
}
