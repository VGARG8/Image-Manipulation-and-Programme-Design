import com.neu.imagemanipulation.controller.AdvancedController;
import com.neu.imagemanipulation.controller.AdvancedControllerInterface;
import com.neu.imagemanipulation.controller.GuiController;
import com.neu.imagemanipulation.controller.GuiControllerInterface;
import com.neu.imagemanipulation.model.entity.Image;
import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.entity.Pixel;
import com.neu.imagemanipulation.model.entity.PixelInterface;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationModel;
import com.neu.imagemanipulation.model.impl.ImageAnalysisImplementation;
import com.neu.imagemanipulation.model.impl.ImageAnalysisInterface;
import com.neu.imagemanipulation.model.impl.ModelGui;
import com.neu.imagemanipulation.view.AdvancedViewConsole;
import com.neu.imagemanipulation.view.AdvancedViewGui;
import com.neu.imagemanipulation.view.AdvancedViewInterface;
import com.neu.imagemanipulation.view.ViewGuiInterface;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;


/**
 * The Main class represents the entry point of the Image Manipulation program. This class
 * initializes the Model, View, and Controller, and hands over the control to the Controller.
 *
 * @author [Girish Kumar Adari, Vaibhav Garg]
 */
public class Main {

  /**
   * The main method is the entry point of the program.It initializes the ImageManipulationModel and
   * View, creates the Controller object, and passes the control to the Controller.
   *
   * @param args the command-line arguments passed to the program
   * @throws IOException if an I/O error occurs
   */
  public static void main(String[] args) throws IOException {


    new Main().run(args);
//    new AdvancedViewGui();
  }
  private static ImageInterface loadStandardFormat(String filename) throws IOException {
    BufferedImage bufferedImage = ImageIO.read(new File(filename));

    int width = bufferedImage.getWidth();
    int height = bufferedImage.getHeight();
    int maxValue = 255;

    PixelInterface[][] pixelData = new Pixel[height][width];
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Color color = new Color(bufferedImage.getRGB(x, y));
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        pixelData[y][x] = new Pixel(red, green, blue);
      }
    }

    ImageInterface image = new Image(height, width, maxValue);
    image.setPixel(pixelData);

    return image;
  }
  private void run(String[] args) throws IOException {
    AdvancedImageManipulationInterface model;

    AdvancedViewInterface view;
    ViewGuiInterface guiView;
    AdvancedControllerInterface controller;
    GuiControllerInterface guiController;

    if (args.length > 0 && args[0].equals("-file")) {
      model = new AdvancedImageManipulationModel();
      view = new AdvancedViewConsole();
      controller = new AdvancedController(new InputStreamReader(System.in), System.out,
          model, view);
      if (args.length < 2) {
        controller.callViewForMain();
      } else {
        String filePath = args[1];

        controller.runCommand("run-script " + filePath);
      }
    } else if (args.length == 1 && args[0].equals("-text")) {
      model = new AdvancedImageManipulationModel();
      view = new AdvancedViewConsole();
      controller = new AdvancedController(new InputStreamReader(System.in), System.out,
          model, view);
      controller.execute();
    } else if (args.length == 0) {
      guiView = new AdvancedViewGui();
      guiController = new GuiController(new ModelGui());
      guiController.setView(guiView);
    }
  }
}

