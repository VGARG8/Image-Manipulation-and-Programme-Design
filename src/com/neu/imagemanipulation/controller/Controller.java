package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.impl.GuiModelInteface;
import com.neu.imagemanipulation.model.impl.ImageManipulationInterface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;

import java.nio.file.Path;
import java.util.Objects;


/**
 * Controller class implements ControllerInterface. It interacts with the
 * user and controls the model and view.
 */
public class Controller implements ControllerInterface {

  protected Boolean flag;
  protected AdvancedViewInterface view;
  protected GuiModelInteface model;
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
  public Controller(Readable in, Appendable out, GuiModelInteface model,
                    AdvancedViewInterface view) {
    Objects.requireNonNull(model);
    this.flag = true;
    this.view = view;
    this.model = model;
    this.in = in;
    this.out = out;
  }



  @Override
  public ImageManipulationInterface getModel() {
    return this.model;
  }

  @Override
  public AdvancedViewInterface getView() {
    return this.view;
  }

// used across the classes to get the file extensions
  protected String getFileExtension(String filename) {
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


}
