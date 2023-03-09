package com.neu.image_manipulation.model.impl;

import com.neu.image_manipulation.model.ImageBrightDark;
import com.neu.image_manipulation.model.entity.Image;
import com.neu.image_manipulation.model.entity.Pixel;
import java.awt.image.RenderedImage;
import javax.imageio.ImageIO;

public class ImageBrightDarkImpl extends ImageManipulationImpl implements ImageBrightDark {

  @Override
  public Image brightenImage(Image image, int value) {
    Image brightenImage = new Image(image.getHeight(),image.getWidth(),image.getMaxValue());
    Pixel[][] brightPixel = new Pixel[image.getHeight()][image.getWidth()];
    Pixel[][] pixel = image.getPixel();
    for(int i=0;i<brightPixel.length;i++){
      for(int j=0;j<brightPixel[i].length;j++){
        int red = Math.min(255,pixel[i][j].getRed()+value);
        int blue = Math.min(255,pixel[i][j].getBlue()+value);
        int green = Math.min(255,pixel[i][j].getGreen()+value);
        brightPixel[i][j]= new Pixel(red,green,blue);
      }
    }

    //ImageIO.write(im)
    brightenImage.setPixel(brightPixel);
    return brightenImage;
  }

  @Override
  public Image darkenImage(Image image, int value) {
    Image darkenImage = new Image(image.getHeight(),image.getWidth(),image.getMaxValue());
    Pixel[][] darkPixel = new Pixel[image.getHeight()][image.getWidth()];
    Pixel[][] pixel = image.getPixel();
    for(int i=0;i<darkPixel.length;i++){
      for(int j=0;j<darkPixel[i].length;j++){
        int red = Math.max(0,pixel[i][j].getRed()-value);
        int blue = Math.max(0,pixel[i][j].getBlue()-value);
        int green = Math.max(0,pixel[i][j].getGreen()-value);
        darkPixel[i][j]= new Pixel(red,green,blue);
      }
    }
    darkenImage.setPixel(darkPixel);
    return darkenImage;
  }
}
