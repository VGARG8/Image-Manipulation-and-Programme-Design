import com.neu.image_manipulation.controller.Controller;
import com.neu.image_manipulation.controller.ControllerInterface;
import com.neu.image_manipulation.model.impl.ImageManipulationInterface;
import com.neu.image_manipulation.model.impl.ImageManipulationModel;
import com.neu.image_manipulation.view.View;
import com.neu.image_manipulation.view.ViewInterface;

import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException {

    ImageManipulationInterface model = new ImageManipulationModel(); //set up before if needed
    ViewInterface view = new View();
    //setup details of view if needed

    //create controller, give it the model and view
    ControllerInterface controller = new Controller(model, view);

    //give control to the controller. Controller relinquishes only when program ends
    controller.go();
  }
}
