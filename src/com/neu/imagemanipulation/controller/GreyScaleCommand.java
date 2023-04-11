package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;

import java.io.IOException;

public class GreyScaleCommand extends AbstractCommand implements CommandInterface{
  public GreyScaleCommand(AdvancedViewInterface view, AdvancedControllerInterface controller, AdvancedImageManipulationInterface model) {
    super(view, controller, model);
  }

  @Override
  public void execute(String[] args) throws IOException {
    ImageInterface result_image;
    switch (args[1]) {
      case "value-component":
        if (!model.containsImages(args[2])) {
          view.displayImageDoesntExist();
        }
        view.displayValueStatus();
        result_image = model.createValueComponentOfImage(model.getImages(args[2]));
        model.storeImages(args[3], result_image);
        break;
      case "luma-component":
        if (!model.containsImages(args[2])) {
          view.displayImageDoesntExist();
        }
        view.displayLumaStatus();
        result_image = model.createLumaComponentOfImage(model.getImages(args[2]));
        model.storeImages(args[3], result_image);
        break;
      case "intensity-component":
        if (!model.containsImages(args[2])) {
          view.displayImageDoesntExist();
        }
        view.displayIntensityStatus();
        result_image = model.createIntensityComponentOfImage(model.getImages(args[2]));
        model.storeImages(args[3], result_image);
        break;
      case "red-component":
        if (!model.containsImages(args[2])) {
          view.displayImageDoesntExist();
        }
        view.displayRedComponentStatus();
        result_image = model.createRedComponentOfImage(model.getImages(args[2]));
        model.storeImages(args[3], result_image);
        break;
      case "green-component":
        if (!model.containsImages(args[2])) {
          view.displayImageDoesntExist();
        }
        view.displayGreenComponentStatus();
        result_image = model.createGreenComponentOfImage(model.getImages(args[2]));
        model.storeImages(args[3], result_image);
        break;
      case "blue-component":
        if (!model.containsImages(args[2])) {
          view.displayImageDoesntExist();
        }
        view.displayBlueComponentStatus();
        result_image = model.createBlueComponentOfImage(model.getImages(args[2]));
        model.storeImages(args[3], result_image);
        break;
    }
  }
}
