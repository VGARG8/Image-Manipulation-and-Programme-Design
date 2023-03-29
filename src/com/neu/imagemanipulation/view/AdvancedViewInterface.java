package com.neu.imagemanipulation.view;

import java.io.IOException;

/**
 * AdvancedViewInterface extends ViewInterface and provides additional functionality for
 * displaying various status messages related to image manipulation operations, such as
 * invalid file formats, greyscale conversion, sharpening, sepia tone, dithering, blurring,
 * and file not specified errors.
 */
public interface AdvancedViewInterface extends ViewInterface {
  /**
   * Displays a message indicating an invalid file format.
   *
   * @throws IOException if there's an issue with input or output operations
   */
  void displayInvalidFileFormat() throws IOException;

  /**
   * Displays a message indicating the greyscale conversion status.
   *
   * @throws IOException if there's an issue with input or output operations
   */
  void displayGreyScaleStatus() throws IOException;

  /**
   * Displays a message indicating the sharpening operation status.
   *
   * @throws IOException if there's an issue with input or output operations
   */
  void displaySharpenStatus() throws IOException;

  /**
   * Displays a message indicating the sepia tone conversion status.
   *
   * @throws IOException if there's an issue with input or output operations
   */
  void displaySepiaStatus() throws IOException;

  /**
   * Displays a message indicating the dithering operation status.
   *
   * @throws IOException if there's an issue with input or output operations
   */
  void displayDitherStatus() throws IOException;

  /**
   * Displays a message indicating the blurring operation status.
   *
   * @throws IOException if there's an issue with input or output operations
   */
  void displayBlurStatus() throws IOException;

  /**
   * Displays a message indicating that a file has not been specified.
   *
   * @throws IOException if there's an issue with input or output operations
   */
  void displayFileNotSpecified() throws IOException;
}
