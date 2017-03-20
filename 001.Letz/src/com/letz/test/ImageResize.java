package com.letz.test;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.util.StopWatch;

import com.mortennobel.imagescaling.AdvancedResizeOp;
import com.mortennobel.imagescaling.ResampleOp;

public class ImageResize {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        File f = new File("D:\\001.workspace\\007.LanMessenger\\Received Files\\NoImage.gif");
        BufferedImage src = ImageIO.read(f);
        int wantWeight = 1000;
        int wantHeight = 1000;
        int[] targetSize = { 50, 60, 72, 80, 122, 150, 160, 234, 260, 274, 300, 320, 485, 500, 100, 120, 130, 135, 190, 220, 230, 295, 600, 640, 860, 2000 };

        StopWatch sw = new StopWatch();
        sw.start();
        for (int i = 0; i < targetSize.length; i++) {
            ResampleOp resampleOp = new ResampleOp(targetSize[i], targetSize[i]);
            resampleOp.setUnsharpenMask(AdvancedResizeOp.UnsharpenMask.VerySharp);
            BufferedImage rescaled = resampleOp.filter(src, null);

            ImageIO.write(rescaled, "JPG",
                    new File("D:\\001.workspace\\007.LanMessenger\\Received Files\\NoImage_" + targetSize[i] + "_" + targetSize[i] + ".jpg"));
        }
        sw.stop();
        System.out.println(sw.prettyPrint());

    }

    public static void makeThumbnail(BufferedImage src, int w, int h, String fileName) throws IOException {
        BufferedImage thumbImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = thumbImage.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics.drawImage(src, 0, 0, w, h, null);

        ResampleOp resampleOp2 = new ResampleOp(w, h);
        resampleOp2.setUnsharpenMask(AdvancedResizeOp.UnsharpenMask.VerySharp);
        // resampleOp2.setUnsharpenMask(AdvancedResizeOp.UnsharpenMask.Soft);
        BufferedImage rescaled2 = resampleOp2.filter(thumbImage, null);
        ImageIO.write(rescaled2, "JPG",
                new File("C:\\test\\" + fileName));

    }

    public static BufferedImage rotate90(BufferedImage bi)
    {
        int width = bi.getWidth();
        int height = bi.getHeight();

        BufferedImage biFlip = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                biFlip.setRGB(height - 1 - j, i, bi.getRGB(i, j));

        return biFlip;
    }

    public static BufferedImage rotate270(BufferedImage bi)
    {
        int width = bi.getWidth();
        int height = bi.getHeight();

        BufferedImage biFlip = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                biFlip.setRGB(j, width - 1 - i, bi.getRGB(i, j));
            }
        }
        return biFlip;
    }

    public static BufferedImage rotate180(BufferedImage bi)
    {
        int width = bi.getWidth();
        int height = bi.getHeight();

        BufferedImage biFlip = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                biFlip.setRGB(width - 1 - i, height - 1 - j, bi.getRGB(i, j));
            }
        }
        return biFlip;
    }
}