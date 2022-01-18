import colorSpaceConverter.RGBtoCMYK;
import colorSpaceConverter.RGBtoHSV;
import colorSpaceConverter.RGBtoLab;
import colorSpaceConverter.RGBtoYCbCr;
import image.Image;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\leoni\\Desktop\\cat_full.jpg";
        Image cat = new Image(path);

        RGBtoCMYK converter = new RGBtoCMYK();
        converter.directConversion(cat);
        RGBtoLab conv = new RGBtoLab();
        conv.directConversion(cat);
        RGBtoHSV converterH = new RGBtoHSV();
        converterH.directConversion(cat);
        RGBtoYCbCr comverterY = new RGBtoYCbCr();
        comverterY.directConversion(cat);
    }
}
