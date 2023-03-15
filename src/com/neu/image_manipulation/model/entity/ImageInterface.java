package com.neu.image_manipulation.model.entity;

public interface ImageInterface {
  int getHeight();

  int getWidth();

  int getMaxValue();

  Pixel[][] getPixel();

  void setHeight(int height);

  void setWidth(int width);

  void setMaxValue(int maxValue);

  void setPixel(Pixel[][] pixel);
}
