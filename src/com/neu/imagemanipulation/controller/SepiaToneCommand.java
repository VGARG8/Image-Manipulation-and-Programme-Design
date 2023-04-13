package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;
import com.neu.imagemanipulation.model.impl.GuiModelInteface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;

import java.io.IOException;

public class SepiaToneCommand extends AbstractCommand implements CommandInterface{
  public SepiaToneCommand(AdvancedViewInterface view,  GuiModelInteface model) {
    super(view,  model);
  }

  @Override
  public void execute(String[] args) throws IOException {
    view.displaySepiaStatus();
    if (!model.containsImages(args[1])) {
      view.displayImageDoesntExist();
    }
    ImageInterface result_image = model.sepiaTone(model.getImages(args[1]));
    model.storeImages(args[2], result_image);
  }
}
