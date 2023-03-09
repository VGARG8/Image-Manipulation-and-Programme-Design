package com.neu.image_manipulation.model.impl;

import com.neu.image_manipulation.model.ImageInRGB;
import com.neu.image_manipulation.model.entity.Image;
import com.neu.image_manipulation.model.entity.Pixel;

public class ImageInRGBImpl extends ImageManipulationImpl implements ImageInRGB  {


  @Override
  public Image createRedComponentOfImage(Image image) {
    Image redImage = new Image(image.getHeight(), image.getWidth(), image.getMaxValue());
    Pixel[][] pixel = image.getPixel();
    Pixel[][] redPixel = new Pixel[redImage.getHeight()][redImage.getWidth()];
    for(int i =0;i< redImage.getHeight();i++){
      for(int j=0;j<redImage.getWidth();j++){
        int r = pixel[i][j].getRed();
        redPixel[i][j] = new Pixel(r,r,r);
      }
    }
    redImage.setPixel(redPixel);
    return redImage;
  }

  @Override
  public Image createGreenComponentOfImage(Image image) {
    Image greenImage = new Image(image.getHeight(), image.getWidth(), image.getMaxValue());
    Pixel[][] pixel = image.getPixel();
    Pixel[][] greenPixel = new Pixel[greenImage.getHeight()][greenImage.getWidth()];
    for(int i =0;i< greenImage.getHeight();i++){
      for(int j=0;j<greenImage.getWidth();j++){
        int g = pixel[i][j].getGreen();
        greenPixel[i][j] = new Pixel(g,g,g);
      }
    }
    greenImage.setPixel(greenPixel);
    return greenImage;
  }

  @Override
  public Image createBlueComponentOfImage(Image image) {
    Image blueImage = new Image(image.getHeight(), image.getWidth(), image.getMaxValue());
    Pixel[][] pixel = image.getPixel();
    Pixel[][] bluePixel = new Pixel[blueImage.getHeight()][blueImage.getWidth()];
    for(int i =0;i< blueImage.getHeight();i++){
      for(int j=0;j<blueImage.getWidth();j++){
        int b = pixel[i][j].getBlue();
        bluePixel[i][j] = new Pixel(b,b,b);
      }
    }
    blueImage.setPixel(bluePixel);
    return blueImage;
  }

  @Override
  public Image combineRGBImages(Image[] images) {

    Image red = images[0];
    Image green = images[1];
    Image blue = images[2];
    Image combined = new Image(red.getHeight(), red.getWidth(), red.getMaxValue());
    Pixel[][] pixels = new Pixel[combined.getHeight()][combined.getWidth()];
    for(int i=0;i<images[0].getHeight();i++){
      for(int j=0;j<images[0].getWidth();j++){
        pixels[i][j] = new Pixel(red.getPixel()[i][j].getRed(),
            green.getPixel()[i][j].getGreen(),blue.getPixel()[i][j].getBlue());
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
}
