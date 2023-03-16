package com.neu.image_manipulation.controller;

import com.neu.image_manipulation.model.entity.Image;
import com.neu.image_manipulation.model.impl.ImageManipulationInterface;
import com.neu.image_manipulation.view.ViewInterface;

import java.io.IOException;

public interface ControllerInterface {

  void go() throws IOException;

  Image loadImageInPPM(String filename) throws IOException;

  void generateImage(String filename, Image image);

  void savePPM(String filename, Image image) throws IOException;

  void runCommand(String command) throws IOException;

  ImageManipulationInterface getModel();

  ViewInterface getView();
}
