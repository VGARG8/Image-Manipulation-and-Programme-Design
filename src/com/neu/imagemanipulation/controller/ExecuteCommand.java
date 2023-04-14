package com.neu.imagemanipulation.controller;

import java.io.IOException;

/**
 * Represents the ExecuteCommand which implements the CommandInterface. This command is used to
 * execute a command line entered by the user. It receives an AdvancedControllerInterface object
 * which it uses to execute the command line.
 */
public class ExecuteCommand implements CommandInterface {

  /**
   * Constructs an ExecuteCommand object with the given AdvancedControllerInterface object.
   *
   * @param controller the AdvancedControllerInterface object used to execute the command line.
   */
  public ExecuteCommand(AdvancedControllerInterface controller) {
  }


  @Override
  public void execute(String[] args) throws IOException {
    // for implementing the interface
  }
}
