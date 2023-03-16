import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Class used to convert a png image to ppm.
 */
public class PngToPpm {
  /**
   * This method is used to convert png image to ppm file.
   *
   * @param pngFile file path to png image.
   * @param ppmFile file path to where the ppm file should be saved.
   * @throws IOException if invalid file path.
   */
  public void convertPngToPPM(String pngFile, String ppmFile) throws IOException {
    File inputFile = new File(pngFile);
    BufferedImage image = ImageIO.read(inputFile);

    File outputFile = new File(ppmFile);
    FileWriter writer = new FileWriter(outputFile);

    // Write PPM header
    writer.write("P3\n");
    writer.write(image.getWidth() + " " + image.getHeight() + "\n");
    writer.write("255\n");

    for (int y = 0; y < image.getHeight(); y++) {
      for (int x = 0; x < image.getWidth(); x++) {
        int pixel = image.getRGB(x, y);
        Color color = new Color(pixel);
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        writer.write(r + " " + g + " " + b + " ");
      }
      writer.write("\n");
    }
    writer.close();
  }

}