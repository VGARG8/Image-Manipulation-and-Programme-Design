package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;
import com.neu.imagemanipulation.model.impl.GuiModelInteface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;

/**
 This abstract class provides the basic structure for commands that manipulate images
 using an implementation of the AdvancedImageManipulationInterface. It requires an implementation
 of the AdvancedViewInterface for the user interface components.
 */
public abstract class AbstractCommand {

  protected AdvancedViewInterface view;

  protected GuiModelInteface model;


  public AbstractCommand(AdvancedViewInterface view,
                         GuiModelInteface model) {
    this.view = view;
    this.model = model;
  }
}
