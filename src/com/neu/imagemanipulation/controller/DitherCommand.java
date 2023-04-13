package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;
import java.io.IOException;

/**
 * A class that represents a Dither command which dithers an input image and stores the result in
 * the model.
 */
public class DitherCommand extends AbstractCommand implements CommandInterface {

  /**
   * Constructs a DitherCommand object with the given view and model.
   *
   * @param view  the view object used to display output to the user
   * @param model the model object used to manipulate images
   */
  public DitherCommand(AdvancedViewInterface view, AdvancedImageManipulationInterface model) {
    super(view, model);
  }

  /**
   * Executes the DitherCommand by dithering an input image and storing the result in the model.
   *
   * @param args the arguments needed to execute the command, including the input image name and
   *             the
   * @throws IOException if an I/O error occurs
   */
  @Override
  public void execute(String[] args) throws IOException {
    view.displayDitherStatus();
    if (!model.containsImages(args[1])) {
      view.displayImageDoesntExist();
    }
    ImageInterface result_image = model.dither(model.getImages(args[1]));
    model.storeImages(args[2], result_image);
  }
}
