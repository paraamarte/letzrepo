package com.letz.test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class FileMakeTest {
    private static List<String> productNoList = Arrays.asList("01P0000129974",
            "01P0000129971",
            "01P0000129987",
            "01P0000129980",
            "01P0000130006",
            "01P0000129984",
            "01P0000115860",
            "01P0000115096",
            "01P0000115522",
            "01P0000115483",
            "01P0000130025",
            "01P0000130030",
            "01P0000129565",
            "01P0000129583",
            "01P0000130012",
            "01P0000130036",
            "01P0000132208",
            "01P0000132209",
            "01P0000127390",
            "01P0000127418",
            "01P0000134603",
            "01P0000115906",
            "01P0000115258",
            "01P0000127396",
            "01P0000114859",
            "01P0000129592",
            "01P0000126501",
            "01P0000127419",
            "01P0000115821",
            "01P0000115744",
            "01P0000115820",
            "01P0000114949",
            "01P0000130028",
            "01P0000129991",
            "01P0000126604",
            "01P0000124097",
            "01P0000129578",
            "01P0000115773",
            "01P0000115457",
            "01P0000129584",
            "01P0000127420",
            "01P0000127412",
            "01P0000129969",
            "01P0000115266",
            "01P0000129562",
            "01P0000124079",
            "01P0000129979",
            "01P0000130034",
            "01P0000124374",
            "01P0000124185",
            "01P0000129573",
            "01P0000115676",
            "01P0000115648",
            "01P0000129976",
            "01P0000130035",
            "01P0000124247",
            "01P0000124276",
            "01P0000129590",
            "01P0000129571",
            "01P0000115470",
            "01P0000115246",
            "01P0000114923",
            "01P0000129994",
            "01P0000129990",
            "01P0000130002",
            "01P0000124315",
            "01P0000114877",
            "01P0000132223",
            "01P0000132230",
            "01P0000130009",
            "01P0000127395",
            "01P0000114943",
            "01P0000114966",
            "01P0000114964",
            "01P0000130038",
            "01P0000129975",
            "01P0000124190",
            "01P0000129993",
            "01P0000130003",
            "01P0000126608",
            "01P0000115313",
            "01P0000127421",
            "01P0000129581",
            "01P0000129580",
            "01P0000130021",
            "01P0000129567",
            "01P0000129568",
            "01P0000127422",
            "01P0000129579",
            "01P0000129570",
            "01P0000129591",
            "01P0000130017",
            "01P0000129582",
            "01P0000124305",
            "01P0000115421",
            "01P0000115587",
            "01P0000115425",
            "01P0000115859",
            "01P0000129585",
            "01P0000115043");
    private static String resourceFilePath = "C:\\sivillage\\workspace\\nas01";
    private static String resourceFileNm = "resourceFileNm.jpg";
    private static String targetProductPath = "\\product";
    private static String targetFileRoot = "\\files";
    private static String targetProductMoPath = "\\MO";
    private static String targetProductNuPath = "\\NU";

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String sourceFilePathFileNm = String.format("%s\\%s", resourceFilePath, resourceFileNm);

        for (String productNo : productNoList) {
            String productPath = getPathFromProductNo(productNo);
            // 1.老馆 捞固瘤 积己
            String destFilePathNmFilePath = String.format("%s%s%s%s\\%s.jpg", resourceFilePath, targetFileRoot, targetProductPath, productPath, productNo);
            moveFile(sourceFilePathFileNm, destFilePathNmFilePath, false);
            // 2.叼抛老 钠 积己
            for (int i = 1; i <= 5; i++) {
                String destDtlFilePathNmFilePath = String.format("%s%s%s%s\\%s_%d.jpg", resourceFilePath, targetFileRoot, targetProductPath, productPath, productNo, i);
                moveFile(sourceFilePathFileNm, destDtlFilePathNmFilePath, false);
            }
            // 3. 葛胆钠 积己
            String destMoFilePathNmFilePath = String.format("%s%s%s%s%s\\%s_MO.jpg", resourceFilePath, targetFileRoot, targetProductPath, productPath, targetProductMoPath, productNo);
            moveFile(sourceFilePathFileNm, destMoFilePathNmFilePath, false);
            // 4. 穿尝钠 积辑
            String destNuFilePathNmFilePath = String.format("%s%s%s%s%s\\%s_NU.jpg", resourceFilePath, targetFileRoot, targetProductPath, productPath, targetProductNuPath, productNo);
            moveFile(sourceFilePathFileNm, destNuFilePathNmFilePath, false);
        }
    }

    public static String getPathFromProductNo(String productNo) {
        StringBuilder path = new StringBuilder();

        path.append("\\");
        path.append(productNo.substring(0, 2));
        path.append("\\");
        path.append(productNo.substring(2, 7));
        path.append("\\");
        path.append(productNo.substring(7, 9));
        path.append("\\");
        path.append(productNo.substring(9, 11));
        path.append("\\");
        path.append(productNo.substring(11));

        return path.toString();
    }

    public static String moveFile(String sourceFilePathFileNm, String destFilePathNmFileNm, boolean isDeleteSourceFile) {

        System.out.println("########### moveFile");
        System.out.println("sourceFilePathFileNm: " + sourceFilePathFileNm);
        System.out.println("destFilePathNmFileNm: " + destFilePathNmFileNm);

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
            System.out.println("destPath: " + destPath);

            File destPathDir = new File(destPath);

            System.out.println("destPathDir: " + destPathDir);

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
}
