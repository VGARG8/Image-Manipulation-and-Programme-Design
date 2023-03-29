package com.neu.imagemanipulation.controller;

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
 * Controller class implements ControllerInterface. It interacts with the
 * user and controls the model and view.
 */
public class Controller implements ControllerInterface {
  protected Boolean flag;
  protected AdvancedViewInterface view;
  protected AdvancedImageManipulationInterface model;
  final Readable in;
  private final Appendable out;

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

//  @Override
//  public void execute() throws IOException {
//    Scanner sc = new Scanner(this.in);
//    while (flag) {
//      view.getCommand();
//      runCommand(sc.nextLine().toLowerCase().trim());
//
//    }
//  }
//
//  @Override
//  public Image loadImageInPPM(String filename) throws IOException {
//    Scanner sc;
//    try {
//      sc = new Scanner(new FileInputStream(filename));
//    } catch (FileNotFoundException e) {
//      view.displayNoFileStatus();
//      return null;
//    }
//    StringBuilder builder = new StringBuilder();
//    //read the file line by line, and populate a string. This will throw away any comment lines
//    while (sc.hasNextLine()) {
//      String s = sc.nextLine();
//      if (s.charAt(0) != '#') {
//        builder.append(s).append(System.lineSeparator());
//      }
//    }
//    //now set up the scanner to read from the string we just built
//    sc = new Scanner(builder.toString());
//
//    String token;
//
//    token = sc.next();
//    if (!token.equals("P3")) {
//      view.displayInvalidPPM();
//    }
//    //start generating Image
//    if (!sc.hasNextInt()) {
//      view.displayInvalidPPMNoValues();
//      return new Image(0, 0, 0);
//    }
//
//    int width = sc.nextInt();
//    int height = sc.nextInt();
//    int maxValue = sc.nextInt();
//
//
//    Image image = new Image(height, width, maxValue);
//    Pixel[][] pixel = new Pixel[height][width];
//    for (int i = 0; i < height; i++) {
//      for (int j = 0; j < width; j++) {
//        int r = sc.nextInt();
//        int g = sc.nextInt();
//        int b = sc.nextInt();
//        pixel[i][j] = new Pixel(r, g, b);
//      }
//    }
//    image.setPixel(pixel);
//    return image;
//  }
//
//
//  @Override
//  public void savePPM(String filename, ImageInterface image) throws IOException {
//
//    PrintWriter out;
//    try {
//      out = new PrintWriter(new FileWriter(filename));
//    } catch (IOException e) {
//      throw new RuntimeException(e);
//    }
//
//    int width = image.getWidth();
//    int height = image.getHeight();
//
//    // Write the header
//    out.println("P3");
//    out.println("# Created by PPMWriter");
//    out.println(width + " " + height);
//    out.println("255");
//
//    // Write the pixel values
//
//    PixelInterface[][] pixelArray = image.getPixel();
//    for (int i = 0; i < image.getHeight(); i++) {
//      for (int j = 0; j < image.getWidth(); j++) {
//        int r = pixelArray[i][j].getRed();
//        int g = pixelArray[i][j].getGreen();
//        int b = pixelArray[i][j].getBlue();
//        out.print(r + " " + g + " " + b + "  ");
//      }
//      out.println();
//    }
//    out.close();
//  }
//
//



  public void runCommand(CommandInterface command, String[] args) throws IOException{
    command.execute(args);
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
