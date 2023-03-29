package com.neu.imagemanipulation.model.impl;
import com.neu.imagemanipulation.model.entity.ImageInterface;

public interface AdvancedImageManipulationInterface extends ImageManipulationInterface {
  ImageInterface blur(ImageInterface image);

  ImageInterface sharpen(ImageInterface image);

  ImageInterface greyscale(ImageInterface image);

  ImageInterface sepiaTone(ImageInterface image);

  ImageInterface dither(ImageInterface image);
}
