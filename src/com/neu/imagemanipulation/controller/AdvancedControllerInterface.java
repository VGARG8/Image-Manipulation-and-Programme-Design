package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.entity.ImageInterface;

import java.io.IOException;

/**
 * AdvancedControllerInterface extends ControllerInterface and provides additional functionality
 * for loading images in standard formats, generating images, and calling views for displaying
 * and processing the images.
 */

public interface AdvancedControllerInterface extends ControllerInterface {
  /**
   * Loads an image in a standard format from a given filename.
   *
   * @param filename the file path of the image to be loaded
   * @return the loaded ImageInterface object
   * @throws IOException if there's an issue with reading the file
   */
  ImageInterface loadStandardFormat(String filename) throws IOException;

  /**
   * Generates an image file from an ImageInterface object and saves it to the specified filename.
   *
   * @param image    the ImageInterface object to be saved
   * @param filename the file path where the image will be saved
   */
  void generateImage(ImageInterface image, String filename);

  /**
   * Calls the view for displaying and processing images using the main interface.
   *
   * @throws IOException if there's an issue with input or output operations
   */
  void callViewforMain() throws IOException;

  /**
   * Calls the view for displaying and processing images using the main interface.
   *
   * @throws IOException if there's an issue with input or output operations
   */
  void callViewForMain() throws IOException;
}
