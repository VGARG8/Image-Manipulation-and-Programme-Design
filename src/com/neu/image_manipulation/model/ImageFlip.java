package com.neu.image_manipulation.model;

import com.neu.image_manipulation.model.entity.Image;

public interface ImageFlip extends ImageManipulation {
  Image flipImageHorizontally(Image image);
  Image flipImageVertically(Image image);
}
