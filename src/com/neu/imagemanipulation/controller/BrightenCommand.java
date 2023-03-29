package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;

import java.io.IOException;

public class BrightenCommand extends AbstractCommand implements CommandInterface{
  public BrightenCommand(AdvancedViewInterface view, AdvancedControllerInterface controller, AdvancedImageManipulationInterface model) {
    super(view, controller, model);
  }

  @Override
  public void execute(String[] args) throws IOException {
    if (Integer.parseInt(args[1]) < 0) {
      view.displayInvalidValue();
    }
    if (!model.containsImages(args[2])) {
      view.displayImageDoesntExist();
    }
    view.displayBrightenStatus();
    ImageInterface result_image = model.brightenImage(model.getImages(args[2]), Integer.parseInt(args[1]));
    model.storeImages(args[3], result_image);

  }
}
