package com.neu.imagemanipulation.view;

import java.io.IOException;

/**
 * This interface extends the {@link ViewInterface} interface to provide additional methods for displaying advanced image processing features.
 */
public interface AdvancedViewInterface extends ViewInterface {

  /**
   * Displays an error message indicating that the file format is invalid.
   *
   * @throws IOException if an I/O error occurs while displaying the message.
   */
  void displayInvalidFileFormat() throws IOException;

  /**
   * Displays a message indicating that the image has been converted to grayscale.
   *
   * @throws IOException if an I/O error occurs while displaying the message.
   */
  void displayGreyScaleStatus() throws IOException;

  /**
   * Displays a message indicating that the image has been sharpened.
   *
   * @throws IOException if an I/O error occurs while displaying the message.
   */
  void displaySharpenStatus() throws IOException;

  /**
   * Displays a message indicating that the image has been converted to sepia tone.
   *
   * @throws IOException if an I/O error occurs while displaying the message.
   */
  void displaySepiaStatus() throws IOException;

  /**
   * Displays a message indicating that the image has been dithered.
   *
   * @throws IOException if an I/O error occurs while displaying the message.
   */
  void displayDitherStatus() throws IOException;

  /**
   * Displays a message indicating that the image has been blurred.
   *
   * @throws IOException if an I/O error occurs while displaying the message.
   */
  void displayBlurStatus() throws IOException;

  /**
   * Displays an error message indicating that no file was specified.
   *
   * @throws IOException if an I/O error occurs while displaying the message.
   */
  void displayFileNotSpecified() throws IOException;

  /**
   * Displays a histogram of the image.
   *
   * @throws IOException if an I/O error occurs while displaying the histogram.
   */
  void displayHistogramStatus() throws IOException;
}
