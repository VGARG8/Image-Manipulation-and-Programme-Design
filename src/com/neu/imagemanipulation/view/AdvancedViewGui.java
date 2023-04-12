package com.neu.imagemanipulation.view;

import com.neu.imagemanipulation.controller.AdvancedController;
import com.neu.imagemanipulation.controller.AdvancedControllerInterface;
import com.neu.imagemanipulation.controller.GuiController;
import com.neu.imagemanipulation.controller.GuiControllerInterface;
import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationModel;
import com.neu.imagemanipulation.model.impl.ModelGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;



public class AdvancedViewGui extends AdvancedView implements ViewGuiInterface{

  private GuiControllerInterface guiController;
  private final Panes panel1;
  private final Panes panel2;
  private final Panes panel3;
  private final Panes panel4;
  private JButton fileChooserButton;
  private JComboBox<String> selectImages;

  private String commandString;
  private  JTextField referenceName;
  private String filePath;
  String selectedImage;


  public AdvancedViewGui() throws HeadlessException {
    super();
    setTitle("Image Manipulation Tool");
    setLocationRelativeTo(null);
    panel1 = new Panes(true,guiController);
    panel1.setLayout(new FlowLayout());
    panel2 = new Panes(true,guiController);
    panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
    panel3 = new Panes(true,guiController);
    panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
    panel4 = new Panes(true,guiController);
    panel4.setLayout(new FlowLayout());

    setPanel1();
    setPanel2();
    setPanel3();
    setPanel4();

    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(panel1, BorderLayout.NORTH);
    getContentPane().add(panel2, BorderLayout.WEST);
    getContentPane().add(panel3, BorderLayout.CENTER);
    getContentPane().add(panel4, BorderLayout.SOUTH);

    pack();
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  private String chooseFile() {
    JFileChooser chooser = new JFileChooser();
    int returnVal = chooser.showOpenDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      return chooser.getSelectedFile().getPath();
    }
    return null;
  }
  private void setPanel1(){
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
      AdvancedImageManipulationModel model = new AdvancedImageManipulationModel();
      AdvancedViewInterface view = new AdvancedViewConsole();
      commandString = "load " + filePath + " "+ referenceName.getText();
      AdvancedControllerInterface controller = new AdvancedController(new InputStreamReader(System.in), System.out, model, view);
      try {
        controller.runCommand(commandString);
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
      System.out.println(model.containsImages(referenceName.getText()));
      referenceName.setForeground(Color.GRAY);
      referenceName.setText("Enter the image name for reference");
    });


    panel1.add(fileChooserButton);
    panel1.add(referenceName);
    panel1.add(loadButton);

  }

  private void setPanel2() {
    ModelGui model = new ModelGui();
    AdvancedView view = new AdvancedViewConsole();
    commandString = "load " + filePath + " "+ referenceName.getText();
    AdvancedControllerInterface controller = new AdvancedController(new InputStreamReader(System.in), System.out, model, view);
    ImageInterface refImg;
    try {
      controller.runCommand("load Res/face.png face");
      refImg = model.getImages("face");

    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
    BufferedImage bufferedImage = ImageConverter.convert(refImg);
    ImageIcon imageIcon = new ImageIcon(bufferedImage);
    JLabel label = new JLabel(imageIcon);

    selectImages = new JComboBox<>(model.getStoredImageNames().toArray(new String[0]));

    JButton zoomInButton = new JButton("Zoom In");
    JButton zoomOutButton = new JButton("Zoom Out");
    zoomInButton.addActionListener(e -> {
      label.setIcon(new ImageIcon(bufferedImage.getScaledInstance((int) (label.getWidth() * 1.1), -1, Image.SCALE_SMOOTH)));
    });
    zoomOutButton.addActionListener(e -> {
      label.setIcon(new ImageIcon(bufferedImage.getScaledInstance((int) (label.getWidth() * 0.9), -1, Image.SCALE_SMOOTH)));
    });

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    buttonPanel.add(zoomInButton);
    buttonPanel.add(zoomOutButton);

    JScrollPane scrollPane = new JScrollPane(label);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setPreferredSize(new Dimension(400, 400));

    panel2.add(selectImages);
    panel2.add(scrollPane);
    panel2.add(buttonPanel);
    pack();

  }

  private void setPanel3() {
    AdvancedImageManipulationModel model = new AdvancedImageManipulationModel();
    AdvancedView view = new AdvancedViewConsole();
    commandString = "load " + filePath + " "+ referenceName.getText();
    AdvancedController controller = new AdvancedController(new InputStreamReader(System.in), System.out, model, view);
    JComboBox<String> commandComboBox = new JComboBox<>(controller.getCommandKeys().toArray(new String[0]));
    panel3.add(commandComboBox);
    JComboBox<String> subCommandComboBox = new JComboBox<>();
    subCommandComboBox.addItem("value-component");
    subCommandComboBox.addItem("luma-component");
    subCommandComboBox.addItem("intensity-component");
    subCommandComboBox.addItem("red-component");
    subCommandComboBox.addItem("green-component");
    subCommandComboBox.addItem("blue-component");
    selectedImage = (String) selectImages.getSelectedItem();
    commandComboBox.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String selectedCommand = (String) commandComboBox.getSelectedItem();
        switch (selectedCommand) {
          case "greyscale":
            panel3.add(subCommandComboBox);
//            commandString = "greyscale " + (String) subCommandComboBox.getSelectedItem() + " "+ selectedImage+ " " + referenceName.getText();
//            try {
//              controller.runCommand(commandString);
//            } catch (IOException ex) {
//              throw new RuntimeException(ex);
//            }
            break;
          case "horizontal-flip":
          case "vertical-flip":
          case "dither":
          case "blur":
          case "sharpen":
          case "greyscale-tone":
          case "sepia-tone":referenceName = new JTextField();
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

          case "brighten":
          case "darken":
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

          case "rgb-split":
            break;
          case "rgb-combine":

          case "run-script":

          default:
            panel3.remove(subCommandComboBox);



        }

        panel3.revalidate();
        panel3.repaint();
      }
    });


    pack();
  }

  private void setPanel4(){
    JButton saveFileButton = new JButton("Save Image");
    selectedImage = (String) selectImages.getSelectedItem();
    System.out.println(selectedImage);
    saveFileButton.addActionListener(e -> {
      JFileChooser chooser = new JFileChooser();
      int returnVal = chooser.showSaveDialog(this);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        File fileToSave = chooser.getSelectedFile();
        ModelGui model = new ModelGui();
            AdvancedViewInterface view = new AdvancedViewConsole();
            commandString = "save " + fileToSave.getAbsolutePath() + " "+ selectedImage;
            AdvancedControllerInterface controller = new AdvancedController(new InputStreamReader(System.in), System.out, model, view);
            try {
              controller.runCommand("load Res/face.png face");
              controller.runCommand(commandString);
            } catch (IOException ex) {
              throw new RuntimeException(ex);
            }
      }
    });
    panel4.add(saveFileButton);
  }


  @Override
  public void addFeatures(GuiControllerInterface guiController) {
    setController(guiController);

  }

  private void setController(GuiControllerInterface guiController) {
    this.guiController = guiController;
  }

}
