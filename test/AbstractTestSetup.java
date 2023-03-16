import com.neu.imageManipulation.controller.Controller;
import com.neu.imageManipulation.controller.ControllerInterface;
import com.neu.imageManipulation.model.entity.Image;
import com.neu.imageManipulation.model.impl.ImageManipulationInterface;
import com.neu.imageManipulation.model.impl.ImageManipulationModel;
import com.neu.imageManipulation.view.View;
import com.neu.imageManipulation.view.ViewInterface;

import org.junit.Before;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintStream;

public abstract class AbstractTestSetup {
  ImageManipulationInterface model;
  ViewInterface view;
  ControllerInterface controller;
  PngToPpm convert;

  FileWriter fileWriter;
  Image image;

  protected final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  protected final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  protected final PrintStream originalOut = System.out;
  protected final PrintStream originalErr = System.err;


  Boolean comparePixels(Image img1, Image img2) {
    if (img1.getPixel().length != img2.getPixel().length) {
      return false;
    }
    for (int i = 0; i < img1.getHeight(); i++) {
      for (int j = 0; j < img1.getWidth(); j++) {
        if (!(img1.getPixel()[i][j].getRed() == (img2.getPixel()[i][j]).getRed()
                && img1.getPixel()[i][j].getGreen() == (img2.getPixel()[i][j]).getGreen()
                && img1.getPixel()[i][j].getBlue() == (img2.getPixel()[i][j]).getBlue())) {

          return false;
        }
      }
    }
    return true;
  }

  @Before
  public void setup() {
    model = new ImageManipulationModel();
    view = new View();
    controller = new Controller(new InputStreamReader(System.in), System.out, model, view);
    convert = new PngToPpm();
  }
}
