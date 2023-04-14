package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.impl.GuiModelInteface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * GetHistogramCommand is a command class that extends AbstractCommand and implements the
 * CommandInterface.
 * It is used to generate a histogram for a given image and display it in the view.
 */
public class GetHistogramCommand extends AbstractCommand implements CommandInterface {


  /**
   * GetHistogramCommand constructor initializes the view and model using the superclass
   * constructor.
   *
   * @param view  an instance of AdvancedViewInterface to interact with the view.
   * @param model an instance of GuiModelInterface to interact with the model.
   */
  public GetHistogramCommand(AdvancedViewInterface view, GuiModelInteface model) {
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
