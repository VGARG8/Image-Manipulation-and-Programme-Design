package com.neu.imagemanipulation.model.impl;

import com.neu.imagemanipulation.model.entity.Image;
import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.entity.Pixel;
import com.neu.imagemanipulation.model.entity.PixelInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * This Class implements the methods of ImageManipulationInterface Interface for the image
 * manipulation program.
 */

public class ImageManipulationModel implements ImageManipulationInterface {

  private final Map<String, ImageInterface> imagesMap = new HashMap<>();


  @Override
  public ImageInterface brightenImage(ImageInterface image, int value) {
    Image brightenImage = new Image(image.getHeight(), image.getWidth(), image.getMaxValue());
    PixelInterface[][] brightPixel = new Pixel[image.getHeight()][image.getWidth()];
    PixelInterface[][] pixel = image.getPixel();
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
  public ImageInterface darkenImage(ImageInterface image, int value) {
    ImageInterface darkenImage = new Image(image.getHeight(), image.getWidth(), image.getMaxValue());
    PixelInterface[][] darkPixel = new Pixel[image.getHeight()][image.getWidth()];
    PixelInterface[][] pixel = image.getPixel();
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
  public ImageInterface flipImageHorizontally(ImageInterface image) {
    ImageInterface horizontalFlip = new Image(image.getHeight(), image.getWidth(), image.getMaxValue());
    PixelInterface[][] pixel = image.getPixel();
    PixelInterface[][] flippedPixel = new Pixel[horizontalFlip.getHeight()][horizontalFlip.getWidth()];

    int col = image.getWidth() - 1;
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        flippedPixel[i][j] = new Pixel(pixel[i][col - j].getRed(),
                pixel[i][col - j].getGreen(), pixel[i][col - j].getBlue());
      }
    }
    horizontalFlip.setPixel(flippedPixel);
    return horizontalFlip;
  }

