import com.neu.image_manipulation.view.View;
import com.neu.image_manipulation.view.ViewInterface;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ViewTest {
  protected final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  protected final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  protected final PrintStream originalOut = System.out;
  protected final PrintStream originalErr = System.err;
  ViewInterface view = new View();

  //  @Before
//  public void setUpStreams() throws IOException {
//    ViewInterface view = new View();
//    System.setOut(new PrintStream(outContent));
//    System.setErr(new PrintStream(errContent));
//  }
  @Before
  public void setUpStreams() throws IOException {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @After
  public void restoreStreams() throws IOException {
    System.setOut(originalOut);
    System.setErr(originalErr);
  }

  @Test
  public void testGetCommand() throws IOException {
    view.getCommand();
    assertEquals("Enter the command:\r\n", outContent.toString());
  }

  @Test
  public void testDisplaySaveStatus() throws IOException {
    view.displaySaveStatus();
    assertEquals("Saving image as ppm\r\n", outContent.toString());
  }

  @Test
  public void testDisplayReadFileError() throws IOException {
    view.displayReadFileError();
    assertEquals("Can't read the file!\r\n", outContent.toString());
  }

  @Test
  public void testDisplayLoadingStatus() throws IOException {
    view.displayLoadingStatus();
    assertEquals("Loading the file\r\n", outContent.toString());
  }

  @Test
  public void testDisplayValueStatus() throws IOException {
    view.displayValueStatus();
    assertEquals("Storing the image's greyscale value component\r\n", outContent.toString());
  }

  @Test
  public void testDisplayLumaStatus() throws IOException {
    view.displayLumaStatus();
    assertEquals("Storing the image's greyscale luma component\r\n", outContent.toString());
  }

  @Test
  public void testDisplayIntensityStatus() throws IOException {
    view.displayIntensityStatus();
    assertEquals("Storing the image's greyscale intensity component\r\n", outContent.toString());
  }

  @Test
  public void testDisplayHorizontalFlipStatus() throws IOException {
    view.displayHorizontalFlipStatus();
    assertEquals("Storing the image after horizontal flip\r\n", outContent.toString());
  }

  @Test
  public void testDisplayVerticalFlipStatus() throws IOException {
    view.displayVerticalFlipStatus();
    assertEquals("Storing the image after vertical flip\r\n", outContent.toString());
  }

  @Test
  public void testDisplayDarkenenStatus() throws IOException {
    view.displayDarkenenStatus();
    assertEquals("Darkening the image\r\n", outContent.toString());
  }

  @Test
  public void testDisplayBrightenStatus() throws IOException {
    view.displayBrightenStatus();
    assertEquals("Brightening the image\r\n", outContent.toString());
  }

  @Test
  public void testDisplayRunScriptStatus() throws IOException {
    view.displayRunScriptStatus("filepath");
    assertEquals("Running the script from: filepath\r\n", outContent.toString());
  }

  @Test
  public void testDisplayRGBSplitStatus() throws IOException {
    view.displayRGBSplitStatus();
    assertEquals("Splitting the image into it's Red, Green, Blue channels.\r\n", outContent.toString());
  }

  @Test
  public void testDisplayRGBCombineStatus() throws IOException {
    view.displayRGBCombineStatus();
    assertEquals("combining the Red, Green, Blue channels to form an image.\r\n", outContent.toString());
  }

  @Test
  public void testDisplayInvalidValue() throws IOException {
    view.displayInvalidValue();
    assertEquals("Value should be a non-negative integer.\r\n", outContent.toString());
  }

  @Test
  public void testDisplayNoFileStatus() throws IOException {
    view.displayNoFileStatus();
    assertEquals("File not found!\r\n", outContent.toString());
  }

  @Test
  public void testDisplayInvalidPPM() throws IOException {
    view.displayInvalidPPM();
    assertEquals("Invalid PPM file: plain RAW file should begin with P3.\r\n", outContent.toString());
  }

  @Test
  public void testDisplayEmptyFileStatus() throws IOException {
    view.displayEmptyFileStatus();
    assertEquals("File is Empty!\r\n", outContent.toString());
  }

  @Test
  public void testDisplayInvalidPPMNoValues() throws IOException {
    view.displayInvalidPPMNoValues();
    assertEquals("PPM file got no values after the header. Image with 0x0 dimensions is created\r\n", outContent.toString());
  }

  @Test
  public void testDisplayWidth() throws IOException {
    view.displayWidth(4);
    assertEquals("Width of image: 4\r\n", outContent.toString());
  }

  @Test
  public void testDisplayHeight() throws IOException {
    view.displayHeight(4);
    assertEquals("Height of image: 4\r\n", outContent.toString());
  }

  @Test
  public void testDisplayMaxValue() throws IOException {
    view.displayMaxValue(255);
    assertEquals("Maximum value of a color in this file (usually 255): 255\r\n", outContent.toString());
  }

  @Test
  public void testDisplayRedComponentStatus() throws IOException {
    view.displayRedComponentStatus();
    assertEquals("Creating greyscale image with red component of the image.\r\n", outContent.toString());
  }

  @Test
  public void testDisplayGreenComponentStatus() throws IOException {
    view.displayGreenComponentStatus();
    assertEquals("Creating greyscale image with green component of the image.\r\n", outContent.toString());
  }

  @Test
  public void testDisplayBlueComponentStatus() throws IOException {
    view.displayBlueComponentStatus();
    assertEquals("Creating greyscale image with blue component of the image.\r\n", outContent.toString());
  }


}