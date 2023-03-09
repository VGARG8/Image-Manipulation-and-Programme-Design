package com.neu.image_manipulation.model;

import com.neu.image_manipulation.model.entity.Image;

public interface ImageInRGB extends ImageManipulation{
  Image createRedComponentOfImage(Image image);
  Image createGreenComponentOfImage(Image image);
  Image createBlueComponentOfImage(Image image);
  Image combineRGBImages(Image[] images);

  Image[] splitIntoRGBImages(Image image);
}
