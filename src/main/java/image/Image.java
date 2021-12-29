package image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {
    private BufferedImage bufferedImage;
    private String pathToImage;
    private int width;
    private int height;

    public Image(String path) {
        this.pathToImage = path;
        try {
            bufferedImage = ImageIO.read(new File(path));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Create buffered image by given width and height, with color space int_argb, 4 bytes per pixel
     * @param width
     * @param height
     */
    public Image(int width, int height) {
        this.width = width;
        this.height = height;
        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }

}
