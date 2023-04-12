package com.neu.imagemanipulation.view;


import com.neu.imagemanipulation.controller.AdvancedController;
import com.neu.imagemanipulation.controller.AdvancedControllerInterface;
import com.neu.imagemanipulation.controller.GuiController;
import com.neu.imagemanipulation.controller.GuiControllerInterface;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationModel;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;

import javax.swing.*;

public class LoadPanel extends Panes {
  private JButton fileChooserButton;
  private JTextField referenceName;
  private String filePath;

  public LoadPanel(Boolean visibility,GuiControllerInterface guiController ) {
    super(visibility,guiController);

    fileChooserButton = new JButton("Open File");
    fileChooserButton.addActionListener(e -> filePath = chooseFile());

    JButton loadButton = new JButton("Load");

    referenceName = new JTextField();
    referenceName.setForeground(Color.GRAY);
    referenceName.setText("Enter the image name for reference");

    referenceName.addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        if (referenceName.getText().equals("Enter the image name for reference")) {
          referenceName.setText("");
          referenceName.setForeground(Color.BLACK);
        }
      }

      @Override
      public void focusLost(FocusEvent e) {
        if (referenceName.getText().isEmpty()) {
          referenceName.setForeground(Color.GRAY);
          referenceName.setText("Enter the image name for reference");
        }
      }
    });

    loadButton.addActionListener(e -> {
//      AdvancedImageManipulationModel model = new AdvancedImageManipulationModel();
//      AdvancedViewInterface view = new AdvancedViewConsole();
//      commandString = "load " + filePath + " "+ referenceName.getText();
//
//      try {
//        controller.runCommand(commandString);
//      } catch (IOException ex) {
//        throw new RuntimeException(ex);
//      }
//      System.out.println(model.containsImages(referenceName.getText()));
      referenceName.setForeground(Color.GRAY);
      referenceName.setText("Enter the image name for reference");
    });

    add(fileChooserButton);
    add(referenceName);
    add(loadButton);
  }

  private String chooseFile() {
    JFileChooser chooser = new JFileChooser();
    int returnVal = chooser.showOpenDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      return chooser.getSelectedFile().getPath();
    }
    return null;
  }

}
