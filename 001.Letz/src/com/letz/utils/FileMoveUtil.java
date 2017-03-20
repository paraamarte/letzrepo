/**
 * @Class Name : FileMoveUtil.java
 * @Description : �뙆�씪 �씠�룞 �쑀�떥由ы떚
 * @Modification Information
 *               @ * @ �닔�젙�씪 �닔�젙�옄 �닔�젙�궡�슜
 *               @ --------- --------- -------------------------------
 *               @ 2014.08.08 沅뚯쨷�뿄 理쒖큹�깮�꽦
 * @author kwon
 * @since 2014. 08.08
 * @version 1.0
 * @see
 *      Copyright(c) 2014 suhyang network Co.,Ltd. All rights reserved
 */
package com.letz.utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sivillage.constant.Constants;
import com.sivillage.core.exception.UserHandleException;
import com.sivillage.utils.message.MessageUtil;

public class FileMoveUtil {

    private static Logger logger = LoggerFactory.getLogger(FileMoveUtil.class);

    /**
     * <pre>
     * �엫�떆 ���옣 寃쎈줈�뿉�꽌 �떎�젣 ���옣 寃쎈줈濡� �뙆�씪�쓣 �씠�룞
     * </pre>
     * 
     * @param filePath
     *            : �뙆�씪紐낆쓣 �룷�븿�븳 寃쎈줈
     * @return
     */
    public static String moveFile(String filePath) {
        String tempPath = MessageUtil.getMessage("config.files.temp.path"); // �엫�떆 寃쎈줈
        String realPath = MessageUtil.getMessage("config.files.real.path"); // �떎�젣 寃쎈줈

        String rootPath = MessageUtil.getMessage("config.files.root.path"); // 猷⑦듃 寃쎈줈

        return moveFile(tempPath + rootPath + filePath, realPath + rootPath + filePath, true);
    }

    /**
     * sourceFile�뿉�꽌 destFile path濡� �씠�룞 �븯硫� �뙆�씪紐낆쓣 �옖�뜡�븯寃� �깮�꽦
     * 
     * @param sourceFileFullPathNm
     *            : �썝蹂� �뙆�씪 ��寃쎈줈+�뙆�씪紐�
     * @param destFilePath
     *            : �씠�룞 �븷 ��寃쎈줈
     * @return
     */
    public static String moveFileToDestPath(String sourceFileFullPathNm, String destFilePathNm) {
        long timestamp = System.currentTimeMillis();
        String fileNm = FileNameUtil.getRandomFileName(sourceFileFullPathNm, timestamp);
        String destFileFullPathNm = destFilePathNm + fileNm;
        return moveFile(sourceFileFullPathNm, destFileFullPathNm, true);
    }

    /**
     * sourceFile�뿉�꽌 destFile path濡� �씠�룞
     * 
     * @param sourceFileFullPathNm
     *            : �썝蹂� �뙆�씪 ��寃쎈줈+�뙆�씪紐�
     * @param destFilePath
     *            : �씠�룞 �븷 ��寃쎈줈
     * @param destFileNm
     *            : �깉 �뙆�씪 �씠由�
     * @return
     */
    public static String moveFileToDestPath(String sourceFileFullPathNm, String destFilePathNm, String destFileNm) {
        String destFileFullPathNm = destFilePathNm + destFileNm;
        return moveFile(sourceFileFullPathNm, destFileFullPathNm, true);
    }

    /**
     * sourceFile�뿉�꽌 destFile濡� �씠�룞
     * 
     * @param sourceFilePath
     *            : �썝蹂� �뙆�씪 ��寃쎈줈
     * @param sourceFileNm
     *            : �썝�룿 �뙆�씪紐�
     * @param destFilePath
     *            : �씠�룞 �븷 ��寃쎈줈
     * @return
     */
    public static String moveFile(String sourceFilePath, String sourceFileNm, String destFilePathNm) {
        return moveFile(sourceFilePath, sourceFileNm, destFilePathNm, "", true, true);
    }

