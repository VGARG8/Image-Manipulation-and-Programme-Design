package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.impl.GuiModelInteface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;

import java.io.IOException;

/**
 * Represents a command to horizontally flip an image using a
 *  model and display the status using a view.
 */
public class HorizontalFlipCommand extends AbstractCommand implements CommandInterface {

  /**
   * Constructs a new {@code HorizontalFlipCommand} with the specified {@link AdvancedViewInterface}
   * and {@link GuiModelInteface}.
   *
   * @param view  the view used to display the status
   * @param model the model used to flip the image
   */
  public HorizontalFlipCommand(AdvancedViewInterface view, GuiModelInteface model) {
    super(view, model);
  }

  @Override
  public void execute(String[] args) throws IOException {
    view.displayHorizontalFlipStatus();
    if (!model.containsImages(args[1])) {
      view.displayImageDoesntExist();
    }
    ImageInterface result_image = model.flipImageHorizontally(model.getImages(args[1]));
    model.storeImages(args[2], result_image);
  }
}
