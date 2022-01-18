package colorSpaceConverter;
import colorSpaces.CMYKColorSpace;
import image.Image;

import java.awt.*;
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

        BufferedImage сImg = new BufferedImage(sourceImage.getWidth(), sourceImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster cRaster = сImg.getRaster();
        BufferedImage mImg = new BufferedImage(sourceImage.getWidth(), sourceImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster mRaster = mImg.getRaster();
        BufferedImage yImg = new BufferedImage(sourceImage.getWidth(), sourceImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster yRaster = yImg.getRaster();
        BufferedImage kImg = new BufferedImage(sourceImage.getWidth(), sourceImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster kRaster = kImg.getRaster();

        for(int y = 0; y < sourceImage.getHeight(); y++){
            for(int x = 0; x < sourceImage.getWidth(); x++){
                float[] pixel = cmykColorSpace.fromRGB(sourceImage.getPixel(y,x));

                cmykRaster.setDataElements(x, y, new byte[] {(byte) (255 - pixel[0]), (byte) (255 - pixel[1]), (byte) (255 - pixel[2]),
                        (byte) (255 - pixel[3])});
                cRaster.setDataElements(x, y, new byte[] {(byte) pixel[0]});
                mRaster.setDataElements(x, y, new byte[] {(byte) pixel[1]});
                yRaster.setDataElements(x, y, new byte[] {(byte) pixel[2]});
                kRaster.setDataElements(x, y, new byte[] {(byte) pixel[3]});
            }
        }
        File output = new File("CMYK\\imageCMYK.jpg");
        ImageIO.write(cmykImg, "jpg", output);
        File COutput = new File("CMYK\\C.jpg");
        ImageIO.write(сImg, "jpg", COutput);
        File MOutput = new File("CMYK\\M.jpg");
        ImageIO.write(mImg, "jpg", MOutput);
        File yOutput = new File("CMYK\\Y.jpg");
        ImageIO.write(yImg, "jpg", yOutput);
        File kOutput = new File("CMYK\\K.jpg");
        ImageIO.write(kImg, "jpg", kOutput);
    }

    @Override
    public void reverseConversion(Image sourceImage) {
        CMYKColorSpace cmykColorSpace = new CMYKColorSpace();


        String path = "C:\\Users\\leoni\\Desktop\\imagecmyk.jpg";
        try {

            BufferedImage img = ImageIO.read(new File(path));
            final byte[] pixels = ((DataBufferByte)img.getRaster().getDataBuffer()).getData();
            final int width = img.getWidth();
            final int height = img.getHeight();
            BufferedImage rgbImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            WritableRaster raster = rgbImg.getRaster();
            int[][] result = new int[height][width];
            final int pixelLength = 4;
            for (int pixel = 0, row = 0, col = 0; pixel + 3 < pixels.length; pixel += pixelLength){
                float c = (pixels[pixel] & 0xff) << 24;
                float m = (pixels[pixel] & 0xff) << 16;
                float y = (pixels[pixel] & 0xff) << 8;
                float k = (pixels[pixel] & 0xff);
                float[] pix = cmykColorSpace.toRGB(new float[] {c, m, y, k});

                raster.setDataElements(col, row, new byte[] {(byte) (255 - pix[0]), (byte) (255 - pix[1]), (byte) (255 - pix[2])});
            }
            File output = new File("imagergb.jpg");
            ImageIO.write(img, "jpg", output);
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
}
