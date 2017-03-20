package com.sivillage.common.util;

import java.io.File;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sivillage.common.constant.ConfigKeys;
import com.sivillage.common.constant.Constants;
import com.sivillage.common.exception.MallException;

/**
 * @Class Name : FileMoveUtil.java
 * @Description : 파일 이동 Util
 * @author UZEN / BILL
 * @since 2016. 4. 20.
 * @version 1.0
 * @see Copyright(c) 2016 SHINSEGAE INTERNATIONAL. All rights reserved
 */

@Slf4j
public class FileMoveUtil implements ConfigKeys {
    private static Logger log = LoggerFactory.getLogger(FileMoveUtil.class);
    
    /**
     * <pre>
     * 임시 저장 경로에서 실제 저장 경로로 파일을 이동
     * </pre>
     * 
     * @param filePath
     *            : 파일명을 포함한 경로
     * @return
     */
    public static String moveFile(String filePath) {
        String tempPath = ConfigUtil.getString(FILE_PATH_TEMP); // 임시 경로
        String realPath = ConfigUtil.getString(FILE_PATH_REAL); // 실제 경로
        String rootPath = ConfigUtil.getString(FILE_PATH_ROOT); // 루트 경로
        log.debug("temp file : " + tempPath + rootPath + filePath);
        log.debug("real file : " + realPath + rootPath + filePath);
        return moveFile(tempPath + rootPath + filePath, realPath + rootPath + filePath, true);
    }

    /**
     * sourceFile에서 destFile path로 이동 하며 파일명을 랜덤하게 생성
     * 
     * @param sourceFileFullPathNm
     *            : 원본 파일 풀경로+파일명
     * @param destFilePath
     *            : 이동 할 풀경로
     * @return
     */
    public static String moveFileToDestPath(String sourceFileFullPathNm, String destFilePathNm) {
        long timestamp = System.currentTimeMillis();
        String fileNm = FileNameUtil.getRandomFileName(sourceFileFullPathNm, timestamp);
        String destFileFullPathNm = destFilePathNm + fileNm;
        return moveFile(sourceFileFullPathNm, destFileFullPathNm, true);
    }

    /**
     * sourceFile에서 destFile path로 이동
     * 
     * @param sourceFileFullPathNm
     *            : 원본 파일 풀경로+파일명
     * @param destFilePath
     *            : 이동 할 풀경로
     * @param destFileNm
     *            : 새 파일 이름
     * @return
     */
    public static String moveFileToDestPath(String sourceFileFullPathNm, String destFilePathNm, String destFileNm) {
        String destFileFullPathNm = destFilePathNm + destFileNm;
        return moveFile(sourceFileFullPathNm, destFileFullPathNm, true);
    }

    /**
     * sourceFile에서 destFile로 이동
     * 
     * @param sourceFilePath
     *            : 원본 파일 풀경로
     * @param sourceFileNm
     *            : 원폰 파일명
     * @param destFilePath
     *            : 이동 할 풀경로
     * @return
     */
    public static String moveFile(String sourceFilePath, String sourceFileNm, String destFilePathNm) {
        return moveFile(sourceFilePath, sourceFileNm, destFilePathNm, "", true, true);
    }

