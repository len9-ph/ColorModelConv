import colorSpaces.CMYKColorSpace;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\Леонид\\Desktop\\cat_full.jpg";
        BufferedImage img = ImageIO.read(new File(path));
        int height = img.getHeight();
        int width = img.getWidth();

        ComponentColorModel cmykModel = new ComponentColorModel(CMYKColorSpace.INSTANCE, false,
                false, Transparency.TRANSLUCENT, DataBuffer.TYPE_BYTE);
        BufferedImage cmykImg = new BufferedImage(cmykModel, cmykModel.createCompatibleWritableRaster(width, height),
                cmykModel.isAlphaPremultiplied(), null);
        WritableRaster cmykRaster = cmykImg.getRaster();

        int R, G, B, pixel;
        float Rc, Gc, Bc, K, C, M, Y;

        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++) {
                pixel = img.getRGB(x, y);
                //System.out.println(pixel);
                R = 1 - (pixel >> 16) & 0xff;
                G = 1 - (pixel >> 8) & 0xff;
                B = 1 - (pixel) & 0xff;

                Rc = (R / 255f);
                Gc = (G / 255f);
                Bc = (B / 255f);
                K = 1 - Math.min(Bc, Math.min(Rc, Gc));
                if (K == 1f) {
                    C = M = Y = 0;
                }
                else {
                    C = (1 - Rc);
                    M = (1 - Gc);
                    Y = (1 - Bc);
                }
                cmykRaster.setDataElements(x, y, new byte[] {(byte)(C * 255), (byte) (M * 255), (byte) (Y * 255),
                        (byte) (K * 255)});
            }
        }
        File output = new File("image.jpg");
        ImageIO.write(cmykImg, "jpg", output);
    }
}
