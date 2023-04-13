package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.impl.GuiModelInteface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;

import java.io.IOException;

/**
 * A default command class that is executed when an invalid command is entered by the user.
 * Displays a message to enter a valid command.
 */
public class DefaultCommand extends AbstractCommand implements CommandInterface {

  /**
   * Constructs a new DefaultCommand with the specified view and model.
   *
   * @param view  the view used to interact with the user
   * @param model the model used to manipulate images
   */
  public DefaultCommand(AdvancedViewInterface view, GuiModelInteface model) {
    super(view, model);
  }

  @Override
  public void execute(String[] args) throws IOException {
    view.displayEnterValidCommand();
  }
}
