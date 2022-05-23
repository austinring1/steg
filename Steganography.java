import java.awt.Color;
import java.util.ArrayList;
import java.awt.Point;

public class Steganography {
    public static void main(String[] args){
        
Picture hall = new Picture("femaleLionAndHall.jpg");
Picture robot2 = new Picture("robot.jpg");
Picture flower2 = new Picture("flower1.jpg"); 

// hide pictures
Picture hall2 = hidePictureCustom(hall, robot2, 50, 300);
Picture hall3 = hidePictureCustom(hall2, flower2, 115, 275);
hall3.explore();
if(!isSame (hall, hall3))
{
Picture hall4 = showDifferentArea(hall, findDifferences(hall, hall3));
hall4.show();
Picture unhiddenHall3 = revealPicture(hall3);
unhiddenHall3.show();
}

}

    public static void clearLow(Pixel p){
        p.setBlue(((int) p.getBlue() / 4) * (4));
        p.setRed(((int) p.getRed() / 4) * (4));
        p.setGreen(((int) (p.getGreen() / 4) * (4)));
}

    public static Picture testClearLow(Picture picture){
        Picture pictureCopy = new Picture(picture);

        Pixel[][] pixelArray = pictureCopy.getPixels2D();

        for(int rows = 0; rows < pixelArray.length; rows++){
            for (int cols = rows; cols < pixelArray[rows].length; cols++){
                clearLow(pixelArray[rows][cols]);
            }
        }

        return pictureCopy;
    }

    public static void setLow(Pixel p, Color c) 
{
    clearLow(p);
    p.setBlue(p.getBlue() + (c.getBlue() / 64));
    p.setRed(p.getRed() + (c.getRed() / 64));
    p.setGreen(p.getGreen() + (c.getGreen() / 64));
}

public static Picture testSetLow(Picture picture, Color color){
    Picture pictureCopy = new Picture(picture);

    Pixel[][] pixelArray = pictureCopy.getPixels2D();

    for(int rows = 0; rows < pixelArray.length; rows++){
        for (int cols = 0; cols < pixelArray[rows].length; cols++){
            setLow(pixelArray[rows][cols], color);
        }
    }
return pictureCopy;
}

public static Picture revealPicture(Picture hidden) 
{ 
    Picture copy = new Picture(hidden); 
    Pixel[][] pixels = copy.getPixels2D();

    for (int r = 0; r < pixels.length; r++)
    { 
    for (int c = 0; c < pixels[0].length; c++)
    { 	
        Color col = pixels[r][c].getColor();
        pixels[r][c].setBlue((col.getBlue() % 4) * 64);
        pixels[r][c].setRed((col.getRed() % 4) * 64);
        pixels[r][c].setGreen((col.getGreen() % 4) * 64);
    
    
    }
}
    return copy; 
}
    public static boolean canHide(Picture source, Picture secret){
        if((source.getHeight() >= secret.getHeight()) && (source.getWidth() >= secret.getHeight())){
            return true;
        }else{
            return false;
        }
    }

    public static boolean isSame(Picture picture1, Picture picture2){
        Picture picture1Copy = new Picture(picture1);
        Picture picture2Copy = new Picture(picture2);

            Pixel[][] pixel1Copy = picture1Copy.getPixels2D();
            Pixel[][] pixel2Copy = picture2Copy.getPixels2D();
            
            for(int rows = 0, r = 0; rows < pixel1Copy.length && r < pixel2Copy.length; rows++, r++){
                for (int cols = 0, c = 0; cols < pixel1Copy[0].length && c < pixel2Copy[0].length; cols++, c++){
                    if(pixel1Copy[rows][cols].getRed() != (pixel2Copy[rows][cols].getRed())){
                        return false; 
                    }
                }
        }
        return true;
           
    } 

    public static ArrayList<Point> findDifferences(Picture picture1, Picture picture2){
       ArrayList<Point> differences = new ArrayList<Point>();
        Picture Copy1 = new Picture(picture1);
        Picture Copy2 = new Picture(picture2);
            Pixel[][] picture1Copy = Copy1.getPixels2D();
            Pixel[][] picture2Copy = Copy2.getPixels2D();
            for(int rows = 0, r = 0; rows < picture1Copy.length && r < picture2Copy.length; rows++, r++){
                for (int cols = 0, c = 0; cols < picture1Copy[0].length && c < picture2Copy[0].length; cols++, c++){
                    if(!((picture1Copy[rows][cols].getColor()).equals((picture2Copy[rows][cols].getColor())))){
                        int x = picture1Copy[rows][cols].getX();
                        int y = picture1Copy[rows][cols].getY();
                        differences.add(new Point(x,y));
                    }
                }   
        }
    return differences;
    }

    public static Picture showDifferentArea(Picture source, ArrayList<Point> list){
        Picture changed = new Picture(source);
        Pixel[][] changedList = changed.getPixels2D();
        
        int Xmin = (int) list.get(0).getX();
        int Xmax = (int) list.get(0).getX();
        int Ymin = (int) list.get(0).getY();
        int Ymax = (int) list.get(0).getY();
        

        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getX() < Xmin){
                Xmin = (int) (list.get(i).getX());
            }
            if(list.get(i).getX() > Xmax){
                Xmax = (int) (list.get(i).getX());
            }
            if(list.get(i).getX() < Ymin){
                Ymin = (int) (list.get(i).getY());
            }
            if(list.get(i).getY() > Ymin){
                Ymax = (int) (list.get(i).getY());
            }

        }

            for(int rows = 0; rows < changedList.length; rows++){
                for (int cols = 0; cols < changedList[0].length; cols++){
                    if ((rows == Xmax && cols <= Ymax && cols >= Ymin) || (rows == Xmin && cols <= Ymax && cols >= Ymin) || (cols == Ymax && rows <= Xmax && rows >= Xmin) || (cols == Ymin && rows <= Xmax && rows >= Xmin)) {
                        changedList[cols][rows].setRed(255);
                        changedList[cols][rows].setBlue(0);
                        changedList[cols][rows].setGreen(0);
                    }
                    }
                }
        
        return changed;
    }


    public static Picture hidePicture(Picture source, Picture secret){
        Picture sourceCopy = new Picture(source);
        Picture secretCopy = new Picture(secret);

            Pixel[][] pixelSourceCopy = sourceCopy.getPixels2D();
            Pixel[][] pixelSecretCopy = secretCopy.getPixels2D();
    
            for(int rows = 0; rows < pixelSourceCopy.length; rows++){
                for (int cols = 0; cols < pixelSourceCopy[rows].length; cols++){
                    clearLow(pixelSourceCopy[rows][cols]);
                    setLow(pixelSourceCopy[rows][cols], pixelSecretCopy[rows][cols].getColor());
                }
        }
        return sourceCopy;
    }

    public static Picture hidePictureCustom(Picture source, Picture secret, int startRow, int startColumn){
        Picture sourceCopy = new Picture(source);
        Picture secretCopy = new Picture(secret);

            Pixel[][] pixelSourceCopy = sourceCopy.getPixels2D();
            Pixel[][] pixelSecretCopy = secretCopy.getPixels2D();
    
            for(int rows = startRow, r = 0; rows < pixelSourceCopy.length && r < pixelSecretCopy.length; rows++, r++){
                for (int cols = startColumn, c = 0; cols < pixelSourceCopy[0].length && c < pixelSecretCopy[0].length; cols++, c++){
                    setLow(pixelSourceCopy[rows][cols], pixelSecretCopy[r][c].getColor());
                }
        }
        return sourceCopy;
    
    }

};
