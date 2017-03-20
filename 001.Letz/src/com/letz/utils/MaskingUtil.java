package com.letz.utils;

import java.lang.Character.UnicodeBlock;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

public class MaskingUtil {
    static Random random = new Random(System.currentTimeMillis());
    static char[] encrytTable = new char[] { '!', '@', '#', '$', '%', '^', '&', '*', '~' };

    /**
     * �꽦紐� 留덉뒪�궧
     * 1) 援�臾�: �꽦紐낆씠 �몢�옄�씤 寃쎌슦, 泥ル쾲吏� 湲��옄瑜� �젣�쇅�븳 留덉뒪�궧. �꽦紐낆씠 �꽭�옄 �씠�긽�씤 寃쎌슦, �씠由꾩쓽 泥ル쾲吏� 湲��옄�� 留덉�留� 湲��옄瑜� �젣�쇅�븳 留덉뒪�궧
     * 2) �쁺臾�: �쁺臾몃챸�쓽 寃쎌슦, �떆�옉 5踰덉�� 湲��옄遺��꽣 �쟾臾� 留덉뒪�궧
     * 
     * @param string
     * @return
     */

    public static String markingName(String name) {
        if (name == null || name.length() < 2) {
            return name;
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
     * �쉶�썝ID瑜� �뮘�쓽 3�옄由щ�� 留덊궧�븳�떎.
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
     * �깮�뀈�썡�씪�뿉�꽌 �닽�옄瑜� 留덊궧�븳�떎.
     * 
     * @param string
     * @return
     */
    public static String markingBirthday(String birthday) {
        if (birthday == null) {
            return null;
        }

        if ("-".equals(birthday)) {
            return birthday;
        }

        return birthday.replaceAll("\\d", "*");
    }

    /**
     * �쟾�솕踰덊샇 留덉뒪�궧
     * 援�踰� 4�옄由� �삉�뒗 援�踰� �뮘 3�옄由�
     * 
     * @param string
     * @return
     */
    public static String markingPhone(String phone) {
        if (phone == null) {
            return null;
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
     * IP Address 瑜� 留덊궧�븳�떎.
     * 
     * @param string
     * @return
     */
    public static String markingIpAddress(String ipAddress) {
        if (ipAddress == null) {
            return ipAddress;
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
     * 二쇰�쇰벑濡� 踰덊샇瑜� �븫�샇�솕�븳�떎.
     * 
     * @param string
     * @return
     */
    public static String markingSSN(String ssn) {
        if (ssn == null) {
            return null;
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
     * 怨꾩쥖踰덊샇瑜� 留덉뒪�궧�븳�떎.
     * 
     * @param string
     * @return
     */
    public static String markingCreditCard(String account) {
        if (account == null) {
            return null;
        }

        if ("-".equals(account)) {
            return account;
        }

        return account.replaceAll("\\d", "*");
    }

    /**
     * ���뻾怨꾩쥖踰덊샇 留덉뒪�궧
     * �쟾泥� 踰덊샇 以�(-�젣�쇅) 5,6,7踰덉㎏ �옄由� *泥섎━
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
     * 二쇱냼
     * �쓭/硫�/�룞 �씠�븯�쓽 �닽�옄
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
     * 1) @ 媛� �씠�쟾: 4踰덉㎏ 臾몄옄遺��꽣 *濡� �몴�떆
     * 2) @ 媛� �씠�썑 : 3踰덉㎏ 臾몄옄遺��꽣 *濡� �몴�떆
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
     * �븘�씠�뵒 �뮘 3�옄由� 留덉뒪�궧
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
        // 2015.11.03 �쉶�썝ID 留덉뒪�궧泥섎━ �닔�젙 : �븵3�옄由щ쭔 留덉뒪�궧泥섎━
        return "***" + strValue.substring(3);
    }

    public static void main(String[] args) {
        String[] emails = { "abcdef@naver.com", "abc@abc.com", "ab@abc.com" };
        String[] names = { "�궓沅곸깉�궓", "�솉湲몃룞", "�솉湲�" };
        String[] birthdays = { "2016�뀈04�썡16�씪", "2016-06-10", "2016/06/10", "2016.06.10" };
        String[] tels = { "010-123-1234", "02-123-4567" };
        String[] addrs = { "�꽌�슱�떆 愿묒쭊援� 泥쒗샇��濡� 133-15湲� 1002�샇", "�꽌�슱�떆 愿묒쭊援� 以묎끝2�룞 138-103踰덉� 1002�샇" };
        String[] accounts = { null, "-", "123", "12345", "12345678901234", "1234-5678-9012-34" };

        for (String email : emails) {
            System.out.println("email = " + markingEmail(email));
        }

        for (String name : names) {
            System.out.println("name = " + markingName(name));
        }

        for (String birthday : birthdays) {
            System.out.println("birthday = " + markingBirthday(birthday));
        }

        for (String tel : tels) {
            System.out.println("tel = " + markingPhone(tel));
        }

        for (String addr : addrs) {
            System.out.println("addr = " + markingAddress(addr));
        }

        for (String account : accounts) {
            System.out.println("account = " + markingAccount(account));
        }
    }
}