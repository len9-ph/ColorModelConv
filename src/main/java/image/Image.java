package image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {
    private BufferedImage bufferedImage;
    public Pixel[][] mapOfRGBColors;
    //private String pathToImage;
    private final int width;
    private final int height;

    /**
     * This constructor creates buffered image of given image and
     * builds color map based on it
     * @param path path to image
     */
    public Image(String path) {
        try {
            bufferedImage = ImageIO.read(new File(path));
        } catch (IOException e){
            e.printStackTrace();
        }
        this.width = bufferedImage.getWidth();
        this.height = bufferedImage.getHeight();
        mapOfRGBColors = new Pixel[height][width];
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                int px = bufferedImage.getRGB(x, y);
                Pixel pixel = new Pixel((px >> 16) & 0xff,(px >> 8) & 0xff, (px) & 0xff);
                mapOfRGBColors[y][x] = pixel;
            }
        }
    }

    public float[] getPixel(int x, int y){
        return mapOfRGBColors[x][y].getValues();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /* public Image(int width, int height) {
        this.width = width;
        this.height = height;
        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }*/

}
