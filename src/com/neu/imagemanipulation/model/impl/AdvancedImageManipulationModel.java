package com.neu.imagemanipulation.model.impl;

import com.neu.imagemanipulation.model.entity.Image;
import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.entity.Pixel;
import com.neu.imagemanipulation.model.entity.PixelInterface;

import java.util.HashSet;
import java.util.Set;

public class AdvancedImageManipulationModel extends ImageManipulationModel
        implements AdvancedImageManipulationInterface {
  private static final double[][] blur_filter
          = {{1.0 / 16, 1.0 / 8, 1.0 / 16}, {1.0 / 8, 1.0 / 4, 1.0 / 8}, {1.0 / 16, 1.0 / 8, 1.0 / 16}};
  private static final double[][] sharpen_filter =
          {
                  {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8},
                  {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
                  {-1.0 / 8, 1.0 / 4, 1, 1.0 / 4, -1.0 / 8},
                  {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
                  {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8}
          };
  private static final double[][] greyscaleFilter = {{0.2126, 0.7152, 0.0722},
          {0.2126, 0.7152, 0.0722},
          {0.2126, 0.7152, 0.0722}};
  private static final double[][] sepiaToneFilter = {{0.393, 0.769, 0.189},
          {0.349, 0.686, 0.168},
          {0.272, 0.534, 0.131}};

  @Override
  public ImageInterface blur(ImageInterface image) {
    ImageInterface blurImage = new Image(image.getHeight(), image.getWidth(), image.getMaxValue());

    PixelInterface[][] originalPixel = image.getPixel();

    int row = image.getHeight();
    int col = image.getWidth();
    PixelInterface[][] blurPixel = new Pixel[row][col];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        int newR = (int) applyBlurOnRedChannel(originalPixel, i, j, row, col);
        int newG = (int) applyBlurOnGreenChannel(originalPixel, i, j, row, col);
        int newB = (int) applyBlurOnBlueChannel(originalPixel, i, j, row, col);
        blurPixel[i][j] = new Pixel(newR, newG, newB);
      }
    }
    blurImage.setPixel(blurPixel);

    return blurImage;
  }

  @Override
  public ImageInterface sharpen(ImageInterface image) {
    ImageInterface sharpenImage = new Image(image.getHeight(), image.getWidth(), image.getMaxValue());

    PixelInterface[][] originalPixel = image.getPixel();

    int row = image.getHeight();
    int col = image.getWidth();
    Pixel[][] sharpenPixel = new Pixel[row][col];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        int newR = (int) applySharpenOnRedChannel(originalPixel, i, j, row, col);
        int newG = (int) applySharpenGreenChannel(originalPixel, i, j, row, col);
        int newB = (int) applySharpenOnBlueChannel(originalPixel, i, j, row, col);
        sharpenPixel[i][j] = new Pixel(newR, newG, newB);
      }
    }
    sharpenImage.setPixel(sharpenPixel);
    return sharpenImage;
  }

  @Override
  public ImageInterface greyscale(ImageInterface image) {
    ImageInterface greyScale = new Image(image.getHeight(), image.getWidth(), image.getMaxValue());

    PixelInterface[][] originalPixel = image.getPixel();

    int row = image.getHeight();
    int col = image.getWidth();
    Pixel[][] greyscalePixel = new Pixel[row][col];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        int newR = multiplyMatrix(originalPixel[i][j], greyscaleFilter[0]);
        int newG = multiplyMatrix(originalPixel[i][j], greyscaleFilter[1]);
        int newB = multiplyMatrix(originalPixel[i][j], greyscaleFilter[2]);
        greyscalePixel[i][j] = new Pixel(newR, newG, newB);
      }
    }
    greyScale.setPixel(greyscalePixel);
    return greyScale;

  }

  private int multiplyMatrix(PixelInterface pixel, double[] doubles) {
    return (int) (pixel.getRed() * doubles[0] + pixel.getGreen() * doubles[1] + pixel.getBlue() * doubles[2]);
  }

  @Override
  public ImageInterface sepiaTone(ImageInterface image) {
    ImageInterface sepiaTone = new Image(image.getHeight(), image.getWidth(), image.getMaxValue());

    PixelInterface[][] originalPixel = image.getPixel();

    int row = image.getHeight();
    int col = image.getWidth();
    Pixel[][] sepiaTonePixel = new Pixel[row][col];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        int newR = multiplyMatrix(originalPixel[i][j], sepiaToneFilter[0]);
        int newG = multiplyMatrix(originalPixel[i][j], sepiaToneFilter[1]);
        int newB = multiplyMatrix(originalPixel[i][j], sepiaToneFilter[2]);

        sepiaTonePixel[i][j] = new Pixel(newR, newG, newB);
      }
    }
    sepiaTone.setPixel(sepiaTonePixel);
    return sepiaTone;
  }

  @Override
  public ImageInterface dither(ImageInterface image) {
    ImageInterface greyImage = greyscale(image);
    ImageInterface ditheredImage = new Image(greyImage.getHeight(), greyImage.getWidth(), greyImage.getMaxValue());

    PixelInterface[][] greyPixels = greyImage.getPixel();
    PixelInterface[][] ditheredPixels = new Pixel[greyImage.getHeight()][greyImage.getWidth()];

    for (int r = 0; r < greyImage.getHeight(); r++) {
      for (int c = 0; c < greyImage.getWidth(); c++) {
        int oldColor = greyPixels[r][c].getRed(); // red, green, and blue are the same since it's greyscale
        int newColor = (oldColor < 128) ? 0 : 255;
        int error = oldColor - newColor;
        ditheredPixels[r][c] = new Pixel(newColor, newColor, newColor);

        if (c + 1 < greyImage.getWidth()) {
          PixelInterface pixel = greyPixels[r][c + 1];

          pixel.setRed(clamp(pixel.getRed() + (int) (error * 7.0 / 16)));
          pixel.setGreen(clamp(pixel.getGreen() + (int) (error * 7.0 / 16)));
          pixel.setBlue(clamp(pixel.getBlue() + (int) (error * 7.0 / 16)));
        }
        if (r + 1 < greyImage.getHeight()) {
          if (c - 1 >= 0) {
            PixelInterface pixel = greyPixels[r + 1][c - 1];
            pixel.setRed(clamp(pixel.getRed() + (int) (error * 3.0 / 16)));
            pixel.setGreen(clamp(pixel.getGreen() + (int) (error * 3.0 / 16)));
            pixel.setBlue(clamp(pixel.getBlue() + (int) (error * 3.0 / 16)));
          }
          PixelInterface pixel = greyPixels[r + 1][c];
          pixel.setRed(clamp(pixel.getRed() + (int) (error * 5.0 / 16)));
          pixel.setGreen(clamp(pixel.getGreen() + (int) (error * 5.0 / 16)));
          pixel.setBlue(clamp(pixel.getBlue() + (int) (error * 5.0 / 16)));

          if (c + 1 < greyImage.getWidth()) {
            PixelInterface pixel2 = greyPixels[r + 1][c + 1];
            pixel2.setRed(clamp(pixel2.getRed() + (int) (error * 1.0 / 16)));
            pixel2.setGreen(clamp(pixel2.getGreen() + (int) (error * 1.0 / 16)));
            pixel2.setBlue(clamp(pixel2.getBlue() + (int) (error * 1.0 / 16)));
          }
        }
      }
    }

    ditheredImage.setPixel(ditheredPixels);
    return ditheredImage;
  }

  private double applySharpenOnBlueChannel(PixelInterface[][] originalPixel, int i, int j,
                                           int rowEnd, int colEnd) {
    Set<String> set = new HashSet<>();
    return computeValue(originalPixel, 2,
            2, i, j, rowEnd, colEnd, sharpen_filter.length, sharpen_filter[0].length, set,
            2, sharpen_filter);
  }

  private double applySharpenGreenChannel(PixelInterface[][] originalPixel, int i, int j,
                                          int rowEnd, int colEnd) {
    Set<String> set = new HashSet<>();
    return computeValue(originalPixel, 2,
            2, i, j, rowEnd, colEnd, sharpen_filter.length, sharpen_filter[0].length, set, 1,
            sharpen_filter);
  }

  private double applySharpenOnRedChannel(PixelInterface[][] originalPixel, int i, int j,
                                          int rowEnd, int colEnd) {
    Set<String> set = new HashSet<>();
    return computeValue(originalPixel, 2,
            2, i, j, rowEnd, colEnd, sharpen_filter.length, sharpen_filter[0].length, set, 0,
            sharpen_filter);
  }

  private double applyBlurOnBlueChannel(PixelInterface[][] originalPixel, int i, int j, int rowEnd, int colEnd) {
    Set<String> set = new HashSet<>();
    return computeValue(originalPixel, 1,
            1, i, j, rowEnd, colEnd, blur_filter.length, blur_filter[0].length, set, 2, blur_filter);

  }

  private double computeValue(PixelInterface[][] originalPixel, int filterRow, int filterCol, int pixelRow,
                              int pixelCol,
                              int pixelRowEnd, int pixelColEnd, int filterRowEnd,
                              int filterColEnd, Set<String> set, int flag, double[][] filter) {
    if (set.contains("" + filterRow + filterCol)) {
      return 0;
    }
    if (filterRow < 0 || filterCol < 0 || pixelRow < 0 || pixelCol < 0 ||
            filterRow == filterRowEnd || filterCol == filterColEnd || pixelRow == pixelRowEnd ||
            pixelCol == pixelColEnd
    ) {
      return 0;
    }

    set.add("" + filterRow + filterCol);
    int channel;
    if (flag == 2) {
      channel = originalPixel[pixelRow][pixelCol].getBlue();
    } else if (flag == 1) {
      channel = originalPixel[pixelRow][pixelCol].getGreen();
    } else {
      channel = originalPixel[pixelRow][pixelCol].getRed();
    }

    double ans = filter[filterRow][filterCol] * channel +
            computeValue(originalPixel, filterRow + 1, filterCol, pixelRow + 1, pixelCol,
                    pixelRowEnd, pixelColEnd, filterRowEnd, filterColEnd, set, flag, filter) +
            computeValue(originalPixel, filterRow, filterCol + 1, pixelRow, pixelCol + 1,
                    pixelRowEnd, pixelColEnd, filterRowEnd, filterColEnd, set, flag, filter) +
            computeValue(originalPixel, filterRow - 1, filterCol, pixelRow - 1, pixelCol,
                    pixelRowEnd, pixelColEnd, filterRowEnd, filterColEnd, set, flag, filter) +
            computeValue(originalPixel, filterRow, filterCol - 1, pixelRow, pixelCol - 1,
                    pixelRowEnd, pixelColEnd, filterRowEnd, filterColEnd, set, flag, filter);

    return ans;
  }

  private double applyBlurOnGreenChannel(PixelInterface[][] originalPixel, int i, int j, int rowEnd, int colEnd) {
    Set<String> set = new HashSet<>();
    return computeValue(originalPixel, 1,
            1, i, j, rowEnd, colEnd, blur_filter.length, blur_filter[0].length, set, 1, blur_filter);
  }

  private double applyBlurOnRedChannel(PixelInterface[][] originalPixel, int i, int j, int rowEnd, int colEnd) {
    Set<String> set = new HashSet<>();
    return computeValue(originalPixel, 1,
            1, i, j, rowEnd, colEnd, blur_filter.length, blur_filter[0].length, set, 0, blur_filter);
  }

  private int clamp(int value) {
    return Math.max(0, Math.min(255, value));
  }
}
