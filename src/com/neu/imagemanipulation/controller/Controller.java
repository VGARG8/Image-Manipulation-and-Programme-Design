package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.entity.Image;
import com.neu.imagemanipulation.model.entity.Pixel;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;
import com.neu.imagemanipulation.model.impl.ImageManipulationInterface;
import com.neu.imagemanipulation.view.ViewInterface;

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
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import javax.imageio.ImageIO;

/**
 * Controller class implements ControllerInterface. It interacts with the
 * user and controls the model and view.
 */
public class Controller implements ControllerInterface {
  Boolean flag;
  ViewInterface view;
  AdvancedImageManipulationInterface model;
  final Readable in;
  final Appendable out;

  /**
   * Constructs a new Controller object with the specified input and output streams, model, and
   * view.
   *
   * @param in    the input stream to read from
   * @param out   the output stream to write to
   * @param model the ImageManipulationInterface model to use for image manipulation operations
   * @param view  the ViewInterface view to use for displaying the manipulated images
   * @throws NullPointerException if the model parameter is null
   */
  public Controller(Readable in, Appendable out, AdvancedImageManipulationInterface model,
                    ViewInterface view) {
    Objects.requireNonNull(model);
    this.flag = true;
    this.view = view;
    this.model = model;
    this.in = in;
    this.out = out;
  }

  @Override
  public void execute() throws IOException {
    Scanner sc = new Scanner(this.in);
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
    if (!sc.hasNextInt()) {
      view.displayInvalidPPMNoValues();
      return new Image(0, 0, 0);
    }

    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();


    Image image = new Image(height, width, maxValue);
    Pixel[][] pixel = new Pixel[height][width];
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

  @Override
  public Image loadStandardFormat(String filename) throws IOException {
    BufferedImage bufferedImage = ImageIO.read(new File(filename));

    int width = bufferedImage.getWidth();
    int height = bufferedImage.getHeight();
    int maxValue = 255;

    Pixel[][] pixelData = new Pixel[height][width];
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Color color = new Color(bufferedImage.getRGB(x, y));
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        pixelData[y][x] = new Pixel(red, green, blue);
      }
    }

    Image image = new Image(height, width, maxValue);
    image.setPixel(pixelData);

