package com.neu.imagemanipulation.model.impl;

import com.neu.imagemanipulation.model.entity.ImageInterface;

import java.awt.image.BufferedImage;
import java.util.Set;

/**
 * The GuiModelInterface extends the AdvancedImageManipulationInterface and provides additional
 * methods specific to the GUI for managing and manipulating images, including generating histogram
 * images and storing buffered images.
 */
public interface GuiModelInteface extends AdvancedImageManipulationInterface {

  /**
   * Generates a histogram image for the given ImageInterface object.
   *
   * @param images the ImageInterface object to create a histogram for.
   * @param type   the type of histogram to generate (e.g., "Red", "Green", "Blue", or "Intensity").
   * @return a BufferedImage representation of the histogram.
   */
  BufferedImage getHistogramImage(ImageInterface images, String type);

  /**
   * Stores a BufferedImage with an associated identifier.
   *
   * @param arg          a string identifier for the stored BufferedImage.
   * @param resultImage  the BufferedImage to store.
   */
  void storeBufferImages(String arg, BufferedImage resultImage);

  /**
   * Retrieves the names of all stored images.
   *
   * @return a Set of Strings containing the names of stored images.
   */
  Set<String> getStoredImageNames();

  /**
   * Retrieves the BufferedImage associated with the stored image name.
   *
   * @return the stored BufferedImage.
   */
  BufferedImage getBufferImages();
}
