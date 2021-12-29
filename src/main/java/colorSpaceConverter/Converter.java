package colorSpaceConverter;


import image.Image;

import java.io.IOException;

public interface Converter {
    void directConversion(Image sourceImage) throws IOException;
    void reverseConversion(Image sourceImage);
}
