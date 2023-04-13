package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;
import java.io.IOException;

/**
 * Command to split an image into its red, green, and blue color channels.
 */
public class RGBSplitCommand extends AbstractCommand implements CommandInterface {

  /**
   * Constructs a new instance of RGBSplitCommand with the specified view and model.
   * @param view the AdvancedViewInterface to use for displaying output
   * @param model the AdvancedImageManipulationInterface to use for image manipulation
   */
  public RGBSplitCommand(AdvancedViewInterface view, AdvancedImageManipulationInterface model) {
    super(view, model);
  }

  /**
   * Executes the RGBSplitCommand with the specified arguments.
   * @param args an array of Strings representing the command arguments
   * @throws IOException if there is an error in reading or writing an image file
   */

  @Override
  public void execute(String[] args) throws IOException {
    if (!model.containsImages(args[1])) {
      view.displayImageDoesntExist();
    }
    view.displayRGBSplitStatus();
    ImageInterface[] result_images = model.splitIntoRGBImages(model.getImages(args[1]));
    model.storeImages(args[2], result_images[0]);
    model.storeImages(args[3], result_images[1]);
    model.storeImages(args[4], result_images[2]);

  }
}
