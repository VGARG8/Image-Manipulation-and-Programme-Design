package com.neu.image_manipulation.controller;

import com.neu.image_manipulation.model.entity.Image;
import com.neu.image_manipulation.model.impl.ImageManipulationInterface;
import com.neu.image_manipulation.view.ViewInterface;

import java.io.IOException;

/**
 * This interface has the blueprint of methods used to load from ppm , save as ppm, and run
 * commands/script provided by the user.
 */

public interface ControllerInterface {
  /**
   * The method is used to take input in the view.
   *
   * @throws IOException
   */
  void go() throws IOException;

  /**
   * This method is used to read a ppm file and generate Image object from it.
   *
   * @param filename the ppm file to be loaded.
   * @return image object
   * @throws IOException if the file is not present at the given location.
   */
  Image loadImageInPPM(String filename) throws IOException;

  /**
   * This method is used to save a ppm file formed using the image object with the file name passed.
   *
   * @param filename name of the ppm file.
   * @param image    image that is to be converted to ppm.
   * @throws IOException
   */
  void savePPM(String filename, Image image) throws IOException;

  /**
   * The method is used to run commands provided by the user from console or Script.
   *
   * @param command the command from the user to manipulate the image.
   * @throws IOException invalid command.
   */
  void runCommand(String command) throws IOException;

  /**
   * this method is used to get the model object.
   *
   * @return returns ImageManipulationInterface model object.
   */
  ImageManipulationInterface getModel();

  /**
   * this method is used to get the view object.
   *
   * @return returns ViewInterface view object.
   */
  ViewInterface getView();
}
