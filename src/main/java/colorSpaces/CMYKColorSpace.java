package colorSpaces;


import java.awt.color.ColorSpace;

public final class CMYKColorSpace extends ColorSpace {

    public static final ColorSpace INSTANCE = new CMYKColorSpace();

    final ColorSpace sRGB = getInstance(CS_sRGB);

    public CMYKColorSpace() {
        super(ColorSpace.TYPE_CMYK, 4);
    }

    public static ColorSpace getInstance() {
        return INSTANCE;
    }

    @Override
    public float[] toRGB(float[] colorvalue) {
        return new float[]{
                (255 - colorvalue[0]) * (255 - colorvalue[3]),
                (255 - colorvalue[1]) * (255 - colorvalue[3]),
                (255 - colorvalue[2]) * (255 - colorvalue[3])
        };
    }

    @Override
    public float[] fromRGB(float[] rgbvalue) {
        float c, m, y, k;
        k = Math.min(255 - rgbvalue[0], Math.min(1 - rgbvalue[1], 1 - rgbvalue[2]));
        if(k == 255) {
            c = m = y = 0;
        }else {
            c = 255 - rgbvalue[0];
            m = 255 - rgbvalue[1];
            y = 255 - rgbvalue[2];
        }

        float[] cmyk = {c, m, y, k};
        for(int i = 0; i < cmyk.length; i++){
            if(cmyk[i] > 255)
                cmyk[i] = 255;
            else
                if (cmyk[i] < 0)
                    cmyk[i] = 0;
        }

        return cmyk;
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
