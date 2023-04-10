package com.neu.imagemanipulation.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

public class MainPanel extends Panel {
  private final JLabel label;
  private JComboBox<String> jComboBox;
  private JButton selectButton;
  private String commandString;
  private String selectedoption;

  public MainPanel() {
    this.label = new JLabel("Select an action: ");
    String[] optionsToChoose = {"load", "save", "horizontal-flip", "vertical-flip",
            "greyscale-value", "greyscale-luma", "greyscale-intensity","greyscale-red",
            "greyscale-blue", "greyscale-green", "brighten", "darken", "rgb split",
            "rgb-combine", "run script", "blur", "sharpen", "dither", "greyscale-tone",
            "sepia-tone"};
    jComboBox = new JComboBox<>(optionsToChoose);
    selectButton = new JButton("select");


    this.add(label);
    this.add(jComboBox);
    this.add(selectButton);
    this.setVisible(true);

  }

  private void getCommandString(){
    selectedoption = jComboBox.getItemAt(jComboBox.getSelectedIndex());
    generateDialogBox(selectedoption);
  }

  private void generateFields(JDialog dialog,String command){
    int result;
    JFileChooser fileChooser;
    JLabel filepath;
    dialog.setLayout(new FlowLayout());
    dialog.setTitle("Pass Arguments");
    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);



    switch(command){
      case "save":
      case "load":
        filepath = new JLabel("File Path");
        fileChooser = new JFileChooser();
        result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
          File selectedFile = fileChooser.getSelectedFile();
          String filePath = selectedFile.getAbsolutePath();
          commandString = commandString + " " + filePath;
        }

        JLabel referenceNameLabel = new JLabel("Reference Name");
        JTextField referenceNameField = new JTextField();
        commandString = commandString + " "+referenceNameField.getText();

        dialog.add(filepath);
        dialog.add(fileChooser);
        dialog.add(referenceNameLabel);
        dialog.add(referenceNameField);

        break;
      case "blur":
      case "sepia-tone":
      case "dither":
      case "sharpen":
      case "horizontal-flip":
      case "vertical-flip":
      case "greyscale-value":
      case "greyscale-luma":
      case "greyscale-intensity":
      case "greyscale-red":
      case "greyscale-blue":
      case "greyscale-green":
      case "brighten":
      case "darken":
      case "greyscale-tone":

        JLabel workingImageLabel = new JLabel("Image to be worked on");
        JTextField workingImageField = new JTextField();
        JLabel newImageLabel = new JLabel("Image to be worked on");
        JTextField newImageField = new JTextField();
        commandString = commandString + " " + workingImageField.getText() + " "
                + newImageField.getText();

        dialog.add(workingImageLabel);
        dialog.add(workingImageField);
        dialog.add(newImageLabel);
        dialog.add(newImageField);
        break;

      case "rgb split":
      case "rgb-combine":

        break;
      case "run-script":
        filepath = new JLabel("File Path");
        fileChooser = new JFileChooser();
        result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
          File selectedFile = fileChooser.getSelectedFile();
          String filePath = selectedFile.getAbsolutePath();
          commandString = commandString + " " + filePath;
        }
        dialog.add(filepath);
        dialog.add(fileChooser);

        break;
      default:
        // handle invalid command
        break;
    }
    dialog.pack();


  }

  private void generateDialogBox(String command) {
    selectButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JDialog dialog = new JDialog();
        dialog.setLayout(new FlowLayout());
        dialog.setTitle("Enter Details");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        generateFields(dialog,command);

//
//
//        okButton.addActionListener(new ActionListener() {
//          @Override
//          public void actionPerformed(ActionEvent e) {
//            // Do something with the name and file path entered
//            String name = nameTextField.getText();
//            String filePath = fileTextField.getText();
//            System.out.println("Name: " + name);
//            System.out.println("File Path: " + filePath);
//
//            dialog.dispose();
//          }
//        });
//
//        dialog.add(nameLabel);
//        dialog.add(nameTextField);
//        dialog.add(fileLabel);
//        dialog.add(fileTextField);
//        dialog.add(okButton);
//
//        dialog.setVisible(true);
      }
    });
  }


  public String getCommandStringGui() {
    return this.commandString;
  }
}
