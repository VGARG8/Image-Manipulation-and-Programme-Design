package com.neu.imagemanipulation.model.impl;

import com.neu.imagemanipulation.Constants;
import com.neu.imagemanipulation.model.entity.Image;
import com.neu.imagemanipulation.model.entity.Pixel;

import java.util.HashMap;
import java.util.Map;

/**
 * This Class implements the methods of ImageManipulationInterface Interface for the image
 * manipulation program.
 */

public class ImageManipulationModel implements ImageManipulationInterface {

  private final Map<String, Image> imagesMap = new HashMap<>();


  @Override
  public Image brightenImage(Image image, int value) {
    Image brightenImage = new Image(image.getHeight(), image.getWidth(), image.getMaxValue());
    Pixel[][] brightPixel = new Pixel[image.getHeight()][image.getWidth()];
    Pixel[][] pixel = image.getPixel();
    for (int i = 0; i < brightPixel.length; i++) {
      for (int j = 0; j < brightPixel[i].length; j++) {
        int red = Math.min(255, pixel[i][j].getRed() + value);
        int blue = Math.min(255, pixel[i][j].getBlue() + value);
        int green = Math.min(255, pixel[i][j].getGreen() + value);
        brightPixel[i][j] = new Pixel(red, green, blue);
      }
    }
    //ImageIO.write(im)
    brightenImage.setPixel(brightPixel);
    return brightenImage;
  }

  @Override
  public Image darkenImage(Image image, int value) {
    Image darkenImage = new Image(image.getHeight(), image.getWidth(), image.getMaxValue());
    Pixel[][] darkPixel = new Pixel[image.getHeight()][image.getWidth()];
    Pixel[][] pixel = image.getPixel();
    for (int i = 0; i < darkPixel.length; i++) {
      for (int j = 0; j < darkPixel[i].length; j++) {
        int red = Math.max(0, pixel[i][j].getRed() - value);
        int blue = Math.max(0, pixel[i][j].getBlue() - value);
        int green = Math.max(0, pixel[i][j].getGreen() - value);
        darkPixel[i][j] = new Pixel(red, green, blue);
      }
    }
    darkenImage.setPixel(darkPixel);
    return darkenImage;
  }

  @Override
  public Image flipImageHorizontally(Image image) {
    return flipImage(image,Constants.HORIZONTAL);
  }

  @Override
  public Image flipImageVertically(Image image) {
    return flipImage(image,Constants.VERTICAL);
  }

  // instead of checking for every pixel and keeping the common for-loop, I
  // am checking once and having separate loops for better time complexity.
  private Image flipImage(Image image, String type){
    Image flipImage = new Image(image.getHeight(), image.getWidth(), image.getMaxValue());
    Pixel[][] pixel = image.getPixel();
    Pixel[][] flippedPixel = new Pixel[flipImage.getHeight()][flipImage.getWidth()];

    if(type.equals(Constants.VERTICAL)){
      int row = image.getHeight() - 1;
      for (int i = 0; i < image.getHeight(); i++) {
        for (int j = 0; j < image.getWidth(); j++) {
          flippedPixel[i][j] = new Pixel(pixel[row - i][j].getRed(),
              pixel[row - i][j].getGreen(), pixel[row - i][j].getBlue());
        }
      }
    }else{
      int col = image.getWidth() - 1;
      for (int i = 0; i < image.getHeight(); i++) {
        for (int j = 0; j < image.getWidth(); j++) {
          flippedPixel[i][j] = new Pixel(pixel[i][col - j].getRed(),
              pixel[i][col - j].getGreen(), pixel[i][col - j].getBlue());
        }
      }
    }

    flipImage.setPixel(flippedPixel);
    return flipImage;
  }

  @Override
  public Image createValueComponentOfImage(Image image) {
    return commonFilterTypeHelper(image,Constants.VALUE);
  }

  @Override
  public Image createIntensityComponentOfImage(Image image) {
    return commonFilterTypeHelper(image,Constants.INTENSITY);
  }

