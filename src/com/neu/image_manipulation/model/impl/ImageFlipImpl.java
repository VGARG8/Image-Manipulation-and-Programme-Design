package com.neu.image_manipulation.model.impl;

import com.neu.image_manipulation.model.ImageFlip;
import com.neu.image_manipulation.model.entity.Image;
import com.neu.image_manipulation.model.entity.Pixel;

public class ImageFlipImpl extends ImageManipulationImpl implements ImageFlip {

  @Override
  public Image flipImageHorizontally(Image image) {
    Image horizontalFlip = new Image(image.getHeight(), image.getWidth(), image.getMaxValue());
    Pixel[][] pixel = image.getPixel();
    Pixel[][] flippedPixel = new Pixel[horizontalFlip.getHeight()][horizontalFlip.getWidth()];
    int row = image.getHeight()-1;
    for(int i=0;i< image.getHeight();i++){
      for(int j=0;j< image.getWidth();j++){
        flippedPixel[i][j] = new Pixel(pixel[row-i][j].getRed(),
            pixel[row-i][j].getGreen(),pixel[row-i][j].getBlue());
      }
    }
    horizontalFlip.setPixel(flippedPixel);
    return horizontalFlip;
  }

  @Override
  public Image flipImageVertically(Image image) {
    Image verticalFlip = new Image(image.getHeight(), image.getWidth(), image.getMaxValue());
    Pixel[][] pixel = image.getPixel();
    Pixel[][] flippedPixel = new Pixel[verticalFlip.getHeight()][verticalFlip.getWidth()];
    int col = image.getWidth()-1;
    for(int i=0;i< image.getHeight();i++){
      for(int j=0;j< image.getWidth();j++){
        flippedPixel[i][j] = new Pixel(pixel[i][col-j].getRed(),
            pixel[i][col-j].getGreen(),pixel[i][col-j].getBlue());
      }
    }
    verticalFlip.setPixel(flippedPixel);
    return verticalFlip;
  }
}
