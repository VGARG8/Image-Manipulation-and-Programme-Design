package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;

import java.io.IOException;

public class GreyScaleToneCommand extends AbstractCommand implements CommandInterface{
  public GreyScaleToneCommand(AdvancedViewInterface view, AdvancedControllerInterface controller, AdvancedImageManipulationInterface model) {
    super(view, controller, model);
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
