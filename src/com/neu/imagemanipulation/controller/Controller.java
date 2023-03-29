package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.Constants;
import com.neu.imagemanipulation.model.entity.Image;
import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.entity.Pixel;
import com.neu.imagemanipulation.model.entity.PixelInterface;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;
import com.neu.imagemanipulation.model.impl.ImageManipulationInterface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;
import com.neu.imagemanipulation.view.ViewInterface;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Scanner;


/**
 * Controller class implements ControllerInterface. It interacts with the user and controls the
 * model and view.
 */
public class Controller implements ControllerInterface {

  Boolean flag;
  AdvancedViewInterface view;
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
                    AdvancedViewInterface view) {
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
  public ImageInterface loadImageInPPM(String filename) throws IOException {
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


  @Override
  public void savePPM(String filename, ImageInterface image) throws RuntimeException {

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


  @Override
  public void runCommand(String command) throws IOException {
    String[] tokens = command.split("\\s+");
    ImageInterface result_image;
    label:
    switch (tokens[0].toLowerCase()) {
      case Constants.LOAD:
        view.displayLoadingStatus();
        String ext = getFileExtension(tokens[1]);
        if (ext.equalsIgnoreCase(Constants.PPM)) {
          result_image = loadImageInPPM(tokens[1]);
          model.storeImages(tokens[2], result_image);
        } else {
          view.displayInvalidFileFormat();
        }
        break;
      case Constants.SAVE:
        if (!model.containsImages(tokens[2])) {
          view.displayImageDoesntExist();
          break;
        }
        if (getFileExtension(tokens[1]).equalsIgnoreCase(Constants.PPM)) {
          savePPM(tokens[1], model.getImages(tokens[2]));
        }
        view.displaySaveStatus(getFileExtension(tokens[1]));
        break;
      case Constants.GREYSCALE:
        switch (tokens[1]) {
          case Constants.VALUE_COMPONENT:
            if (!model.containsImages(tokens[2])) {
              view.displayImageDoesntExist();
              break label;
            }
            view.displayValueStatus();
            result_image = model.createValueComponentOfImage(model.getImages(tokens[2]));
            model.storeImages(tokens[3], result_image);
            break;
          case Constants.LUMA_COMPONENT:
            if (!model.containsImages(tokens[2])) {
              view.displayImageDoesntExist();
              break label;
            }
            view.displayLumaStatus();
            result_image = model.createLumaComponentOfImage(model.getImages(tokens[2]));
            model.storeImages(tokens[3], result_image);
            break;
          case Constants.INTENSITY_COMPONENT:
            if (!model.containsImages(tokens[2])) {
              view.displayImageDoesntExist();
              break label;
            }
            view.displayIntensityStatus();
            result_image = model.createIntensityComponentOfImage(model.getImages(tokens[2]));
            model.storeImages(tokens[3], result_image);
            break;
          case Constants.RED_COMPONENT:
            if (!model.containsImages(tokens[2])) {
              view.displayImageDoesntExist();
              break label;
            }
            view.displayRedComponentStatus();
            result_image = model.createRedComponentOfImage(model.getImages(tokens[2]));
            model.storeImages(tokens[3], result_image);
            break;
          case Constants.GREEN_COMPONENT:
            if (!model.containsImages(tokens[2])) {
              view.displayImageDoesntExist();
              break label;
            }
            view.displayGreenComponentStatus();
            result_image = model.createGreenComponentOfImage(model.getImages(tokens[2]));
            model.storeImages(tokens[3], result_image);
            break;
          case Constants.BLUE_COMPONENT:
            if (!model.containsImages(tokens[2])) {
              view.displayImageDoesntExist();
              break label;
            }
            view.displayBlueComponentStatus();
            result_image = model.createBlueComponentOfImage(model.getImages(tokens[2]));
            model.storeImages(tokens[3], result_image);
            break;
          default:
            view.displayInvalidSyntax();
        }
        break;
      case Constants.HORIZONTAL_FLIP:
        view.displayHorizontalFlipStatus();
        if (!model.containsImages(tokens[1])) {
          view.displayImageDoesntExist();
          break;
        }
        result_image = model.flipImageHorizontally(model.getImages(tokens[1]));
        model.storeImages(tokens[2], result_image);
        break;
      case Constants.VERTICAL_FLIP:
        if (!model.containsImages(tokens[1])) {
          view.displayImageDoesntExist();
          break;
        } else {
          view.displayVerticalFlipStatus();
          result_image = model.flipImageVertically(model.getImages(tokens[1]));
          model.storeImages(tokens[2], result_image);
          break;
        }
      case Constants.BRIGHTEN:
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
      case Constants.DARKEN:
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
      case Constants.RGB_SPLIT:
        if (!model.containsImages(tokens[1])) {
          view.displayImageDoesntExist();
          break;
        }
        view.displayRGBSplitStatus();
        ImageInterface[] result_images = model.splitIntoRGBImages(model.getImages(tokens[1]));
        model.storeImages(tokens[2], result_images[0]);
        model.storeImages(tokens[3], result_images[1]);
        model.storeImages(tokens[4], result_images[2]);
        break;
      case Constants.RGB_COMBINE:
        if (!model.containsImages(tokens[3])) {
          view.displayImageDoesntExist();
          break;
        }
        view.displayRGBCombineStatus();
        ImageInterface[] rgb_images = {model.getImages(tokens[2]), model.getImages(tokens[3]),
                model.getImages(tokens[4])};
        result_image = model.combineRGBImages(rgb_images);
        model.storeImages(tokens[1], result_image);
        break;

      case Constants.RUN_SCRIPT:
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
      case Constants.EXIT:
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


  String getFileExtension(String filename) {
    Path path = Path.of(filename);
    String extension = "";
    String name = path.getFileName().toString();
    int dotIndex = name.lastIndexOf(".");
    if (dotIndex > 0 && dotIndex < name.length() - 1) {
      extension = name.substring(dotIndex + 1);
    }
    return extension;
  }

}
