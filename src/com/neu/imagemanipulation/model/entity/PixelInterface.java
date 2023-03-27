package com.neu.imagemanipulation.model.entity;

/**
 * This interface represents the pixel of an image with its Red, Green and Blue value.
 */
public interface PixelInterface {
  /**
   * The method is used to get the red part of pixel.
   *
   * @return integer value of red part between 0-255
   */
  int getRed();

  /**
   * The method is used to get the blue part of pixel.
   *
   * @return integer value of blue part between 0-255
   */
  int getBlue();

  /**
   * The method is used to get the green part of pixel.
   *
   * @return integer value of green part between 0-255
   */
  int getGreen();

  void setRed(int red);

  void setGreen(int green);

  void setBlue(int blue);
}
