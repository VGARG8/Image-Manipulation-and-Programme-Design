package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.entity.Image;
import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.entity.Pixel;
import com.neu.imagemanipulation.model.entity.PixelInterface;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;
import com.neu.imagemanipulation.model.impl.ImageManipulationInterface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;
import com.neu.imagemanipulation.view.ViewInterface;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Scanner;


/**
 * Controller class implements ControllerInterface. It interacts with the
 * user and controls the model and view.
 */
public class Controller implements ControllerInterface {
  protected Boolean flag;
  protected AdvancedViewInterface view;
  protected AdvancedImageManipulationInterface model;
  final Readable in;
  private final Appendable out;

  /**
   * Constructs a new Controller object with the specified input and output streams, model, and
   * view.
   *
   * @param in    the input stream to read from
   * @param out   the output stream to write to
   * @param model the ImageManipulationInterface model to use for image manipulation operations
   * @param view  the ViewInterface view to use for displaying the manipulated images
   * @throws NullPointerException if the model parameter is null
   */
  public Controller(Readable in, Appendable out, AdvancedImageManipulationInterface model,
                    AdvancedViewInterface view) {
    Objects.requireNonNull(model);
    this.flag = true;
    this.view = view;
    this.model = model;
    this.in = in;
    this.out = out;
  }

  public void runCommand(CommandInterface command, String[] args) throws IOException{
    command.execute(args);
  }

  @Override
  public ImageManipulationInterface getModel() {
    return this.model;
  }

  @Override
  public ViewInterface getView() {
    return this.view;
  }


  protected  String getFileExtension(String filename) {
    Path path = Path.of(filename);
    String extension = "";
    if (path != null) {
      String name = path.getFileName().toString();
      int dotIndex = name.lastIndexOf(".");
      if (dotIndex > 0 && dotIndex < name.length() - 1) {
        extension = name.substring(dotIndex + 1);
      }
    }
    return extension;
  }


  private enum ImageType {
    Red,
    Green,
    Blue,
    Luma,
    Intensity,
    Value
  }
}
