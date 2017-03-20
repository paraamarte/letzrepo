package com.sivillage.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @Class Name : FileExtractUtil.java
 * @Description : 파일 압축해제 Util
 * @author UZEN / BILL
 * @since 2016. 4. 20.
 * @version 1.0
 * @see Copyright(c) 2016 SHINSEGAE INTERNATIONAL. All rights reserved
 */
public class FileExtractUtil {

    /**
     * zip 파일 압축해제
     * 
     * <pre>
     * zip 파일을 입력된 경로에 압축해제한다.
     * </pre>
     * 
     * @param compressedFile
     *            압축해제할 zip 파일
     * @param outputFolder
     *            압축해제할 경로
     */
    public static void extract(String compressedFile, String outputFolder) {
        byte[] buffer = new byte[1024];

        try {
            // 경로가 없으면 생성
            File folder = new File(outputFolder);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // 압축파일 열기
            ZipInputStream zis = new ZipInputStream(new FileInputStream(compressedFile));
            ZipEntry ze;
            while ((ze = zis.getNextEntry()) != null) {
                // String entryName = ze.getName();

                File entryFile = new File(outputFolder, ze.getName());
                if (ze.isDirectory()) { // 디렉토리
                    if (!entryFile.exists()) {
                        entryFile.mkdirs();
                    }
                } else { // 파일
                    // 상위 경로 생성
                    new File(entryFile.getParent()).mkdirs();

                    FileOutputStream fos = new FileOutputStream(entryFile);

                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }

                    // and rewrite data from stream
                    /*
                     * OutputStream os = null;
                     * try {
                     * os = new FileOutputStream(entryFile);
                     * IOUtils.copy(zis, os);
                     * } finally {
                     * IOUtils.closeQuietly(os);
                     * }
                     */
                    fos.close();
                }
            }

            zis.closeEntry();
            zis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}