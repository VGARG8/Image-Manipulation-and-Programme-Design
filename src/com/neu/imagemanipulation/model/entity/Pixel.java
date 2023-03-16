package com.neu.imagemanipulation.model.entity;

/**
 * This Class represents Pixel.
 */
public class Pixel implements PixelInterface {
  private final int red;
  private final int green;
  private final int blue;

  /**
   * Constructor method of the Pixel Class.
   *
   * @param red   sets the value of red.
   * @param green sets the value of green.
   * @param blue  sets the value of blue.
   */
  public Pixel(int red, int green, int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;

  }

  @Override
  public int getRed() {
    return red;
  }

  @Override
  public int getBlue() {
    return blue;
  }

  @Override
  public int getGreen() {
    return green;
  }


}
