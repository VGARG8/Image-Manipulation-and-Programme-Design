package com.neu.image_manipulation.controller;

import com.neu.image_manipulation.model.entity.Image;

import java.io.IOException;

/**
 * This interface has the blueprint of methods used to load, save, generate Images and run commands
 * to run the script given by the user.
 */
public interface ControllerInterface {

  void go() throws IOException;

  Image loadImageInPPM(String filename) throws IOException;

  void generateImage(String filename, Image image);

  void savePPM(String filename, Image image) throws IOException;

  void runCommand(String command) throws IOException;
}
