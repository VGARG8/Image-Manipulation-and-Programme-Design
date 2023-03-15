package com.neu.image_manipulation.model.impl;

import com.neu.image_manipulation.model.entity.Image;


/**
 * This interface list the image modification methods we are going to provide to the user.
 */
public interface ImageManipulationInterface {

  Image flipImageHorizontally(Image image);

  Image flipImageVertically(Image image);

  Image brightenImage(Image image, int value);

  Image darkenImage(Image image, int value);

  Image createValueComponentOfImage(Image image);

  Image createIntensityComponentOfImage(Image image);

  Image createLumaComponentOfImage(Image image);

  Image[] splitIntoLIV(Image image);

  Image createRedComponentOfImage(Image image);

  Image createGreenComponentOfImage(Image image);

  Image createBlueComponentOfImage(Image image);

  Image combineRGBImages(Image[] images);

  Image[] splitIntoRGBImages(Image image);

  void storeImages(String name, Image image);

  Image getImages(String name);


  Boolean containsImages(String name);
}
