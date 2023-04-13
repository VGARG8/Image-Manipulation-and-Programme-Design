package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;
import java.io.IOException;

/**
 * A command class that applies a greyscale tone to an image and stores the result.
 */
public class GreyScaleToneCommand extends AbstractCommand implements CommandInterface {

  /**
   * Constructs a new GreyScaleToneCommand object with the given view and model.
   *
   * @param view  the view associated with this command.
   * @param model the model associated with this command.
   */
  public GreyScaleToneCommand(AdvancedViewInterface view,
      AdvancedImageManipulationInterface model) {
    super(view, model);
  }

  /**
   * Executes the greyscale tone command on an image and stores the result.
   *
   * @param args the arguments passed to the command, where args[1] is the name of the input image
   *             and args[2] is the name of the output image.
   * @throws IOException if there is an IO error during execution.
   */
  @Override
  public void execute(String[] args) throws IOException {
    view.displayGreyScaleStatus();
    if (!model.containsImages(args[1])) {
      view.displayImageDoesntExist();
    }
    ImageInterface result_image = model.greyscale(model.getImages(args[1]));
    model.storeImages(args[2], result_image);
  }
}
