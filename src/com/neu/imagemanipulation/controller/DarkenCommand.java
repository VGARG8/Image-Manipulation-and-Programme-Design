package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.impl.GuiModelInteface;
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
  public DarkenCommand(AdvancedViewInterface view, GuiModelInteface model) {
    super(view, model);
  }

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
