package com.neu.imagemanipulation.view;

/**
 * This class represents a load panel of view.
 */

public class LoadPanel {
//  private JButton loadButton;
//  private JButton openFileButton;
//  private JTextField referenceLoadName;
//  private String filePath;
//  private String commandString;
//
//  public LoadPanel() {
//    this.setLayout(new FlowLayout());
//
//    this.setVisible(true);
//    commandString = "load ";
//    openFileButton = new JButton("Select File");
//    openFileButton.setActionCommand("Select File");
//    openFileButton.addActionListener(this);
//
//    loadButton = new JButton("Load");
//    loadButton.setActionCommand("Load");
//    loadButton.addActionListener(this);
//
//
//    referenceLoadName = new JTextField();
//    referenceLoadName.setForeground(Color.GRAY);
//    referenceLoadName.setText("Enter the image name for reference");
//
//    referenceLoadName.addFocusListener(new FocusAdapter() {
//      @Override
//      public void focusGained(FocusEvent e) {
//        if (referenceLoadName.getText().equals("Enter the image name for reference")) {
//          referenceLoadName.setText("");
//          referenceLoadName.setForeground(Color.BLACK);
//        }
//      }
//
//      @Override
//      public void focusLost(FocusEvent e) {
//        if (referenceLoadName.getText().isEmpty()) {
//          referenceLoadName.setForeground(Color.GRAY);
//          referenceLoadName.setText("Enter the image name for reference");
//        }
//      }
//    });
//
//
//
//    add(openFileButton);
//    add(referenceLoadName);
//    add(loadButton);
//
//  }
//
//  private String chooseFile() {
//    JFileChooser chooser = new JFileChooser();
//    int returnVal = chooser.showOpenDialog(this);
//    if (returnVal == JFileChooser.APPROVE_OPTION) {
//      return chooser.getSelectedFile().getAbsolutePath();
//    }
//    return null;
//  }
//
//  @Override
//  public void actionPerformed(ActionEvent e) {
//    switch (e.getActionCommand()) {
//     case "Select File":
//       filePath = chooseFile();
//       commandString = commandString + filePath;
//        break;
//      case "Load":
//        commandString = commandString + " " +referenceLoadName.getText();
//    }
//
//  }

//  @Override
//  public void addFeature(GuiControllerInterface guiController) throws IOException {
//    guiController.runCommand(commandString);
//
//  }
}
