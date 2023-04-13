package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;
import java.io.IOException;

/**
 * This class represents a command to apply a sepia tone effect to an image.
 * It implements the CommandInterface and is used in conjunction with the AdvancedViewInterface and AdvancedImageManipulationInterface.
 */
public class SepiaToneCommand extends AbstractCommand implements CommandInterface {

  /**
   * Constructs a new SepiaToneCommand with the given view and model.
   *
   * @param view  The view to use.
   * @param model The model to use.
   */
  public SepiaToneCommand(AdvancedViewInterface view, AdvancedImageManipulationInterface model) {
    super(view, model);
  }

  /**
   * Executes the sepia tone command with the given arguments.
   *
   * @param args The arguments to use.
   * @throws IOException if an I/O error occurs.
   */

  @Override
  public void execute(String[] args) throws IOException {
    view.displaySepiaStatus();
    if (!model.containsImages(args[1])) {
      view.displayImageDoesntExist();
    }
    ImageInterface result_image = model.sepiaTone(model.getImages(args[1]));
    model.storeImages(args[2], result_image);
  }
}
