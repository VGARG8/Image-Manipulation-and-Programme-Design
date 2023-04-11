package com.neu.imagemanipulation.view;

import java.awt.image.BufferedImage;
import com.neu.imagemanipulation.model.entity.Image;
import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.entity.PixelInterface;

public class ImageConverter {
  public static BufferedImage convert(ImageInterface image) {
    BufferedImage bufferedImage = new BufferedImage(
            image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
    PixelInterface[][] pixels = image.getPixel();
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        int red = pixels[i][j].getRed();
        int green = pixels[i][j].getGreen();
        int blue = pixels[i][j].getBlue();
        int rgb = (red << 16) | (green << 8) | blue;
        bufferedImage.setRGB(j, i, rgb);
      }
    }
    return bufferedImage;
  }
}