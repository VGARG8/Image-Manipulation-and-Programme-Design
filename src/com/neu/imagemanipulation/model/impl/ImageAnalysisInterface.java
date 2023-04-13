package com.neu.imagemanipulation.model.impl;

import com.neu.imagemanipulation.model.entity.ImageInterface;

import java.awt.image.BufferedImage;

/**
 * This interface is responsible for generating an histogram after analyzing an image.
 */
public interface ImageAnalysisInterface {


  /**
   * The method is used to generate a bar graph or a line graph using an histogram of an image.
   *
   * @param image the input image.
   * @param type  the type of graph to be generated.
   * @return returns the Buffered Image object of histogram.
   */
  BufferedImage getHistogramImage(ImageInterface image, String type);
}
