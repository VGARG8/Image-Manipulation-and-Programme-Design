package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;

import java.io.IOException;

public class BlurCommand extends AbstractCommand implements CommandInterface{
  public BlurCommand(AdvancedViewInterface view,  AdvancedImageManipulationInterface model) {
    super(view,  model);
  }

  @Override
  public void execute(String[] args) throws IOException {
    view.displayBlurStatus();
    if (!model.containsImages(args[1])) {
      view.displayImageDoesntExist();
    }
    ImageInterface result_image = model.blur(model.getImages(args[1]));
    model.storeImages(args[2], result_image);
  }
}
