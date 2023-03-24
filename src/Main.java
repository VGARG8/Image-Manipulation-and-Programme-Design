import com.neu.imagemanipulation.controller.Controller;
import com.neu.imagemanipulation.controller.ControllerInterface;
import com.neu.imagemanipulation.model.entity.Image;
import com.neu.imagemanipulation.model.entity.Pixel;
import com.neu.imagemanipulation.model.impl.AdvancedImageMaipulationModel;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;
import com.neu.imagemanipulation.model.impl.ImageManipulationInterface;
import com.neu.imagemanipulation.model.impl.ImageManipulationModel;
import com.neu.imagemanipulation.view.View;
import com.neu.imagemanipulation.view.ViewInterface;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 * The Main class represents the entry point of the Image Manipulation program. This class
 * initializes the Model, View, and Controller, and hands over the control to the Controller.
 *
 * @author [Girish Kumar Adari] [Vaibhav Garg]
 */
public class Main {
  /**
   * The main method is the entry point of the program.It initializes the ImageManipulationModel
   * and View, creates the Controller object, and passes the control to the Controller.
   *
   * @param args the command-line arguments passed to the program
   * @throws IOException if an I/O error occurs
   */
  public static void main(String[] args) throws IOException {
    Image image = loadImageInPPM("Res/gamecontroller.ppm");
    AdvancedImageManipulationInterface model = new AdvancedImageMaipulationModel();
    Image blurImage = model.blur(image);
    generateImage(blurImage,"blurgamecontroller");
    Image sharpenImage = model.sharpen(image);
    generateImage(sharpenImage,"sharpengamecontroller");
    Image greyscale = model.greyscale(image);
    generateImage(greyscale,"greImage");
    Image sepiascale = model.sepiaTone(image);
    generateImage(sepiascale,"sepiaImage");
//    ImageManipulationInterface model = new ImageManipulationModel(); //set up before if needed
//    ViewInterface view = new View();
//    //setup details of view if needed
//
//    //create controller, give it the model and view
//    ControllerInterface controller = new Controller(new InputStreamReader(System.in), System.out,
//            model, view);
//
//    //give control to the controller. Controller relinquishes only when program ends
//    controller.execute();

//    Controller con = new Controller()
  }

  public static Image loadImageInPPM(String filename) throws IOException {
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
     // view.displayNoFileStatus();
      return null;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }
    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      //view.displayInvalidPPM();
    }
    //start generating Image
    if (!sc.hasNextInt()) {
     // view.displayInvalidPPMNoValues();
      return new Image(0, 0, 0);
    }

    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();


    Image image = new Image(height, width, maxValue);
    Pixel[][] pixel = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        pixel[i][j] = new Pixel(r, g, b);
      }
    }
    image.setPixel(pixel);
    return image;
  }
  public static void generateImage(Image image, String filename) {
    List<Integer> pixels = new ArrayList<>();
    Pixel[][] pixelArray = image.getPixel();
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        int r = pixelArray[i][j].getRed();
        int g = pixelArray[i][j].getGreen();
        int b = pixelArray[i][j].getBlue();
        Color color = new Color( r, g, b);

        pixels.add(color.getRGB());
      }
    }
    BufferedImage outputImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
    int[] outputImagePixelData = ((DataBufferInt) outputImg.getRaster().getDataBuffer()).getData();

    for (int i = 0; i < pixels.size(); i++) {
      outputImagePixelData[i] = pixels.get(i);
    }
    try {
      ImageIO.write(outputImg, "png",
          new File("Res/"+filename+".png"));
    } catch (IOException e) {
      System.out.println("Exception occurred :" + e.getMessage());
    }
  }
}
