package com.neu.imagemanipulation.view;

import java.io.IOException;

public class AdvancedView extends View implements AdvancedViewInterface {

  @Override
  public void displayInvalidFileFormat() throws IOException {
    out.append("File format is invalid!\n");
  }

  @Override
  public void displayGreyScaleStatus() throws IOException {
    out.append("Creating greyscale toned image.\n");
  }

  @Override
  public void displaySharpenStatus() throws IOException {
    out.append("Sharpening the image.\n");
  }

  @Override
  public void displaySepiaStatus() throws IOException {
    out.append("Creating sepia toned image.\n");
  }

  @Override
  public void displayDitherStatus() throws IOException {
    out.append(" Dithering the image.\n");
  }

  @Override
  public void displayBlurStatus() throws IOException {
    out.append(" Blurring the image.\n");
  }

  @Override
  public void displayFileNotSpecified() throws IOException {
    out.append("Error: no script file specified.");
  }
}
