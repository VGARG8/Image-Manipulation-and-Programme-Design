package com.neu.imagemanipulation.model.impl;

import com.neu.imagemanipulation.model.entity.Image;

public interface AdvancedImageManipulationInterface extends ImageManipulationInterface{
  Image blur(Image image);
  Image sharpen(Image image);
  Image greyscale(Image image);
  Image sepiaTone(Image image);
}
