package com.neu.imagemanipulation.model.impl;

import com.neu.imagemanipulation.Constants;
import com.neu.imagemanipulation.model.entity.ImageInterface;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * The class is used for
 */
public class ModelGui extends AdvancedImageManipulationModel implements ImageAnalysisInterface,GuiModelInteface{
  final Map<String, BufferedImage> bufferedImagesMap = new HashMap<>();
  @Override
  public Set<String> getStoredImageNames() {
    Set<String> keys = new LinkedHashSet<>(imagesMap.keySet());
    return keys;
  }

  private int[] getHistogramArray(ImageInterface image) {

    // Convert the image to grayscale
    BufferedImage grayImage = new BufferedImage(image.getWidth(), image.getHeight(),
            BufferedImage.TYPE_BYTE_GRAY);
    for (int x = 0; x < image.getWidth(); x++) {
      for (int y = 0; y < image.getHeight(); y++) {
        Color color = new Color(image.getPixel()[x][y].getRed(), image.getPixel()[x][y].getGreen(),
                image.getPixel()[x][y].getBlue());
        int gray = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
        grayImage.setRGB(x, y, new Color(gray, gray, gray).getRGB());
      }
    }

    // Compute the histogram
    int[] histogram = new int[256];
    for (int x = 0; x < grayImage.getWidth(); x++) {
      for (int y = 0; y < grayImage.getHeight(); y++) {
        int gray = new Color(grayImage.getRGB(x, y)).getRed();
        histogram[gray]++;
      }
    }
    return histogram;
  }

  @Override
  public BufferedImage getHistogramImage(ImageInterface image, String type){
    int[] histogram = getHistogramArray(image);
    int max = 0;
    for (int i = 0; i < histogram.length; i++) {
      if (histogram[i] > max) {
        max = histogram[i];
      }
    }

    BufferedImage imageGraph = new BufferedImage(histogram.length, max, BufferedImage.TYPE_INT_RGB);

    Graphics2D g2d = imageGraph.createGraphics();
    g2d.setColor(Color.WHITE);
    g2d.fillRect(0, 0, histogram.length, max);

    g2d.setColor(Color.BLACK);
    if(type.equals(Constants.LINE)){
      drawLineGraph(g2d,histogram,max);
    }else{
      drawBarGraph(g2d,histogram,max);
    }
    return imageGraph;
  }

  private void drawBarGraph(Graphics2D g2d, int[] histogram, int max) {
    for (int i = 0; i < histogram.length; i++) {
      int height = (int) (histogram[i] * ((double) max / histogram.length));
      g2d.drawLine(i, max, i, max - height);
    }
  }

  private void drawLineGraph(Graphics2D g2d, int[] histogram, int max) {
    for (int i = 0; i < histogram.length - 1; i++) {
      int x1 = i;
      int y1 = max - (int) (histogram[i] * ((double) max / histogram.length));
      int x2 = i + 1;
      int y2 = max - (int) (histogram[i+1] * ((double) max / histogram.length));
      g2d.drawLine(x1, y1, x2, y2);
    }
  }


  @Override
  public void storeBufferImages(String arg, BufferedImage resultImage) {
    bufferedImagesMap.put(arg,resultImage);   
  }
}
