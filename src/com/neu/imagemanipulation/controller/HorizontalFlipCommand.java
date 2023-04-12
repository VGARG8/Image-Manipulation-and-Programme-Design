package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;

import java.io.IOException;

public class HorizontalFlipCommand extends AbstractCommand implements CommandInterface{
  public HorizontalFlipCommand(AdvancedViewInterface view,  AdvancedImageManipulationInterface model) {
    super(view, model);
  }

  @Override
  public void execute(String[] args) throws IOException {
    view.displayHorizontalFlipStatus();
    if (!model.containsImages(args[1])) {
      view.displayImageDoesntExist();
    }
    ImageInterface result_image = model.flipImageHorizontally(model.getImages(args[1]));
    model.storeImages(args[2], result_image);
  }
}
