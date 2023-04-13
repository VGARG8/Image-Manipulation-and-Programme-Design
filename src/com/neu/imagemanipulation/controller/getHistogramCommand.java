package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;
import com.neu.imagemanipulation.model.impl.GuiModelInteface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class getHistogramCommand extends AbstractCommand implements CommandInterface{
  public getHistogramCommand(AdvancedViewInterface view, GuiModelInteface model) {
    super(view, model);
  }

  @Override
  public void execute(String[] args) throws IOException {
    view.displayHistogramStatus();
    if (!model.containsImages(args[1])) {
      view.displayImageDoesntExist();
    }
    BufferedImage result_image = model.getHistogramImage(model.getImages(args[1]), args[2]);
    model.storeBufferImages("bufferedimg", result_image);
  }
}
