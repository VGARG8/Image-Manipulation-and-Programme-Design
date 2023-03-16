package com.neu.image_manipulation.view;

public interface ViewInterface {

  void displayMenu();

  void getCommand();

  void displaySaveStatus();

  void displayReadFileError();

  void displayLoadingStatus();

  void displayValueStatus();

  void displayLumaStatus();

  void displayIntensityStatus();

  void displayHorizontalFlipStatus();

  void displayVerticalFlipStatus();

  void displayBrightenStatus();

  void displayDarkenenStatus();

  void displayRunScriptStatus(String token);

  void displayRGBSplitStatus();

  void displayRGBCombineStatus();

  void displayInvalidValue();

  void displayNoFileStatus();

  void displayInvalidPPM();

  void displayEmptyFileStatus();

  void displayInvalidPPMNoValues();

  void displayWidth(int width);

  void displayHeight(int height);

  void displayMaxValue(int maxValue);

  void displayBlueComponentStatus();

  void displayRedComponentStatus();

  void displayGreenComponentStatus();

  void displayEnterValidCommand();

  void displayExceptionMessage(String message);
}
