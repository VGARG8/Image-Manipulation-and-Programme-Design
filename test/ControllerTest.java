import com.neu.imagemanipulation.controller.AdvancedController;
import com.neu.imagemanipulation.controller.Controller;
import com.neu.imagemanipulation.controller.LoadCommand;
import com.neu.imagemanipulation.model.entity.Image;
import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.entity.Pixel;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;


import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Class to test the methods of Controller Class. Extends the Abstract class.
 */
public class ControllerTest extends AbstractTestSetup {
  private StringBuilder log ;
  AdvancedImageManipulationInterface mockModel;
  AdvancedController mockController;

  @Before
  public void testSetupModel() {
    log = new StringBuilder();
    mockModel = new MockModel(log);
    mockController = new AdvancedController(null,null, mockModel, view);

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



//
//  @Test
//  public void testLoadImageInPPM() throws IOException {
//    String filename = "test.ppm";
//    String ppmContents = "P3\n# Test image\n2 2\n255\n255 0 0 0 255 0\n0 0 255 255 255 255\n";
//    fileWriter = new FileWriter(filename);
//    fileWriter.write(ppmContents);
//    fileWriter.close();
//
//    Image image = controller.loadImageInPPM(filename);
//
//    assertEquals(2, image.getWidth());
//    assertEquals(2, image.getHeight());
//    assertEquals(255, image.getMaxValue());
//    assertEquals(255, image.getPixel()[0][0].getRed());
//    assertEquals(0, image.getPixel()[0][0].getGreen());
//    assertEquals(0, image.getPixel()[0][0].getBlue());
//    assertEquals(0, image.getPixel()[1][0].getRed());
//    assertEquals(0, image.getPixel()[1][0].getGreen());
//    assertEquals(255, image.getPixel()[1][0].getBlue());
//    assertEquals(0, image.getPixel()[0][1].getRed());
//    assertEquals(255, image.getPixel()[0][1].getGreen());
//    assertEquals(0, image.getPixel()[0][1].getBlue());
//    assertEquals(255, image.getPixel()[1][1].getRed());
//    assertEquals(255, image.getPixel()[1][1].getGreen());
//    assertEquals(255, image.getPixel()[1][1].getBlue());
//  }
//
//  @Test
//  public void testLoadImageInPPMOnlyHeader() throws IOException {
//    String filename = "test.ppm";
//    String ppmContents = "P3";
//    fileWriter = new FileWriter(filename);
//    fileWriter.write(ppmContents);
//    fileWriter.close();
//    String[] args = new String[]{filename};
//
//    LoadCommand command = new LoadCommand(view, controller, model);
//    command.execute(args);
//
//    assertEquals(0, image.getWidth());
//    assertEquals(0, image.getHeight());
//    assertEquals(0, image.getMaxValue());
//  }
//
//  @Test
//  public void testInvalidFileLoadImageInPPM() throws IOException {
//    image = controller.loadImageInPPM("./Res/Koala.png");
//    assertNull(image);
//  }
//
//  @Test
//  public void testSavePPM() throws IOException {
//    Image image = new Image(4, 4, 255);
//    Pixel[][] pixelArray = new Pixel[4][4];
//    for (int i = 0; i < 4; i++) {
//      for (int j = 0; j < 4; j++) {
//        pixelArray[i][j] = new Pixel(255, 255, 255);
//      }
//    }
//    image.setPixel(pixelArray);
//    controller.savePPM("test.ppm", image);
//    File file = new File("test.ppm");
//    assertTrue(file.exists());
//    file.delete();
//  }
//
//
//  @Test
//  public void testSavePPMFile() throws IOException {
//    ImageInterface image = controller.runCommand("load ./Res/gamecontroller.ppm");
//    image = model.flipImageHorizontally(image);
//
//    controller.savePPM("test-horizontal.ppm", image);
//    File file = new File("test-horizontal.ppm");
//    assertTrue(file.exists());
//    file.delete();
//  }
//
//  @Test
//  public void testRunCommandLoad() throws IOException {
//    controller.runCommand("load ./Res/gamecontroller.ppm gamecontroller");
//    assertTrue(model.containsImages("gamecontroller"));
//  }
//
//  @Test
//  public void testRunCommandLoadPNG() throws IOException {
//    controller.runCommand("load ./Res/face.png face");
//    assertTrue(model.containsImages("face"));
//  }
//
//  @Test
//  public void testRunCommandLoadJPG() throws IOException {
//    controller.runCommand("load ./Res/car.jpg car");
//    assertTrue(model.containsImages("car"));
//  }
//
//  @Test
//  public void testRunCommandLoadBMP() throws IOException {
//    controller.runCommand("load ./Res/car.bmp car");
//    assertTrue(model.containsImages("car"));
//  }
//
//  @Test
//  public void testRunCommandGreyValue() throws IOException {
//    String input = "load ./Res/gamecontroller.ppm gamecontroller\ngreyscale value-component " +
//            "gamecontroller gamecontroller-greyscale-value";
//    String[] commands = input.split("\n");
//    for (String command : commands) {
//      controller.runCommand(command);
//    }
//    assertTrue(model.containsImages("gamecontroller-greyscale-value"));
//  }
//
//  @Test
//  public void testLRunCommandGreyLuma() throws IOException {
//    String input = "load ./Res/gamecontroller.ppm gamecontroller\ngreyscale luma-component " +
//            "gamecontroller gamecontroller-greyscale-luma";
//    String[] commands = input.split("\n");
//    for (String command : commands) {
//      controller.runCommand(command);
//    }
//    assertTrue(model.containsImages("gamecontroller-greyscale-luma"));
//  }
//
//  @Test
//  public void testLRunCommandGreyIntensity() throws IOException {
//    String input = "load ./Res/gamecontroller.ppm gamecontroller\ngreyscale intensity-component " +
//            "gamecontroller gamecontroller-greyscale-intensity";
//    String[] commands = input.split("\n");
//    for (String command : commands) {
//      controller.runCommand(command);
//    }
//    assertTrue(model.containsImages("gamecontroller-greyscale-intensity"));
//  }
//
//  @Test
//  public void testRunCommandRGBSplit() throws IOException {
//    String input = "load ./Res/gamecontroller.ppm gamecontroller\nrgb-split gamecontroller " +
//            "gamecontroller-red gamecontroller-green gamecontroller-blue";
//    String[] commands = input.split("\n");
//    for (String command : commands) {
//      controller.runCommand(command);
//    }
//    assertTrue(model.containsImages("gamecontroller-red"));
//    assertTrue(model.containsImages("gamecontroller-green"));
//    assertTrue(model.containsImages("gamecontroller-blue"));
//  }
//
//  @Test
//  public void testRunCommandRGBCombine() throws IOException {
//    String input = "load ./Res/gamecontroller.ppm gamecontroller\nrgb-split gamecontroller " +
//            "gamecontroller-red gamecontroller-green gamecontroller-blue\nrgb-combine " +
//            "gamecontroller-red-tint gamecontroller-red gamecontroller-green gamecontroller-blue";
//    String[] commands = input.split("\n");
//    for (String command : commands) {
//      controller.runCommand(command);
//    }
//    assertTrue(model.containsImages("gamecontroller-red"));
//    assertTrue(model.containsImages("gamecontroller-green"));
//    assertTrue(model.containsImages("gamecontroller-blue"));
//    assertTrue(model.containsImages("gamecontroller-red-tint"));
//  }
//
//  @Test
//  public void testRunCommandSavePPM() throws IOException {
//    String input = "load ./Res/gamecontroller.ppm gamecontroller\nsave test.ppm gamecontroller";
//    String[] commands = input.split("\n");
//    for (String command : commands) {
//      controller.runCommand(command);
//    }
//    File file = new File("test.ppm");
//    assertTrue(file.exists());
//    file.delete();
//  }
//
//  @Test
//  public void testRunScriptInScript() throws IOException {
//    String input = "run-script ./Res/scriptfile2.txt";
//    String[] commands = input.split("\n");
//    for (String command : commands) {
//      controller.runCommand(command);
//    }
//    assertTrue(model.containsImages("gamecontroller"));
//    assertTrue(model.containsImages("gamecontroller-bright"));
//  }
//
//  @Test
//  public void testMockLoadPngImage() throws IOException {
//
//    mockController.runCommand("load ./Res/gamecontroller.ppm face");
//    assertEquals("Image is stored.\n", log.toString());
//
//  }
//
//
//
//  @Test
//  public void testFlipImageHorizontallyMock() throws IOException {
//    String input = "load ./Res/gamecontroller.ppm koala\nhorizontal-flip koala koala-horizontal";
//    String[] commands = input.split("\n");
//    for (String command : commands) {
//      mockController.runCommand(command);
//    }
//    assertEquals("Image is stored.\n" +
//            "checking if image exists.\n" +
//            "getting image\n" +
//            "Storing the image after horizontal flip\n" +
//            "Image is stored.\n", log.toString());
//  }
//
//  @Test
//  public void testFlipImageVerticallyMock() throws IOException {
//    String input = "load ./Res/gamecontroller.ppm koala\nvertical-flip koala koala-vertical";
//    String[] commands = input.split("\n");
//    for (String command : commands) {
//      mockController.runCommand(command);
//    }
//    assertEquals("Image is stored.\n" +
//            "checking if image exists.\n" +
//            "getting image\n" +
//            "Storing the image after vertical flip\n" +
//            "Image is stored.\n", log.toString());
//  }
//
//
//  @Test
//  public void testBrightenImage() throws IOException {
//    String input = "load ./Res/gamecontroller.ppm koala\nbrighten 10 koala koala-brighter ";
//    String[] commands = input.split("\n");
//    for (String command : commands) {
//      mockController.runCommand(command);
//    }
//    assertEquals("Image is stored.\n" +
//            "checking if image exists.\n" +
//            "getting image\n" +
//            "Brightening the image\n" +
//            "Image is stored.\n", log.toString());
//  }
//
//  @Test
//  public void testDarkenImage() throws IOException {
//    String input = "load ./Res/gamecontroller.ppm koala\ndarken 10 koala koala-brighter ";
//    String[] commands = input.split("\n");
//    for (String command : commands) {
//      mockController.runCommand(command);
//    }
//    assertEquals("Image is stored.\n" +
//            "checking if image exists.\n" +
//            "getting image\n" +
//            "Darkening the image\n" +
//            "Image is stored.\n", log.toString());
//  }
//
//  @Test
//  public void testCreateValueComponentOfImage() throws IOException {
//    String input = "load ./Res/gamecontroller.ppm koala\ngreyscale value-component koala " +
//            "koala-greyscale";
//    String[] commands = input.split("\n");
//    for (String command : commands) {
//      mockController.runCommand(command);
//    }
//    assertEquals("Image is stored.\n" +
//            "checking if image exists.\n" +
//            "getting image\n" +
//            "Storing the image's greyscale value component\n" +
//            "Image is stored.\n", log.toString());
//  }
//
//  @Test
//  public void testCreateIntensityComponentOfImage() throws IOException {
//    String input = "load ./Res/gamecontroller.ppm koala\ngreyscale intensity-component koala " +
//            "koala-greyscale";
//    String[] commands = input.split("\n");
//    for (String command : commands) {
//      mockController.runCommand(command);
//    }
//    assertEquals("Image is stored.\n" +
//            "checking if image exists.\n" +
//            "getting image\n" +
//            "Storing the image's greyscale intensity component\n" +
//            "Image is stored.\n", log.toString());
//  }
//
//  @Test
//  public void testCreateLumaComponentOfImage() throws IOException {
//    String input = "load ./Res/gamecontroller.ppm koala\ngreyscale luma-component koala " +
//            "koala-greyscale";
//    String[] commands = input.split("\n");
//    for (String command : commands) {
//      mockController.runCommand(command);
//    }
//    assertEquals("Image is stored.\n" +
//            "checking if image exists.\n" +
//            "getting image\n" +
//            "Storing the image's greyscale luma component\n" +
//            "Image is stored.\n", log.toString());
//  }
//
//  @Test
//  public void testCreateRedComponentOfImage() throws IOException {
//    String input = "load ./Res/gamecontroller.ppm koala\ngreyscale red-component koala " +
//            "koala-greyscale";
//    String[] commands = input.split("\n");
//    for (String command : commands) {
//      mockController.runCommand(command);
//    }
//    assertEquals("Image is stored.\n" +
//            "checking if image exists.\n" +
//            "getting image\n" +
//            "Creating greyscale image with red component of the image.\n" +
//            "Image is stored.\n", log.toString());
//  }
//
//  @Test
//  public void testCreateGreenComponentOfImage() throws IOException {
//    String input = "load ./Res/gamecontroller.ppm koala\ngreyscale green-component koala " +
//            "koala-greyscale";
//    String[] commands = input.split("\n");
//    for (String command : commands) {
//      mockController.runCommand(command);
//    }
//    assertEquals("Image is stored.\n" +
//            "checking if image exists.\n" +
//            "getting image\n" +
//            "Creating greyscale image with green component of the image.\n" +
//            "Image is stored.\n", log.toString());
//  }
//
//  @Test
//  public void testCreateBlueComponentOfImage() throws IOException {
//    String input = "load ./Res/gamecontroller.ppm koala\ngreyscale blue-component koala " +
//            "koala-greyscale";
//    String[] commands = input.split("\n");
//    for (String command : commands) {
//      mockController.runCommand(command);
//    }
//    assertEquals("Image is stored.\n" +
//            "checking if image exists.\n" +
//            "getting image\n" +
//            "Creating greyscale image with blue component of the image.\n" +
//            "Image is stored.\n", log.toString());
//  }
//
//  @Test
//  public void testCombineRGBImages() throws IOException {
//    String input = "rgb-combine koala-red-tint koala-red koala-green koala-blue";
//    String[] commands = input.split("\n");
//    for (String command : commands) {
//      mockController.runCommand(command);
//    }
//    assertEquals("checking if image exists.\n" +
//            "getting image\n" +
//            "getting image\n" +
//            "getting image\n" +
//            "combining the Red, Green, Blue channels to form an image.\n" +
//            "Image is stored.\n", log.toString());
//  }
//
//  @Test (expected = NullPointerException.class)
//  public void testSplitIntoRGBImages() throws IOException {
//    String input = "load ./Res/gamecontroller.ppm koala\n" +
//            "rgb-split koala koala-red koala-green koala-blue";
//    String[] commands = input.split("\n");
//    for (String command : commands) {
//      mockController.runCommand(command);
//    }
//    assertEquals("Loading the file\n" +
//            "Splitting the image into it's Red, Green, Blue channels.\n", log.toString());
//  }
//
//  @Test
//  public void testBlur() throws IOException {
//    String input = "blur blue-component koala ";
//    String[] commands = input.split("\n");
//    for (String command : commands) {
//      mockController.runCommand(command);
//    }
//    assertEquals("checking if image exists.\n" +
//            "getting image\n" +
//            "blurring the image.\n" +
//            "Image is stored.\n", log.toString());
//  }
//
//  @Test
//  public void testSharpen() throws IOException {
//    String input = "sharpen blue-component koala ";
//    String[] commands = input.split("\n");
//    for (String command : commands) {
//      mockController.runCommand(command);
//    }
//    assertEquals("checking if image exists.\n" +
//            "getting image\n" +
//            "sharpening the image.\n" +
//            "Image is stored.\n", log.toString());
//  }
//
//  @Test
//  public void testGreyScaleToneMock() throws IOException {
//    String input = "greyscale-tone blue-component koala ";
//    String[] commands = input.split("\n");
//    for (String command : commands) {
//      mockController.runCommand(command);
//    }
//    assertEquals("checking if image exists.\n" +
//            "getting image\n" +
//            "greyscale tone of the image.\n" +
//            "Image is stored.\n", log.toString());
//  }
//
//  @Test
//  public void testSepiaToneMock() throws IOException {
//    String input = "sepia-tone blue-component koala ";
//    String[] commands = input.split("\n");
//    for (String command : commands) {
//      mockController.runCommand(command);
//    }
//    assertEquals("checking if image exists.\n" +
//            "getting image\n" +
//            "sepia tone of the image.\n" +
//            "Image is stored.\n", log.toString());
//  }
//
//  @Test
//  public void testDitherMock() throws IOException {
//    String input = "dither blue-component koala ";
//    String[] commands = input.split("\n");
//    for (String command : commands) {
//      mockController.runCommand(command);
//    }
//    assertEquals("checking if image exists.\n" +
//            "getting image\n" +
//            "dithering the image.\n" +
//            "Image is stored.\n", log.toString());
//  }


}