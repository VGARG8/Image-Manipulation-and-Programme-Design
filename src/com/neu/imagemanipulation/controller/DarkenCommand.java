package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;
import java.io.IOException;

/**
 * The DarkenCommand class represents a command to darken an image by a given value. It implements
 * the CommandInterface and extends the AbstractCommand.
 */
public class DarkenCommand extends AbstractCommand implements CommandInterface {

  /**
   * Constructs a DarkenCommand object with the given view and model.
   *
   * @param view  the view used to display the status of the command execution
   * @param model the model used to manipulate the image
   */
  public DarkenCommand(AdvancedViewInterface view, AdvancedImageManipulationInterface model) {
    super(view, model);
  }

  /**
   * Executes the darken command on the given image by the given value and stores the result image
   * with a given name. Displays the status of the command execution on the view.
   *
   * @param args an array of command arguments, where args[1] is the value to darken by, args[2] is
   *             the name of the image to darken, and args[3] is the name to store the result image
   *             as
   * @throws IOException if an I/O error occurs while executing the command
   */
  @Override
  public void execute(String[] args) throws IOException {
    if (Integer.parseInt(args[1]) < 0) {
      view.displayInvalidValue();
    }
    if (!model.containsImages(args[2])) {
      view.displayImageDoesntExist();
    }
    view.displayDarkenenStatus();
    ImageInterface result_image = model.darkenImage(model.getImages(args[2]),
        Integer.parseInt(args[1]));
    model.storeImages(args[3], result_image);
  }
}
