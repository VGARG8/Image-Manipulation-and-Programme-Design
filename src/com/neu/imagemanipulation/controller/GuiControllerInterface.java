package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.view.ViewGuiInterface;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Set;

public interface GuiControllerInterface {
  void setView(ViewGuiInterface v);

  Set<String> getCommandKeys();

  void runCommand(String commandLine) throws IOException;

  Set<String> getKeys();

  BufferedImage getImage(String face);
}
