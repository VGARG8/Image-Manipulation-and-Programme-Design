package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;
import java.io.IOException;

/**
 * Represents a command to horizontally flip an image using an
 * {@link AdvancedImageManipulationInterface} model and display the status using an
 * {@link AdvancedViewInterface} view.
 */
public class HorizontalFlipCommand extends AbstractCommand implements CommandInterface {

  /**
   * Constructs a new {@code HorizontalFlipCommand} with the specified {@link AdvancedViewInterface}
   * and {@link AdvancedImageManipulationInterface}.
   *
   * @param view  the view used to display the status
   * @param model the model used to flip the image
   */
  public HorizontalFlipCommand(AdvancedViewInterface view,
      AdvancedImageManipulationInterface model) {
    super(view, model);
  }

  /**
   * Executes the horizontal flip command with the specified arguments.
   *
   * @param args the arguments of the command (the name of the image to flip and the name to store
   *             the result image)
   * @throws IOException if an I/O error occurs
   */
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