  @Override
  public Image createLumaComponentOfImage(Image image) {
    return commonFilterTypeHelper(image,Constants.LUMA);
  }


  @Override
  public Image[] splitIntoLIV(Image image) {
    Image[] images = new Image[3];
    images[0] = createLumaComponentOfImage(image);
    images[1] = createIntensityComponentOfImage(image);
    images[2] = createValueComponentOfImage(image);
    return images;
  }

  @Override
  public Image createRedComponentOfImage(Image image) {
    return commonFilterTypeHelper(image,Constants.RED);
  }

  @Override
  public Image createGreenComponentOfImage(Image image) {
    return commonFilterTypeHelper(image,Constants.GREEN);
  }

  @Override
  public Image createBlueComponentOfImage(Image image) {
    return commonFilterTypeHelper(image, Constants.BLUE);
  }

  @Override
  public Image combineRGBImages(Image[] images) {

    Image red = images[0];
    Image green = images[1];
    Image blue = images[2];
    Image combined = new Image(red.getHeight(), red.getWidth(), red.getMaxValue());
    Pixel[][] pixels = new Pixel[combined.getHeight()][combined.getWidth()];
    for (int i = 0; i < images[0].getHeight(); i++) {
      for (int j = 0; j < images[0].getWidth(); j++) {
        pixels[i][j] = new Pixel(red.getPixel()[i][j].getRed(),
                green.getPixel()[i][j].getGreen(), blue.getPixel()[i][j].getBlue());
      }
    }
    combined.setPixel(pixels);
    return combined;
  }

  @Override
  public Image[] splitIntoRGBImages(Image image) {
    Image[] images = new Image[3];
    images[0] = createRedComponentOfImage(image);
    images[1] = createGreenComponentOfImage(image);
    images[2] = createBlueComponentOfImage(image);
    return images;
  }

  @Override
  public void storeImages(String name, Image img) {
    imagesMap.put(name, img);
  }

  @Override
  public Image getImages(String name) {
    return imagesMap.get(name);
  }

  @Override
  public Boolean containsImages(String name) {
    return imagesMap.containsKey(name);
  }


  private Image commonFilterTypeHelper(Image image,String type){
    Image newImage = new Image(image.getHeight(), image.getWidth(), image.getMaxValue());
    Pixel[][] pixel = image.getPixel();
    Pixel[][] newPixel = new Pixel[newImage.getHeight()][newImage.getWidth()];
    for (int i = 0; i < newImage.getHeight(); i++) {
      for (int j = 0; j < newImage.getWidth(); j++) {
        switch (type) {
          case Constants.INTENSITY:
            int intensityVal = (pixel[i][j].getRed() + pixel[i][j].getGreen() + pixel[i][j].getBlue()) / 3;
            newPixel[i][j] = new Pixel(intensityVal, intensityVal, intensityVal);
            break;
          case Constants.VALUE:
            int valueVal = Math.max(pixel[i][j].getRed(), Math.max(pixel[i][j].getGreen(),
                pixel[i][j].getBlue()));
            newPixel[i][j] = new Pixel(valueVal, valueVal, valueVal);
            break;

          case Constants.LUMA:
            int lumaVal = (int) (0.2126 * (pixel[i][j].getRed()) + 0.7152 * pixel[i][j].getGreen() +
                0.0722 * pixel[i][j].getBlue());
            newPixel[i][j] = new Pixel(lumaVal, lumaVal, lumaVal);
            break;
          case Constants.RED:
            int r = pixel[i][j].getRed();
            newPixel[i][j] = new Pixel(r, r, r);
            break;
          case Constants.GREEN:
            int g = pixel[i][j].getGreen();
            newPixel[i][j] = new Pixel(g, g, g);
            break;
          case Constants.BLUE:
            int b = pixel[i][j].getBlue();
            newPixel[i][j] = new Pixel(b, b, b);
            break;
          default:
            break;
        }
      }
    }
    newImage.setPixel(newPixel);
    return newImage;
  }
}
