package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.impl.GuiModelInteface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;

import java.io.IOException;

/**
 * The {@code BlurCommand} class represents a command to blur an image. It implements the
 * {@code CommandInterface}.
 */
public class BlurCommand extends AbstractCommand implements CommandInterface {

  /**
   * Constructs a new {@code BlurCommand} object with the specified view and model.
   *
   * @param view  the {@code AdvancedViewInterface} object for displaying status messages
   * @param model the {@code AdvancedImageManipulationInterface} object for manipulating images
   */
  public BlurCommand(AdvancedViewInterface view, GuiModelInteface model) {
    super(view, model);
  }

  /**
   * Executes the blur command on the specified image.
   *
   * @param args args[0] is the command name, args[1] is the name of the image to be blurred,
   *             args[2] is the name to save the blurred image as.
   * @throws IOException if an I/O error occurs while executing the command.
   */
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
