package com.neu.imagemanipulation.controller;

import java.io.IOException;

/**
 * An interface for an advanced controller that extends the basic ControllerInterface with
 * additional functionality for running commands and managing the execution of the application.
 */
public interface AdvancedControllerInterface extends ControllerInterface {

  /**
   * Displays a message to the user indicating that a file has not been specified.
   *
   * @throws IOException if an I/O error occurs
   */
  void callViewForMain() throws IOException;

  /**
   * Parses the input command line and executes the corresponding CommandInterface object.
   *
   * @param commandLine the input command line
   * @throws IOException if an I/O error occurs
   */
  void runCommand(String commandLine) throws IOException;

  /**
   * Executes the commands entered by the user by getting input from the Scanner object and calling
   * the corresponding command's execute method.
   *
   * @throws IOException if there is an error reading the input
   */
  void execute() throws IOException;

  /**
   * Sets the value of the flag that controls whether the execute method continues to loop.
   *
   * @param exitFlag the new value of the flag
   */
  void setExitFlag(boolean exitFlag);
}
