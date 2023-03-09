import com.neu.image_manipulation.model.ImageBrightDark;
import com.neu.image_manipulation.model.ImageFlip;
import com.neu.image_manipulation.model.ImageInLIV;
import com.neu.image_manipulation.model.ImageInRGB;
import com.neu.image_manipulation.model.entity.Image;
import com.neu.image_manipulation.model.entity.Pixel;
import com.neu.image_manipulation.model.impl.ImageBrightDarkImpl;
import com.neu.image_manipulation.model.impl.ImageFlipImpl;
import com.neu.image_manipulation.model.impl.ImageInLIVImpl;
import com.neu.image_manipulation.model.impl.ImageInRGBImpl;
import com.neu.image_manipulation.model.impl.ImageManipulationImpl;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;



/**
 * This class contains utility methods to read a PPM image from file and simply print its contents. Feel free to change this method 
 *  as required.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file. 
   */
  public static void readPPM(String filename) throws FileNotFoundException {
    Scanner sc;
    
    try {
        sc = new Scanner(new FileInputStream(filename));
    }
    catch (FileNotFoundException e) {
        System.out.println("File "+filename+ " not found!");
        return;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
        String s = sc.nextLine();
        if (s.charAt(0)!='#') {
            builder.append(s+System.lineSeparator());
        }
    }
    
    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token; 

    token = sc.next();
    if (!token.equals("P3")) {
        System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: "+width);
    int height = sc.nextInt();
    System.out.println("Height of image: "+height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): "+maxValue);
    
    for (int i=0;i<height;i++) {
        for (int j=0;j<width;j++) {
            int r = sc.nextInt();
            int g = sc.nextInt();
            int b = sc.nextInt();
            System.out.println("Color of pixel ("+j+","+i+"): "+ r+","+g+","+b);
        }
    }

  }

  //demo main
  public static void main(String []args) throws IOException {
      String filename;
      
      if (args.length>0) {
          filename = args[0];
      }
      else {
          filename = "resources/Koala.ppm";
      }
    ImageManipulationImpl imgVal = new ImageManipulationImpl();
    ImageBrightDark imageBright = new ImageBrightDarkImpl();
    ImageFlip imageFlip = new ImageFlipImpl();
    ImageInRGB imgRgb = new ImageInRGBImpl();
    ImageInLIV imgLIV = new ImageInLIVImpl();
      Image image = imgVal.loadImageInPPM(filename);
      imgVal.generateImage(image,"image");
      Image brightImage = imageBright.brightenImage(image,50);
      imgVal.generateImage(brightImage,"image-bright");
      Image darkenImage = imageBright.darkenImage(image,50);
      imgVal.generateImage(darkenImage,"image-dark");
      Image[] splitImage = imgRgb.splitIntoRGBImages(image);
      imgVal.generateImage(splitImage,"image",true);
      Image combined = imgRgb.combineRGBImages(splitImage);
      imgVal.generateImage(combined,"image-combined");
      Image horizontal = imageFlip.flipImageHorizontally(image);
      imgVal.generateImage(horizontal,"image-horizontal");
      Image vertical = imageFlip.flipImageVertically(image);
      imgVal.generateImage(vertical,"image-vertical");
      Image horizontal_vertical = imageFlip.flipImageHorizontally(vertical);
      imgVal.generateImage(horizontal_vertical,"image-vertical-to-horizontal");
      Image[] images = imgLIV.splitIntoLIV(image);
      imgVal.generateImage(images,"imageInLiv",false);

//      Image greenImage = imgRgb.createGreenComponentOfImage(image);
//      imgVal.generateImage(redImage,"image-red");
//      imgVal.generateImage(greenImage,"image-green");

      //ImageUtil.readPPM(filename);
  }
}

