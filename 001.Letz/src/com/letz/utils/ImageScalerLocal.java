/*
 * This software is the confidential and proprietary information of
 * Shinsegae Internatinal Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Shinsegae International.
 */
package com.letz.utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Class Name : ImageScaler.java
 * @Description : �씠誘몄� 由ъ궗�씠吏� Util
 * @author UZEN / �씠�슜�꽑
 * @since 2015. 12. 03.
 * @version 1.0
 * @see
 *      Copyright(c) 2016 SHINSEGAE INTERNATIONAL. All rights reserved
 */
public class ImageScalerLocal {

    private static Logger logger = LoggerFactory.getLogger(ImageScalerLocal.class);

    public void resize(String inputImagePath, String outputImagePath, int scaledWidth, int scaledHeight)
            throws Exception {

        logger.error("#### Image resize \ninpath|outpath = {}|{}", inputImagePath, outputImagePath);
        if (!"loc".equals(System.getenv("spring.profiles.active"))) {
            // create command
            ConvertCmd cmd = new ConvertCmd();

            // create the operation, add images and operators/options
            IMOperation op = new IMOperation();
            op.addImage(inputImagePath);
            if (scaledWidth < 2000) {
                op.scale(scaledWidth, scaledHeight);
            }
            op.addImage(outputImagePath);

            cmd.run(op);
        } else {
            File inputFile = new File(inputImagePath);
            BufferedImage inputImage = ImageIO.read(inputFile);
            BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, inputImage.getType());
            Graphics2D g2d = outputImage.createGraphics();
            g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
            g2d.dispose();
            String imageExtension = inputImagePath.substring(inputImagePath.lastIndexOf(".") + 1);
            File folder = new File(outputImagePath).getParentFile();
            if (!folder.exists()) {
                folder.mkdirs();
            }
            ImageIO.write(outputImage, imageExtension, new File(outputImagePath));
        }
    }
}
