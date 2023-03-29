package com.neu.imagemanipulation.model.entity;

/**
 * This class represents and image in ppm format with height wight max pixel value and array of
 * pixels.
 */

public interface ImageInterface {

  /**
   * The method is used to get the height of an image.
   *
   * @return the height of an image.
   */
  int getHeight();

  /**
   * The method is used to get the width of an image.
   *
   * @return the width of an image.
   */
  int getWidth();

  /**
   * The method is used to get the maxValue of an image.
   *
   * @return the maxVALUE of an image.
   */
  int getMaxValue();

  /**
   * The method is used to get the ARRAY of pixels of an image.
   *
   * @return the array of pixels of an image.
   */
  PixelInterface[][] getPixel();

  /**
   * The method is used to set the array of pixels for an image.
   *
   * @param pixel the array of pixels.
   */

  void setPixel(PixelInterface[][] pixel);
}
