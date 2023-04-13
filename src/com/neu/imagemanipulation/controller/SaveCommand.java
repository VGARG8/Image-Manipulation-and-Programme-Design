package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.entity.PixelInterface;
import com.neu.imagemanipulation.model.impl.GuiModelInteface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 A command that saves an image to a file.
 */
public class SaveCommand extends AbstractCommand implements CommandInterface {

  /**
   Constructs a new SaveCommand object with the specified view and model.
   @param view the view used to interact with the user
   @param model the model used to store and manipulate images
   */
  public SaveCommand(AdvancedViewInterface view,
                     GuiModelInteface model) {
    super(view, model);
  }

  @Override
  public void execute(String[] args) throws IOException {
    String filename = args[1];
    String fileExtension = getFileExtension(filename);
    if (!model.containsImages(args[2])) {
      view.displayImageDoesntExist();
    }
    if (fileExtension.equalsIgnoreCase("ppm")) {
      savePPM(args[1], model.getImages(args[2]));
    } else if (fileExtension.equalsIgnoreCase("png") ||
            fileExtension.equalsIgnoreCase("jpg") ||
            fileExtension.equalsIgnoreCase("bmp")) {
      generateImage(model.getImages(args[2]), filename);
    }
    view.displaySaveStatus(getFileExtension(args[1]));
  }

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

  private void savePPM(String filename, ImageInterface image) throws IOException {

    PrintWriter out;
    try {
      out = new PrintWriter(new FileWriter(filename));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    int width = image.getWidth();
    int height = image.getHeight();

    // Write the header
    out.println("P3");
    out.println("# Created by PPMWriter");
    out.println(width + " " + height);
    out.println("255");

    // Write the pixel values

    PixelInterface[][] pixelArray = image.getPixel();
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        int r = pixelArray[i][j].getRed();
        int g = pixelArray[i][j].getGreen();
        int b = pixelArray[i][j].getBlue();
        out.print(r + " " + g + " " + b + "  ");
      }
      out.println();
    }
    out.close();
  }

  private void generateImage(ImageInterface image, String filename) {
    List<Integer> pixels = new ArrayList<>();
    PixelInterface[][] pixelArray = image.getPixel();
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        int r = pixelArray[i][j].getRed();
        int g = pixelArray[i][j].getGreen();
        int b = pixelArray[i][j].getBlue();
        Color color = new Color(r, g, b);

        pixels.add(color.getRGB());
      }
    }
    BufferedImage outputImg = new BufferedImage(image.getWidth(), image.getHeight(),
            BufferedImage.TYPE_INT_RGB);
    int[] outputImagePixelData = ((DataBufferInt) outputImg.getRaster().getDataBuffer()).getData();

    for (int i = 0; i < pixels.size(); i++) {
      outputImagePixelData[i] = pixels.get(i);
    }
    try {
      ImageIO.write(outputImg, getFileExtension(filename),
              new File(filename));
    } catch (IOException e) {
      System.out.println("Exception occurred :" + e.getMessage());
    }
  }
}
