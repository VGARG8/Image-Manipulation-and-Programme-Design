package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.impl.GuiModelInteface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 A command class that executes a script file. Reads each line of the file and executes the command
 specified in the line. This command class is used in the AdvancedImageManipulation program.
 */
public class RunScriptCommand extends AbstractCommand implements CommandInterface {
  private AdvancedControllerInterface controller;

  /**
   Constructs a new instance of the RunScriptCommand with the specified view, controller, and model.
   @param view the view associated with this command.
   @param controller the controller used to run commands specified in the script file.
   @param model the model associated with this command.
   */
  public RunScriptCommand(AdvancedViewInterface view, AdvancedControllerInterface controller,
                          GuiModelInteface model) {
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
