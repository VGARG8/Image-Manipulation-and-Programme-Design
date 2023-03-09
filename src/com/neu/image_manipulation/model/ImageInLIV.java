package com.neu.image_manipulation.model;

import com.neu.image_manipulation.model.entity.Image;

public interface ImageInLIV extends ImageManipulation{
  Image createValueComponentOfImage(Image image);
  Image createIntensityComponentOfImage(Image image);
  Image createLumaComponentOfImage(Image image);


  Image[] splitIntoLIV(Image image);
}
