package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;

public abstract class AbstractCommand {

  protected AdvancedViewInterface view;
  protected AdvancedControllerInterface controller;
  protected AdvancedImageManipulationInterface model;


  public AbstractCommand(AdvancedViewInterface view, AdvancedControllerInterface controller,
                         AdvancedImageManipulationInterface model) {
    this.view = view;
    this.controller = controller;
    this.model = model;
  }
}
