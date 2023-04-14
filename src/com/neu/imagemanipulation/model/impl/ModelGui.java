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
 * The ModelGui class extends AdvancedImageManipulationModel and implements ImageAnalysisInterface
 * and GuiModelInterface. This class provides additional methods for managing and manipulating
 * images in a GUI environment, including generating histogram images and storing buffered images.
 */
public class ModelGui extends AdvancedImageManipulationModel implements ImageAnalysisInterface,
        GuiModelInteface {
  final Map<String, BufferedImage> bufferedImagesMap = new HashMap<>();

  @Override
  public Set<String> getStoredImageNames() {
    Set<String> keys = new LinkedHashSet<>(imagesMap.keySet());
    return keys;
  }

  private int[][] getHistogramArrays(ImageInterface image) {

    // Convert the image to grayscale
    BufferedImage grayImage = new BufferedImage(image.getWidth(), image.getHeight(),
            BufferedImage.TYPE_BYTE_GRAY);


    // Compute the histograms
    int[][] histograms = new int[4][256];
    for (int x = 0; x < image.getPixel().length; x++) {
      for (int y = 0; y < image.getPixel()[x].length; y++) {
        Color color = new Color(image.getPixel()[x][y].getRed(), image.getPixel()[x][y].getGreen(),
                image.getPixel()[x][y].getBlue());
        histograms[0][color.getRed()]++;
        histograms[1][color.getGreen()]++;
        histograms[2][color.getBlue()]++;
        int gray = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
        histograms[3][gray]++;
      }
    }

    return histograms;
  }


  @Override
  public BufferedImage getHistogramImage(ImageInterface image, String type) {
    int[][] histograms = getHistogramArrays(image);
    int intensityMax = 0;
    for (int i = 0; i < histograms[3].length; i++) {
      if (histograms[3][i] > intensityMax) {
        intensityMax = histograms[3][i];
      }
    }
    int redMax = 0;
    for (int i = 0; i < histograms[0].length; i++) {
      if (histograms[0][i] > redMax) {
        redMax = histograms[0][i];
      }
    }
    int greenMax = 0;
    for (int i = 0; i < histograms[1].length; i++) {
      if (histograms[1][i] > greenMax) {
        greenMax = histograms[1][i];
      }
    }
    int blueMax = 0;
    for (int i = 0; i < histograms[2].length; i++) {
      if (histograms[2][i] > blueMax) {
        blueMax = histograms[2][i];
      }
    }

    int max = Math.max(blueMax,
            Math.max(redMax, Math.max(greenMax, intensityMax)));
    BufferedImage imageGraph = new BufferedImage(histograms[0].length*2,max*2,
            BufferedImage.TYPE_INT_RGB);

    Graphics2D g2d = imageGraph.createGraphics();
    g2d.setColor(Color.WHITE);
    g2d.fillRect(0, 0, histograms[0].length*2, max*2);

    g2d.setColor(Color.RED);
    if (type.equals(Constants.LINE)) {
      drawLineGraph(g2d, histograms[0],redMax);
    } else {
      drawBarGraph(g2d, histograms[0]);
    }

    g2d.setColor(Color.GREEN);
    if (type.equals(Constants.LINE)) {
      drawLineGraph(g2d, histograms[1],greenMax);
    } else {
      drawBarGraph(g2d, histograms[1]);
    }

    g2d.setColor(Color.BLUE);
    if (type.equals(Constants.LINE)) {
      drawLineGraph(g2d, histograms[2],blueMax);
    } else {
      drawBarGraph(g2d, histograms[2]);
    }

    g2d.setColor(Color.YELLOW);
    if (type.equals(Constants.LINE)) {
      drawLineGraph(g2d, histograms[3],intensityMax);
    } else {
      drawBarGraph(g2d, histograms[3]);
    }

    return imageGraph;
  }


  private void drawBarGraph(Graphics2D g2d, int[] histogram) {
    for (int i = 0; i < histogram.length; i++) {
      int height = histogram[i];
      g2d.drawLine(i, 0, i, height);
    }
  }

  private void drawLineGraph(Graphics2D g2d, int[] histogram,int max) {
    for (int i = 0; i < histogram.length - 1; i++) {
      int x1 = i;
      int y1 = max - (int) (histogram[i] * ((double) max / histogram.length));
      int x2 = i + 1;
      int y2 = max - (int) (histogram[i + 1] * ((double) max / histogram.length));
      g2d.drawLine(x1, y1, x2, y2);
    }

  }


  @Override
  public void storeBufferImages(String arg, BufferedImage resultImage) {
    bufferedImagesMap.put(arg, resultImage);
  }

  @Override
  public BufferedImage getBufferImages() {
    return bufferedImagesMap.get("bufferedimg");

  }
}
