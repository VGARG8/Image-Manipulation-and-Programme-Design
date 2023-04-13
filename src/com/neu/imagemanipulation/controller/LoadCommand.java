package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.Constants;
import com.neu.imagemanipulation.model.entity.Image;
import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.entity.Pixel;
import com.neu.imagemanipulation.model.entity.PixelInterface;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 * A command class for loading images into the model. Supports loading images in standard file
 * formats such as PNG, JPG, and BMP as well as images in PPM format.
 */
public class LoadCommand extends AbstractCommand implements CommandInterface {

  /**
   * Constructor for LoadCommand that takes in a view and model.
   *
   * @param view  The view to be used for displaying messages.
   * @param model The model to be used for storing and manipulating images.
   */
  public LoadCommand(AdvancedViewInterface view,
      AdvancedImageManipulationInterface model) {
    super(view, model);
  }


  /**
   * Executes the load command, loading an image from a specified file and storing it in the model.
   *
   * @param args An array of Strings containing the file path and image name.
   * @throws IOException If there is an issue with loading the image file.
   */
  @Override
  public void execute(String[] args) throws IOException {
    ImageInterface result_image;
    view.displayLoadingStatus();
    String fileExtension = getFileExtension(args[1]);
    if (fileExtension.equalsIgnoreCase(Constants.PPM)) {
      result_image = loadImageInPPM(args[1]);
      model.storeImages(args[2], result_image);
    } else if (fileExtension.equalsIgnoreCase(Constants.PNG) ||
        fileExtension.equalsIgnoreCase(Constants.JPG) ||
        fileExtension.equalsIgnoreCase(Constants.BMP)) {
      result_image = loadStandardFormat(args[1]);
      model.storeImages(args[2], result_image);
    } else {
      view.displayInvalidFileFormat();
    }
  }

  /**
   * Loads an image in PPM format from a specified file.
   *
   * @param filename The file path of the image to be loaded.
   * @return The loaded image as an ImageInterface object.
   * @throws IOException If there is an issue with loading the image file.
   */
  private ImageInterface loadImageInPPM(String filename) throws IOException {
    Scanner sc;
    System.out.println(filename);
    try {
      sc = new Scanner(new FileInputStream(filename), "UTF-8");
      System.out.println(sc);
    } catch (FileNotFoundException e) {
      view.displayNoFileStatus();
      return null;
    }
    StringBuilder builder = new StringBuilder();

    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }

    sc = new Scanner(builder.toString());

    String token;
    if (!sc.hasNext()) {
      return null;
    }

    token = sc.next();
    if (!token.equals("P3")) {
      view.displayInvalidPPM();
    }
    //start generating Image
    if (!sc.hasNextInt()) {
      view.displayInvalidPPMNoValues();
      return new Image(0, 0, 0);
    }

    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    ImageInterface image = new Image(height, width, maxValue);
    PixelInterface[][] pixel = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        pixel[i][j] = new Pixel(r, g, b);
      }
    }
    image.setPixel(pixel);
    return image;
  }

  /**
   * Gets the file extension of a specified file.
   *
   * @param filename The file path of the file to get the extension of.
   * @return The file extension as a String.
   */
  private String getFileExtension(String filename) {
    Path path = Path.of(filename);
    String extension = "";
    if (path != null) {
      String name = path.getFileName().toString();
      int dotIndex = name.lastIndexOf(".");
      if (dotIndex > 0 && dotIndex < name.length() - 1) {
        extension = name.substring(dotIndex + 1);
      }
    }
    return extension;
  }

  private ImageInterface loadStandardFormat(String filename) throws IOException {
    BufferedImage bufferedImage = ImageIO.read(new File(filename));

    int width = bufferedImage.getWidth();
    int height = bufferedImage.getHeight();
    int maxValue = 255;

    PixelInterface[][] pixelData = new Pixel[height][width];
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Color color = new Color(bufferedImage.getRGB(x, y));
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        pixelData[y][x] = new Pixel(red, green, blue);
      }
    }

    ImageInterface image = new Image(height, width, maxValue);
    image.setPixel(pixelData);

    return image;
  }
}
