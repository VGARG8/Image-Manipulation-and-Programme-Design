package com.neu.image_manipulation.model;

import com.neu.image_manipulation.model.entity.Image;

public interface ImageBrightDark extends ImageManipulation {
  Image brightenImage(Image image, int value);

  Image darkenImage(Image image, int value);
}
