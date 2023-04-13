package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;
import com.neu.imagemanipulation.model.impl.GuiModelInteface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;

import java.io.IOException;

public class RGBCombineCommand extends AbstractCommand implements CommandInterface{
  public RGBCombineCommand(AdvancedViewInterface view,  GuiModelInteface model) {
    super(view, model);
  }

  @Override
  public void execute(String[] args) throws IOException {
    if (!model.containsImages(args[3])) {
      view.displayImageDoesntExist();
    }
    view.displayRGBCombineStatus();
    ImageInterface[] rgb_images = {model.getImages(args[2]), model.getImages(args[3]),
            model.getImages(args[4])};
    ImageInterface result_image = model.combineRGBImages(rgb_images);
    model.storeImages(args[1], result_image);
  }
}
