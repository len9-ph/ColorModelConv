package colorSpaces;


import java.awt.color.ColorSpace;

public final class LABColorSpace extends ColorSpace {
    public static final ColorSpace INSTANCE = new LABColorSpace();

    public LABColorSpace() {
        super(ColorSpace.TYPE_Lab, 3);
    }

    public static ColorSpace getInstance() { return  INSTANCE; }

    @Override
    public float[] toRGB(float[] colorvalue) {
        float[] xyz = fromCIEXYZ(colorvalue);
        for (int i = 0; i < xyz.length; i++)
            xyz[i] /= 100.;
        float[] rgb = new float[3];
        rgb[0] = (float) (3.2406 * xyz[0] + -1.5372 * xyz[1] + -0.4986 * xyz[2]);
        rgb[1] = (float) (-0.9689 * xyz[0] + 1.8758 * xyz[1] + 0.0415 * xyz[2]);
        rgb[2] = (float) (0.0557 * xyz[0] + -0.2040 * xyz[1] + 1.0570 * xyz[2]);

        for (int i = 0; i < rgb.length; i++) {
            if (rgb[i] > 0.0031308)
                rgb[i] = func1(rgb[i]);
            else
                rgb[i] *= 12.92;
            rgb[i] *= 255;
        }
        return rgb;
    }

    @Override
    public float[] fromRGB(float[] rgbvalue) {
        float[] xyz = toCIEXYZ(rgbvalue);
        for(int i = 0; i < xyz.length; i++) {
            xyz[i] /= 100;
            if(xyz[i] > 0.008856)
                xyz[i] = (float) Math.cbrt(xyz[i]);
            else
                xyz[i] = (float) ((7.787 * xyz[i]) + 16/116);
        }

        float[] lab = {116 * xyz[0] - 16, 500 * (xyz[0] - xyz[1]), 200 * (xyz[1] - xyz[2])};
        for (int i = 0; i < lab.length; i++)
            lab[i] += 128;
        return lab;
    }

    @Override
    public float[] toCIEXYZ(float[] colorvalue) {
        float[] RGBValues =  {colorvalue[0] / 255, colorvalue[1] / 255, colorvalue[2] / 255 };
        for (int i = 0; i < RGBValues.length; i++) {
            if(RGBValues[i] > 0.4045)
                RGBValues[i] = func(RGBValues[i]);
            else
                RGBValues[i] = (float) (RGBValues[i] / 12.92);
            RGBValues[i] *= 100;
        }

        return new float[] {(float) (0.4124 * RGBValues[0] + 0.3576 * RGBValues[1] + 0.1805 * RGBValues[2]),
                (float) (0.2126 * RGBValues[0] + 0.7152 * RGBValues[1] + 0.0722 * RGBValues[2]),
                (float) (0.0193 * RGBValues[0] + 0.1192 * RGBValues[1] + 0.9505 * RGBValues[2])};
    }

    @Override
    public float[] fromCIEXYZ(float[] colorvalue) {
        float[] xyz = new float[3];
        xyz[0] = (colorvalue[0] + 16) / 116;
        xyz[1] = colorvalue[1] / 500 + xyz[1];
        xyz[2] = xyz[1] - colorvalue[2] / 200;

        for (int i = 0; i < xyz.length; i++) {
            if (Math.cbrt(xyz[i]) > 0.008856)
                xyz[i] = (float) Math.cbrt(xyz[i]);
            else
                xyz[i] = (float) ((xyz[i] - 16. / 116.) / 7.787);
            xyz[i] *= 100;
        }
        return xyz;
    }

    private static float func(float x) {
        double result = Math.pow(((x + 0.055)/1.055), 2.4);
        return (float) result;
    }

    private static float func1(float x) {
        return (float) (1.055 * Math.pow(x, 1 / 2.4) - 0.055);
    }
}
