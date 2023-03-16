package com.neu.image_manipulation.view;

/**
 * This interface has the blueprint of user interaction and commands user follow to interact.
 */
public interface ViewInterface {

  /**
   * The method is used to display menu.
   */
  void displayMenu();

  /**
   * The method is used to get commands.
   */
  void getCommand();

  /**
   * This methdo saves the status of an image after every process.
   */
  void displaySaveStatus();

  /**
   * The method is used to display error to the user.
   */
  void displayReadFileError();

  /**
   * The method is used to display the loading status of an image.
   */
  void displayLoadingStatus();

  /**
   * This method is used to show the status of an image after value -greyscale is formed.
   */
  void displayValueStatus();

  /**
   * This method is used to show the status of an image after Luma -greyscale is formed.
   */
  void displayLumaStatus();

  /**
   * This method is used to show the status of an image after Intensity -greyscale is formed.
   */

  void displayIntensityStatus();

  /**
   * This method is used to show the status of an image under horizontal image operation.
   */
  void displayHorizontalFlipStatus();

  /**
   * This method is used to show the status of an image under vertical image operation.
   */

  void displayVerticalFlipStatus();

  /**
   * This method is used to show the status of an image under brighten operation.
   */
  void displayBrightenStatus();


  /**
   * This method is used to show the status of an image under darkening operation.
   */
  void displayDarkenenStatus();

  /**
   * This method is used to show the status from which path script is used to run.
   */

  void displayRunScriptStatus(String token);

  /**
   * This method is used to show the status of an image under split into R,G,B operation.
   */
  void displayRGBSplitStatus();

  /**
   * This method is used to show the status of an image under R,G,B combining operation.
   */
  void displayRGBCombineStatus();

  /**
   * This method is used to show the user if there is invalid value - negative.
   */
  void displayInvalidValue();

  /**
   * This method is used to show the user if that file is not available.
   */
  void displayNoFileStatus();

  /**
   * This method is used to show the user if there is invalid ppm file .
   */
  void displayInvalidPPM();

  /**
   * If the ppm file is empty then method is used to inform the user.
   */
  void displayEmptyFileStatus();

  /**
   * The method is used to output on the console to the user if the ppm file has invalid enteries.
   */
  void displayInvalidPPMNoValues();

  /**
   * The method is used to display the width of an image.
   *
   * @param width of an image.
   */
  void displayWidth(int width);

  /**
   * The method is used to display the width of an image.
   *
   * @param height of an image.
   */
  void displayHeight(int height);

  /**
   * The method is used to display the maxValue of pixels of an image.
   *
   * @param maxValue of an image.
   */
  void displayMaxValue(int maxValue);
}
