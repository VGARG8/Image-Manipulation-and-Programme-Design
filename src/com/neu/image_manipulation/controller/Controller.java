package com.neu.image_manipulation.controller;

import com.neu.image_manipulation.model.entity.Image;
import com.neu.image_manipulation.model.entity.Pixel;
import com.neu.image_manipulation.model.impl.ImageManipulationInterface;
import com.neu.image_manipulation.view.ViewInterface;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Controller implements ControllerInterface {
  Boolean flag;
  ViewInterface view;
  ImageManipulationInterface model;

  public Controller(ImageManipulationInterface model, ViewInterface view) {
    this.flag = true;
    this.view = view;
    this.model = model;
    this.view.displayMenu();
  }

  @Override
  public void go() throws IOException {
    Scanner sc = new Scanner(System.in);
    while (flag) {
      view.getCommand();
      runCommand(sc.nextLine().toLowerCase().trim());

    }
  }

  @Override
  public Image loadImageInPPM(String filename) throws IOException {
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      view.displayNoFileStatus();
      return null;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }
    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      view.displayInvalidPPM();
    }
    //start generating Image
    if(!sc.hasNextInt()){
      view.displayInvalidPPMNoValues();
      return new Image(0,0,0);

    }
    int width = sc.nextInt();
//    view.displayWidth(width);

    int height = sc.nextInt();
//    view.displayHeight(height);

    int maxValue = sc.nextInt();
//    view.displayMaxValue(maxValue);


    Image image = new Image(height, width, maxValue);
    Pixel[][] pixel = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
//        System.out.println("Color of pixel (" + j + "," + i + "): " + r + "," + g + "," + b);
        pixel[i][j] = new Pixel(r, g, b);
      }
    }
    image.setPixel(pixel);
    return image;
  }

  @Override
  public void generateImage(String filename, Image image) {
    List<Integer> pixels = new ArrayList<>();
    Pixel[][] pixelArray = image.getPixel();
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
      ImageIO.write(outputImg, "png",
              new File("resources/" + filename + ".png"));
    } catch (IOException e) {
      System.out.println("Exception occurred :" + e.getMessage());
    }
  }

  @Override
  public void savePPM(String filename, Image image) throws IOException {

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

    Pixel[][] pixelArray = image.getPixel();
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

  public void generateImage(Image[] images, String filename1, String filename2, String filename3,
                            Boolean bool) {
    if (bool) {
      generateImage(filename1, images[0]);
      generateImage(filename2, images[1]);
      generateImage(filename3, images[2]);
    } else {
      generateImage(filename1, images[0]);
      generateImage(filename2, images[1]);
      generateImage(filename3, images[2]);
    }
  }
  @Override
  public void runCommand(String command) throws IOException {
    String[] tokens = command.split("\\s+");
    Image result_image;
    switch (tokens[0]) {
      case "load":
        view.displayLoadingStatus();
        result_image = loadImageInPPM(tokens[1]);
        model.storeImages(tokens[2], result_image);
        break;
      case "save":
        view.displaySaveStatus();
        savePPM(tokens[1], model.getImages(tokens[2]));
        break;
      case "greyscale":
        if (tokens[1].equals("value-component")) {
          view.displayValueStatus();
          result_image = model.createValueComponentOfImage(model.getImages(tokens[2]));
          model.storeImages(tokens[3], result_image);
        } else if (tokens[1].equals("luma-component")) {
          view.displayLumaStatus();
          result_image = model.createLumaComponentOfImage(model.getImages(tokens[2]));
          model.storeImages(tokens[3], result_image);
        } else if (tokens[1].equals("intensity-component")) {
          view.displayIntensityStatus();
          result_image = model.createIntensityComponentOfImage(model.getImages(tokens[2]));
          model.storeImages(tokens[3], result_image);
        } else if (tokens[1].equals("red-component")) {
          view.displayRedComponentStatus();
          result_image = model.createRedComponentOfImage(model.getImages(tokens[2]));
          model.storeImages(tokens[3], result_image);
        } else if (tokens[1].equals("green-component")) {
          view.displayGreenComponentStatus();
          result_image = model.createGreenComponentOfImage(model.getImages(tokens[2]));
          model.storeImages(tokens[3], result_image);
        } else if (tokens[1].equals("blue-component")) {
          view.displayBlueComponentStatus();
          result_image = model.createBlueComponentOfImage(model.getImages(tokens[2]));
          model.storeImages(tokens[3], result_image);
        }
        break;
      case "horizontal-flip":
        view.displayHorizontalFlipStatus();
        result_image = model.flipImageHorizontally(model.getImages(tokens[1]));
        model.storeImages(tokens[2], result_image);
        break;
      case "vertical-flip":
        view.displayVerticalFlipStatus();
        result_image = model.flipImageVertically(model.getImages(tokens[1]));
        model.storeImages(tokens[2], result_image);
        break;
      case "brighten":
        if (Integer.parseInt(tokens[1])<0){
          view.displayInvalidValue();
          break;
      }
        view.displayBrightenStatus();
        result_image = model.brightenImage(model.getImages(tokens[2]), Integer.parseInt(tokens[1]));
        model.storeImages(tokens[3], result_image);
        break;
      case "darken":
        if (Integer.parseInt(tokens[1])<0){
          view.displayInvalidValue();
          break;
        }
        view.displayDarkenenStatus();
        result_image = model.darkenImage(model.getImages(tokens[2]), Integer.parseInt(tokens[1]));
        model.storeImages(tokens[3], result_image);
        break;
      case "rgb-split":
        view.displayRGBSplitStatus();
        Image[] result_images = model.splitIntoRGBImages(model.getImages(tokens[1]));
        model.storeImages(tokens[2], result_images[0]);
        model.storeImages(tokens[3], result_images[1]);
        model.storeImages(tokens[4], result_images[2]);
        break;
      case "rgb-combine":
        view.displayRGBCombineStatus();
        Image[] rgb_images = {model.getImages(tokens[2]), model.getImages(tokens[3]),
                model.getImages(tokens[4])};
        result_image = model.combineRGBImages(rgb_images);
        model.storeImages(tokens[1], result_image);
        break;
      case "run-script":
        try {
          view.displayRunScriptStatus(tokens[1]);
          BufferedReader reader = new BufferedReader(new FileReader(tokens[1]));
          String line;
          while ((line = reader.readLine()) != null) {
            if (line.startsWith("#") || line.isEmpty()) {
              continue;
            }
            runCommand(line);
          }
          reader.close();
        } catch (IOException e) {
          view.displayReadFileError();

        }
        break;
      case "exit":
        flag = false;
        break;
      default:
        System.out.println("Enter a valid a command!");
        break;
    }
  }

  private enum ImageType {
    Red,
    Green,
    Blue,
    Luma,
    Intensity,
    Value
  }
}
