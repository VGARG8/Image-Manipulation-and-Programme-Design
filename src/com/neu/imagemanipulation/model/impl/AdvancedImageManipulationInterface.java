package com.neu.imagemanipulation.model.impl;
import com.neu.imagemanipulation.model.entity.ImageInterface;

/**
 * The interface provides the additional functionality of image manipulation like Blur,
 * sharpe, greyscale, sepiatone and dither.
 */
public interface AdvancedImageManipulationInterface extends ImageManipulationInterface {

  /**
   * The  method applies the gaussian blur filter on an image object.
   * @param image image object to be applied filter on.
   * @return image object after being blurred by gaussian filter.
   */
  ImageInterface blur(ImageInterface image);

  /**
   * The method applies the sharpen filter on an image object.
   * @param image image to be applied on.
   * @return image after sharpen filter.
   */
  ImageInterface sharpen(ImageInterface image);

  /**
   * The method multiple the image pixels tp generate a grey scale image.
   * @param image the filter to be applied on.
   * @return image after greyscale filter.
   */
  ImageInterface greyscale(ImageInterface image);

  /**
   * The method applies the sepia tone filter on an image object.
   * @param image the filter to be applied on.
   * @return image after sepia tone filter.
   */
  ImageInterface sepiaTone(ImageInterface image);

  /**
   * This method generates the image after applying dither filter.
   * @param image the filter to be applied on.
   * @return image after dither filter.
   */
  ImageInterface dither(ImageInterface image);
}
