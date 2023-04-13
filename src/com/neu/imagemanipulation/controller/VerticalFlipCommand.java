package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;
import java.io.IOException;

/**
 * Represents a command that flips an image vertically.
 */
public class VerticalFlipCommand extends AbstractCommand implements CommandInterface {

  /**
   * Creates a new instance of the VerticalFlipCommand.
   *
   * @param view the view component of the application
   * @param model the model component of the application
   */
  public VerticalFlipCommand(AdvancedViewInterface view, AdvancedImageManipulationInterface model) {
    super(view, model);
  }

  /**
   * Executes the vertical flip command.
   *
   * @param args the arguments for the command
   * @throws IOException if an I/O error occurs
   */
  @Override
  public void execute(String[] args) throws IOException {
    if (!model.containsImages(args[1])) {
      view.displayImageDoesntExist();
      return;
    }
    view.displayVerticalFlipStatus();
    ImageInterface result_image = model.flipImageVertically(model.getImages(args[1]));
    model.storeImages(args[2], result_image);
  }
}
