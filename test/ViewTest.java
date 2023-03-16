import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ViewTest extends AbstractTestSetup {

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private final PrintStream originalErr = System.err;

  @Before
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @After
  public void restoreStreams() {
    System.setOut(originalOut);
    System.setErr(originalErr);
  }

  @Test
  public void testGetCommand() {
    view.getCommand();
    assertEquals("Enter the command:\r\n", outContent.toString());
  }

  @Test
  public void testGetMenu() {
    view.displayMenu();
    assertEquals("Menu: \nThe Available functions are: \n" +
            "1.  Load: load the image from a filepath.\n" +
            "2.  Save: save the image in a filepath.\n" +
            "3.  Brighten: brightens the image by a value.\n" +
            "4.  Darken: darkens the image by a value.\n" +
            "5.  Vertical-flip: flips the image vertically.\n" +
            "6.  Horizontal-flip: flips the image horizontally.\n" +
            "7.  greyscale red-component: Creates greyscale image with the red-component.\n" +
            "8.  greyscale green-component: Creates greyscale image with the green-component.\n" +
            "9.  greyscale blue-component: Creates greyscale image with the blue-component.\n" +
            "10. greyscale value-component: Creates greyscale image with the value-component.\n" +
            "11. greyscale intensity-component: Creates greyscale image with the " +
            "intensity-component.\n" +
            "12. greyscale luma-component: Creates greyscale image with the luma-component.\n" +
            "13. rgb-split: splits the given image into three greyscale images containing its red, " +
            "green and blue components respectively.\n" +
            "14. rgb-combine: Combine the three greyscale images into a single image that gets its " +
            "red, green and blue components from the three images respectively.\n" +
            "15. run-script: Load and run the script commands in the specified file.\r\n", outContent.toString());
  }

  @Test
  public void testDisplaySaveStatus() {
    view.displaySaveStatus();
    assertEquals("Saving image as ppm\r\n", outContent.toString());
  }

  @Test
  public void testDisplayReadFileError() {
    view.displayReadFileError();
    assertEquals("Can't read the file!\r\n", outContent.toString());
  }

  @Test
  public void testDisplayLoadingStatus() {
    view.displayLoadingStatus();
    assertEquals("Loading the file\r\n", outContent.toString());
  }

  @Test
  public void testDisplayValueStatus() {
    view.displayValueStatus();
    assertEquals("Storing the image's greyscale value component\r\n", outContent.toString());
  }

  @Test
  public void testDisplayLumaStatus() {
    view.displayLumaStatus();
    assertEquals("Storing the image's greyscale luma component\r\n", outContent.toString());
  }

  @Test
  public void testDisplayIntensityStatus() {
    view.displayIntensityStatus();
    assertEquals("Storing the image's greyscale intensity component\r\n", outContent.toString());
  }

  @Test
  public void testDisplayHorizontalFlipStatus() {
    view.displayHorizontalFlipStatus();
    assertEquals("Storing the image after horizontal flip\r\n", outContent.toString());
  }

  @Test
  public void testDisplayVerticalFlipStatus() {
    view.displayVerticalFlipStatus();
    assertEquals("Storing the image after vertical flip\r\n", outContent.toString());
  }

  @Test
  public void testDisplayDarkenenStatus() {
    view.displayDarkenenStatus();
    assertEquals("Darkening the image\r\n", outContent.toString());
  }

  @Test
  public void testDisplayBrightenStatus() {
    view.displayBrightenStatus();
    assertEquals("Brightening the image\r\n", outContent.toString());
  }

  @Test
  public void testDisplayRunScriptStatus() {
    view.displayRunScriptStatus("filepath");
    assertEquals("Running the script from: filepath\r\n", outContent.toString());
  }

  @Test
  public void testDisplayRGBSplitStatus() {
    view.displayRGBSplitStatus();
    assertEquals("Splitting the image into it's Red, Green, Blue channels.\r\n", outContent.toString());
  }

  @Test
  public void testDisplayRGBCombineStatus() {
    view.displayRGBCombineStatus();
    assertEquals("combining the Red, Green, Blue channels to form an image.\r\n", outContent.toString());
  }

  @Test
  public void testDisplayInvalidValue() {
    view.displayInvalidValue();
    assertEquals("Value should be a non-negative integer.\r\n", outContent.toString());
  }

  @Test
  public void testDisplayNoFileStatus() {
    view.displayNoFileStatus();
    assertEquals("File not found!\r\n", outContent.toString());
  }

  @Test
  public void testDisplayInvalidPPM() {
    view.displayInvalidPPM();
    assertEquals("Invalid PPM file: plain RAW file should begin with P3.\r\n", outContent.toString());
  }

  @Test
  public void testDisplayEmptyFileStatus() {
    view.displayEmptyFileStatus();
    assertEquals("File is Empty!\r\n", outContent.toString());
  }

  @Test
  public void testDisplayInvalidPPMNoValues() {
    view.displayInvalidPPMNoValues();
    assertEquals("PPM file got no values after the header. Image with 0x0 dimensions is created\r\n", outContent.toString());
  }

  @Test
  public void testDisplayWidth() {
    view.displayWidth(4);
    assertEquals("Width of image: 4\r\n", outContent.toString());
  }

  @Test
  public void testDisplayHeight() {
    view.displayHeight(4);
    assertEquals("Height of image: 4\r\n", outContent.toString());
  }

  @Test
  public void testDisplayMaxValue() {
    view.displayMaxValue(255);
    assertEquals("Maximum value of a color in this file (usually 255): 255\r\n", outContent.toString());
  }

  @Test
  public void testDisplayRedComponentStatus() {
    view.displayRedComponentStatus();
    assertEquals("Creating greyscale image with red component of the image.\r\n", outContent.toString());
  }

  @Test
  public void testDisplayGreenComponentStatus() {
    view.displayGreenComponentStatus();
    assertEquals("Creating greyscale image with green component of the image.\r\n", outContent.toString());
  }

  @Test
  public void testDisplayBlueComponentStatus() {
    view.displayBlueComponentStatus();
    assertEquals("Creating greyscale image with blue component of the image.\r\n", outContent.toString());
  }


}