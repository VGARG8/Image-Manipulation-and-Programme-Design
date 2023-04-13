package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;

import java.io.IOException;

public class VerticalFlipCommand extends AbstractCommand implements CommandInterface  {

  public VerticalFlipCommand(AdvancedViewInterface view, AdvancedImageManipulationInterface model) {
    super(view, model);
  }



  @Override
  public void execute(String[] args) throws IOException {
    if (!model.containsImages(args[1])) {
      view.displayImageDoesntExist();
      return;
    }
    view.displayVerticalFlipStatus();
    ImageInterface result_image = model.flipImageVertically(model.getImages(args[1]));
    model.storeImages(args[2], result_image);
  }
}