package com.neu.imagemanipulation.model.impl;

import com.neu.imagemanipulation.model.entity.ImageInterface;


/**
 * This interface list the image modification methods we are going to provide to the user.
 */
public interface ImageManipulationInterface {

  /**
   * This method is used to flip the passed image Horizontally.
   *
   * @param image image to modify
   * @return horizontal version of passed image
   */
  ImageInterface flipImageHorizontally(ImageInterface image);

  /**
   * This method is used to flip the passed image vertically.
   *
   * @param image image to modify
   * @return vertical version of passed image
   */
  ImageInterface flipImageVertically(ImageInterface image);

  /**
   * This method is used to brighten the passed image.
   *
   * @param image image to brighten
   * @param value by what value image pixel to be brightened
   * @return the brightened version of image
   */
  ImageInterface brightenImage(ImageInterface image, int value);

  /**
   * This method darken the image by given value.
   *
   * @param image image to be modified.
   * @param value by what value image pixel to be darkened.
   * @return the darkened version of image.
   */
  ImageInterface darkenImage(ImageInterface image, int value);

  /**
   * The method use the value component of pixel to modify the image.
   *
   * @param image image to be modified
   * @return the image using value component as its new pixel.
   */
  ImageInterface createValueComponentOfImage(ImageInterface image);

  /**
   * The method use the Intensity component of pixel to modify the image.
   *
   * @param image image to be modified
   * @return the image using Intensity component as its new pixel.
   */
  ImageInterface createIntensityComponentOfImage(ImageInterface image);

  /**
   * The method use the Luna component of pixel to modify the image.
   *
   * @param image image to be modified
   * @return the image using Luna component as its new pixel.
   */
  ImageInterface createLumaComponentOfImage(ImageInterface image);

  /**
   * This method split the passed image into three images of value,luna and intensity greyscale.
   *
   * @param image image to be split
   * @return array of images in R,G,B greyscale.
   */
  ImageInterface[] splitIntoLIV(ImageInterface image);

  /**
   * The method use the red component of pixel to generate the red-greyscale image.
   *
   * @param image image to be modified
   * @return the image using red component as its new pixel.
   */
  ImageInterface createRedComponentOfImage(ImageInterface image);

  /**
   * The method use the Green component of pixel to generate the Green-greyscale image.
   *
   * @param image image to be modified
   * @return the image using Green component as its new pixel.
   */
  ImageInterface createGreenComponentOfImage(ImageInterface image);

  /**
   * The method use the Blue component of pixel to generate the Blue-greyscale image.
   *
   * @param image image to be modified
   * @return the image using Blue component as its new pixel.
   */
  ImageInterface createBlueComponentOfImage(ImageInterface image);

  /**
   * The method is used to combine the image in the array using r,g,b value from them respectively.
   *
   * @param images the array of image having red, blue and green greyscale images
   * @return image combining all the images in the array.
   */
  ImageInterface combineRGBImages(ImageInterface[] images);

  /**
   * This method split the passed image into three images of Red, Green and Blue greyscale.
   *
   * @param image image to be split
   * @return array of images in R,G,B greyscale.
   */
  ImageInterface[] splitIntoRGBImages(ImageInterface image);

  /**
   * This method is used to store image, and it's name within the program.
   *
   * @param name  name of the image with which Image object is stored in the program.
   * @param image Image object which is stored.
   */

  void storeImages(String name, ImageInterface image);

  /**
   * This method is used to retrieve the image by its name from the program.
   *
   * @param name name of the image with which Image object is stored in the program.
   * @return Image object which is stored.
   */
  ImageInterface getImages(String name);

  /**
   * This method is used to check if the image exists in the program's storage.
   *
   * @param name name of the image which has to be checked.
   * @return True if exists else false.
   */
  Boolean containsImages(String name);
}
