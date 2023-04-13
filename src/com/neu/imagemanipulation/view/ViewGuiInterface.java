package com.neu.imagemanipulation.view;

import com.neu.imagemanipulation.controller.GuiControllerInterface;
import java.io.IOException;

/**
 * The ViewGuiInterface interface represents the graphical user interface for the image manipulation program.
 * It extends the AdvancedViewInterface interface and defines additional methods for displaying various types of status
 * and error messages related to image manipulation.
 */

public interface ViewGuiInterface extends AdvancedViewInterface {

  /**
   * Adds features to the GUI view by setting up listeners for GUI components
   * and connecting them to the given controller.
   *
   * @param guiController the controller to connect the GUI components to
   */
  void addFeatures(GuiControllerInterface guiController);

  /**
   * Displays an error message indicating that the specified file format is invalid.
   *
   * @throws IOException if there is an error displaying the message
   */
  void displayInvalidFileFormat() throws IOException;

  /**
   * Displays a status message indicating that the image has been converted to grayscale.
   *
   * @throws IOException if there is an error displaying the message
   */
  void displayGreyScaleStatus() throws IOException;

  /**
   * Displays a status message indicating that the image has been sharpened.
   *
   * @throws IOException if there is an error displaying the message
   */
  void displaySharpenStatus() throws IOException;

  /**
   * Displays a status message indicating that the image has been converted to sepia tone.
   *
   * @throws IOException if there is an error displaying the message
   */
  void displaySepiaStatus() throws IOException;

  /**
   * Displays a status message indicating that the image has been dithered.
   *
   * @throws IOException if there is an error displaying the message
   */
  void displayDitherStatus() throws IOException;

  /**
   * Displays a status message indicating that the image has been blurred.
   *
   * @throws IOException if there is an error displaying the message
   */
  void displayBlurStatus() throws IOException;

  /**
   * Displays an error message indicating that no file was specified.
   *
   * @throws IOException if there is an error displaying the message
   */
  void displayFileNotSpecified() throws IOException;

  /**
   * Gets a user command through the graphical user interface.
   *
   * @throws IOException if there is an error getting the command
   */
  void getCommand() throws IOException;

  /**
   * Displays a status message indicating that the image has been saved with the given file extension.
   *
   * @param fileExtension the file extension of the saved image file
   * @throws IOException if there is an error displaying the message
   */
  void displaySaveStatus(String fileExtension) throws IOException;

  /**
   * Displays an error message indicating that there was an error reading the file.
   *
   * @throws IOException if there is an error displaying the message
   */
  void displayReadFileError() throws IOException;

  /**
   * Displays a status message indicating that the image is being loaded.
   *
   * @throws IOException if there is an error displaying the message
   */
  void displayLoadingStatus() throws IOException;

  /**
   * Displays a status message indicating that the pixel values of the image are being displayed.
   *
   * @throws IOException if there is an error displaying the message
   */
  void displayValueStatus() throws IOException;

  /**
   * Displays a status message indicating that the luminance values of the image are being displayed.
   *
   * @throws IOException if there is an error displaying the message
   */

  @Override
  void displayLumaStatus() throws IOException;

  /**
   * Displays the status of the image intensity modification.
   *
   * @throws IOException if an I/O error occurs.
   */
  void displayIntensityStatus() throws IOException;

  /**
   * Displays the status of the horizontal flip operation.
   *
   * @throws IOException if an I/O error occurs.
   */
  void displayHorizontalFlipStatus() throws IOException;

  /**
   * Displays the status of the vertical flip operation.
   *
   * @throws IOException if an I/O error occurs.
   */
  void displayVerticalFlipStatus() throws IOException;

  /**
   * Displays the status of the image brightening operation.
   *
   * @throws IOException if an I/O error occurs.
   */
  void displayBrightenStatus() throws IOException;

  /**
   * Displays the status of the image darkening operation.
   *
   * @throws IOException if an I/O error occurs.
   */
  void displayDarkenenStatus() throws IOException;

  /**
   * Displays the status of running a script with a given token.
   *
   * @param token the token of the script being run.
   * @throws IOException if an I/O error occurs.
   */
  void displayRunScriptStatus(String token) throws IOException;

  /**
   * Displays the status of the RGB split operation.
   *
   * @throws IOException if an I/O error occurs.
   */
  void displayRGBSplitStatus() throws IOException;

  /**
   * Displays the status of the RGB combine operation.
   *
   * @throws IOException if an I/O error occurs.
   */
  void displayRGBCombineStatus() throws IOException;

  /**
   * Displays an error message for an invalid value.
   *
   * @throws IOException if an I/O error occurs.
   */
  void displayInvalidValue() throws IOException;

  /**
   * Displays an error message for when a file is not specified.
   *
   * @throws IOException if an I/O error occurs.
   */
  void displayNoFileStatus() throws IOException;

  /**
   * Displays an error message for an invalid PPM file format.
   *
   * @throws IOException if an I/O error occurs.
   */
  void displayInvalidPPM() throws IOException;

  /**
   * Displays an error message for an empty file.
   *
   * @throws IOException if an I/O error occurs.
   */
  void displayEmptyFileStatus() throws IOException;

  /**
   * Displays an error message for an invalid PPM file format with no values.
   *
   * @throws IOException if an I/O error occurs.
   */
  void displayInvalidPPMNoValues() throws IOException;

  /**
   * Displays the width of the current image.
   *
   * @param width the width of the image.
   * @throws IOException if an I/O error occurs.
   */
  void displayWidth(int width) throws IOException;

  /**
   * Displays the height of the current image.
   *
   * @param height the height of the image.
   * @throws IOException if an I/O error occurs.
   */
  void displayHeight(int height) throws IOException;

  /**
   * Displays the maximum value of the current image.
   *
   * @param maxValue the maximum value of the image.
   * @throws IOException if an I/O error occurs.
   */
  void displayMaxValue(int maxValue) throws IOException;

  /**
   * Displays the status of the blue component operation.
   *
   * @throws IOException if an I/O error occurs.
   */
  void displayBlueComponentStatus() throws IOException;

  /**
   * Displays the status of the red component operation.
   *
   * @throws IOException if an I/O error occurs.
   */
  void displayRedComponentStatus() throws IOException;

  /**
   * Displays the status of the green component operation.
   *
   * @throws IOException if an I/O error occurs.
   */
  void displayGreenComponentStatus() throws IOException;

  /**
   * Displays a message prompting the user to enter a valid command.
   *
   * @throws IOException if an I/O error occurs.
   */
  void displayEnterValidCommand() throws IOException;

/**
 * Displays an error message for when an image does not exist.
 *
 * @throws IOException if an I/O error occurs.
 */
}
