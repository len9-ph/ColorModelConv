package image;

/**
 * This class implements one cell of mapOfColors in class Image
 * @see Image
 */
public class Pixel {
    public int red;
    public int green;
    public int blue;

    /**
     * @param red
     * @param green
     * @param blue
     */
    public Pixel(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
    /**
     * @return array that contains values of "pixel"
     */
    public float[] getValues(){
        return new float[]{red, green, blue};
    }
}
