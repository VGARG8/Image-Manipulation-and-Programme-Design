import com.neu.imageManipulation.controller.Controller;
import com.neu.imageManipulation.controller.ControllerInterface;
import com.neu.imageManipulation.model.entity.Image;
import com.neu.imageManipulation.model.entity.Pixel;
import com.neu.imageManipulation.model.impl.ImageManipulationInterface;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Class to test the methods of Controller Class. Extends the Abstract class.
 */
public class ControllerTest extends AbstractTestSetup {
  private StringBuilder log = new StringBuilder();
  ImageManipulationInterface mockModel;
  ControllerInterface mockController;

  @Before
  public void testSetupModel() {
    mockModel = new MockModel(log);
    mockController = new Controller(new InputStreamReader(System.in), System.out, mockModel, view);
    assertTrue(true);
  }

  @Test(expected = NullPointerException.class)
  public void testConstructorNullModel() {
    new Controller(new InputStreamReader(System.in), System.out, null, view);
  }

  @Test
  public void testConstructorValid() {
    assertNotNull(controller);
    assertEquals(model, controller.getModel());
    assertEquals(view, controller.getView());
  }


  @Test
  public void testGameControllerLoadImageInPPM() throws IOException {
    image = controller.loadImageInPPM("./Res/gamecontroller.ppm");
    assertEquals(80, image.getWidth());
    assertEquals(40, image.getHeight());
    assertEquals(255, image.getMaxValue());
    assertEquals(255, image.getPixel()[0][0].getRed());
    assertEquals(255, image.getPixel()[0][0].getGreen());
    assertEquals(255, image.getPixel()[0][0].getBlue());
  }

  @Test
  public void testLoadImageInPPM() throws IOException {
    String filename = "test.ppm";
    String ppmContents = "P3\n# Test image\n2 2\n255\n255 0 0 0 255 0\n0 0 255 255 255 255\n";
    fileWriter = new FileWriter(filename);
    fileWriter.write(ppmContents);
    fileWriter.close();

    Image image = controller.loadImageInPPM(filename);

    assertEquals(2, image.getWidth());
    assertEquals(2, image.getHeight());
    assertEquals(255, image.getMaxValue());
    assertEquals(255, image.getPixel()[0][0].getRed());
    assertEquals(0, image.getPixel()[0][0].getGreen());
    assertEquals(0, image.getPixel()[0][0].getBlue());
    assertEquals(0, image.getPixel()[1][0].getRed());
    assertEquals(0, image.getPixel()[1][0].getGreen());
    assertEquals(255, image.getPixel()[1][0].getBlue());
    assertEquals(0, image.getPixel()[0][1].getRed());
    assertEquals(255, image.getPixel()[0][1].getGreen());
    assertEquals(0, image.getPixel()[0][1].getBlue());
    assertEquals(255, image.getPixel()[1][1].getRed());
    assertEquals(255, image.getPixel()[1][1].getGreen());
    assertEquals(255, image.getPixel()[1][1].getBlue());
  }

  @Test
  public void testLoadImageInPPMOnlyHeader() throws IOException {
    String filename = "test.ppm";
    String ppmContents = "P3";
    fileWriter = new FileWriter(filename);
    fileWriter.write(ppmContents);
    fileWriter.close();

    Image image = controller.loadImageInPPM(filename);

    assertEquals(0, image.getWidth());
    assertEquals(0, image.getHeight());
    assertEquals(0, image.getMaxValue());
  }

  @Test
  public void testInvalidFileLoadImageInPPM() throws IOException {
    image = controller.loadImageInPPM("./Res/Koala.png");
    assertNull(image);
  }

  @Test
  public void testSavePPM() throws IOException {
    Image image = new Image(4, 4, 255);
    Pixel[][] pixelArray = new Pixel[4][4];
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        pixelArray[i][j] = new Pixel(255, 255, 255);
      }
    }
    image.setPixel(pixelArray);
    controller.savePPM("test.ppm", image);
    File file = new File("test.ppm");
    assertTrue(file.exists());
    file.delete();
  }


  @Test
  public void testSavePPMFile() throws IOException {
    Image image = controller.loadImageInPPM("./Res/gamecontroller.ppm");
    image = model.flipImageHorizontally(image);

    controller.savePPM("test-horizontal.ppm", image);
    File file = new File("test-horizontal.ppm");
    assertTrue(file.exists());
    file.delete();
  }

  @Test
  public void testRunCommandLoad() throws IOException {
    controller.runCommand("load ./Res/gamecontroller.ppm gamecontroller");
    assertTrue(model.containsImages("gamecontroller"));
  }

  @Test
  public void testRunCommandGreyValue() throws IOException {
    String input = "load ./Res/gamecontroller.ppm gamecontroller\ngreyscale value-component " +
            "gamecontroller gamecontroller-greyscale-value";
    String[] commands = input.split("\n");
    for (String command : commands) {
      controller.runCommand(command);
    }
    assertTrue(model.containsImages("gamecontroller-greyscale-value"));
  }

  @Test
  public void testLRunCommandGreyLuma() throws IOException {
    String input = "load ./Res/gamecontroller.ppm gamecontroller\ngreyscale luma-component " +
            "gamecontroller gamecontroller-greyscale-luma";
    String[] commands = input.split("\n");
    for (String command : commands) {
      controller.runCommand(command);
    }
    assertTrue(model.containsImages("gamecontroller-greyscale-luma"));
  }

  @Test
  public void testLRunCommandGreyIntensity() throws IOException {
    String input = "load ./Res/gamecontroller.ppm gamecontroller\ngreyscale intensity-component " +
            "gamecontroller gamecontroller-greyscale-intensity";
    String[] commands = input.split("\n");
    for (String command : commands) {
      controller.runCommand(command);
    }
    assertTrue(model.containsImages("gamecontroller-greyscale-intensity"));
  }

  @Test
  public void testRunCommandRGBSplit() throws IOException {
    String input = "load ./Res/gamecontroller.ppm gamecontroller\nrgb-split gamecontroller " +
            "gamecontroller-red gamecontroller-green gamecontroller-blue";
    String[] commands = input.split("\n");
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    for (String command : commands) {
      controller.runCommand(command);
    }
    assertTrue(model.containsImages("gamecontroller-red"));
    assertTrue(model.containsImages("gamecontroller-green"));
    assertTrue(model.containsImages("gamecontroller-blue"));
  }

  @Test
  public void testRunCommandRGBCombine() throws IOException {
    String input = "load ./Res/gamecontroller.ppm gamecontroller\nrgb-split gamecontroller " +
            "gamecontroller-red gamecontroller-green gamecontroller-blue\nrgb-combine " +
            "gamecontroller-red-tint gamecontroller-red gamecontroller-green gamecontroller-blue";
    String[] commands = input.split("\n");
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    for (String command : commands) {
      controller.runCommand(command);
    }
    assertTrue(model.containsImages("gamecontroller-red"));
    assertTrue(model.containsImages("gamecontroller-green"));
    assertTrue(model.containsImages("gamecontroller-blue"));
    assertTrue(model.containsImages("gamecontroller-red-tint"));
  }

  @Test
  public void testRunCommandSavePPM() throws IOException {
    String input = "load ./Res/gamecontroller.ppm gamecontroller\nsave test.ppm gamecontroller";
    String[] commands = input.split("\n");
    for (String command : commands) {
      controller.runCommand(command);
    }
    File file = new File("test.ppm");
    assertTrue(file.exists());
    file.delete();
  }


}