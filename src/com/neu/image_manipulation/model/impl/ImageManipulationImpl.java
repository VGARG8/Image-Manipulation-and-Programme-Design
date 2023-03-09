package com.neu.image_manipulation.model.impl;

import com.neu.image_manipulation.model.ImageManipulation;
import com.neu.image_manipulation.model.entity.Image;
import com.neu.image_manipulation.model.entity.Pixel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class ImageManipulationImpl implements ImageManipulation {

  @Override
  public Image loadImageInPPM(String filename) throws IOException {
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(filename));
    }
    catch (FileNotFoundException e) {
      System.out.println("File "+filename+ " not found!");
      return null;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0)!='#') {
        builder.append(s+System.lineSeparator());
      }
    }
    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    //start generating Image

    int width = sc.nextInt();
    System.out.println("Width of image: "+width);
    int height = sc.nextInt();
    System.out.println("Height of image: "+height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): "+maxValue);

    Image image = new Image(height,width,maxValue);
    Pixel[][] pixel = new Pixel[height][width];
    for (int i=0;i<height;i++) {
      for (int j=0;j<width;j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        System.out.println("Color of pixel ("+j+","+i+"): "+ r+","+g+","+b);
        pixel[i][j]=new Pixel(r,g,b);
      }
    }
    image.setPixel(pixel);
    return image;
  }

  @Override
  public void generateImage(Image image, String filename) {
    List<Integer> pixels = new ArrayList<>();
    Pixel[][] pixelArray = image.getPixel();
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        int r = pixelArray[i][j].getRed();
        int g = pixelArray[i][j].getGreen();
        int b = pixelArray[i][j].getBlue();
        Color color = new Color( r, g, b);

        pixels.add(color.getRGB());
      }
    }
    BufferedImage outputImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
    int[] outputImagePixelData = ((DataBufferInt) outputImg.getRaster().getDataBuffer()).getData();

    for (int i = 0; i < pixels.size(); i++) {
      outputImagePixelData[i] = pixels.get(i);
    }
    try {
      ImageIO.write(outputImg, "png",
          new File("resources/"+filename+".png"));
    } catch (IOException e) {
      System.out.println("Exception occurred :" + e.getMessage());
    }
  }

  public void generateImage(Image[] images, String filename,Boolean bool){
    if(bool){
      generateImage(images[0],filename+"_"+ImageType.Red);
      generateImage(images[1],filename+"_"+ImageType.Green);
      generateImage(images[2],filename+"_"+ImageType.Blue);
    }else{
      generateImage(images[0],filename+"_"+ImageType.Luma);
      generateImage(images[1],filename+"_"+ImageType.Intensity);
      generateImage(images[2],filename+"_"+ImageType.Value);
    }

  }
  @Override
  public void runScript(String filename) {

  }
  private enum ImageType{
    Red,
    Green,
    Blue,
    Luma,
    Intensity,
    Value
  }
}
