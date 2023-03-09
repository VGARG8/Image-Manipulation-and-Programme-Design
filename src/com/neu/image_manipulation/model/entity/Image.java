package com.neu.image_manipulation.model.entity;

/**
 * This class represents the ppm image.
 */
public class Image {
  private int height;
  private int width;
  private int maxValue;
  private Pixel[][] pixel;

  public Image(int height, int width, int maxValue) {
    this.height = height;
    this.width = width;
    this.maxValue = maxValue;
    this.pixel = new Pixel[height][width];
  }

  public int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }

  public int getMaxValue() {
    return maxValue;
  }

  public Pixel[][] getPixel() {
    return pixel;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public void setMaxValue(int maxValue) {
    this.maxValue = maxValue;
  }

  public void setPixel(Pixel[][] pixel) {
    this.pixel = pixel;
  }
}
