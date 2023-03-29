package com.neu.imagemanipulation.model.impl;

import com.neu.imagemanipulation.model.entity.ImageInterface;

/**
 * AdvancedImageManipulationInterface extends ImageManipulationInterface and provides additional
 * image manipulation functionality, such as blurring, sharpening, converting to greyscale,
 * applying sepia tone, and dithering.
 */

public interface AdvancedImageManipulationInterface extends ImageManipulationInterface {
  /**
   * Blurs the input image.
   *
   * @param image the ImageInterface object to be blurred
   * @return the blurred ImageInterface object
   */
  ImageInterface blur(ImageInterface image);

  /**
   * Sharpens the input image.
   *
   * @param image the ImageInterface object to be sharpened
   * @return the sharpened ImageInterface object
   */
  ImageInterface sharpen(ImageInterface image);

  /**
   * Converts the input image to greyscale.
   *
   * @param image the ImageInterface object to be converted to greyscale
   * @return the greyscale ImageInterface object
   */
  ImageInterface greyscale(ImageInterface image);

  /**
   * Applies a sepia tone to the input image.
   *
   * @param image the ImageInterface object to be converted to sepia tone
   * @return the sepia-toned ImageInterface object
   */
  ImageInterface sepiaTone(ImageInterface image);

  /**
   * Applies dithering to the input image.
   *
   * @param image the ImageInterface object to be dithered
   * @return the dithered ImageInterface object
   */
  ImageInterface dither(ImageInterface image);
}
