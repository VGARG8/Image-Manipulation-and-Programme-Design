package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;
import java.io.IOException;

/**
 * The RGBCombineCommand class implements the CommandInterface and is responsible for combining
 * three images, each representing the red, green, and blue channels of a resulting RGB image. The
 * class uses the AdvancedViewInterface and AdvancedImageManipulationInterface interfaces.
 */
public class RGBCombineCommand extends AbstractCommand implements CommandInterface {

  /**
   * Constructor for the RGBCombineCommand class. Takes in an AdvancedViewInterface and an
   * AdvancedImageManipulationInterface object.
   *
   * @param view  An object implementing the AdvancedViewInterface.
   * @param model An object implementing the AdvancedImageManipulationInterface.
   */
  public RGBCombineCommand(AdvancedViewInterface view, AdvancedImageManipulationInterface model) {
    super(view, model);
  }

  /**
   * Executes the command by combining the red, green, and blue channel images and storing the
   * resulting image.
   *
   * @param args An array of arguments for the command. The first argument is the filename to store
   *             the resulting image, and the next three arguments are the filenames of the red,
   *             green, and blue channel images, respectively.
   * @throws IOException If there is an error loading or storing the image.
   */
  @Override
  public void execute(String[] args) throws IOException {
    if (!model.containsImages(args[3])) {
      view.displayImageDoesntExist();
    }
    view.displayRGBCombineStatus();
    ImageInterface[] rgb_images = {model.getImages(args[2]), model.getImages(args[3]),
        model.getImages(args[4])};
    ImageInterface result_image = model.combineRGBImages(rgb_images);
    model.storeImages(args[1], result_image);
  }
}
