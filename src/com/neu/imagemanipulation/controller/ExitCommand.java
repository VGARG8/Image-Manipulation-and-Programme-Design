package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;

import java.io.IOException;

public class ExitCommand extends AbstractCommand implements CommandInterface{
  private AdvancedControllerInterface controller;
  public ExitCommand(AdvancedViewInterface view, AdvancedControllerInterface controller, AdvancedImageManipulationInterface model) {
    super(view, model);
    this.controller = controller;
  }

  @Override
  public void execute(String[] args) throws IOException {
    controller.setExitFlag(true);
  }
}
