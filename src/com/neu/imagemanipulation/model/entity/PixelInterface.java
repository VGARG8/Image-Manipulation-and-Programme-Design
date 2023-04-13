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

  /**
   * Sets the Red component of the pixel.
   *
   * @param red an integer value for the Red component, between 0 and 255.
   */
  void setRed(int red);

  /**
   * Sets the Green component of the pixel.
   *
   * @param green an integer value for the Green component, between 0 and 255.
   */
  void setGreen(int green);

  /**
   * Sets the Blue component of the pixel.
   *
   * @param blue an integer value for the Blue component, between 0 and 255.
   */
  void setBlue(int blue);
}
