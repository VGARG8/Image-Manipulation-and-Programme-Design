package com.neu.imagemanipulation.view;

import java.io.IOException;

/**
 * The View class is responsible for providing the user interface for the Image Manipulation
 * program. It implements the ViewInterface, which provides a contract for the methods that a View
 * class must implement.
 */

public class View implements ViewInterface {
  final Appendable out;

  /**
   * The View constructor initializes the out field with the System.Out object.
   * This constructor is called when a new View object is created.
   */
  public View() {
    this.out = System.out;
  }

  @Override
  public void getCommand() throws IOException {
    out.append("Enter the command:\n");
  }

  @Override
  public void displaySaveStatus() throws IOException {
    out.append("Saving image as ppm\n");
  }

  @Override
  public void displayReadFileError() throws IOException {
    out.append("Can't read the file!\n");
  }

  @Override
  public void displayLoadingStatus() throws IOException {
    out.append("Loading the file\n");
  }

  @Override
  public void displayValueStatus() throws IOException {
    out.append("Storing the image's greyscale value component\n");
  }

  @Override
  public void displayLumaStatus() throws IOException {
    out.append("Storing the image's greyscale luma component\n");
  }

  @Override
  public void displayIntensityStatus() throws IOException {
    out.append("Storing the image's greyscale intensity component\n");
  }

  @Override
  public void displayHorizontalFlipStatus() throws IOException {
    out.append("Storing the image after horizontal flip\n");
  }

  @Override
  public void displayVerticalFlipStatus() throws IOException {
    out.append("Storing the image after vertical flip\n");
  }

  @Override
  public void displayBrightenStatus() throws IOException {
    out.append("Brightening the image\n");
  }

  @Override
  public void displayDarkenenStatus() throws IOException {
    out.append("Darkening the image\n");
  }

  @Override
  public void displayRunScriptStatus(String filepath) throws IOException {
    out.append("Running the script from: ").append(filepath).append("\n");
  }

  @Override
  public void displayRGBSplitStatus() throws IOException {
    out.append("Splitting the image into it's Red, Green, Blue channels.\n");
  }

  @Override
  public void displayRGBCombineStatus() throws IOException {
    out.append("combining the Red, Green, Blue channels to form an image.\n");
  }

  @Override
  public void displayInvalidValue() throws IOException {
    out.append("Value should be a non-negative integer.\n");
  }


  @Override
  public void displayNoFileStatus() throws IOException {
    out.append("File not found!\n");
  }

  @Override
  public void displayInvalidPPM() throws IOException {
    out.append("Invalid PPM file: plain RAW file should begin with P3.\n");
  }

  @Override
  public void displayEmptyFileStatus() throws IOException {
    out.append("File is Empty!\n");
  }

  @Override
  public void displayInvalidPPMNoValues() throws IOException {
    out.append("PPM file got no values after the header. Image with 0x0 dimensions is " +
            "created\n");
  }

  @Override
  public void displayWidth(int width) throws IOException {
    out.append("Width of image: ").append(String.valueOf(width)).append("\n");
  }

  @Override
  public void displayHeight(int height) throws IOException {
    out.append("Height of image: ").append(String.valueOf(height)).append("\n");
  }

  @Override
  public void displayMaxValue(int maxValue) throws IOException {
    out.append("Maximum value of a color in this file (usually 255): ")
            .append(String.valueOf(maxValue)).append("\n");
  }

  @Override
  public void displayBlueComponentStatus() throws IOException {
    out.append("Creating greyscale image with blue component of the image.\n");
  }

  @Override
  public void displayRedComponentStatus() throws IOException {
    out.append("Creating greyscale image with red component of the image.\n");
  }

  @Override
  public void displayGreenComponentStatus() throws IOException {
    out.append("Creating greyscale image with green component of the image.\n");
  }

  @Override
  public void displayEnterValidCommand() throws IOException {
    out.append("Enter a valid a command!\n");
  }

  @Override
  public void displayExceptionMessage(String message) throws IOException {
    out.append("Exception occurred :").append(message).append("\n");
  }

  @Override
  public void displayImageDoesntExist() throws IOException {
    out.append("Image doesn't exist!\n");
  }



}
