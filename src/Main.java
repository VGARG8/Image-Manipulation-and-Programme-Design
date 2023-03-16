import com.neu.imageManipulation.controller.Controller;
import com.neu.imageManipulation.controller.ControllerInterface;
import com.neu.imageManipulation.model.impl.ImageManipulationInterface;
import com.neu.imageManipulation.model.impl.ImageManipulationModel;
import com.neu.imageManipulation.view.View;
import com.neu.imageManipulation.view.ViewInterface;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The Main class represents the entry point of the Image Manipulation program. This class
 * initializes the Model, View, and Controller, and hands over the control to the Controller.
 *
 * @author [Girish Kumar Adari] [Vaibhav Garg]
 */
public class Main {
  /**
   * The main method is the entry point of the program.It initializes the ImageManipulationModel
   * and View, creates the Controller object, and passes the control to the Controller.
   *
   * @param args the command-line arguments passed to the program
   * @throws IOException if an I/O error occurs
   */
  public static void main(String[] args) throws IOException {

    ImageManipulationInterface model = new ImageManipulationModel(); //set up before if needed
    ViewInterface view = new View();
    //setup details of view if needed

    //create controller, give it the model and view
    ControllerInterface controller = new Controller(new InputStreamReader(System.in), System.out,
            model, view);

    //give control to the controller. Controller relinquishes only when program ends
    controller.execute();
  }
}
