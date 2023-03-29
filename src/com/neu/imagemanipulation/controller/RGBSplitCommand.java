package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;

import java.io.IOException;

public class RGBSplitCommand extends AbstractCommand implements CommandInterface{
  public RGBSplitCommand(AdvancedViewInterface view, AdvancedControllerInterface controller, AdvancedImageManipulationInterface model) {
    super(view, controller, model);
  }

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
