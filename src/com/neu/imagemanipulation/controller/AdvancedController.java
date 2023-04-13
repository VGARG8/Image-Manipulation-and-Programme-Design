package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.Constants;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The AdvancedController class implements the AdvancedControllerInterface and extends the
 * Controller abstract class. It provides an implementation for executing advanced image
 * manipulation commands using the AdvancedImageManipulationInterface model and the
 * AdvancedViewInterface view.
 */
public class AdvancedController extends Controller implements AdvancedControllerInterface {

  Map<String, CommandInterface> commands;
  private boolean flag = true;

  /**
   * Constructs a new Controller object with the specified input and output streams, model, and
   * view.
   *
   * @param in    the input stream to read from
   * @param out   the output stream to write to
   * @param model the ImageManipulationInterface model to use for image manipulation operations
   * @param view  the ViewInterface view to use for displaying the manipulated images
   * @throws NullPointerException if the model parameter is null
   */
  public AdvancedController(Readable in, Appendable out, AdvancedImageManipulationInterface model,
      AdvancedViewInterface view) {
    super(in, out, model, view);
    initializeCommands();
  }

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
    commands.put(Constants.RUN_SCRIPT, new RunScriptCommand(view, this, model));
    commands.put(Constants.DEFAULT, new DefaultCommand(view, model));
    commands.put(Constants.EXIT, new ExitCommand(view, this, model));
  }

  /**
   * Displays a message to the user indicating that a file has not been specified.
   *
   * @throws IOException if an I/O error occurs
   */
  @Override
  public void callViewForMain() throws IOException {
    view.displayFileNotSpecified();
  }

  /**
   * Parses the input command line and executes the corresponding CommandInterface object.
   *
   * @param commandLine the input command line
   * @throws IOException if an I/O error occurs
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
   * Executes the commands entered by the user by getting input from the Scanner object and calling
   * the corresponding command's execute method.
   *
   * @throws IOException if there is an error reading the input
   */

  @Override
  public void execute() throws IOException {
    Scanner sc = new Scanner(this.in);
    while (flag) {
      view.getCommand();
      String input = sc.nextLine().trim();
      String[] tokens = input.split("\\s+");
      CommandInterface command = commands.get(tokens[0]);
      if (command == null) {
        command = new DefaultCommand(view, model);
      }

      command.execute(tokens);
    }
  }

  /**
   * Sets the value of the flag that controls whether the execute method continues to loop.
   *
   * @param exitFlag the new value of the flag
   */
  @Override
  public void setExitFlag(boolean exitFlag) {
    this.flag = !exitFlag;
  }

//  public Set<String> getCommandKeys() {
//    Set<String> keys = new LinkedHashSet<>(commands.keySet());
//    keys.remove(Constants.SAVE);
//    keys.remove(Constants.LOAD);
//    keys.remove(Constants.EXIT);
//    keys.remove("default");
//    return keys;
//  }
}
