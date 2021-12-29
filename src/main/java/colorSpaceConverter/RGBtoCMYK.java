package colorSpaceConverter;
import colorSpaces.CMYKColorSpace;
import image.Image;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class RGBtoCMYK implements Converter{
    @Override
    public void directConversion(Image sourceImage) throws IOException {
        CMYKColorSpace cmykColorSpace = new CMYKColorSpace();
        ComponentColorModel cmykModel = new ComponentColorModel(CMYKColorSpace.INSTANCE, false,
                false, Transparency.TRANSLUCENT, DataBuffer.TYPE_BYTE);
        BufferedImage cmykImg = new BufferedImage(cmykModel, cmykModel.createCompatibleWritableRaster(sourceImage.getWidth(),
                sourceImage.getHeight()), cmykModel.isAlphaPremultiplied(), null);
        WritableRaster cmykRaster = cmykImg.getRaster();

        for(int y = 0; y < sourceImage.getHeight(); y++){
            for(int x = 0; x < sourceImage.getWidth(); x++){
                float[] pixel = cmykColorSpace.fromRGB(sourceImage.getPixel(y,x));

                cmykRaster.setDataElements(x, y, new byte[] {(byte) (255 - pixel[0]), (byte) (255 - pixel[1]), (byte) (255 - pixel[2]),
                        (byte) (255 - pixel[3])});
            }
        }
        File output = new File("image.jpg");
        ImageIO.write(cmykImg, "jpg", output);
    }

    @Override
    public void reverseConversion(Image sourceImage) {

    }
}
