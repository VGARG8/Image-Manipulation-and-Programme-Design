package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;

public abstract class AbstractCommand {

  protected AdvancedViewInterface view;

  protected AdvancedImageManipulationInterface model;


  public AbstractCommand(AdvancedViewInterface view,
                         AdvancedImageManipulationInterface model) {
    this.view = view;
    this.model = model;
  }
}
