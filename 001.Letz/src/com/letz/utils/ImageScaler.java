/*
 * This software is the confidential and proprietary information of
 * Shinsegae Internatinal Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Shinsegae International.
 */
package com.letz.utils;

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
public class ImageScaler {

    private static Logger logger = LoggerFactory.getLogger(ImageScaler.class);

    public void resize(String inputImagePath, String outputImagePath, int scaledWidth, int scaledHeight)
            throws Exception {

        logger.error("#### Image resize \ninpath|outpath = {}|{}", inputImagePath, outputImagePath);
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

    }

    public void resize(String inputImagePath, String outputImagePath, int scaledWidth)
            throws Exception {

        logger.error("#### Image resize \ninpath|outpath = {}|{}", inputImagePath, outputImagePath);
        // create command
        ConvertCmd cmd = new ConvertCmd();

        // create the operation, add images and operators/options
        IMOperation op = new IMOperation();
        op.addImage(inputImagePath);
        if (scaledWidth < 2000) {
            op.scale(scaledWidth);
        }
        op.addImage(outputImagePath);

        cmd.run(op);
    }
}
