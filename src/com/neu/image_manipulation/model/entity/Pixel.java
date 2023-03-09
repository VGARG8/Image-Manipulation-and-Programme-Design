package com.neu.image_manipulation.model.entity;

public class Pixel {
  private int red;
  private int green;
  private int blue;


  public Pixel(int red, int green, int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;

  }

  public int getRed() {
    return red;
  }

  public int getBlue() {
    return blue;
  }

  public int getGreen() {
    return green;
  }
}
