import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * class to test the method when the arguments are passed.
 */

public class MainTest extends AbstractTestSetup {

  @Test
  public void testFileArgument() throws IOException {
    ByteArrayOutputStream log = new ByteArrayOutputStream();
    System.setOut(new PrintStream(log));
    String[] args = {"-file", "Res/script3.txt"};
    Main main = new Main();
    main.run(args);
    String expectedLog = "Running the script from: Res/script3.txt\n" +
            "Saving image as png\n" +
            "Storing the image after vertical flip\n" +
            " Blurring the image.\n" +
            "Creating sepia toned image.\n" +
            " Dithering the image.\n";
    assertEquals(expectedLog, log.toString());

  }

  @Test
  public void testFileArgumentNoFile() throws IOException {
    ByteArrayOutputStream log = new ByteArrayOutputStream();
    System.setOut(new PrintStream(log));
    String[] args = {"-file", null};
    Main main = new Main();
    main.run(args);
    String expectedLog = "Running the script from: null\n" +
            "Can't read the file!\n";
    assertEquals(expectedLog, log.toString());

  }

}