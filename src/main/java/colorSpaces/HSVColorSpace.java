package colorSpaces;

import java.awt.*;
import java.awt.color.ColorSpace;

public class HSVColorSpace extends ColorSpace {

    public static final ColorSpace INSTANCE = new HSVColorSpace();

    public HSVColorSpace() { super(ColorSpace.TYPE_HSV, 3);}

    @Override
    public float[] toRGB(float[] colorvalue) {
        int Hi = (int) ((colorvalue[0] / 60) % 6);
        float Vmin = ((100 - colorvalue[1]) * colorvalue[2]) / 100;
        float a = (colorvalue[0] - Vmin) * ((colorvalue[0] % 60)/60);
        float Vinc = Vmin + a;
        float Vdec = colorvalue[2] - a;
        float[] rgb = new float[3];
        switch (Hi){
            case 0:
                rgb[0] = colorvalue[2];
                rgb[1] = Vinc;
                rgb[2] = Vmin;
            case 1:
                rgb[0] = Vdec;
                rgb[1] = colorvalue[2];
                rgb[2] = Vmin;
            case 2:
                rgb[0] = Vmin;
                rgb[1] = colorvalue[2];
                rgb[2] = Vinc;
            case 3:
                rgb[0] = Vmin;
                rgb[1] = Vdec;
                rgb[2] = colorvalue[2];
            case 4:
                rgb[0] = Vinc;
                rgb[1] = Vmin;
                rgb[2] = colorvalue[2];
            case 5:
                rgb[0] = colorvalue[2];
                rgb[1] = Vmin;
                rgb[2] = Vdec;


        }
        // Добавть умножение на 255/100
        return rgb;
    }

    @Override
    public float[] fromRGB(float[] rgbvalue) {
        float MAX = Math.max(rgbvalue[0], Math.max(rgbvalue[1],rgbvalue[2]));
        float MIN = Math.min(rgbvalue[0], Math.min(rgbvalue[1],rgbvalue[2]));
        float H = 0, S, V = MAX;
        if(MAX == MIN)
            H = 0;
        else
            if(MAX == rgbvalue[0] && rgbvalue[1] >= rgbvalue[2])
                H = 6 / 36 * (rgbvalue[1] - rgbvalue[2])/(MAX - MIN) + 1;
            else
                if(MAX == rgbvalue[0] && rgbvalue[1] < rgbvalue[2])
                    H = 6 / 36 * (rgbvalue[1] - rgbvalue[2])/(MAX - MIN) + 1;
                else
                    if(MAX == rgbvalue[1])
                        H = 6 / 36 * (rgbvalue[2] - rgbvalue[0])/(MAX - MIN) + 12 / 36;
                    else
                        if(MAX == rgbvalue[2])
                            H = 6 / 36 * (rgbvalue[0] - rgbvalue[2])/(MAX - MIN) + 24 / 36;

        if(MAX == 0)
            S = 0;
        else
            S = 1 - (MIN / MAX) * 255;
        if (H > 360)
            H = 255;
        else{
            H = (float) (H / 360. * 255);
        }

        //System.out.println("H=" + H);
//        H = H / 360 ;
//        V = V / 255 ;
//        if (H > 100)
//            System.out.println(H);
//            H = 100;
//        if(V > 100)
//            V = 100;
//        if(S > 100)
//            S = 100;

        return new float[] {H, S, V} ;
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
