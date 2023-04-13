package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.Constants;
import com.neu.imagemanipulation.model.impl.ImageConverter;
import com.neu.imagemanipulation.model.impl.ModelGui;
import com.neu.imagemanipulation.view.ViewGuiInterface;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * The GuiController class is responsible for controlling the image editing application through user
 * interaction. It implements the GuiControllerInterface interface and uses a ModelGui instance to
 * store and retrieve images and a ViewGuiInterface instance to display the images and receive user
 * input.
 */
public class GuiController implements GuiControllerInterface {

  private BufferedImage img;
  private ViewGuiInterface view;
  private ModelGui model;
  private Map<String, CommandInterface> commands;
  private Set<String> keys;

  /**
   * Constructs a new GuiController object with the given ModelGui object.
   *
   * @param model The ModelGui object used to store and retrieve images.
   */
  public GuiController(ModelGui model) {
    this.model = model;

  }

  /**
   * Sets the ViewGuiInterface object used to display images and receive user input. Also
   * initializes the available commands.
   *
   * @param view The ViewGuiInterface object used to display images and receive user input.
   */
  @Override
  public void setView(ViewGuiInterface view) {
    this.view = view;
    view.addFeatures(this);
    initializeCommands();
  }

  /**
   * Initializes the available commands and adds them to the commands Map.
   */
  private void initializeCommands() {
    commands = new LinkedHashMap<>();
    commands.put(Constants.LOAD, new LoadCommand(view, model));
    commands.put(Constants.SAVE, new SaveCommand(view, model));
    commands.put(Constants.HORIZONTAL_FLIP, new HorizontalFlipCommand(view, model));
    commands.put(Constants.VERTICAL_FLIP, new VerticalFlipCommand(view, model));
    commands.put(Constants.BRIGHTEN, new BrightenCommand(view, model));
    commands.put(Constants.DARKEN, new DarkenCommand(view, model));
    commands.put(Constants.GREYSCALE, new GreyScaleCommand(view, model));
    commands.put(Constants.BLUR, new BlurCommand(view, model));
    commands.put(Constants.SHARPEN, new SharpenCommand(view, model));
    commands.put(Constants.DITHER, new DitherCommand(view, model));
    commands.put(Constants.GREYSCALE_TONE, new GreyScaleToneCommand(view, model));
    commands.put(Constants.SEPIA_TONE, new SepiaToneCommand(view, model));
    commands.put(Constants.RGB_SPLIT, new RGBSplitCommand(view, model));
    commands.put(Constants.RGB_COMBINE, new RGBCombineCommand(view, model));
    commands.put(Constants.DEFAULT, new DefaultCommand(view, model));
  }

  /**
   * Returns the Set of command names used to filter the available commands when displaying them to
   * the user.
   *
   * @return The Set of command names used to filter the available commands when displaying them to
   * the user.
   */
  @Override
  public Set<String> getCommandKeys() {
    Set<String> keys = new LinkedHashSet<>(commands.keySet());
    keys.remove(Constants.SAVE);
    keys.remove(Constants.LOAD);
    keys.remove(Constants.EXIT);
    keys.remove(Constants.RGB_COMBINE);
    keys.remove(Constants.RGB_SPLIT);
    keys.remove(Constants.EXIT);
    keys.remove(Constants.DEFAULT);
    return keys;
  }

  /**
   * Executes a command based on the given command line input.
   *
   * @param commandLine the command line input string
   * @throws IOException if there is an error while executing the command
   */
  @Override
  public void runCommand(String commandLine) throws IOException {
    String[] tokens = commandLine.trim().split("\\s+");
    CommandInterface command = commands.get(tokens[0].toLowerCase());
    if (command == null) {
      command = new DefaultCommand(view, model);
    }
    command.execute(tokens);
  }

  /**
   * Returns a set of image keys stored in the model.
   *
   * @return a set of image keys stored in the model
   */
  @Override
  public Set<String> getKeys() {
    return model.getStoredImageNames();
  }

  /**
   * Returns a BufferedImage object corresponding to the specified image name.
   *
   * @param imageName the name of the image
   * @return the BufferedImage object corresponding to the specified image name
   */

  @Override
  public BufferedImage getImage(String imageName) {
    BufferedImage bufferedImage = ImageConverter.convert(model.getImages(imageName));

    return bufferedImage;
  }


}