    /**
     * sourceFile에서 destFile로 이동
     * 
     * @param sourceFilePath
     *            : 원본 파일 풀경로
     * @param sourceFileNm
     *            : 원폰 파일명
     * @param destFilePath
     *            : 이동 할 풀경로
     * @param destFileNm
     *            : 이동 하고 변경 될 파일명
     * @param isRandomFileNm
     *            : 랜덤 파일명으로 변경 여부( 타임스템프로 생성 )
     * @param isDeleteSourceFile
     *            : 원본 파일 삭제 여부
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
     * sourceFile에서 destFile로 이동
     * 
     * @param sourceFilePathFileNm
     *            : 원본 파일 풀경로 + 파일명
     * @param destFilePathNmFileNm
     *            : 이동 할 풀경로 + 파일명
     * @param isDeleteSourceFile
     *            : 원본 파일 삭제 여부
     * @return
     */
    public static String moveFile(String sourceFilePathFileNm, String destFilePathNmFileNm, boolean isDeleteSourceFile) {
        String result = destFilePathNmFileNm;
        File sourceFile = null;
        File destFile = null;
        try {
            sourceFile = new File(sourceFilePathFileNm);
            String destPath = destFilePathNmFileNm;
            if (destPath.indexOf('.') > -1) {
                if (destPath.indexOf('/') > -1) {
                    destPath = destPath.substring(0, destPath.lastIndexOf('/'));
                } else if (destPath.indexOf('\\') > -1) {
                    destPath = destPath.substring(0, destPath.lastIndexOf('\\'));
                }
            }
            log.debug("destPath : " + destPath);
            File destPathDir = new File(destPath);

            if (!destPathDir.exists()) {
                destPathDir.mkdirs();
            }

            destFile = new File(destFilePathNmFileNm);
            log.debug("before file copy [sourceFilePathFileNm: " + sourceFilePathFileNm + "] [destFilePathNmFileNm: " + destFilePathNmFileNm + "]");

            FileUtils.copyFile(sourceFile, destFile);
            log.debug("file copy sucess");
            if (isDeleteSourceFile) {
                sourceFile.delete();
                log.debug("file delete sucess");
            }
        } catch (Exception e) {
            log.error(e.toString());
            e.printStackTrace();
            result = null;
        }
        return result;
    }

    /**
     * 파일 삭제
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
     * 전달 받은 String에서 path 부분만 돌려줌
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
     * 전달 받은 String에서 file명 부분만 돌려줌
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
     * 임시 저장 경로에서 실제 저장 경로로 파일을 이동
     * </pre>
     * 
     * @param filePath
     *            : 파일명을 포함한 경로
     * @param fileSubPath
     *            : 카테고리별 경로
     * @return
     */
    public static String moveFileToAddPath(String filePath, String fileSubPath) {
        String tempPath = ConfigUtil.getString(FILE_PATH_TEMP); // 임시 경로
        String realPath = ConfigUtil.getString(FILE_PATH_REAL); // 실제 경로
        String rootPath = ConfigUtil.getString(FILE_PATH_ROOT); // 루트 경로

        String alterFilePath = getStrFilePath(filePath) + "/" + fileSubPath + "/" + getStrFileNm(filePath);

        return moveFile(tempPath + rootPath + filePath, realPath + rootPath + alterFilePath, true);
    }

    /**
     * 이미지 temp -> real 이동
     * 
     * <pre>
     *  - 예시 경로 Path : upload1/contents/fileSubPath/파일들.png
     * </pre>
     * 
     * @param tmpImgPath
     * @param tmpImgNm
     * @param no
     * @return true/false
     */
    public static boolean moveTempImageToRealImage(String tmpImgPath, String tmpImgNm, String fileSubPath) {
        String moveResult = "result";

        if (StringUtils.isNotBlank(tmpImgNm)) {

            moveResult = FileMoveUtil.moveFileToAddPath(tmpImgPath, fileSubPath);

            if (StringUtils.isBlank(moveResult)) {
                throw new MallException("업로드중 오류가 발생하였습니다.");
            } else {
                return true;
            }
        }

        return false;
    }

    /**
     * 실제 이미지 저장 Path 정보 Get
     * 
     * <pre>
     * </pre>
     * 
     * @param filePath
     *            : 파일명을 포함한 경로
     * @param fileSubPath
     *            : 카테고리별 경로
     * @return
     */
    public static String getMoveFileToAddPath(String filePath, String fileSubPath) {
        String alterFilePath = getStrFilePath(filePath) + "/" + fileSubPath + "/" + getStrFileNm(filePath);
        return alterFilePath;
    }

    /**
     * 이미지 Full Url 정보 Get
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
        if (StringUtils.isNotEmpty(imgPath)) {
            if (Constants.FLAG_YES.equals(cdnYn)) {
                fileUrl = ConfigUtil.getString(FILE_URL_CDN) + ConfigUtil.getString(FILE_PATH_ROOT) + imgPath;
            } else {
                fileUrl = ConfigUtil.getString(FILE_URL_NCDN) + ConfigUtil.getString(FILE_PATH_ROOT) + imgPath;
            }
        }
        return fileUrl;
    }

}
