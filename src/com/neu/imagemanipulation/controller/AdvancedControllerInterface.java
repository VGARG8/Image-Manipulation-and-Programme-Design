package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.entity.ImageInterface;

import java.io.IOException;

public interface AdvancedControllerInterface extends ControllerInterface {
//  ImageInterface loadStandardFormat(String filename) throws IOException;

//  void generateImage(ImageInterface image, String filename);

  void callViewForMain() throws IOException;

  void runCommand(String commandLine) throws IOException;

  void execute() throws IOException;

  void setExitFlag(boolean exitFlag);
}
