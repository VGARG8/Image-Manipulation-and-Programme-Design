package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;

import java.io.IOException;

public class VerticalFlipCommand extends AbstractCommand implements CommandInterface  {

  public VerticalFlipCommand(AdvancedViewInterface view, AdvancedControllerInterface controller,
                             AdvancedImageManipulationInterface model) {
    super(view, controller, model);
  }



  @Override
  public void execute(String[] args) throws IOException {
    if (!model.containsImages(args[0])) {
      view.displayImageDoesntExist();
      return;
    }
    view.displayVerticalFlipStatus();
    ImageInterface result_image = model.flipImageVertically(model.getImages(args[0]));
    model.storeImages(args[1], result_image);
  }
}
