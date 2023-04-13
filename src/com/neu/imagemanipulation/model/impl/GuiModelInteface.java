package com.neu.imagemanipulation.model.impl;

import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;

import java.awt.image.BufferedImage;
import java.util.Set;

public interface GuiModelInteface extends AdvancedImageManipulationInterface {
  BufferedImage getHistogramImage(ImageInterface images, String type);

  void storeBufferImages(String arg, BufferedImage resultImage);

  Set<String> getStoredImageNames();
}
