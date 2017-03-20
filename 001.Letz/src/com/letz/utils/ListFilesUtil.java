package com.letz.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class ListFilesUtil {

    private static final Logger log = LoggerFactory.getLogger(ListFilesUtil.class);

    /**
     * List all the files and folders from a directory
     * 
     * @param directoryName
     *            to be listed
     */
    public static void listFilesAndFolders(String directoryName) {
        File directory = new File(directoryName);
        // get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList) {
            System.out.println(file.getName());
        }
    }

    /**
     * List all the files under a directory
     * 
     * @param directoryName
     *            to be listed
     */
    public static List<String> listFiles(String directoryName) {
        List<String> fileList = new ArrayList<String>();

        File directory = new File(directoryName);
        // get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isFile()) {
                // log.debug(StringUtils.replace(file.getAbsolutePath(), "\\", "/"));
                fileList.add(StringUtils.replace(file.getAbsolutePath(), "\\", "/"));
            }
        }

        return fileList;
    }

    /**
     * List all the folder under a directory
     * 
     * @param directoryName
     *            to be listed
     */
    public static void listFolders(String directoryName) {
        File directory = new File(directoryName);
        // get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isDirectory()) {
                System.out.println(file.getName());
            }
        }
    }

    /**
     * List all files from a directory and its subdirectories
     * 
     * @param directoryName
     *            to be listed
     */
    public static List<String> listFilesAndFilesSubDirectories(String directoryName) {
        List<String> fileList = new ArrayList<String>();

        File directory = new File(directoryName);
        // get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isFile()) {
                // log.debug(file.getName() + ":" + file.getAbsolutePath());
                fileList.add(StringUtils.replace(file.getAbsolutePath(), "\\", "/"));
            } else if (file.isDirectory()) {
                fileList.addAll(listFilesAndFilesSubDirectories(file.getAbsolutePath()));
            }
        }

        return fileList;
    }

    public static void main(String[] args) {
        final String directoryWindows = "C://temp";
        List<String> fileList = ListFilesUtil.listFilesAndFilesSubDirectories(directoryWindows);

        for (String filePath : fileList) {
            log.debug("filePath = {}", filePath);
        }
    }
}