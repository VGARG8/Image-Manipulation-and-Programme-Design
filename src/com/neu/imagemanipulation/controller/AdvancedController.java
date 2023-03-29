package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AdvancedController extends Controller implements AdvancedControllerInterface {
  private Map<String, CommandInterface> commands;
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
    commands = new HashMap<>();
    commands.put("load", new LoadCommand(view, this, model));
    commands.put("save", new SaveCommand(view, this, model));
    commands.put("greyscale", new GreyScaleCommand(view, this, model));
    commands.put("horizontal-flip", new HorizontalFlipCommand(view, this, model));
    commands.put("vertical-flip", new VerticalFlipCommand(view, this, model));
    commands.put("brighten", new BrightenCommand(view, this, model));
    commands.put("darken", new DarkenCommand(view, this, model));
    commands.put("rgb-split", new RGBSplitCommand(view, this, model));
    commands.put("rgb-combine", new RGBCombineCommand(view, this, model));
    commands.put("run-script", new RunScriptCommand(view, this, model));
    commands.put("blur", new BlurCommand(view, this, model));
    commands.put("sharpen", new SharpenCommand(view, this, model));
    commands.put("dither", new DitherCommand(view, this, model));
    commands.put("greyscale-tone", new GreyScaleToneCommand(view, this, model));
    commands.put("sepia-tone", new SepiaToneCommand(view, this, model));
    commands.put("default", new DefaultCommand(view, this, model));
    commands.put("exit", new ExitCommand(view, this, model));
  }

  @Override
  public void callViewForMain() throws IOException {
    view.displayFileNotSpecified();
  }

  @Override
  public void runCommand(String commandLine) throws IOException {
    String[] tokens = commandLine.toLowerCase().trim().split("\\s+");
    CommandInterface command = commands.get(tokens[0]);
    if (command == null) {
      command = new DefaultCommand(view, this, model);
    }
    command.execute(tokens);

  }

  @Override
  public void execute() throws IOException {
    Scanner sc = new Scanner(this.in);
    while (flag) {
      view.getCommand();
      String input = sc.nextLine().trim();
      String[] tokens = input.split("\\s+");
      CommandInterface command = commands.get(tokens[0]);
      if (command == null) {
        command = new DefaultCommand(view, this, model);
      }

      command.execute(tokens);
    }
  }




  @Override
  public void setExitFlag(boolean exitFlag) {
    this.flag = !exitFlag;
  }
}
