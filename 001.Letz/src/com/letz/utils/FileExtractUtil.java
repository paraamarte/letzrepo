/*
 * This software is the confidential and proprietary information of
 * Shinsegae Internatinal Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Shinsegae International.
 */
package com.letz.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @Class Name : FileExtractUtil.java
 * @Description : �뙆�씪 �븬異뺥빐�젣 Util
 * @author UZEN / �씠�슜�꽑
 * @since 2015. 12. 03.
 * @version 1.0
 * @see
 *      Copyright(c) 2016 SHINSEGAE INTERNATIONAL. All rights reserved
 */
public class FileExtractUtil {

    /**
     * zip �뙆�씪 �븬異뺥빐�젣
     * 
     * <pre>
     * zip �뙆�씪�쓣 �엯�젰�맂 寃쎈줈�뿉 �븬異뺥빐�젣�븳�떎.
     * </pre>
     * 
     * @param compressedFile
     *            �븬異뺥빐�젣�븷 zip �뙆�씪
     * @param outputFolder
     *            �븬異뺥빐�젣�븷 寃쎈줈
     */
    public static void extract(String compressedFile, String outputFolder) {
        byte[] buffer = new byte[1024];

        try {
            // 寃쎈줈媛� �뾾�쑝硫� �깮�꽦
            File folder = new File(outputFolder);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // �븬異뺥뙆�씪 �뿴湲�
            ZipInputStream zis = new ZipInputStream(new FileInputStream(compressedFile));
            ZipEntry ze;
            while ((ze = zis.getNextEntry()) != null) {
                // String entryName = ze.getName();

                File entryFile = new File(outputFolder, ze.getName());
                if (ze.isDirectory()) { // �뵒�젆�넗由�
                    if (!entryFile.exists()) {
                        entryFile.mkdirs();
                    }
                } else { // �뙆�씪
                    // �긽�쐞 寃쎈줈 �깮�꽦
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

    /**
     * targetPath�뿉 �엳�뒗 �뙆�씪�뱾�쓣 �븬異�
     * @param targetPath
     */
    public static void compress(String targetPath) {
        String outFilePath = targetPath + ".zip";
        FileInputStream fis = null;
        ZipOutputStream zos = null;
        
        try {
            zos = new ZipOutputStream(new FileOutputStream(outFilePath));
            File targetPathFolder = new File(targetPath);
            if (!targetPathFolder.isDirectory()) {
                return;
            }
            
            byte[] buffer = new byte[1024];
            String[] fileList = targetPathFolder.list();
            if (fileList == null) {
                return;
            }
            
            for (String file : fileList) {
                fis = new FileInputStream(targetPath + "/" + file);
                zos.putNextEntry(new ZipEntry(file));
                int length;
                while ((length = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (zos != null) zos.close();
                if (fis != null) fis.close();
            } catch (IOException e) {}            
        }
    }
    
    /**
     * fileList�뱾�쓣 zipFilePath濡� �븬異�
     */
    public static void compress(String zipFilePath, List<String> fileList) {
        FileInputStream fis = null;
        ZipOutputStream zos = null;
        byte[] buffer = new byte[1024];

        if (fileList == null) {
            return;
        }
        
        try {
            zos = new ZipOutputStream(new FileOutputStream(zipFilePath));
            
            for (String file : fileList) {
                fis = new FileInputStream(file);
                zos.putNextEntry(new ZipEntry(file.substring(file.lastIndexOf("/")+1)));
                int length;
                while ((length = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (zos != null) zos.close();
                if (fis != null) fis.close();
            } catch (IOException e) {}            
        }
    }
    
}