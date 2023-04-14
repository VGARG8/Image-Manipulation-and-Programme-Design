package com.neu.imagemanipulation.view;

import java.io.IOException;

/**
 * This interface extends the ViewInterface and provides additional methods for
 * displaying status messages specific to advanced image manipulation operations.
 */
public interface AdvancedViewInterface extends ViewInterface {

  /**
   * Displays a message indicating that the input file format is invalid.
   *
   * @throws IOException if an error occurs during the operation
   */
  void displayInvalidFileFormat() throws IOException;

  /**
   * Displays a message indicating the progress of the greyscale operation.
   *
   * @throws IOException if an error occurs during the operation
   */
  void displayGreyScaleStatus() throws IOException;

  /**
   * Displays a message indicating the progress of the sharpen operation.
   *
   * @throws IOException if an error occurs during the operation
   */
  void displaySharpenStatus() throws IOException;

  /**
   * Displays a message indicating the progress of the sepia operation.
   *
   * @throws IOException if an error occurs during the operation
   */
  void displaySepiaStatus() throws IOException;

  /**
   * Displays a message indicating the progress of the dither operation.
   *
   * @throws IOException if an error occurs during the operation
   */
  void displayDitherStatus() throws IOException;

  /**
   * Displays a message indicating the progress of the blur operation.
   *
   * @throws IOException if an error occurs during the operation
   */
  void displayBlurStatus() throws IOException;

  /**
   * Displays a message indicating that no file was specified for the operation.
   *
   * @throws IOException if an error occurs during the operation
   */
  void displayFileNotSpecified() throws IOException;

  /**
   * Displays a message indicating the progress of the histogram operation.
   *
   * @throws IOException if an error occurs during the operation
   */
  void displayHistogramStatus() throws IOException;
}
