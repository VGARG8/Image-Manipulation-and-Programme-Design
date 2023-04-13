package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.view.ViewGuiInterface;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Set;

/**
 * This interface defines the methods that a GUI controller should implement.
 */
public interface GuiControllerInterface {

  /**
   * Sets the view for this controller.
   *
   * @param v The view to be set.
   */
  void setView(ViewGuiInterface v);

  /**
   * Returns a set of command keys.
   *
   * @return A set of strings representing command keys.
   */
  Set<String> getCommandKeys();

  /**
   * Runs a command based on a given command line.
   *
   * @param commandLine A string representing the command line.
   * @throws IOException If an I/O error occurs.
   */
  void runCommand(String commandLine) throws IOException;

  /**
   * Returns a set of image names.
   *
   * @return A set of strings representing image names.
   */
  Set<String> getKeys();
  /**
   * Returns the buffered image associated with a given image name.
   *
   * @param str string representing the name of the image.
   * @return The buffered image associated with the given image name.
   */
  BufferedImage getImage(String str);

  /**
   * returns Returns the buffered image.
   *
   * @return
   */
  BufferedImage getBufferedImg();
}
