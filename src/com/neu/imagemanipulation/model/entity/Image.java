package com.neu.imagemanipulation.model.entity;

import java.util.Arrays;
import java.util.Objects;

/**
 * This class represents the image.
 */
public class Image implements ImageInterface {

  private  int height;
  private int width;
  private int maxValue;
  private PixelInterface[][] pixel;

  /**
   * Constructor method of the class.
   *
   * @param height   sets the height of the image.
   * @param width    sets the width of the image.
   * @param maxValue sets the maxValue of the image.
   */
  public Image(int height, int width, int maxValue) {
    this.height = height;
    this.width = width;
    this.maxValue = maxValue;
    this.pixel = new Pixel[height][width];
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getMaxValue() {
    return maxValue;
  }

  @Override
  public PixelInterface[][] getPixel() {
    return pixel;
  }

  @Override
  public void setPixel(PixelInterface[][] pixel) {
    this.pixel = pixel;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof Image)) {
      return false;
    }
    Image other = (Image) obj;
    if (this.height != other.height || this.width != other.width || this.maxValue !=
        other.maxValue) {
      return false;
    }
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        if (!this.pixel[i][j].equals(other.pixel[i][j])) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(height, width, maxValue);
    result = 31 * result + Arrays.deepHashCode(pixel);
    return result;
  }


}
