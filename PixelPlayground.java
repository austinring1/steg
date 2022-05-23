import java.awt.Color;

public class PixelPlayground {
public static void main(String[] args){

Picture waterPic = new Picture("water.jpg");

waterPic.explore();

//Picture beachPicNoBlue = grayscale(beachPic);

//beachPicNoBlue.explore();
Picture waterPicFixed = grayscale(waterPic);

waterPicFixed.explore();

}


 public static Picture zeroBlue(Picture picture){
    Picture newPic = new Picture(picture);

    Pixel[][] pixels = newPic.getPixels2D();

    for(int row = 0; row < pixels.length; row++){
        for(int column = 0; column < pixels[row].length; column++)
        {
            pixels[row][column].setBlue(0);
        }
    }


    return newPic;
 }

 public static Picture negate(Picture picture){
    Picture newPic = new Picture(picture);

    Pixel[][] pixels = newPic.getPixels2D();

    for(int row = 0; row < pixels.length; row++){
        for(int column = 0; column < pixels[row].length; column++)
        {
            pixels[row][column].setBlue(255 - pixels[row][column].getBlue());
            pixels[row][column].setRed(255 - pixels[row][column].getRed());
            pixels[row][column].setGreen(255 - pixels[row][column].getGreen());
        }
    }


    return newPic;
 }

 public static Picture grayscale(Picture picture){
    Picture newPic = new Picture(picture);

    Pixel[][] pixels = newPic.getPixels2D();

    for(int row = 0; row < pixels.length; row++){
        for(int column = 0; column < pixels[row].length; column++)
        {
            pixels[row][column].setBlue((pixels[row][column].getBlue() + pixels[row][column].getRed() + pixels[row][column].getGreen()) / 3);
            pixels[row][column].setRed((pixels[row][column].getBlue() + pixels[row][column].getRed() + pixels[row][column].getGreen()) / 3);
            pixels[row][column].setGreen((pixels[row][column].getBlue() + pixels[row][column].getRed() + pixels[row][column].getGreen()) / 3);
        }
    }


    return newPic;
 }

 public static Picture fixUnderwater(Picture picture){
    Picture newPic = new Picture(picture);

    Pixel[][] pixels = newPic.getPixels2D();

    for(int row = 0; row < pixels.length; row++){
        for(int column = 0; column < pixels[row].length; column++)
        {
                
            
        }
    }


    return newPic;
 }

}
