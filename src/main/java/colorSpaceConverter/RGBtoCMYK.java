package colorSpaceConverter;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

public class RGBtoCMYK implements Converter{
    @Override
    public void directConversion(String pathToImage) {
        File file = new File(pathToImage);
        try {
            BufferedImage image = ImageIO.read(file);
            //Color color = new Color(image.getRGB())

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void reverseConversion(String pathToImage) {
        File imageFile = new File(pathToImage);
        try {
            BufferedImage image = ImageIO.read(imageFile);
            if(image != null) {
                int colorSpaceType = image.getColorModel().getColorSpace().getType();
                if(colorSpaceType == ColorSpace.TYPE_CMYK)
                {
                    BufferedImage rgbImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
                    Raster raster = image.getRaster();

                    for(int i = 0; i < image.getHeight(); i++){
                        for (int j = 0; j < image.getWidth(); j++){
                            raster.getSample(i, j,0);
                            Color color = new Color(ColorSpace.TYPE_CMYK, ColorSpace.TYPE_CMYK, ColorSpace.TYPE_CMYK);
                        }
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
