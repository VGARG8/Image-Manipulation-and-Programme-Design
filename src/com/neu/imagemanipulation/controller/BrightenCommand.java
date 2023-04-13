package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.entity.Image;
import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;
import com.neu.imagemanipulation.model.impl.GuiModelInteface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;

import java.io.IOException;

public class BrightenCommand extends AbstractCommand implements CommandInterface{
  public BrightenCommand(AdvancedViewInterface view, GuiModelInteface model) {
    super(view, model);
  }

  @Override
  public void execute(String[] args) throws IOException {
    if (Integer.parseInt(args[1]) < 0) {
      view.displayInvalidValue();
    }
    if (!model.containsImages(args[2])) {
      view.displayImageDoesntExist();
    }
    ImageInterface trail =  model.getImages(args[2]);
    System.out.println(trail);

    ImageInterface result_image = model.brightenImage(model.getImages(args[2]),
            Integer.parseInt(args[1]));
    view.displayBrightenStatus();
    model.storeImages(args[3], result_image);
  }
}
