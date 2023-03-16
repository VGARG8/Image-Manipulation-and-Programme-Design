package com.neu.image_manipulation.model.entity;

public class Pixel implements PixelInterface {
  private final int red;
  private final int green;
  private final int blue;


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
