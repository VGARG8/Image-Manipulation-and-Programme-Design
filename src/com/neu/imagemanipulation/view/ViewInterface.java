package com.neu.imagemanipulation.view;

import java.io.IOException;

/**
 * This interface has the blueprint of user interaction and commands user follow to interact.
 */
public interface ViewInterface {

  /**
   * The method is used to get commands.
   */
  void getCommand() throws IOException;

  /**
   * This method saves the status of an image.
   */
  void displaySaveStatus() throws IOException;

  /**
   * The method is used to display error to the user.
   */
  void displayReadFileError() throws IOException;

  /**
   * The method is used to display the loading status of an image.
   */
  void displayLoadingStatus() throws IOException;

  /**
   * This method is used to show the status of an image after value -greyscale is formed.
   */
  void displayValueStatus() throws IOException;

  /**
   * This method is used to show the status of an image after Luma -greyscale is formed.
   */
  void displayLumaStatus() throws IOException;

  /**
   * This method is used to show the status of an image after Intensity -greyscale is formed.
   */

  void displayIntensityStatus() throws IOException;

  /**
   * This method is used to show the status of an image under horizontal image operation.
   */
  void displayHorizontalFlipStatus() throws IOException;

  /**
   * This method is used to show the status of an image under vertical image operation.
   */

  void displayVerticalFlipStatus() throws IOException;

  /**
   * This method is used to show the status of an image under brighten operation.
   */
  void displayBrightenStatus() throws IOException;


  /**
   * This method is used to show the status of an image under darkening operation.
   */
  void displayDarkenenStatus() throws IOException;

  /**
   * This method is used to show the status from which path script is used to run.
   */

  void displayRunScriptStatus(String token) throws IOException;

  /**
   * This method is used to show the status of an image under split into R,G,B operation.
   */
  void displayRGBSplitStatus() throws IOException;

  /**
   * This method is used to show the status of an image under R,G,B combining operation.
   */
  void displayRGBCombineStatus() throws IOException;

  /**
   * This method is used to show the user if there is invalid value - negative.
   */
  void displayInvalidValue() throws IOException;

  /**
   * This method is used to show the user if that file is not available.
   */
  void displayNoFileStatus() throws IOException;

  /**
   * This method is used to show the user if there is invalid ppm file .
   */
  void displayInvalidPPM() throws IOException;

  /**
   * If the ppm file is empty then method is used to inform the user.
   */
  void displayEmptyFileStatus() throws IOException;

  /**
   * The method is used to output on the console to the user if the ppm file has invalid enteries.
   */
  void displayInvalidPPMNoValues() throws IOException;

  /**
   * The method is used to display the width of an image.
   *
   * @param width of an image.
   */
  void displayWidth(int width) throws IOException;

  /**
   * The method is used to display the width of an image.
   *
   * @param height of an image.
   */
  void displayHeight(int height) throws IOException;

  /**
   * The method is used to display the maxValue of pixels of an image.
   *
   * @param maxValue of an image.
   */
  void displayMaxValue(int maxValue) throws IOException;

  /**
   * This method shows greyscale of the image is generated with Blue component.
   */
  void displayBlueComponentStatus() throws IOException;

  /**
   * This method shows greyscale of the image is generated with Red component.
   */
  void displayRedComponentStatus() throws IOException;

  /**
   * This method shows greyscale of the image is generated with Green component.
   */
  void displayGreenComponentStatus() throws IOException;

  /**
   * This method shows that an invalid command was entered.
   */
  void displayEnterValidCommand() throws IOException;

  /**
   * This method displays the exception error message.
   */
  void displayExceptionMessage(String message) throws IOException;

  /**
   * This method shows that there is no image with the given name passed.
   */
  void displayImageDoesntExist() throws IOException;

  void displayInvalidFileFormat() throws IOException;

  void displayGreyScaleStatus() throws IOException;

  void displaySharpenStatus() throws IOException;

  void displaySepiaStatus() throws IOException;

  void displayDitherStatus() throws IOException;

  void displayBlurStatus() throws IOException;
}
