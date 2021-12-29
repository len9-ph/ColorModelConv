package colorSpaces;


import java.awt.color.ColorSpace;

public final class CMYKColorSpace extends ColorSpace {

    public static final ColorSpace INSTANCE = new CMYKColorSpace();

    final ColorSpace sRGB = getInstance(CS_sRGB);

    private CMYKColorSpace() {
        super(ColorSpace.TYPE_CMYK, 4);
    }

    public static ColorSpace getInstance() {
        return INSTANCE;
    }

    @Override
    public float[] toRGB(float[] colorvalue) {
        return new float[]{
                (1 - colorvalue[0]) * (1 - colorvalue[3]),
                (1 - colorvalue[1]) * (1 - colorvalue[3]),
                (1 - colorvalue[2]) * (1 - colorvalue[3])
        };
    }

    @Override
    public float[] fromRGB(float[] rgbvalue) {
        float c = 1 - rgbvalue[0];
        float m = 1 - rgbvalue[1];
        float y = 1 - rgbvalue[2];

        float k = Math.min(c, Math.min(m,y));
        return new float[]{(c - k), (m - k), (y - k), k};
    }

    @Override
    public float[] toCIEXYZ(float[] colorvalue) {
        return sRGB.toCIEXYZ(toRGB(colorvalue));
    }

    @Override
    public float[] fromCIEXYZ(float[] colorvalue) {
        return sRGB.fromCIEXYZ(fromRGB(colorvalue));
    }
}
