package colorSpaces;

import java.awt.color.ColorSpace;

public class YCbCrColorSpace extends ColorSpace {

    public static final ColorSpace INSTANCE = new YCbCrColorSpace();

    public YCbCrColorSpace() { super(TYPE_YCbCr, 3); }

    @Override
    public float[] toRGB(float[] colorvalue) {
        float R, G, B;
        R = (float) ((298.082 * (colorvalue[0] - 16) + 0. + 408.583 * (colorvalue[0] - 16)) / 256);
        G = (float) ((298.082 * (colorvalue[0] - 16) + -100.291 * (colorvalue[1] - 128) + -208.120 * (colorvalue[1] - 128)) / 256);
        B = (float) ((298.082 * (colorvalue[0] - 16) + 516.411 * (colorvalue[1] - 128) + 0.) / 256);
        return new float[] {R, G, B};
    }

    @Override
    public float[] fromRGB(float[] rgbvalue) {
        float Y, Cb, Cr;
        Y = (float) (16 + (65.738 * rgbvalue[0] + 129.057 * rgbvalue[1] + 25.064 * rgbvalue[2]) / 256);
        Cb = (float) (128 + (-37.945 * rgbvalue[0] + -74.494 * rgbvalue[1] + 112.439 * rgbvalue[2]) / 256);
        Cr = (float) (128 + (112.439 * rgbvalue[0] + -94.154 * rgbvalue[1] + -18.285 * rgbvalue[2]) / 256);

        return new float[] {Y, Cb, Cr};
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
