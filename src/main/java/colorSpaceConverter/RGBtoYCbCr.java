package colorSpaceConverter;

import colorSpaces.CMYKColorSpace;
import colorSpaces.YCbCrColorSpace;
import image.Image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

public class RGBtoYCbCr implements Converter{
    @Override
    public void directConversion(Image sourceImage) throws IOException {
        YCbCrColorSpace yCbCrColorSpace = new YCbCrColorSpace();
        ComponentColorModel ycbcrModel = new ComponentColorModel(YCbCrColorSpace.INSTANCE, false,
                false, Transparency.TRANSLUCENT, DataBuffer.TYPE_BYTE);
        BufferedImage ycbcrImg = new BufferedImage(ycbcrModel, ycbcrModel.createCompatibleWritableRaster(sourceImage.getWidth(),
                sourceImage.getHeight()), ycbcrModel.isAlphaPremultiplied(), null);
        WritableRaster cmykRaster = ycbcrImg.getRaster();
        BufferedImage yImg = new BufferedImage(sourceImage.getWidth(), sourceImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster yRaster = yImg.getRaster();
        BufferedImage CbImg = new BufferedImage(sourceImage.getWidth(), sourceImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster CbRaster = CbImg.getRaster();
        BufferedImage CrImg = new BufferedImage(sourceImage.getWidth(), sourceImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster CrRaster = CrImg.getRaster();

        for(int y = 0; y < sourceImage.getHeight(); y++){
            for(int x = 0; x < sourceImage.getWidth(); x++){
                float[] pixel = yCbCrColorSpace.fromRGB(sourceImage.getPixel(y,x));

                cmykRaster.setDataElements(x, y, new byte[] {(byte) (pixel[0]), (byte) (pixel[1]), (byte) (pixel[2]),});
                yRaster.setDataElements(x, y, new byte[] {(byte) pixel[0]});
                CbRaster.setDataElements(x, y, new byte[] {(byte) pixel[1]});
                CrRaster.setDataElements(x, y, new byte[] {(byte) pixel[2]});
            }
        }
        File output = new File("YCbCr\\imageYCbCr.jpg");
        ImageIO.write(ycbcrImg, "jpg", output);
        File yOutput = new File("YCbCr\\Y.jpg");
        ImageIO.write(yImg, "jpg", yOutput);
        File CbOutput = new File("YCbCr\\Cb.jpg");
        ImageIO.write(CbImg, "jpg", CbOutput);
        File CrOutput = new File("YCbCr\\Cr.jpg");
        ImageIO.write(CrImg, "jpg", CrOutput);
    }

    @Override
    public void reverseConversion(Image sourceImage) {

    }
}
