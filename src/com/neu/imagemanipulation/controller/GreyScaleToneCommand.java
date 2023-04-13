package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.impl.GuiModelInteface;
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
  public GreyScaleToneCommand(AdvancedViewInterface view, GuiModelInteface model) {
    super(view, model);
  }

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
