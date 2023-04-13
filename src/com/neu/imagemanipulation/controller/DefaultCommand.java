package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;
import java.io.IOException;

/**
 * A default command class that is executed when an invalid command is entered by the user. Displays
 * a message to enter a valid command.
 */
public class DefaultCommand extends AbstractCommand implements CommandInterface {

  /**
   * Constructs a new DefaultCommand with the specified view and model.
   *
   * @param view  the view used to interact with the user
   * @param model the model used to manipulate images
   */
  public DefaultCommand(AdvancedViewInterface view, AdvancedImageManipulationInterface model) {
    super(view, model);
  }

  /**
   * Executes the DefaultCommand by displaying a message to enter a valid command.
   *
   * @param args the arguments passed to the command (not used)
   * @throws IOException if an I/O error occurs
   */
  @Override
  public void execute(String[] args) throws IOException {
    view.displayEnterValidCommand();
  }
}