    /**
     * sourceFile�뿉�꽌 destFile濡� �씠�룞
     * 
     * @param sourceFilePath
     *            : �썝蹂� �뙆�씪 ��寃쎈줈
     * @param sourceFileNm
     *            : �썝�룿 �뙆�씪紐�
     * @param destFilePath
     *            : �씠�룞 �븷 ��寃쎈줈
     * @param destFileNm
     *            : �씠�룞 �븯怨� 蹂�寃� �맆 �뙆�씪紐�
     * @param isRandomFileNm
     *            : �옖�뜡 �뙆�씪紐낆쑝濡� 蹂�寃� �뿬遺�( ���엫�뒪�뀥�봽濡� �깮�꽦 )
     * @param isDeleteSourceFile
     *            : �썝蹂� �뙆�씪 �궘�젣 �뿬遺�
     * @return
     */
    public static String moveFile(String sourceFilePath, String sourceFileNm, String destFilePathNm, String destFileNm,
            boolean isRandomFileNm, boolean isDeleteSourceFile) {
        String result = destFilePathNm + destFileNm;
        File sourceFile = null;
        File destFile = null;
        try {
            sourceFile = new File(sourceFilePath + "/" + sourceFileNm);
            String destFileFullNm = destFilePathNm;
            File destPath = new File(destFilePathNm);
            if (!destPath.exists()) {
                destPath.mkdirs();
            }
            String fileNm = destFileNm;
            if (isRandomFileNm) {
                long timestamp = System.currentTimeMillis();
                fileNm = FileNameUtil.getRandomFileName(sourceFileNm, timestamp);
            }
            destFile = new File(destFileFullNm + "/" + fileNm);
            sourceFile.renameTo(destFile);
            if (isDeleteSourceFile) {
                sourceFile.delete();
            }
        } catch (Exception e) {
            result = null;
        }
        return result;
    }

