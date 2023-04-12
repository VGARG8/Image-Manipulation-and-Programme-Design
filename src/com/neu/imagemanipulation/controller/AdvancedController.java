package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

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
    commands.put("load", new LoadCommand(view,  model));
    commands.put("save", new SaveCommand(view,  model));
    commands.put("horizontal-flip", new HorizontalFlipCommand(view,  model));
    commands.put("vertical-flip", new VerticalFlipCommand(view,  model));
    commands.put("brighten", new BrightenCommand(view,  model));
    commands.put("darken", new DarkenCommand(view,  model));
    commands.put("greyscale", new GreyScaleCommand(view,  model));
    commands.put("blur", new BlurCommand(view, model));
    commands.put("sharpen", new SharpenCommand(view,  model));
    commands.put("dither", new DitherCommand(view,  model));
    commands.put("greyscale-tone", new GreyScaleToneCommand(view,  model));
    commands.put("sepia-tone", new SepiaToneCommand(view,  model));
    commands.put("rgb-split", new RGBSplitCommand(view, model));
    commands.put("rgb-combine", new RGBCombineCommand(view,  model));
    commands.put("run-script", new RunScriptCommand(view, this, model));
    commands.put("default", new DefaultCommand(view,  model));
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
      command = new DefaultCommand(view,  model);
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
        command = new DefaultCommand(view,  model);
      }

      command.execute(tokens);
    }
  }

  @Override
  public void setExitFlag(boolean exitFlag) {
    this.flag = !exitFlag;
  }

  public Set<String> getCommandKeys() {
    Set<String> keys = new LinkedHashSet<>(commands.keySet());
    keys.remove("save");
    keys.remove("load");
    keys.remove("exit");
    keys.remove("default");
    return keys;
  }
}
