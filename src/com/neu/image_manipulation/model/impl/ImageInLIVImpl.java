package com.neu.image_manipulation.model.impl;

import com.neu.image_manipulation.model.ImageInLIV;
import com.neu.image_manipulation.model.entity.Image;
import com.neu.image_manipulation.model.entity.Pixel;

public class ImageInLIVImpl extends ImageManipulationImpl implements ImageInLIV {


  @Override
  public Image createValueComponentOfImage(Image image) {
    Image valueImage = new Image(image.getHeight(), image.getWidth(), image.getMaxValue());
    Pixel[][] pixel = image.getPixel();
    Pixel[][] valuePixel = new Pixel[valueImage.getHeight()][valueImage.getWidth()];
    for(int i =0;i< valueImage.getHeight();i++){
      for(int j=0;j< valueImage.getWidth();j++){
        int maxVal =Math.max(pixel[i][j].getRed(),Math.max(pixel[i][j].getGreen(),pixel[i][j].getBlue()));
        valuePixel[i][j]= new Pixel(maxVal,maxVal,maxVal);
      }
    }
    valueImage.setPixel(valuePixel);
    return valueImage;
  }

  @Override
  public Image createIntensityComponentOfImage(Image image) {
    Image intensityImage = new Image(image.getHeight(), image.getWidth(), image.getMaxValue());
    Pixel[][] pixel = image.getPixel();
    Pixel[][] avgValuePixel = new Pixel[intensityImage.getHeight()][intensityImage.getWidth()];
    for(int i =0;i< intensityImage.getHeight();i++){
      for(int j=0;j< intensityImage.getWidth();j++){
        int maxVal =(pixel[i][j].getRed()+pixel[i][j].getGreen()+pixel[i][j].getBlue())/3;
        avgValuePixel[i][j]= new Pixel(maxVal,maxVal,maxVal);
      }
    }
    intensityImage.setPixel(avgValuePixel);
    return intensityImage;
  }

  @Override
  public Image createLumaComponentOfImage(Image image) {
    Image lumaImage = new Image(image.getHeight(), image.getWidth(), image.getMaxValue());
    Pixel[][] pixel = image.getPixel();
    Pixel[][] valuePixel = new Pixel[lumaImage.getHeight()][lumaImage.getWidth()];
    for(int i =0;i< lumaImage.getHeight();i++){
      for(int j=0;j< lumaImage.getWidth();j++){
        int lumaVal = (int) (0.2126*(pixel[i][j].getRed()) + 0.7152*pixel[i][j].getGreen()+
                    0.0722*pixel[i][j].getBlue());
        valuePixel[i][j]= new Pixel(lumaVal,lumaVal,lumaVal);
      }
    }
    lumaImage.setPixel(valuePixel);
    return lumaImage;
  }


  @Override
  public Image[] splitIntoLIV(Image image) {
    Image[] images = new Image[3];
    images[0] = createLumaComponentOfImage(image);
    images[1] = createIntensityComponentOfImage(image);
    images[2] = createValueComponentOfImage(image);
    return images;
  }
}
