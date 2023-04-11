package com.neu.imagemanipulation.view;

import com.neu.imagemanipulation.controller.AdvancedController;
import com.neu.imagemanipulation.controller.AdvancedControllerInterface;
import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.*;

public class AdvancedViewGui extends JFrame {
  private Panes panel1;
  private Panes panel2;
  private Panes panel3;
  private Panes panel4;
  private Panes mainPanel;
  private JFileChooser chooser;
  private JButton fileChooserButton;
  private JButton loadButton;
  private String commandString;
  private  JTextField referenceName;
  private String filePath;
  private JButton File;

  private ImageInterface refImg;
  private ImageInterface refImg1;



  public AdvancedViewGui() throws HeadlessException {
    super();
    setTitle("Image Manipulation Tool");
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    panel1 = new Panes(true);
    panel2 = new Panes(true);
    panel3 = new Panes(true);
    panel4 = new Panes(true);
    mainPanel = new Panes(true);
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    panel1.setLayout(new FlowLayout());
    panel2.setLayout(new FlowLayout());
    panel3.setLayout(new BoxLayout(panel3,BoxLayout.Y_AXIS));
    panel4.setLayout(new BoxLayout(panel4,BoxLayout.Y_AXIS));
    mainPanel.add(panel1);
    mainPanel.add(panel2);
    panel2.add(panel3);
    panel2.add(panel4);
    add(mainPanel);
    setPanel1();
    setPanel2();
    pack();
  }

  private String chooseFile(JFileChooser chooser) {
    chooser = new JFileChooser();
    int returnVal = chooser.showOpenDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      return chooser.getSelectedFile().getPath();
    }
    return null;
  }
  private void setPanel1(){
    fileChooserButton = new JButton("File Path");
    fileChooserButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        filePath = chooseFile(chooser);
      }
    });

    loadButton = new JButton("Load");


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

    loadButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        AdvancedImageManipulationModel model = new AdvancedImageManipulationModel();
        AdvancedViewInterface view = new AdvancedView();
        commandString = "load " + filePath + " "+ referenceName.getText();
        AdvancedControllerInterface controller = new AdvancedController(new InputStreamReader(System.in), System.out, model, view);
//        try {
//          controller.runCommand("load Res/face.png face");
//          refImg = model.getImages("face");
//          refImg1 = refImg;
//        } catch (IOException ex) {
//          throw new RuntimeException(ex);
//        }
        try {
          controller.runCommand(commandString);
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        System.out.println(model.containsImages(referenceName.getText()));
        referenceName.setForeground(Color.GRAY);
        referenceName.setText("Enter the image name for reference");
      }
    });


    panel1.add(fileChooserButton);
    panel1.add(referenceName);
    panel1.add(loadButton);
  }

  private void setPanel2(){
    setPanel3();
    setPanel4();

  }

  private void setPanel4() {
    JButton sampleButton = new JButton("Sample Button");
    panel3.add(sampleButton);
  }

  private void setPanel3() {
    AdvancedImageManipulationModel model = new AdvancedImageManipulationModel();
    AdvancedViewInterface view = new AdvancedView();
    commandString = "load " + filePath + " "+ referenceName.getText();
    AdvancedControllerInterface controller = new AdvancedController(new InputStreamReader(System.in), System.out, model, view);
    try {
      controller.runCommand("load Res/face.png face");
      refImg = model.getImages("face");

    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
    BufferedImage bufferedImage = ImageConverter.convert(refImg);
    ImageIcon imageIcon = new ImageIcon(bufferedImage);
    JLabel label = new JLabel(imageIcon);
    panel3.add(label);

  }


}
