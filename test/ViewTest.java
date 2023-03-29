import com.neu.imagemanipulation.view.View;
import com.neu.imagemanipulation.view.ViewInterface;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * Class to test methods of View class.
 */
public class ViewTest {


  @Test
  public void testGetCommand() throws IOException {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    ViewInterface view2 = new View();
    view2.getCommand();
    assertEquals("Enter the command:\n", outContent.toString());
  }

  @Test
  public void testDisplaySaveStatus() throws IOException {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    ViewInterface view2 = new View();
    view2.displaySaveStatus("ppm");
    assertEquals("Saving image as ppm\n", outContent.toString());
  }

  @Test
  public void testDisplayReadFileError() throws IOException {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    ViewInterface view2 = new View();
    view2.displayReadFileError();
    assertEquals("Can't read the file!\n", outContent.toString());
  }

  @Test
  public void testDisplayLoadingStatus() throws IOException {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    ViewInterface view2 = new View();
    view2.displayLoadingStatus();
    assertEquals("Loading the file\n", outContent.toString());
  }

  @Test
  public void testDisplayValueStatus() throws IOException {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    ViewInterface view2 = new View();
    view2.displayValueStatus();
    assertEquals("Storing the image's greyscale value component\n",
            outContent.toString());
  }

  @Test
  public void testDisplayLumaStatus() throws IOException {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    ViewInterface view2 = new View();
    view2.displayLumaStatus();
    assertEquals("Storing the image's greyscale luma component\n", outContent.toString());
  }

  @Test
  public void testDisplayIntensityStatus() throws IOException {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    ViewInterface view2 = new View();
    view2.displayIntensityStatus();
    assertEquals("Storing the image's greyscale intensity component\n",
            outContent.toString());
  }

  @Test
  public void testDisplayHorizontalFlipStatus() throws IOException {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    ViewInterface view2 = new View();
    view2.displayHorizontalFlipStatus();
    assertEquals("Storing the image after horizontal flip\n", outContent.toString());
  }

  @Test
  public void testDisplayVerticalFlipStatus() throws IOException {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    ViewInterface view2 = new View();
    view2.displayVerticalFlipStatus();
    assertEquals("Storing the image after vertical flip\n", outContent.toString());
  }

  @Test
  public void testDisplayDarkenenStatus() throws IOException {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    ViewInterface view2 = new View();
    view2.displayDarkenenStatus();
    assertEquals("Darkening the image\n", outContent.toString());
  }

  @Test
  public void testDisplayBrightenStatus() throws IOException {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    ViewInterface view2 = new View();
    view2.displayBrightenStatus();
    assertEquals("Brightening the image\n", outContent.toString());
  }

  @Test
  public void testDisplayRunScriptStatus() throws IOException {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    ViewInterface view2 = new View();
    view2.displayRunScriptStatus("filepath");
    assertEquals("Running the script from: filepath\n", outContent.toString());
  }

  @Test
  public void testDisplayRGBSplitStatus() throws IOException {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    ViewInterface view2 = new View();
    view2.displayRGBSplitStatus();
    assertEquals("Splitting the image into it's Red, Green, Blue channels.\n",
            outContent.toString());
  }

  @Test
  public void testDisplayRGBCombineStatus() throws IOException {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    ViewInterface view2 = new View();
    view2.displayRGBCombineStatus();
    assertEquals("combining the Red, Green, Blue channels to form an image.\n",
            outContent.toString());
  }

  @Test
  public void testDisplayInvalidValue() throws IOException {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    ViewInterface view2 = new View();
    view2.displayInvalidValue();
    assertEquals("Value should be a non-negative integer.\n", outContent.toString());
  }

  @Test
  public void testDisplayNoFileStatus() throws IOException {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    ViewInterface view2 = new View();
    view2.displayNoFileStatus();
    assertEquals("File not found!\n", outContent.toString());
  }

  @Test
  public void testDisplayInvalidPPM() throws IOException {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    ViewInterface view2 = new View();
    view2.displayInvalidPPM();
    assertEquals("Invalid PPM file: plain RAW file should begin with P3.\n",
            outContent.toString());
  }

  @Test
  public void testDisplayEmptyFileStatus() throws IOException {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    ViewInterface view2 = new View();
    view2.displayEmptyFileStatus();
    assertEquals("File is Empty!\n", outContent.toString());
  }

  @Test
  public void testDisplayInvalidPPMNoValues() throws IOException {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    ViewInterface view2 = new View();
    view2.displayInvalidPPMNoValues();
    assertEquals("PPM file got no values after the header. Image with 0x0 dimensions is " +
            "created\n", outContent.toString());
  }

  @Test
  public void testDisplayWidth() throws IOException {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    ViewInterface view2 = new View();
    view2.displayWidth(4);
    assertEquals("Width of image: 4\n", outContent.toString());
  }

  @Test
  public void testDisplayHeight() throws IOException {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    ViewInterface view2 = new View();
    view2.displayHeight(4);
    assertEquals("Height of image: 4\n", outContent.toString());
  }

  @Test
  public void testDisplayMaxValue() throws IOException {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    ViewInterface view2 = new View();
    view2.displayMaxValue(255);
    assertEquals("Maximum value of a color in this file (usually 255): 255\n",
            outContent.toString());
  }

  @Test
  public void testDisplayRedComponentStatus() throws IOException {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    ViewInterface view2 = new View();
    view2.displayRedComponentStatus();
    assertEquals("Creating greyscale image with red component of the image.\n",
            outContent.toString());
  }

  @Test
  public void testDisplayGreenComponentStatus() throws IOException {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    ViewInterface view2 = new View();
    view2.displayGreenComponentStatus();
    assertEquals("Creating greyscale image with green component of the image.\n",
            outContent.toString());
  }

  @Test
  public void testDisplayBlueComponentStatus() throws IOException {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    ViewInterface view2 = new View();
    view2.displayBlueComponentStatus();
    assertEquals("Creating greyscale image with blue component of the image.\n",
            outContent.toString());
  }

  @Test
  public void testGetCommand1() throws IOException {


    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));


    ViewInterface view2 = new View();

    view2.getCommand();


    String expectedOutput = "Enter the command:\n";
    assertEquals(expectedOutput, outContent.toString());
  }


}