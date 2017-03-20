package com.sivillage.common.util;

import java.lang.Character.UnicodeBlock;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MaskingUtil {
    private static Logger log = LoggerFactory.getLogger(MaskingUtil.class);
    
    static Random random = new Random(System.currentTimeMillis());
    static char[] encrytTable = new char[] { '!', '@', '#', '$', '%', '^', '&', '*', '~' };

    /**
     * 성명 마스킹
     * 1) 국문: 성명이 두자인 경우, 첫번째 글자를 제외한 마스킹. 성명이 세자 이상인 경우, 이름의 첫번째 글자와 마지막 글자를 제외한 마스킹
     * 2) 영문: 영문명의 경우, 시작 5번쨰 글자부터 전무 마스킹
     * 
     * @param string
     * @return
     */

    public static String markingName(String name) {
        if (name == null) {
            return "";
        }

        if ("-".equals(name)) {
            return name;
        }

        char ch = name.charAt(0);
        Character.UnicodeBlock unicodeBlock = Character.UnicodeBlock.of(ch);
        if (isHangul(unicodeBlock)) {
            if (name.length() == 2) {
                return name.charAt(0) + "*";
            }

            StringBuffer buf = new StringBuffer();
            buf.append(name.charAt(0));
            for (int i = 1; i < name.length() - 1; i++) {
                buf.append('*');
            }
            buf.append(name.charAt(name.length() - 1));

            return buf.toString();
        } else {
            if (name.length() <= 4) {
                return name;
            }

            StringBuffer buf = new StringBuffer();
            buf.append(name.substring(0, 4));
            for (int i = 4; i < name.length(); i++) {
                buf.append(name.charAt(i) == ' ' ? ' ' : '*');
            }
            return buf.toString();
        }
    }

    private static boolean isHangul(UnicodeBlock unicodeBlock) {
        if (UnicodeBlock.HANGUL_SYLLABLES.equals(unicodeBlock) || UnicodeBlock.HANGUL_COMPATIBILITY_JAMO.equals(unicodeBlock)
                || UnicodeBlock.HANGUL_JAMO.equals(unicodeBlock)) {
            return true;
        }

        return false;
    }

    /**
     * 회원ID를 뒤의 3자리를 마킹한다.
     * 
     * @param string
     * @return
     */
    public static String markingMemberId(String memberId) {
        if (memberId == null || memberId.length() < 2) {
            return memberId;
        }

        if ("-".equals(memberId)) {
            return memberId;
        }

        if (memberId.length() < 4) {
            String marking = "";
            for (int i = 0; i < (memberId.length() - 1); i++) {
                marking += "*";
            }
            return memberId.charAt(0) + marking;
        }

        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < memberId.length(); i++) {
            if (i < (memberId.length() - 3)) {
                buf.append(memberId.charAt(i));
            } else {
                buf.append('*');
            }
        }

        return buf.toString();
    }

    /**
     * 생년월일에서 숫자를 마킹한다.
     * 
     * @param string
     * @return
     */
    public static String markingBirthday(String birthday) {
        if (birthday == null) {
            return "";
        }

        if ("-".equals(birthday)) {
            return birthday;
        }

        return birthday.replaceAll("\\d", "*");
    }

    /**
     * 전화번호 마스킹
     * 국번 4자리 또는 국번 뒤 3자리
     * 
     * @param string
     * @return
     */
    public static String markingPhone(String phone) {
        if (phone == null) {
            return "";
        }

        if ("-".equals(phone)) {
            return phone;
        }

        if (phone.contains("-") == false) {
            return phone.replaceAll("\\d", "\\*");
        }

        String[] tokens = phone.split("-");
        if (tokens.length == 2) {
            return tokens[0] + "-" + tokens[1].replaceAll("\\d", "\\*");
        }
        if (tokens.length == 3) {
            return tokens[0] + "-" + tokens[1].replaceAll("\\d", "\\*") + "-" + tokens[2];
        }
        return phone;
    }

    /**
     * IP Address 를 마킹한다.
     * 
     * @param string
     * @return
     */
    public static String markingIpAddress(String ipAddress) {
        if (ipAddress == null) {
            return "";
        }

        if ("-".equals(ipAddress)) {
            return ipAddress;
        }

        if (ipAddress.contains(".") == false) {
            return ipAddress;
        }

        String[] tokens = ipAddress.split("\\.");
        if (tokens.length != 4) {
            return ipAddress;
        }

        return tokens[0] + "." + tokens[1] + "." + tokens[0].replaceAll("\\d", "\\*") + "." + tokens[3];
    }

    /**
     * 주민등록 번호를 암호화한다.
     * 
     * @param string
     * @return
     */
    public static String markingSSN(String ssn) {
        if (ssn == null) {
            return "";
        }

        if ("-".equals(ssn)) {
            return ssn;
        }

        return maskingSusfixDigit(ssn, 6);
    }

    private static String maskingSusfixDigit(String str, int count) {

        StringBuffer buf = new StringBuffer();

        for (int i = str.length(); i > 0; i--) {
            if (count > 0) {
                if (Character.isDigit(str.charAt(i - 1))) {
                    buf.insert(0, encrytTable[random.nextInt(encrytTable.length)]);
                    count--;
                }
            } else {
                buf.insert(0, str.charAt(i - 1));
            }

        }
        return buf.toString();
    }

    /**
     * 계좌번호를 마스킹한다.
     * 
     * @param string
     * @return
     */
    public static String markingCreditCard(String account) {
        if (account == null) {
            return "";
        }

        if ("-".equals(account)) {
            return account;
        }

        return account.replaceAll("\\d", "*");
    }

    /**
     * 은행계좌번호 마스킹
     * 전체 번호 중(-제외) 5,6,7번째 자리 *처리
     *
     * @param string
     * @return
     */
    public static String markingAccount(String account) {
        if (StringUtils.isBlank(account) || "-".equals(account)) {
            return account;
        }

        String ret = "";

        account = account.replaceAll("-", "");

        for (int i = 0; i < account.length(); i++) {
            if (i < 4 || i >= 7) {
                ret += account.charAt(i);
            } else if (i < 7) {
                ret += "*";
            }
        }

        return ret;
    }

    /**
     * 주소
     * 읍/면/동 이하의 숫자
     * 
     * @param address
     * @return
     */
    public static String markingAddress(String address) {
        if (address == null) {
            return "";
        }

        if ("-".equals(address)) {
            return address;
        }

        String[] tokens = address.split(" ");
        if (tokens.length <= 3) {
            return address.replaceAll("[^-\\s]", "*");
        }

        StringBuffer buf = new StringBuffer();
        buf.append(StringUtils.join(new String[] { tokens[0], tokens[1], tokens[2] }, " "));

        for (int i = 3; i < tokens.length; i++) {
            buf.append(" " + tokens[i].replaceAll("\\d", "*"));
        }

        return buf.toString();
    }

    /**
     * 1) @ 갑 이전: 4번째 문자부터 *로 표시
     * 2) @ 값 이후 : 3번째 문자부터 *로 표시
     * 
     * @param email
     * @return
     */
    public static String markingEmail(String email) {

        if (StringUtils.isEmpty(email)) {
            return email;
        }
        if ("-".equals(email)) {
            return email;
        }

        String[] tokens = email.split("@");
        if (tokens.length < 2) {
            return email;
        }

        String left = tokens[0];
        String right = tokens[1];

        if (left.length() >= 4) {
            left = left.substring(0, 3) + left.substring(3).replaceAll(".", "*");
        } else if (left.length() >= 3) {
            left = left.substring(0, 1) + left.substring(1).replaceAll(".", "*");
        } else if (left.length() >= 2) {
            left = left.substring(0, 1) + left.substring(1).replaceAll(".", "*");
        }

        right = right.substring(0, 2) + (right.substring(2)).replaceAll(".", "*");

        return left + "@" + right;
    }

    /**
     * 1) @ 갑 이전: 4번째 문자부터 *로 표시
     * 
     * @param email
     * @return
     */
    public static String markingEmailOnlyId(String email) {

        if (StringUtils.isEmpty(email)) {
            return email;
        }
        if ("-".equals(email)) {
            return email;
        }

        String[] tokens = email.split("@");
        if (tokens.length < 2) {
            return email;
        }

        String left = tokens[0];

        if (left.length() >= 4) {
            left = left.substring(0, 3) + left.substring(3).replaceAll(".", "*");
        } else if (left.length() >= 3) {
            left = left.substring(0, 1) + left.substring(1).replaceAll(".", "*");
        } else if (left.length() >= 2) {
            left = left.substring(0, 1) + left.substring(1).replaceAll(".", "*");
        }

        return left;
    }

    /**
     * 아이디 뒤 3자리 마스킹
     * 
     * @param strValue
     * @return
     */
    public static String markingId(String strValue) {

        if (strValue == null) {
            return strValue;
        }

        if ("-".equals(strValue)) {
            return strValue;
        }

        if (strValue.length() < 3) {
            return strValue;
        }
        // return strValue.substring(0, strValue.length() - 3) + "***";
        // 2015.11.03 회원ID 마스킹처리 수정 : 앞3자리만 마스킹처리
        return "***" + strValue.substring(3);
    }

    public static void main(String[] args) {
        String[] emails = { "abcdef@naver.com", "abc@abc.com", "ab@abc.com" };
        String[] names = { "남궁새남", "홍길동", "홍길" };
        String[] birthdays = { "2016년04월16일", "2016-06-10", "2016/06/10", "2016.06.10" };
        String[] tels = { "010-123-1234", "02-123-4567" };
        String[] addrs = { "서울시 광진구 천호대로 133-15길 1002호", "서울시 광진구 중곡2동 138-103번지 1002호" };
        String[] accounts = { null, "-", "123", "12345", "12345678901234", "1234-5678-9012-34" };

        for (String email : emails) {
            log.debug("email = " + markingEmail(email));
        }

        for (String name : names) {
            log.debug("name = " + markingName(name));
        }

        for (String birthday : birthdays) {
            log.debug("birthday = " + markingBirthday(birthday));
        }

        for (String tel : tels) {
            log.debug("tel = " + markingPhone(tel));
        }

        for (String addr : addrs) {
            log.debug("addr = " + markingAddress(addr));
        }

        for (String account : accounts) {
            log.debug("account = " + markingAccount(account));
        }
    }
}
