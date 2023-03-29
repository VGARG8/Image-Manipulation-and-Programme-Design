package com.neu.imagemanipulation.controller;

import java.io.IOException;

public class ExecuteCommand implements CommandInterface{
  private final AdvancedControllerInterface controller;

  public ExecuteCommand(AdvancedControllerInterface controller) {
    this.controller = controller;
  }
  @Override
  public void execute(String[] args) throws IOException {

  }
}
