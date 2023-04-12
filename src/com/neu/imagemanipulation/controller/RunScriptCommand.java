package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RunScriptCommand extends AbstractCommand implements CommandInterface{
  private AdvancedControllerInterface controller;
  public RunScriptCommand(AdvancedViewInterface view, AdvancedControllerInterface controller, AdvancedImageManipulationInterface model) {
    super(view, model);
    this.controller = controller;
  }

  @Override
  public void execute(String[] args) throws IOException {
    try {
      view.displayRunScriptStatus(args[0]);
      BufferedReader reader = new BufferedReader(new FileReader(args[1]));
      String line;
      while ((line = reader.readLine()) != null) {
        if (line.startsWith("#") || line.isEmpty()) {
          continue;
        }
        controller.runCommand(line);
      }
      reader.close();
    } catch (IOException e) {
      view.displayReadFileError();
    }
  }
}