    return image;
  }

  @Override
  public void generateImage(Image image, String filename) {
    List<Integer> pixels = new ArrayList<>();
    Pixel[][] pixelArray = image.getPixel();
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        int r = pixelArray[i][j].getRed();
        int g = pixelArray[i][j].getGreen();
        int b = pixelArray[i][j].getBlue();
        Color color = new Color( r, g, b);

        pixels.add(color.getRGB());
      }
    }
    BufferedImage outputImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
    int[] outputImagePixelData = ((DataBufferInt) outputImg.getRaster().getDataBuffer()).getData();

    for (int i = 0; i < pixels.size(); i++) {
      outputImagePixelData[i] = pixels.get(i);
    }
    try {
      ImageIO.write(outputImg, getFileExtension(filename) ,
              new File("Res/"+filename));
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


  @Override
  public void runCommand(String command) throws IOException {
    String[] tokens = command.split("\\s+");
    Image result_image;
    switch (tokens[0]) {
      case "load":
        view.displayLoadingStatus();
        String ext = getFileExtension(tokens[1]);
        if(ext.equalsIgnoreCase("ppm")){
          result_image = loadImageInPPM(tokens[1]);
          model.storeImages(tokens[2], result_image);
        } else if (ext.equalsIgnoreCase("jpg") || ext.equalsIgnoreCase("png")
          || ext.equalsIgnoreCase("bmp") || ext.equalsIgnoreCase("jpeg")){
          result_image = loadStandardFormat(tokens[1]);
          model.storeImages(tokens[2], result_image);
        } else{
          view.displayInvalidFileFormat();
        }


        break;
      case "save":
        if (!model.containsImages(tokens[2])) {
          view.displayImageDoesntExist();
          break;
        }
        if(getFileExtension(tokens[1]).equalsIgnoreCase("ppm")){
          savePPM(tokens[1], model.getImages(tokens[2]));
        } else if (getFileExtension(tokens[1]).equalsIgnoreCase("png") ||
                getFileExtension(tokens[1]).equalsIgnoreCase("jpg") ||
                getFileExtension(tokens[1]).equalsIgnoreCase("jpeg") ||
                getFileExtension(tokens[1]).equalsIgnoreCase("bmp")) {
          generateImage(model.getImages(tokens[2]), tokens[1]);
        }
        view.displaySaveStatus();
        break;
      case "greyscale":
        if (tokens[1].equals("value-component")) {
          if (!model.containsImages(tokens[2])) {
            view.displayImageDoesntExist();
            break;
          }
          view.displayValueStatus();
          result_image = model.createValueComponentOfImage(model.getImages(tokens[2]));
          model.storeImages(tokens[3], result_image);
        } else if (tokens[1].equals("luma-component")) {
          if (!model.containsImages(tokens[2])) {
            view.displayImageDoesntExist();
            break;
          }
          view.displayLumaStatus();
          result_image = model.createLumaComponentOfImage(model.getImages(tokens[2]));
          model.storeImages(tokens[3], result_image);
        } else if (tokens[1].equals("intensity-component")) {
          if (!model.containsImages(tokens[2])) {
            view.displayImageDoesntExist();
            break;
          }
          view.displayIntensityStatus();
          result_image = model.createIntensityComponentOfImage(model.getImages(tokens[2]));
          model.storeImages(tokens[3], result_image);
        } else if (tokens[1].equals("red-component")) {
          if (!model.containsImages(tokens[2])) {
            view.displayImageDoesntExist();
            break;
          }
          view.displayRedComponentStatus();
          result_image = model.createRedComponentOfImage(model.getImages(tokens[2]));
          model.storeImages(tokens[3], result_image);
        } else if (tokens[1].equals("green-component")) {
          if (!model.containsImages(tokens[2])) {
            view.displayImageDoesntExist();
            break;
          }
          view.displayGreenComponentStatus();
          result_image = model.createGreenComponentOfImage(model.getImages(tokens[2]));
          model.storeImages(tokens[3], result_image);
        } else if (tokens[1].equals("blue-component")) {
          if (!model.containsImages(tokens[2])) {
            view.displayImageDoesntExist();
            break;
          }
          view.displayBlueComponentStatus();
          result_image = model.createBlueComponentOfImage(model.getImages(tokens[2]));
          model.storeImages(tokens[3], result_image);
        }
        break;
      case "horizontal-flip":
        view.displayHorizontalFlipStatus();
        if (!model.containsImages(tokens[1])) {
          view.displayImageDoesntExist();
          break;
        }
        result_image = model.flipImageHorizontally(model.getImages(tokens[1]));
        model.storeImages(tokens[2], result_image);
        break;
      case "vertical-flip":
        if (!model.containsImages(tokens[1])) {
          view.displayImageDoesntExist();
          break;
        }
        view.displayVerticalFlipStatus();
        result_image = model.flipImageVertically(model.getImages(tokens[1]));
        model.storeImages(tokens[2], result_image);
        break;
      case "brighten":
        if (Integer.parseInt(tokens[1]) < 0) {
          view.displayInvalidValue();
          break;
        }
        if (!model.containsImages(tokens[2])) {
          view.displayImageDoesntExist();
          break;
        }
        view.displayBrightenStatus();
        result_image = model.brightenImage(model.getImages(tokens[2]), Integer.parseInt(tokens[1]));
        model.storeImages(tokens[3], result_image);
        break;
      case "darken":
        if (Integer.parseInt(tokens[1]) < 0) {
          view.displayInvalidValue();
          break;
        }
        if (!model.containsImages(tokens[2])) {
          view.displayImageDoesntExist();
          break;
        }
        view.displayDarkenenStatus();
        result_image = model.darkenImage(model.getImages(tokens[2]), Integer.parseInt(tokens[1]));
        model.storeImages(tokens[3], result_image);
        break;
      case "rgb-split":
        if (!model.containsImages(tokens[1])) {
          view.displayImageDoesntExist();
          break;
        }
        view.displayRGBSplitStatus();
        Image[] result_images = model.splitIntoRGBImages(model.getImages(tokens[1]));
        model.storeImages(tokens[2], result_images[0]);
        model.storeImages(tokens[3], result_images[1]);
        model.storeImages(tokens[4], result_images[2]);
        break;
      case "rgb-combine":
        if (!model.containsImages(tokens[3])) {
          view.displayImageDoesntExist();
          break;
        }
        view.displayRGBCombineStatus();
        Image[] rgb_images = {model.getImages(tokens[2]), model.getImages(tokens[3]),
                model.getImages(tokens[4])};
        result_image = model.combineRGBImages(rgb_images);
        model.storeImages(tokens[1], result_image);
        break;
      case "blur":
        view.displayBlurStatus();
        if (!model.containsImages(tokens[1])) {
          view.displayImageDoesntExist();
          break;
        }
        result_image = model.blur(model.getImages(tokens[1]));
        model.storeImages(tokens[2], result_image);
        break;
      case "sharpen":
        view.displaySharpenStatus();
        if (!model.containsImages(tokens[1])) {
          view.displayImageDoesntExist();
          break;
        }
        result_image = model.sharpen(model.getImages(tokens[1]));
        model.storeImages(tokens[2], result_image);
        break;
      case "greyscale-tone":
        view.displayGreyScaleStatus();
        if (!model.containsImages(tokens[1])) {
          view.displayImageDoesntExist();
          break;
        }
        result_image = model.greyscale(model.getImages(tokens[1]));
        model.storeImages(tokens[2], result_image);
        break;
      case "sepia-tone":
        view.displaySepiaStatus();
        if (!model.containsImages(tokens[1])) {
          view.displayImageDoesntExist();
          break;
        }
        result_image = model.sepiaTone(model.getImages(tokens[1]));
        model.storeImages(tokens[2], result_image);
        break;
      case "dither":
        view.displayDitherStatus();
        if (!model.containsImages(tokens[1])) {
          view.displayImageDoesntExist();
          break;
        }
        result_image = model.dither(model.getImages(tokens[1]));
        model.storeImages(tokens[2], result_image);
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
        view.displayEnterValidCommand();
        break;
    }
  }

  @Override
  public ImageManipulationInterface getModel() {
    return this.model;
  }

  @Override
  public ViewInterface getView() {
    return this.view;
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


  private enum ImageType {
    Red,
    Green,
    Blue,
    Luma,
    Intensity,
    Value
  }
}
