package com.neu.imagemanipulation.controller;

import java.io.IOException;

/**
 * The CommandInterface interface defines the basic behavior for all commands in the image
 * manipulation application. Every command in the application must implement this interface and
 * provide an implementation for the execute method.
 */
public interface CommandInterface {

  /**
   * Executes the command with the given arguments.
   *
   * @param args The arguments required to execute the command.
   * @throws IOException If there is an error executing the command.
   */
  void execute(String[] args) throws IOException;

}
