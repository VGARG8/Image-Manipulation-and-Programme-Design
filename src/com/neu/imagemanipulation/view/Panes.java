package com.neu.imagemanipulation.view;

import com.neu.imagemanipulation.controller.GuiControllerInterface;

import java.awt.*;

import javax.swing.*;


public class Panes extends JPanel {
  private  GuiControllerInterface guiController;

  public Panes(Boolean visibility, GuiControllerInterface guiController) {
    super();
    setVisible(visibility);
    setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    this.guiController = guiController;
  }


}
