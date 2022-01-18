package colorSpaceConverter;

import colorSpaces.LABColorSpace;
import image.Image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

public class RGBtoLab implements Converter{
    @Override
    public void directConversion(Image sourceImage) throws IOException {
        LABColorSpace labColorSpace = new LABColorSpace();
        ComponentColorModel labModel = new ComponentColorModel(LABColorSpace.INSTANCE, false,
                false, Transparency.TRANSLUCENT, DataBuffer.TYPE_BYTE);
        BufferedImage labImg = new BufferedImage(labModel, labModel.createCompatibleWritableRaster(sourceImage.getWidth(),
                sourceImage.getHeight()), labModel.isAlphaPremultiplied(), null);
        WritableRaster labRaster = labImg.getRaster();
        BufferedImage labImgL = new BufferedImage(labModel, labModel.createCompatibleWritableRaster(sourceImage.getWidth(),
                sourceImage.getHeight()), labModel.isAlphaPremultiplied(), null);
        WritableRaster labRasterL = labImgL.getRaster();

        BufferedImage lImg = new BufferedImage(sourceImage.getWidth(), sourceImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster lRaster = lImg.getRaster();
        BufferedImage aImg = new BufferedImage(sourceImage.getWidth(), sourceImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster aRaster = aImg.getRaster();
        BufferedImage bImg = new BufferedImage(sourceImage.getWidth(), sourceImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster bRaster = bImg.getRaster();

        for(int y = 0; y < sourceImage.getHeight(); y++) {
            for (int x = 0; x < sourceImage.getWidth(); x++) {
                float[] pixel = labColorSpace.fromRGB(sourceImage.getPixel(y,x));

                labRaster.setDataElements(x, y, new byte[] {(byte) (pixel[0]), (byte) (pixel[1]), (byte) (pixel[2])});
                lRaster.setDataElements(x, y, new byte[] {(byte) pixel[0]});
                aRaster.setDataElements(x, y, new byte[] {(byte) pixel[1]});
                bRaster.setDataElements(x, y, new byte[] {(byte) pixel[2]});
            }
        }
        File output = new File("Lab\\imageLab.jpg");
        ImageIO.write(labImg, "jpg", output);
        File lOutput = new File("Lab\\L.jpg");
        ImageIO.write(lImg, "jpg", lOutput);
        File aOutput = new File("Lab\\a.jpg");
        ImageIO.write(aImg, "jpg", aOutput);
        File bOutput = new File("Lab\\b.jpg");
        ImageIO.write(bImg, "jpg", bOutput);
    }

    @Override
    public void reverseConversion(Image sourceImage) {

    }
}
