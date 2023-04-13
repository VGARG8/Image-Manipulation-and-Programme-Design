package com.neu.imagemanipulation.view;

import java.io.IOException;

/**
 * Interface for an advanced view that supports displaying image manipulation statuses and error messages.
 */
public interface AdvancedViewInterface extends ViewInterface {

  /**
   * Displays an error message for an invalid file format.
   */
  void displayInvalidFileFormat() throws IOException;

  /**
   * Displays a status message for applying grayscale to an image.
   */
  void displayGreyScaleStatus() throws IOException;

  /**
   * Displays a status message for applying sharpen to an image.
   */
  void displaySharpenStatus() throws IOException;

  /**
   * Displays a status message for applying sepia tone to an image.
   */
  void displaySepiaStatus() throws IOException;

  /**
   * Displays a status message for applying dithering to an image.
   */
  void displayDitherStatus() throws IOException;

  /**
   * Displays a status message for applying blur to an image.
   */
  void displayBlurStatus() throws IOException;

  /**
   * Displays an error message for when a file is not specified.
   */
  void displayFileNotSpecified() throws IOException;
}