    /**
     * sourceFile�뿉�꽌 destFile濡� �씠�룞
     * 
     * @param sourceFilePathFileNm
     *            : �썝蹂� �뙆�씪 ��寃쎈줈 + �뙆�씪紐�
     * @param destFilePathNmFileNm
     *            : �씠�룞 �븷 ��寃쎈줈 + �뙆�씪紐�
     * @param isDeleteSourceFile
     *            : �썝蹂� �뙆�씪 �궘�젣 �뿬遺�
     * @return
     */
    public static String moveFile(String sourceFilePathFileNm, String destFilePathNmFileNm, boolean isDeleteSourceFile) {

        logger.error("########### moveFile");
        logger.error("sourceFilePathFileNm: " + sourceFilePathFileNm);
        logger.error("destFilePathNmFileNm: " + destFilePathNmFileNm);

        String result = destFilePathNmFileNm;
        File sourceFile = null;
        File destFile = null;
        try {
            sourceFile = new File(sourceFilePathFileNm);
            String destPath = destFilePathNmFileNm;

            if (!sourceFile.exists()) {
                return null;
            }

            if (destPath.indexOf('.') > -1) {
                if (destPath.indexOf('/') > -1) {
                    destPath = destPath.substring(0, destPath.lastIndexOf('/'));
                } else if (destPath.indexOf('\\') > -1) {
                    destPath = destPath.substring(0, destPath.lastIndexOf('\\'));
                }
            }

            logger.debug("destPath: " + destPath);

            File destPathDir = new File(destPath);

            logger.debug("destPathDir: " + destPathDir);

            if (!destPathDir.exists()) {
                destPathDir.mkdirs();
            }
            destFile = new File(destFilePathNmFileNm);
            FileUtils.copyFile(sourceFile, destFile);
            if (isDeleteSourceFile) {
                sourceFile.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = null;
        }
        return result;
    }

    /**
     * �뙆�씪 �궘�젣
     * 
     * @param filePathNm
     * @return
     */
    public static boolean deleteFile(String filePathNm) {
        boolean result = true;
        File file = null;
        try {
            file = new File(filePathNm);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    /**
     * �쟾�떖 諛쏆� String�뿉�꽌 path 遺�遺꾨쭔 �룎�젮以�
     * 
     * @param fileFullPathFileNm
     * @return
     */
    public static String getStrFilePath(String fileFullPathFileNm) {
        String result = fileFullPathFileNm;
        if (fileFullPathFileNm.indexOf('.') > -1) {
            if (fileFullPathFileNm.indexOf('/') > -1) {
                result = fileFullPathFileNm.substring(0, fileFullPathFileNm.lastIndexOf('/'));
            } else if (fileFullPathFileNm.indexOf('\\') > -1) {
                result = fileFullPathFileNm.substring(0, fileFullPathFileNm.lastIndexOf('\\'));
            }
        }
        return result;
    }

    /**
     * �쟾�떖 諛쏆� String�뿉�꽌 file紐� 遺�遺꾨쭔 �룎�젮以�
     * 
     * @param fileFullPathFileNm
     * @return
     */
    public static String getStrFileNm(String fileFullPathFileNm) {
        String result = fileFullPathFileNm;
        if (fileFullPathFileNm.indexOf('.') > -1) {
            if (fileFullPathFileNm.indexOf('/') > -1) {
                result = fileFullPathFileNm.substring(fileFullPathFileNm.lastIndexOf('/') + 1);
            } else if (fileFullPathFileNm.indexOf('\\') > -1) {
                result = fileFullPathFileNm.substring(fileFullPathFileNm.lastIndexOf('\\') + 1);
            }
        }
        return result;
    }

    /**
     * <pre>
     * �엫�떆 ���옣 寃쎈줈�뿉�꽌 �떎�젣 ���옣 寃쎈줈濡� �뙆�씪�쓣 �씠�룞
     * </pre>
     * 
     * @param filePath
     *            : �뙆�씪紐낆쓣 �룷�븿�븳 寃쎈줈
     * @param fileSubPath
     *            : 移댄뀒怨좊━蹂� 寃쎈줈
     * @return
     */
    public static String moveFileToAddPath(String filePath, String fileSubPath) {

        logger.info("########### moveFileToAddPath");
        logger.info("filePath: " + filePath);
        logger.info("fileSubPath: " + fileSubPath);

        String tempPath = MessageUtil.getMessage("config.files.temp.path"); // �엫�떆 寃쎈줈
        String realPath = MessageUtil.getMessage("config.files.real.path"); // �떎�젣 寃쎈줈
        String rootPath = MessageUtil.getMessage("config.files.root.path"); // 猷⑦듃 寃쎈줈

        String alterFilePath = getStrFilePath(filePath) + "/" + fileSubPath + "/" + getStrFileNm(filePath);

        return moveFile(tempPath + rootPath + filePath, realPath + rootPath + alterFilePath, true);
    }

    /**
     * �씠誘몄� temp -> real �씠�룞
     * 
     * <pre>
     *  - �삁�떆 寃쎈줈 Path : upload1/contents/fileSubPath/�뙆�씪�뱾.png
     * </pre>
     * 
     * @param tmpImgPath
     * @param tmpImgNm
     * @param no
     * @return true/false
     */
    public static boolean moveTempImageToRealImage(String tmpImgPath, String tmpImgNm, String fileSubPath) {

        logger.info("########### moveTempImageToRealImage");
        logger.info("tmpImgPath: " + tmpImgPath);
        logger.info("tmpImgNm: " + tmpImgNm);
        logger.info("fileSubPath: " + fileSubPath);

        String moveResult = "result";

        if (StringUtils.isNotBlank(tmpImgNm)) {

            moveResult = FileMoveUtil.moveFileToAddPath(tmpImgPath, fileSubPath);

            if (StringUtils.isBlank(moveResult)) {
                throw new UserHandleException("message.common.upload.error");
            } else {
                return true;
            }
        }

        return false;
    }

    /**
     * �떎�젣 �씠誘몄� ���옣 Path �젙蹂� Get
     * 
     * <pre>
     * </pre>
     * 
     * @param filePath
     *            : �뙆�씪紐낆쓣 �룷�븿�븳 寃쎈줈
     * @param fileSubPath
     *            : 移댄뀒怨좊━蹂� 寃쎈줈
     * @return
     */
    public static String getMoveFileToAddPath(String filePath, String fileSubPath) {
        String alterFilePath = getStrFilePath(filePath) + "/" + fileSubPath + "/" + getStrFileNm(filePath);
        return alterFilePath;
    }

    /**
     * �씠誘몄� Full Url �젙蹂� Get
     * 
     * <pre>
     * </pre>
     * 
     * @param cdnYn
     * @param imgPath
     * @return
     */
    public static String getImageFullUrl(String cdnYn, String imgPath) {
        String fileUrl = null;
        if (StringUtils.isNotNull(imgPath)) {
            if (Constants.FLAG_YES.equals(cdnYn)) {
                fileUrl = MessageUtil.getMessage("config.files.cdn.url") + MessageUtil.getMessage("config.files.root.path") + imgPath;
            } else {
                fileUrl = MessageUtil.getMessage("config.files.ncdn.url") + MessageUtil.getMessage("config.files.root.path") + imgPath;
            }
        }
        return fileUrl;
    }

    /**
     * �뵒�젆�넗由� �깮�꽦
     * �뾾�쑝硫� �깮�꽦, �엳�쑝硫� skip
     */
    public static boolean makeDirectory(String dir) {
        boolean success = false;
        String FILE_SEPERATOR = "/";

        String[] strs = dir.split(FILE_SEPERATOR);

        StringBuilder tmpDirectory = new StringBuilder();
        for (String str : strs) {
            tmpDirectory.append(str).append(FILE_SEPERATOR);

            File file = new File(tmpDirectory.toString());

            if (!file.exists()) {
                success = file.mkdir();

                if (!success) {
                    break;
                }
            } else {
                success = true;
            }
        }

        return success;
    }
}
