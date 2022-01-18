package colorSpaceConverter;

import colorSpaces.HSVColorSpace;
import image.Image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

public class RGBtoHSV implements Converter{
    @Override
    public void directConversion(Image sourceImage) throws IOException {
        HSVColorSpace hsvColorSpace = new HSVColorSpace();
        ComponentColorModel hsvModel = new ComponentColorModel(HSVColorSpace.INSTANCE, false,
                false, Transparency.TRANSLUCENT, DataBuffer.TYPE_BYTE);
        BufferedImage hsvImg = new BufferedImage(hsvModel, hsvModel.createCompatibleWritableRaster(sourceImage.getWidth(),
                sourceImage.getHeight()), hsvModel.isAlphaPremultiplied(), null);
        WritableRaster hsvRaster = hsvImg.getRaster();

        BufferedImage hImg = new BufferedImage(sourceImage.getWidth(), sourceImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster hRaster = hImg.getRaster();
        BufferedImage sImg = new BufferedImage(sourceImage.getWidth(), sourceImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster sRaster = sImg.getRaster();
        BufferedImage vImg = new BufferedImage(sourceImage.getWidth(), sourceImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster vRaster = vImg.getRaster();

        for(int y = 0; y < sourceImage.getHeight(); y++){
            for(int x = 0; x < sourceImage.getWidth(); x++){
                float[] pixel = hsvColorSpace.fromRGB(sourceImage.getPixel(y,x));

                hsvRaster.setDataElements(x, y, new byte[] {(byte) (pixel[0]), (byte) (pixel[1]), (byte) (pixel[2])});
                hRaster.setDataElements(x, y, new byte[] {(byte) pixel[0]});
                sRaster.setDataElements(x, y, new byte[] {(byte) pixel[1]});
                vRaster.setDataElements(x, y, new byte[] {(byte) pixel[2]});
            }
        }
        File output = new File("HSV\\imageHSV.jpg");
        ImageIO.write(hsvImg, "jpg", output);
        File hOutput = new File("HSV\\H.jpg");
        ImageIO.write(hImg, "jpg", hOutput);
        File sOutput = new File("HSV\\S.jpg");
        ImageIO.write(sImg, "jpg", sOutput);
        File vOutput = new File("HSV\\V.jpg");
        ImageIO.write(vImg, "jpg", vOutput);
    }

    @Override
    public void reverseConversion(Image sourceImage) {

    }
}
