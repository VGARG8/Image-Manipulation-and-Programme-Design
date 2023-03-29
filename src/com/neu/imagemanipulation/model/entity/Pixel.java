package com.neu.imagemanipulation.model.entity;

/**
 * This Class represents Pixel.
 */
public class Pixel implements PixelInterface {

  private int red;
  private int green;
  private int blue;

  /**
   * Constructor method of the Pixel Class.
   *
   * @param red   sets the value of red.
   * @param green sets the value of green.
   * @param blue  sets the value of blue.
   */
  public Pixel(int red, int green, int blue) {
    setRed(red);
    setGreen(green);
    setBlue(blue);

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

  @Override
  public void setRed(int red) {
    this.red = Math.min(Math.max(red, 0), 255);
  }

  @Override
  public void setGreen(int green) {
    this.green = Math.min(Math.max(green, 0), 255);
  }

  @Override
  public void setBlue(int blue) {
    this.blue = Math.min(Math.max(blue, 0), 255);
  }


}
