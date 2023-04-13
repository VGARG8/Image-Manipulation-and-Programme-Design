package com.neu.imagemanipulation.view;

import java.io.IOException;

/**
 * this class extends the abstract class AdvancedView and is used for the console based view.
 */
public class AdvancedViewConsole extends AdvancedView {

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

  @Override
  public void displayHistogramStatus() throws IOException {
    out.append("histogram is stored");
  }
}

