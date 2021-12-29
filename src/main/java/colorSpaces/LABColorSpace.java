package colorSpaces;


import java.awt.*;
import java.awt.color.ColorSpace;

final public class LABColorSpace extends ColorSpace {
    static final ColorSpace INSTANCE = new LABColorSpace();
    final ColorSpace sRGB = getInstance(CS_sRGB);

    private LABColorSpace() {
        super(ColorSpace.TYPE_Lab, 3);
    }


    @Override
    public float[] toRGB(float[] colorvalue) {
        return new float[0];
    }

    @Override
    public float[] fromRGB(float[] rgbvalue) {
        return new float[0];
    }

    @Override
    public float[] toCIEXYZ(float[] colorvalue) {
        return new float[0];
    }

    @Override
    public float[] fromCIEXYZ(float[] colorvalue) {
        return new float[0];
    }
}
