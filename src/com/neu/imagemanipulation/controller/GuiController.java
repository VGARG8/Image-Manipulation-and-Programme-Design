package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;
import com.neu.imagemanipulation.view.ViewGuiInterface;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class GuiController implements GuiControllerInterface{
  private ViewGuiInterface view;
  private AdvancedImageManipulationInterface model;
  Map<String, CommandInterface> commands;

  public GuiController(AdvancedImageManipulationInterface model) {
  this.model = model;
  }
  @Override
  public void setView(ViewGuiInterface view) {
    this.view = view;
    view.addFeatures(this);
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
    commands.put("default", new DefaultCommand(view,  model));
  }

  private Set<String> getCommandKeys() {
    Set<String> keys = new LinkedHashSet<>(commands.keySet());
    keys.remove("save");
    keys.remove("load");
    keys.remove("exit");
    keys.remove("default");
    return keys;
  }

  private void runCommand(String commandLine) throws IOException {
    String[] tokens = commandLine.trim().split("\\s+");
    CommandInterface command = commands.get(tokens[0].toLowerCase());
    if (command == null) {
      command = new DefaultCommand(view,  model);
    }
    command.execute(tokens);
  }





}
