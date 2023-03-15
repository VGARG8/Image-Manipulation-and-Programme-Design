package com.neu.image_manipulation.view;

public class View implements ViewInterface {

  @Override
  public void displayMenu() {
    System.out.println("Menu");
    System.out.println("The Available functions are: \n" +
            "1.  Load: load the image from a filepath.\n" +
            "2.  Save: save the image in a filepath.\n" +
            "3.  Brighten: brightens the image by a value.\n" +
            "4.  Darken: darkens the image by a value.\n" +
            "5.  Vertical-flip: flips the image vertically.\n" +
            "6.  Horizontal-flip: flips the image horizontally.\n" +
            "7.  greyscale red-component: Creates greyscale image with the red-component.\n" +
            "8.  greyscale green-component: Creates greyscale image with the green-component.\n" +
            "9.  greyscale blue-component: Creates greyscale image with the blue-component.\n" +
            "10. greyscale value-component: Creates greyscale image with the value-component.\n" +
            "11. greyscale intensity-component: Creates greyscale image with the " +
            "intensity-component.\n" +
            "12. greyscale luma-component: Creates greyscale image with the luma-component.\n" +
            "13. rgb-split: splits the given image into three greyscale images containing its red, " +
            "green and blue components respectively.\n" +
            "14. rgb-combine: Combine the three greyscale images into a single image that gets its " +
            "red, green and blue components from the three images respectively.\n" +
            "15. run-script: Load and run the script commands in the specified file.\n");
  }

  @Override
  public void getCommand() {
    System.out.println("Enter the command: ");
  }

  @Override
  public void displaySaveStatus(String filename) {
    System.out.println("Saving as: " + filename);
  }

  @Override
  public void displayReadFileError() {
    System.out.println("Can't read the file!");
  }

  @Override
  public void displayLoadingStatus(String filename, String name) {
    System.out.println("Loading the file " + filename + " as : " + name);
  }

  @Override
  public void displayValueStatus(String name) {
    System.out.println("storing the image's greyscale value component as: " + name);
  }

  @Override
  public void displayLumaStatus(String name) {
    System.out.println("storing the image's greyscale luma component as: " + name);
  }

  @Override
  public void displayIntensityStatus(String name) {
    System.out.println("storing the image's greyscale intensity component as: " + name);
  }

  @Override
  public void displayHorizontalFlipStatus(String name) {
    System.out.println("storing the image after horizontal flip as: " + name);
  }

  @Override
  public void displayVerticalFlipStatus(String name) {
    System.out.println("storing the image after vertical flip as: " + name);
  }

  @Override
  public void displayBrightenStatus(int value, String name) {
    System.out.println("brightening the image by " + value + " and storing as: " + name);
  }

  @Override
  public void displayDarkenenStatus(int value, String name) {
    System.out.println("darkening the image by " + value + " and storing as: " + name);
  }

  @Override
  public void displayRunScriptStatus(String filepath) {
    System.out.println("Running the script from: " + filepath);
  }

  @Override
  public void displayRGBSplitStatus() {
    System.out.println("Splitting the image into it's Red, Green, Blue channels. ");
  }

  @Override
  public void displayRGBCombineStatus() {
    System.out.println("combining the Red, Green, Blue channels to form an image.");
  }

  @Override
  public void displayInvalidValue() {
    System.out.println("Value should be a non-negative integer.");
  }

  @Override
  public void displayNoFileStatus(String filename) {
    System.out.println("File " + filename + " not found!");
  }

  @Override
  public void displayInvalidPPM() {
    System.out.println("Invalid PPM file: plain RAW file should begin with P3.");
  }

  @Override
  public void displayEmptyFileStatus() {
    System.out.println("File is Empty!");
  }

  @Override
  public void displayInvalidPPMNoValues() {
    System.out.println("PPM file got no values after the header. Image with 0x0 dimensions is " +
            "created");
  }

  @Override
  public void displayWidth(int width) {
    System.out.println("Width of image: " + width);
  }

  @Override
  public void displayHeight(int height) {
    System.out.println("Height of image: " + height);
  }

  @Override
  public void displayMaxValue(int maxValue) {
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);
  }

}
