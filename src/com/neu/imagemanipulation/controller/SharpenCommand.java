package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.impl.GuiModelInteface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;

import java.io.IOException;

/**
 * A command that applies a sharpen filter to an image and stores the result in the model.
 */
public class SharpenCommand extends AbstractCommand implements CommandInterface {

  /**
   * Constructs a SharpenCommand with the given view and model.
   *
   * @param view the view component
   * @param model the model component
   */
  public SharpenCommand(AdvancedViewInterface view, GuiModelInteface model) {
    super(view, model);
  }

  @Override
  public void execute(String[] args) throws IOException {
    view.displaySharpenStatus();
    if (!model.containsImages(args[1])) {
      view.displayImageDoesntExist();
    }
    ImageInterface result_image = model.sharpen(model.getImages(args[1]));
    model.storeImages(args[2], result_image);
  }
}
