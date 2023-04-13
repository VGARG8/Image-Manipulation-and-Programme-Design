package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.impl.GuiModelInteface;
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
  public DitherCommand(AdvancedViewInterface view, GuiModelInteface model) {
    super(view, model);
  }

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
