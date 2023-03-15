package com.neu.image_manipulation.view;

public interface ViewInterface {

  void displayMenu();

  void getCommand();

  void displaySaveStatus(String filename);

  void displayReadFileError();

  void displayLoadingStatus(String filename, String name);

  void displayValueStatus(String name);

  void displayLumaStatus(String name);

  void displayIntensityStatus(String name);

  void displayHorizontalFlipStatus(String name);

  void displayVerticalFlipStatus(String name);

  void displayBrightenStatus(int value, String name);

  void displayDarkenenStatus(int value, String name);

  void displayRunScriptStatus(String token);

  void displayRGBSplitStatus();

  void displayRGBCombineStatus();

  void displayInvalidValue();

  void displayNoFileStatus(String filename);

  void displayInvalidPPM();

  void displayEmptyFileStatus();

  void displayInvalidPPMNoValues();

  void displayWidth(int width);

  void displayHeight(int height);

  void displayMaxValue(int maxValue);
}
