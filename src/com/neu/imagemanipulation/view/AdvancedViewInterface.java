package com.neu.imagemanipulation.view;

import java.io.IOException;

public interface AdvancedViewInterface extends ViewInterface {

  void displayInvalidFileFormat() throws IOException;

  void displayGreyScaleStatus() throws IOException;

  void displaySharpenStatus() throws IOException;

  void displaySepiaStatus() throws IOException;

  void displayDitherStatus() throws IOException;

  void displayBlurStatus() throws IOException;

  void displayFileNotSpecified() throws IOException;
}
