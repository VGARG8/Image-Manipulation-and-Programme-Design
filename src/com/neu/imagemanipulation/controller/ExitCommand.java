package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;
import java.io.IOException;

/**
 * The ExitCommand class is responsible for exiting the program when executed. It implements the
 * CommandInterface and inherits from the AbstractCommand class.
 */
public class ExitCommand extends AbstractCommand implements CommandInterface {

  private AdvancedControllerInterface controller;

  /**
   * Constructor for the ExitCommand class.
   *
   * @param view       the AdvancedViewInterface that will display the exit status
   * @param controller the AdvancedControllerInterface that controls the program flow
   * @param model      the AdvancedImageManipulationInterface that provides image manipulation
   *                   functionality
   */
  public ExitCommand(AdvancedViewInterface view, AdvancedControllerInterface controller,
      AdvancedImageManipulationInterface model) {
    super(view, model);
    this.controller = controller;
  }

  /**
   * Executes the exit command by setting the exit flag of the controller to true.
   *
   * @param args the arguments passed to the command, not used in this implementation
   * @throws IOException if an IO error occurs
   */
  @Override
  public void execute(String[] args) throws IOException {
    controller.setExitFlag(true);
  }
}
