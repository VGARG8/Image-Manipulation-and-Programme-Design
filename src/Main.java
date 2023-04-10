import com.neu.imagemanipulation.controller.AdvancedController;
import com.neu.imagemanipulation.controller.AdvancedControllerInterface;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationModel;
import com.neu.imagemanipulation.view.AdvancedView;
import com.neu.imagemanipulation.view.AdvancedViewInterface;
import com.neu.imagemanipulation.view.GuiView;

import java.io.IOException;
import java.io.InputStreamReader;


/**
 * The Main class represents the entry point of the Image Manipulation program. This class
 * initializes the Model, View, and Controller, and hands over the control to the Controller.
 *
 * @author [Girish Kumar Adari, Vaibhav Garg]
 */
public class Main {

  /**
   * The main method is the entry point of the program.It initializes the ImageManipulationModel and
   * View, creates the Controller object, and passes the control to the Controller.
   *
   * @param args the command-line arguments passed to the program
   * @throws IOException if an I/O error occurs
   */
  public static void main(String[] args) throws IOException {
    new Main().run(args);
  }

  protected void run(String[] args) throws IOException {
    AdvancedImageManipulationInterface model = new AdvancedImageManipulationModel();
    AdvancedViewInterface view = new AdvancedView();
    AdvancedViewInterface guiView = new GuiView();


    if (args.length > 0 && args[0].equals("-file")) {
      if (args.length < 2) {
        AdvancedControllerInterface controller = new AdvancedController(
                new InputStreamReader(System.in), System.out,
                model, view);
        controller.callViewForMain();
      } else {
        String filePath = args[1];
        AdvancedControllerInterface controller = new AdvancedController(
                new InputStreamReader(System.in), System.out,
                model, view);
        controller.runCommand("run-script " + filePath);
      }
    } else if (args.length > 0 && args[0].equals("-text")) {
      AdvancedControllerInterface controller = new AdvancedController(
              new InputStreamReader(System.in), System.out,
              model, view);
      controller.execute();
    } else if (args.length == 0) {
      AdvancedControllerInterface controller = new AdvancedController(
              new InputStreamReader(System.in), System.out,
              model, guiView);
      controller.execute();
    }
  }
}
