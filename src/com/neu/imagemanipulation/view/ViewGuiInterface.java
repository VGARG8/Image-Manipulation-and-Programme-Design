package com.neu.imagemanipulation.view;

import com.neu.imagemanipulation.controller.GuiControllerInterface;

import java.io.IOException;

/**
 * this interface for GUI view, it extends AdvancedViewInterface.
 */
public interface ViewGuiInterface extends AdvancedViewInterface {
  void addFeatures(GuiControllerInterface guiController);

  @Override
  void displayInvalidFileFormat() throws IOException;

  @Override
  void displayGreyScaleStatus() throws IOException;

  @Override
  void displaySharpenStatus() throws IOException;

  @Override
  void displaySepiaStatus() throws IOException;

  @Override
  void displayDitherStatus() throws IOException;

  @Override
  void displayBlurStatus() throws IOException;

  @Override
  void displayFileNotSpecified() throws IOException;

  @Override
  void getCommand() throws IOException;

  @Override
  void displaySaveStatus(String fileExtension) throws IOException;

  @Override
  void displayReadFileError() throws IOException;

  @Override
  void displayLoadingStatus() throws IOException;

  @Override
  void displayValueStatus() throws IOException;

  @Override
  void displayLumaStatus() throws IOException;

  @Override
  void displayIntensityStatus() throws IOException;

  @Override
  void displayHorizontalFlipStatus() throws IOException;

  @Override
  void displayVerticalFlipStatus() throws IOException;

  @Override
  void displayBrightenStatus() throws IOException;

  @Override
  void displayDarkenenStatus() throws IOException;

  @Override
  void displayRunScriptStatus(String token) throws IOException;

  @Override
  void displayRGBSplitStatus() throws IOException;

  @Override
  void displayRGBCombineStatus() throws IOException;

  @Override
  void displayInvalidValue() throws IOException;

  @Override
  void displayNoFileStatus() throws IOException;

  @Override
  void displayInvalidPPM() throws IOException;

  @Override
  void displayEmptyFileStatus() throws IOException;

  @Override
  void displayInvalidPPMNoValues() throws IOException;

  @Override
  void displayWidth(int width) throws IOException;

  @Override
  void displayHeight(int height) throws IOException;

  @Override
  void displayMaxValue(int maxValue) throws IOException;

  @Override
  void displayBlueComponentStatus() throws IOException;

  @Override
  void displayRedComponentStatus() throws IOException;

  @Override
  void displayGreenComponentStatus() throws IOException;

  @Override
  void displayEnterValidCommand() throws IOException;

  @Override
  void displayImageDoesntExist() throws IOException;
}
