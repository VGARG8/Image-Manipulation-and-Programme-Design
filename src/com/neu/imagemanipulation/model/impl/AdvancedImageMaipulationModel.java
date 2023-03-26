package com.neu.imagemanipulation.model.impl;

import com.neu.imagemanipulation.model.entity.Image;
import com.neu.imagemanipulation.model.entity.Pixel;
import java.util.HashSet;
import java.util.Set;

public class AdvancedImageMaipulationModel extends ImageManipulationModel
    implements AdvancedImageManipulationInterface{
  public static final double blur_filter[][]
      = {{1.0/16,1.0/8,1.0/16},{1.0/8,1.0/4,1.0/8},{1.0/16,1.0/8,1.0/16}};
  public static final double sharpen_filter[][] =
      {
          {-1.0/8,-1.0/8,-1.0/8,-1.0/8,-1.0/8},
          {-1.0/8,1.0/4,1.0/4,1.0/4,-1.0/8},
          {-1.0/8,1.0/4,1,1.0/4,-1.0/8},
          {-1.0/8,1.0/4,1.0/4,1.0/4,-1.0/8},
          {-1.0/8,-1.0/8,-1.0/8,-1.0/8,-1.0/8}
      };
  public static final double greyscaleFilter[][] ={{0.2126,0.7152,0.0722},
      {0.2126,0.7152,0.0722},
      {0.2126,0.7152,0.0722}};
  public static final double sepiaToneFilter[][] ={{0.393,0.769,0.189},
      {0.349,0.686,0.168},
      {0.272,0.534,0.131}};

  @Override
  public Image blur(Image image) {
    Image blurImage = new Image(image.getHeight(),image.getWidth(), image.getMaxValue());

    Pixel[][] originalPixel  = image.getPixel();

    int row= image.getHeight();
    int col = image.getWidth();
    Pixel[][] blurPixel = new Pixel[row][col];
    for(int i=0;i<row;i++){
      for(int j=0;j<col;j++){
        int newR = (int)applyBlurOnRedChannel(originalPixel,i,j,row,col);
        int newG = (int)applyBlurOnGreenChannel(originalPixel,i,j,row,col);
        int newB = (int) applyBlurOnBlueChannel(originalPixel,i,j,row,col);
        newR = (newR>255) ? 255 : newR;
        newR = (newR<0) ? 0 : newR;
        newG = (newG>255) ? 255 : newG;
        newG = (newG<0) ? 0 : newG;
        newB = (newB>255) ? 255 : newB;
        newB = (newB<0) ? 0 : newB;
       // System.out.println(newR+" " +newG+" " +newB);
        blurPixel[i][j] = new Pixel(newR,newG,newB);
      }
    }
    blurImage.setPixel(blurPixel);

    return blurImage;
  }

  @Override
  public Image sharpen(Image image) {
    Image sharpenImage = new Image(image.getHeight(),image.getWidth(), image.getMaxValue());

    Pixel[][] originalPixel  = image.getPixel();
 
    int row= image.getHeight();
    int col = image.getWidth();
    Pixel[][] sharpenPixel = new Pixel[row][col];
    for(int i=0;i<row;i++){
      for(int j=0;j<col;j++){
        int newR = (int)applySharpenOnRedChannel(originalPixel,i,j,row,col);
        int newG = (int)applySharpenGreenChannel(originalPixel,i,j,row,col);
        int newB = (int) applySharpenOnBlueChannel(originalPixel,i,j,row,col);
        newR = (newR>255) ? 255 : newR;
        newR = (newR<0) ? 0 : newR;
        newG = (newG>255) ? 255 : newG;
        newG = (newG<0) ? 0 : newG;
        newB = (newB>255) ? 255 : newB;
        newB = (newB<0) ? 0 : newB;
        // System.out.println(newR+" " +newG+" " +newB);
        sharpenPixel[i][j] = new Pixel(newR,newG,newB);
      }
    }
    sharpenImage.setPixel(sharpenPixel);
    return sharpenImage;
  }

  @Override
  public Image greyscale(Image image) {
    Image greyScale = new Image(image.getHeight(),image.getWidth(), image.getMaxValue());

    Pixel[][] originalPixel  = image.getPixel();

    int row= image.getHeight();
    int col = image.getWidth();
    Pixel[][] greyscalePixel = new Pixel[row][col];
    for(int i=0;i<row;i++){
      for(int j=0;j<col;j++){
        int newR = multiplyMatrix(originalPixel[i][j],greyscaleFilter[0]);
        int newG = multiplyMatrix(originalPixel[i][j],greyscaleFilter[1]);
        int newB = multiplyMatrix(originalPixel[i][j],greyscaleFilter[2]);
        newR = (newR>255) ? 255 : newR;
        newR = (newR<0) ? 0 : newR;
        newG = (newG>255) ? 255 : newG;
        newG = (newG<0) ? 0 : newG;
        newB = (newB>255) ? 255 : newB;
        newB = (newB<0) ? 0 : newB;
        // System.out.println(newR+" " +newG+" " +newB);
        greyscalePixel[i][j] = new Pixel(newR,newG,newB);
      }
    }
    greyScale.setPixel(greyscalePixel);
    return greyScale;

  }

  private int multiplyMatrix(Pixel pixel, double[] doubles) {
    return (int) (pixel.getRed()*doubles[0]+pixel.getGreen()*doubles[1]+pixel.getBlue()*doubles[2]);
  }

  @Override
  public Image sepiaTone(Image image) {
    Image sepiaTone = new Image(image.getHeight(),image.getWidth(), image.getMaxValue());

    Pixel[][] originalPixel  = image.getPixel();

    int row= image.getHeight();
    int col = image.getWidth();
    Pixel[][] sepiaTonePixel = new Pixel[row][col];
    for(int i=0;i<row;i++){
      for(int j=0;j<col;j++){
        int newR = multiplyMatrix(originalPixel[i][j],sepiaToneFilter[0]);
        int newG = multiplyMatrix(originalPixel[i][j],sepiaToneFilter[1]);
        int newB = multiplyMatrix(originalPixel[i][j],sepiaToneFilter[2]);
        newR = (newR>255) ? 255 : newR;
        newR = (newR<0) ? 0 : newR;
        newG = (newG>255) ? 255 : newG;
        newG = (newG<0) ? 0 : newG;
        newB = (newB>255) ? 255 : newB;
        newB = (newB<0) ? 0 : newB;
        // System.out.println(newR+" " +newG+" " +newB);
        sepiaTonePixel[i][j] = new Pixel(newR,newG,newB);
      }
    }
    sepiaTone.setPixel(sepiaTonePixel);
    return sepiaTone;
  }

  private double applySharpenOnBlueChannel(Pixel[][] originalPixel, int i, int j,
      int rowEnd, int colEnd) {
    Set<String> set = new HashSet<>();
    return computeValue(originalPixel,2,
        2,i,j,rowEnd,colEnd,sharpen_filter.length,sharpen_filter[0].length,set,
        2,sharpen_filter);
  }

  private double applySharpenGreenChannel(Pixel[][] originalPixel, int i, int j,
      int rowEnd, int colEnd) {
    Set<String> set = new HashSet<>();
    return computeValue(originalPixel,2,
        2,i,j,rowEnd,colEnd,sharpen_filter.length,sharpen_filter[0].length,set,1,
        sharpen_filter);
  }

  private double applySharpenOnRedChannel(Pixel[][] originalPixel, int i, int j,
      int rowEnd, int colEnd) {
    Set<String> set = new HashSet<>();
    return computeValue(originalPixel,2,
        2,i,j,rowEnd,colEnd,sharpen_filter.length,sharpen_filter[0].length,set,0,
        sharpen_filter);
  }

  private double applyBlurOnBlueChannel(Pixel[][] originalPixel, int i, int j,int rowEnd,int colEnd) {
    Set<String> set = new HashSet<>();
    return computeValue(originalPixel,1,
        1,i,j,rowEnd,colEnd,blur_filter.length,blur_filter[0].length,set,2, blur_filter);

  }

  private double computeValue(Pixel[][] originalPixel, int filterRow, int filterCol, int pixelRow,
      int pixelCol,
      int pixelRowEnd, int pixelColEnd, int filterRowEnd,
      int filterColEnd, Set<String> set,int flag, double[][] filter) {
    if(set.contains(""+filterRow+filterCol)){
      return 0;
    }
    if(filterRow<0 || filterCol <0 || pixelRow<0 || pixelCol<0 ||
    filterRow == filterRowEnd || filterCol == filterColEnd || pixelRow == pixelRowEnd ||
        pixelCol == pixelColEnd
    ){
      return 0;
    }

    set.add(""+filterRow+filterCol);
    int channel =0;
    if(flag == 2){
      channel = originalPixel[pixelRow][pixelCol].getBlue();
    }else if (flag==1){
      channel = originalPixel[pixelRow][pixelCol].getGreen();
    }else{
      channel = originalPixel[pixelRow][pixelCol].getRed();
    }

    double ans = filter[filterRow][filterCol] * channel +
    computeValue(originalPixel,filterRow+1,filterCol,pixelRow+1,pixelCol,
        pixelRowEnd,pixelColEnd,filterRowEnd,filterColEnd, set,flag, filter)+
    computeValue(originalPixel,filterRow,filterCol+1,pixelRow,pixelCol+1,
        pixelRowEnd,pixelColEnd,filterRowEnd,filterColEnd, set,flag, filter)+
    computeValue(originalPixel,filterRow-1,filterCol,pixelRow-1,pixelCol,
        pixelRowEnd,pixelColEnd,filterRowEnd,filterColEnd, set,flag, filter)+
    computeValue(originalPixel,filterRow,filterCol-1,pixelRow,pixelCol-1,
        pixelRowEnd,pixelColEnd,filterRowEnd,filterColEnd, set,flag, filter);

      return ans;
  }

  private double applyBlurOnGreenChannel(Pixel[][] originalPixel, int i, int j,int rowEnd,int colEnd) {
    Set<String> set = new HashSet<>();
    return computeValue(originalPixel,1,
        1,i,j,rowEnd,colEnd,blur_filter.length,blur_filter[0].length,set,1, blur_filter);
  }

  private double applyBlurOnRedChannel(Pixel[][] originalPixel, int i, int j,int rowEnd,int colEnd) {
    Set<String> set = new HashSet<>();
    return computeValue(originalPixel,1,
        1,i,j,rowEnd,colEnd,blur_filter.length,blur_filter[0].length,set,0, blur_filter);
  }
}
