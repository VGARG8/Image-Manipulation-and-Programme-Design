package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.Constants;
import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;
import java.io.IOException;

/**
 * A command to convert an image to grayscale using different color models. Implements the
 * CommandInterface and extends the AbstractCommand class.
 */

public class GreyScaleCommand extends AbstractCommand implements CommandInterface {

  /**
   * Constructor for the GreyScaleCommand class.
   *
   * @param view  The AdvancedViewInterface instance to interact with the user.
   * @param model The AdvancedImageManipulationInterface instance to perform image manipulation.
   */
  public GreyScaleCommand(AdvancedViewInterface view, AdvancedImageManipulationInterface model) {
    super(view, model);
  }

  /**
   * Executes the grayscale conversion based on the specified color model and stores the result in a
   * new image. Displays relevant messages to the user depending on the color model selected.
   *
   * @param args String array containing the command arguments: [color model] [source image name]
   *             [destination image name]
   * @throws IOException If an I/O error occurs while storing the new image.
   */
  @Override
  public void execute(String[] args) throws IOException {
    ImageInterface result_image;
    switch (args[1]) {
      case Constants.VALUE_COMPONENT:
        if (!model.containsImages(args[2])) {
          view.displayImageDoesntExist();
        }
        view.displayValueStatus();
        result_image = model.createValueComponentOfImage(model.getImages(args[2]));
        model.storeImages(args[3], result_image);
        break;
      case Constants.LUMA_COMPONENT:
        if (!model.containsImages(args[2])) {
          view.displayImageDoesntExist();
        }
        view.displayLumaStatus();
        result_image = model.createLumaComponentOfImage(model.getImages(args[2]));
        model.storeImages(args[3], result_image);
        break;
      case Constants.INTENSITY_COMPONENT:
        if (!model.containsImages(args[2])) {
          view.displayImageDoesntExist();
        }
        view.displayIntensityStatus();
        result_image = model.createIntensityComponentOfImage(model.getImages(args[2]));
        model.storeImages(args[3], result_image);
        break;
      case Constants.RED_COMPONENT:
        if (!model.containsImages(args[2])) {
          view.displayImageDoesntExist();
        }
        view.displayRedComponentStatus();
        result_image = model.createRedComponentOfImage(model.getImages(args[2]));
        model.storeImages(args[3], result_image);
        break;
      case Constants.GREEN_COMPONENT:
        if (!model.containsImages(args[2])) {
          view.displayImageDoesntExist();
        }
        view.displayGreenComponentStatus();
        result_image = model.createGreenComponentOfImage(model.getImages(args[2]));
        model.storeImages(args[3], result_image);
        break;
      case Constants.BLUE_COMPONENT:
        if (!model.containsImages(args[2])) {
          view.displayImageDoesntExist();
        }
        view.displayBlueComponentStatus();
        result_image = model.createBlueComponentOfImage(model.getImages(args[2]));
        model.storeImages(args[3], result_image);
        break;
      default:
        view.displayEnterValidCommand();
    }
  }
}
