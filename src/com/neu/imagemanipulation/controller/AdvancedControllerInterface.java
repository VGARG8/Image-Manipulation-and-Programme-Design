package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.entity.Image;

import java.io.IOException;

public interface AdvancedControllerInterface extends ControllerInterface {
  Image loadStandardFormat(String filename) throws IOException;

  void generateImage(Image image, String filename);

  void callViewforMain() throws IOException;
}
