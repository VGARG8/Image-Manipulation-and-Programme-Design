package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.impl.ImageManipulationInterface;
import com.neu.imagemanipulation.view.ViewInterface;
import java.io.IOException;

/**
 * This interface has the blueprint of methods used to load from ppm , save as ppm, and run
 * commands/script provided by the user.
 */

public interface ControllerInterface {

  /**
   * The method is used to take input in the view.
   *
   * @throws IOException if invalid command/file.
   */


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
