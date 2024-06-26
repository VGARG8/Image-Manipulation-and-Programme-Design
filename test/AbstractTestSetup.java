import com.neu.imagemanipulation.controller.AdvancedController;
import com.neu.imagemanipulation.controller.AdvancedControllerInterface;
import com.neu.imagemanipulation.model.entity.Image;
import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationModel;
import com.neu.imagemanipulation.view.AdvancedView;
import com.neu.imagemanipulation.view.AdvancedViewInterface;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import org.junit.Before;


/**
 * Abstract class which has the common fields and methods required for the other classes to test.
 */
public abstract class AbstractTestSetup {

  AdvancedImageManipulationInterface model;
  AdvancedViewInterface view;
  AdvancedControllerInterface controller;
  PngToPpm convert;

  FileWriter fileWriter;
  Image image;

  Boolean comparePixels(ImageInterface img1, ImageInterface img2) {
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
    model = new AdvancedImageManipulationModel();
    view = new AdvancedView();
    controller = new AdvancedController(new InputStreamReader(System.in), System.out, model, view);
    convert = new PngToPpm();

  }
}
