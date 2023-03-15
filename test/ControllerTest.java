import org.junit.Before;
import org.junit.Test;

import com.neu.image_manipulation.controller.Controller;
import com.neu.image_manipulation.controller.ControllerInterface;
import com.neu.image_manipulation.model.entity.Image;
import com.neu.image_manipulation.model.entity.Pixel;
import com.neu.image_manipulation.model.impl.ImageManipulationInterface;
import com.neu.image_manipulation.model.impl.ImageManipulationModel;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ControllerTest extends AbstractTestSetup {
  private StringBuilder log = new StringBuilder();
  ImageManipulationInterface mockModel;
  ControllerInterface mockController;

  @Before
  public void testSetupModel(){
    mockModel = new MockModel(log) ;
    mockController = new Controller(mockModel,view);
  }

  @Test
  public void testKoalaLoadImageInPPM() throws IOException {
    image = controller.loadImageInPPM("./Resources/Koala.ppm");
    assertEquals(1024, image.getWidth());
    assertEquals(768, image.getHeight());
    assertEquals(255, image.getMaxValue());
    assertEquals(101, image.getPixel()[0][0].getRed());
    assertEquals(90, image.getPixel()[0][0].getGreen());
    assertEquals(58, image.getPixel()[0][0].getBlue());
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
    image = controller.loadImageInPPM("./Resources/Koala.png");
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
    Image image = controller.loadImageInPPM("./Resources/Koala.ppm");
    image = model.flipImageHorizontally(image);

    controller.savePPM("test-horizontal.ppm", image);
    File file = new File("test-horizontal.ppm");
    assertTrue(file.exists());
    file.delete();
  }

  @Test
  public void testRunCommandLoad() throws IOException {
    controller.runCommand("load ./Resources/Koala.ppm Koala");
    assertTrue(model.containsImages("Koala"));
  }

  @Test
  public void testRunCommandGreyValue() throws IOException {
    String input = "load ./Resources/Koala.ppm koala\ngreyscale value-component koala koala-greyscale-value";
    String[] commands = input.split("\n");
    for (String command : commands){
      controller.runCommand(command);
    }
    assertTrue(model.containsImages("koala-greyscale-value"));
  }
  @Test
  public void testLRunCommandGreyLuma() throws IOException {
    String input = "load ./Resources/Koala.ppm koala\ngreyscale luma-component koala koala-greyscale-luma";
    String[] commands = input.split("\n");
    for (String command : commands){
      controller.runCommand(command);
    }
    assertTrue(model.containsImages("koala-greyscale-luma"));
  }
  @Test
  public void testLRunCommandGreyIntensity() throws IOException {
    String input = "load ./Resources/Koala.ppm koala\ngreyscale intensity-component koala koala-greyscale-intensity";
    String[] commands = input.split("\n");
    for (String command : commands){
      controller.runCommand(command);
    }
    assertTrue(model.containsImages("koala-greyscale-intensity"));
  }
  @Test
  public void testRunCommandRGBSplit() throws IOException {
    String input = "load ./Resources/Koala.ppm koala\nrgb-split koala koala-red koala-green koala-blue";
    String[] commands = input.split("\n");
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    for (String command : commands){
      controller.runCommand(command);
    }
    assertTrue(model.containsImages("koala-red"));
    assertTrue(model.containsImages("koala-green"));
    assertTrue(model.containsImages("koala-blue"));
  }

  @Test
  public void testRunCommandRGBCombine() throws IOException {
    String input = "load ./Resources/Koala.ppm koala\nrgb-split koala koala-red koala-green koala-blue\nrgb-combine koala-red-tint koala-red koala-green koala-blue";
    String[] commands = input.split("\n");
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    for (String command : commands){
      controller.runCommand(command);
    }
    assertTrue(model.containsImages("koala-red"));
    assertTrue(model.containsImages("koala-green"));
    assertTrue(model.containsImages("koala-blue"));
    assertTrue(model.containsImages("koala-red-tint"));
  }

  @Test
  public void testRunCommandSavePPM() throws IOException {
    String input = "load ./Resources/Koala.ppm koala\nsave test.ppm koala";
    String[] commands = input.split("\n");
    for (String command : commands){
      controller.runCommand(command);
    }
    File file = new File("test.ppm");
    assertTrue(file.exists());
    file.delete();
  }

  @Test
  public void testGoCombineRGBCommand() throws IOException {
    String input = "load ./Resources/Koala.ppm koala\nrgb-split koala koala-red koala-green koala-blue";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    mockController.go();
    assertEquals("Loading the file\n"
            + "Splitting the image into it's Red, Green, Blue channels.", log.toString());
  }









}