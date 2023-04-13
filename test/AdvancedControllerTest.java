import com.neu.imagemanipulation.controller.AdvancedController;
import com.neu.imagemanipulation.model.entity.Image;
import com.neu.imagemanipulation.model.impl.GuiModelInteface;
import com.neu.imagemanipulation.view.AdvancedViewConsole;
import com.neu.imagemanipulation.view.AdvancedViewInterface;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

/**
 * The class is responsible for testing the Advanced Controller class.
 */
public class AdvancedControllerTest {

  private AdvancedController advancedController;
  private GuiModelInteface mockModel;
  private AdvancedViewInterface mockView;
  private Readable stringReader;
  private Appendable stringWriter;
  private InputStream inputStream;
  private OutputStream outputStream;
  private StringBuilder log;
  private Image image;

  @Before
  public void setUp() {
    log = new StringBuilder();
    mockModel = new MockModel(log);
    mockView = new AdvancedViewConsole();
    stringReader = new StringReader("");
    stringWriter = new StringWriter();
    advancedController = new AdvancedController(stringReader, stringWriter, mockModel, mockView);
  }

  @Test
  public void testBlurCommand() throws Exception {
    advancedController.runCommand("blur inputImage outputImage");
    assertEquals("checking if image exists.\n" +
            "getting image\n" +
            "blurring the image.\n" +
            "Image is stored.\n", log.toString());
  }

  @Test
  public void testBrightenCommand() throws Exception {
    advancedController.runCommand("brighten 20 inputImage outputImage");
    assertEquals("checking if image exists.\ngetting image\nBrightening the image\n" +
            "Image is stored.\n", log.toString());
  }

  @Test
  public void testGameControllerLoadImageInPPM() throws IOException {
    advancedController.runCommand("load ./Res/gamecontroller.ppm controller");
    assertEquals("Image is stored.\n", log.toString());
  }

  @Test(expected = NullPointerException.class)
  public void testSavePPMFile() throws IOException {
    advancedController.runCommand("load ./Res/gamecontroller.ppm controller");
    advancedController.runCommand("save ./Res/gamecontroller.ppm controller");
    assertEquals("Loading the file\n" +
            "PPM file got no values after the header. " +
            "Image with 0x0 dimensions is created\n", log.toString());
  }

  @Test(expected = NullPointerException.class)
  public void testSavePNGFile() throws IOException {
    advancedController.runCommand("load ./Res/face.png face");
    advancedController.runCommand("save ./Res/face.png face");
    assertEquals("Loading the file\n", log.toString());
  }
}