  @Override
  public ImageInterface flipImageVertically(ImageInterface image) {
    ImageInterface verticalFlip = new Image(image.getHeight(), image.getWidth(), image.getMaxValue());
    PixelInterface[][] pixel = image.getPixel();
    PixelInterface[][] flippedPixel = new Pixel[verticalFlip.getHeight()][verticalFlip.getWidth()];
    int row = image.getHeight() - 1;
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        flippedPixel[i][j] = new Pixel(pixel[row - i][j].getRed(),
                pixel[row - i][j].getGreen(), pixel[row - i][j].getBlue());
      }
    }
    verticalFlip.setPixel(flippedPixel);
    return verticalFlip;
  }

  @Override
  public ImageInterface createValueComponentOfImage(ImageInterface image) {
    ImageInterface valueImage = new Image(image.getHeight(), image.getWidth(), image.getMaxValue());
    PixelInterface[][] pixel = image.getPixel();
    PixelInterface[][] valuePixel = new Pixel[valueImage.getHeight()][valueImage.getWidth()];
    for (int i = 0; i < valueImage.getHeight(); i++) {
      for (int j = 0; j < valueImage.getWidth(); j++) {
        int maxVal = Math.max(pixel[i][j].getRed(), Math.max(pixel[i][j].getGreen(),
                pixel[i][j].getBlue()));
        valuePixel[i][j] = new Pixel(maxVal, maxVal, maxVal);
      }
    }
    valueImage.setPixel(valuePixel);
    return valueImage;
  }

  @Override
  public ImageInterface createIntensityComponentOfImage(ImageInterface image) {
    ImageInterface intensityImage = new Image(image.getHeight(), image.getWidth(), image.getMaxValue());
    PixelInterface[][] pixel = image.getPixel();
    PixelInterface[][] avgValuePixel = new Pixel[intensityImage.getHeight()][intensityImage.getWidth()];
    for (int i = 0; i < intensityImage.getHeight(); i++) {
      for (int j = 0; j < intensityImage.getWidth(); j++) {
        int maxVal = (pixel[i][j].getRed() + pixel[i][j].getGreen() + pixel[i][j].getBlue()) / 3;
        avgValuePixel[i][j] = new Pixel(maxVal, maxVal, maxVal);
      }
    }
    intensityImage.setPixel(avgValuePixel);
    return intensityImage;
  }

  @Override
  public ImageInterface createLumaComponentOfImage(ImageInterface image) {
    ImageInterface lumaImage = new Image(image.getHeight(), image.getWidth(), image.getMaxValue());
    PixelInterface[][] pixel = image.getPixel();
    PixelInterface[][] valuePixel = new Pixel[lumaImage.getHeight()][lumaImage.getWidth()];
    for (int i = 0; i < lumaImage.getHeight(); i++) {
      for (int j = 0; j < lumaImage.getWidth(); j++) {
        int lumaVal = (int) (0.2126 * (pixel[i][j].getRed()) + 0.7152 * pixel[i][j].getGreen() +
                0.0722 * pixel[i][j].getBlue());
        valuePixel[i][j] = new Pixel(lumaVal, lumaVal, lumaVal);
      }
    }
    lumaImage.setPixel(valuePixel);
    return lumaImage;
  }


  @Override
  public ImageInterface[] splitIntoLIV(ImageInterface image) {
    ImageInterface[] images = new Image[3];
    images[0] = createLumaComponentOfImage(image);
    images[1] = createIntensityComponentOfImage(image);
    images[2] = createValueComponentOfImage(image);
    return images;
  }

  @Override
  public ImageInterface createRedComponentOfImage(ImageInterface image) {
    ImageInterface redImage = new Image(image.getHeight(), image.getWidth(), image.getMaxValue());
    PixelInterface[][] pixel = image.getPixel();
    PixelInterface[][] redPixel = new Pixel[redImage.getHeight()][redImage.getWidth()];
    for (int i = 0; i < redImage.getHeight(); i++) {
      for (int j = 0; j < redImage.getWidth(); j++) {
        int r = pixel[i][j].getRed();
        redPixel[i][j] = new Pixel(r, r, r);
      }
    }
    redImage.setPixel(redPixel);
    return redImage;
  }

  @Override
  public ImageInterface createGreenComponentOfImage(ImageInterface image) {
    ImageInterface greenImage = new Image(image.getHeight(), image.getWidth(), image.getMaxValue());
    PixelInterface[][] pixel = image.getPixel();
    PixelInterface[][] greenPixel = new Pixel[greenImage.getHeight()][greenImage.getWidth()];
    for (int i = 0; i < greenImage.getHeight(); i++) {
      for (int j = 0; j < greenImage.getWidth(); j++) {
        int g = pixel[i][j].getGreen();
        greenPixel[i][j] = new Pixel(g, g, g);
      }
    }
    greenImage.setPixel(greenPixel);
    return greenImage;
  }

  @Override
  public ImageInterface createBlueComponentOfImage(ImageInterface image) {
    ImageInterface blueImage = new Image(image.getHeight(), image.getWidth(), image.getMaxValue());
    PixelInterface[][] pixel = image.getPixel();
    PixelInterface[][] bluePixel = new Pixel[blueImage.getHeight()][blueImage.getWidth()];
    for (int i = 0; i < blueImage.getHeight(); i++) {
      for (int j = 0; j < blueImage.getWidth(); j++) {
        int b = pixel[i][j].getBlue();
        bluePixel[i][j] = new Pixel(b, b, b);
      }
    }
    blueImage.setPixel(bluePixel);
    return blueImage;
  }

  @Override
  public ImageInterface combineRGBImages(ImageInterface[] images) {

    ImageInterface red = images[0];
    ImageInterface green = images[1];
    ImageInterface blue = images[2];
    ImageInterface combined = new Image(red.getHeight(), red.getWidth(), red.getMaxValue());
    PixelInterface[][] pixels = new Pixel[combined.getHeight()][combined.getWidth()];
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
  public ImageInterface[] splitIntoRGBImages(ImageInterface image) {
    ImageInterface[] images = new Image[3];
    images[0] = createRedComponentOfImage(image);
    images[1] = createGreenComponentOfImage(image);
    images[2] = createBlueComponentOfImage(image);
    return images;
  }

  @Override
  public void storeImages(String name, ImageInterface img) {
    imagesMap.put(name, img);
    System.out.println(imagesMap);
  }

  @Override
  public ImageInterface getImages(String name) {
    return imagesMap.get(name);
  }

  @Override
  public Boolean containsImages(String name) {
    return imagesMap.containsKey(name);
  }

}
