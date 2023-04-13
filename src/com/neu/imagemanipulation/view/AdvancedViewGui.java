package com.neu.imagemanipulation.view;

import com.neu.imagemanipulation.controller.AdvancedController;
import com.neu.imagemanipulation.controller.AdvancedControllerInterface;
import com.neu.imagemanipulation.controller.GuiControllerInterface;
import com.neu.imagemanipulation.model.impl.ModelGui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * This class represents the GUI view and its components.
 */

public class AdvancedViewGui extends AdvancedView implements ViewGuiInterface {

  private JFrame frame;
  private String selectedCommand;

  private final Panes panel1;
  private final Panes panel2;
  private final Panes panel3;
  private final Panes panel4;
  private DefaultComboBoxModel<String> model;
  private JComboBox<String> subCommandComboBox;
  private JComboBox<String> commandComboBox;
  private JScrollPane imageScrollPane;
  private JPanel controlsPanel;
  private JLabel statusLabel;
  private JLabel imageLabel;
  private JTextField value;
  private JButton fileChooserButton;
  private JButton applyButton;
  private JComboBox<String> selectImages;
  private JButton loadButton;
  private JButton zoomInButton;
  private JButton zoomOutButton;
  private String commandString;
  private JTextField referenceName;
  private String filePath;
  String selectedImage;


  public AdvancedViewGui() throws HeadlessException, IOException {
    super();
    setTitle("Image Manipulation Tool");
    setLocationRelativeTo(null);
    panel1 = new Panes(true);
    panel1.setLayout(new FlowLayout());
    panel2 = new Panes(true);
    panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
    panel3 = new Panes(true);
    panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
    panel4 = new Panes(true);
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

  private void setPanel1() {
    fileChooserButton = new JButton("Open File");
    fileChooserButton.addActionListener(e -> filePath = chooseFile());
    loadButton = new JButton("Load");
    referenceName = new JTextField(20);
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

    panel1.add(fileChooserButton);
    panel1.add(referenceName);
    panel1.add(loadButton);

  }


  private void setPanel2() {
    String[] options = {"select an image"};
    selectImages = new JComboBox<>(options);
    panel2.add(selectImages);
    imageLabel = new JLabel("image will be displayed here", SwingConstants.CENTER);
    imageLabel.setPreferredSize(new Dimension(400, 400));
    imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    controlsPanel = new JPanel(new FlowLayout());

    zoomInButton = new JButton("Zoom In");
    controlsPanel.add(zoomInButton);
    zoomOutButton = new JButton("Zoom Out");
    controlsPanel.add(zoomOutButton);
    controlsPanel.setVisible(false);
    panel2.add(controlsPanel);

    imageScrollPane = new JScrollPane();
    imageScrollPane.setViewportView(imageLabel);
    imageScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    imageScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    imageScrollPane.setPreferredSize(new Dimension(400, 400));
    panel2.add(imageScrollPane);

  }

  private void setPanel3() {
    String[] options = {"select a command"};
    commandComboBox = new JComboBox<>(options);
    panel3.add(commandComboBox);
    subCommandComboBox = new JComboBox<>();
    subCommandComboBox.addItem("value-component");
    subCommandComboBox.addItem("luma-component");
    subCommandComboBox.addItem("intensity-component");
    subCommandComboBox.addItem("red-component");
    subCommandComboBox.addItem("green-component");
    subCommandComboBox.addItem("blue-component");
    selectedImage = (String) selectImages.getSelectedItem();
    value = new JTextField(20);
    value.setForeground(Color.GRAY);
    value.setText("Enter integer value of intensity");
    value.addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        if (value.getText().equals("Enter integer value of intensity")) {
          value.setText("");
          value.setForeground(Color.BLACK);
        }
      }

      @Override
      public void focusLost(FocusEvent e) {
        if (value.getText().isEmpty()) {
          value.setForeground(Color.GRAY);
          value.setText("Enter integer value of intensity");
        }
      }
    });

    applyButton = new JButton("Apply");
    pack();
  }

  private void setPanel4() {
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
        commandString = "save " + fileToSave.getAbsolutePath() + " " + selectedImage;
        AdvancedControllerInterface controller = new AdvancedController(
            new InputStreamReader(System.in), System.out, model, view);
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

  private void zoomImage(double scaleFactor) {
    ImageIcon currentIcon = (ImageIcon) imageLabel.getIcon();
    Image currentImage = currentIcon.getImage();
    Image newImage = currentImage.getScaledInstance(
        (int) (currentImage.getWidth(null) * scaleFactor),
        (int) (currentImage.getHeight(null) * scaleFactor),
        Image.SCALE_SMOOTH);
    imageLabel.setIcon(new ImageIcon(newImage));
    imageLabel.setPreferredSize(new Dimension(newImage.getWidth(null), newImage.getHeight(null)));
    imageScrollPane.revalidate();
    imageScrollPane.repaint();
  }

  @Override
  public void addFeatures(GuiControllerInterface guiController) {
    loadButton.addActionListener(e -> {
      commandString = "load " + filePath + " " + referenceName.getText();
      DefaultComboBoxModel<String> modelCommands = new DefaultComboBoxModel<>(
          guiController.getCommandKeys().toArray(new String[0]));
      commandComboBox.setModel(modelCommands);
      try {
        guiController.runCommand(commandString);
        statusLabel = new JLabel("File loaded");
        model = new DefaultComboBoxModel<>(guiController.getKeys().toArray(new String[0]));
        selectImages.setModel(model);
        statusLabel.setVisible(true);
        panel1.add(statusLabel);
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
      referenceName.setForeground(Color.GRAY);
      referenceName.setText("Enter the image name for reference");
    });

    selectImages.addActionListener(e -> {
      BufferedImage image = guiController.getImage(Objects.
          requireNonNull(selectImages.getSelectedItem()).toString());
      ImageIcon imageIcon = new ImageIcon(image);
      imageLabel.setIcon(imageIcon);
      imageLabel.setText("");
      controlsPanel.setVisible(true);

      panel2.revalidate();
      panel2.repaint();
    });

    zoomInButton.addActionListener(e -> {
      zoomImage(2.0);
    });

    zoomOutButton.addActionListener(e -> {
      zoomImage(0.5);
    });
    commandComboBox.addActionListener(e -> {
      panel3.remove(subCommandComboBox);
      panel3.remove(value);

      selectedCommand = commandComboBox.getSelectedItem().toString();
      switch (selectedCommand) {
        case "greyscale":
          panel3.add(subCommandComboBox);
          commandString = "greyscale " + subCommandComboBox.getSelectedItem().toString() + " " +
              selectImages.getSelectedItem().toString() + " " +
              selectImages.getSelectedItem().toString() +
              subCommandComboBox.getSelectedItem().toString();
          break;
        case "horizontal-flip":
          commandString = "horizontal-flip " + selectImages.getSelectedItem().toString() + " " +
              selectImages.getSelectedItem().toString() + "horizontal-flip";
          break;
        case "vertical-flip":
          commandString = "vertical-flip " + selectImages.getSelectedItem().toString() + " " +
              selectImages.getSelectedItem().toString() + "vertical-flip";
          break;
        case "blur":
          commandString = "blur " + selectImages.getSelectedItem().toString() + " " +
              selectImages.getSelectedItem().toString() + "blur";
          break;
        case "sharpen":
          commandString = "sharpen " + selectImages.getSelectedItem().toString() + " " +
              selectImages.getSelectedItem().toString() + "sharpen";
          break;
        case "dither":
          commandString = "dither " + selectImages.getSelectedItem().toString() + " " +
              selectImages.getSelectedItem().toString() + "dither";
          break;
        case "sepia-tone":
          commandString = "sepia-tone " + selectImages.getSelectedItem().toString() + " " +
              selectImages.getSelectedItem().toString() + "sepia-tone";
          break;
        case "greyscale-tone":
          commandString = "greyscale-tone " + selectImages.getSelectedItem().toString() + " " +
              selectImages.getSelectedItem().toString() + "greyscale-tone";
          break;
        case "brighten":
        case "darken":
          panel3.add(value);
          break;
      }
      if (!panel3.isAncestorOf(applyButton)) {
        panel3.add(applyButton);
      }
      panel3.revalidate();
      panel3.repaint();
    });

    applyButton.addActionListener(e -> {

      selectedCommand = commandComboBox.getSelectedItem().toString();
      if (selectedCommand.equals("brighten")) {
        commandString = "brighten " + value.getText() + " " +
            selectImages.getSelectedItem().toString() + " " +
            selectImages.getSelectedItem().toString() + "brighten";
      } else if (selectedCommand.equals("darken")) {
        commandString = "darken " + value.getText() + " " +
            selectImages.getSelectedItem().toString() + " " +
            selectImages.getSelectedItem().toString() + "darken";
      }

      try {
        guiController.runCommand(commandString);
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }

      DefaultComboBoxModel<String> updatedModel = new DefaultComboBoxModel<>(guiController.
          getKeys().toArray(new String[0]));
      selectImages.setModel(updatedModel);

      panel2.revalidate();
      panel2.repaint();
      commandComboBox.setSelectedIndex(0);

      panel3.remove(subCommandComboBox);
      panel3.remove(value);

      panel3.revalidate();
      panel3.repaint();
    });
  }


  private void generateDialogueBoxMsg(String msg) {
    frame = new JFrame();
    JOptionPane.showMessageDialog(frame, msg);
  }

  @Override
  public void displayInvalidFileFormat() throws IOException {
    generateDialogueBoxMsg("File format is invalid!\n");
  }

  @Override
  public void displayGreyScaleStatus() throws IOException {
    generateDialogueBoxMsg("Creating greyscale toned image.\n");
  }

  @Override
  public void displaySharpenStatus() throws IOException {
    generateDialogueBoxMsg("Sharpening the image.\n");
  }

  @Override
  public void displaySepiaStatus() throws IOException {
    generateDialogueBoxMsg("Creating sepia toned image.\n");
  }

  @Override
  public void displayDitherStatus() throws IOException {
    generateDialogueBoxMsg("Dithering the image.\n");
  }

  @Override
  public void displayBlurStatus() throws IOException {
    generateDialogueBoxMsg("Blurring the image.\n");
  }

  @Override
  public void displayFileNotSpecified() throws IOException {
    generateDialogueBoxMsg("Error: no script file specified.");
  }

  @Override
  public void getCommand() throws IOException {
    generateDialogueBoxMsg("Enter the command:\n");
  }

  @Override
  public void displaySaveStatus(String fileExtension) throws IOException {
    generateDialogueBoxMsg("Saving image as " + fileExtension + "\n");
  }

  @Override
  public void displayReadFileError() throws IOException {
    generateDialogueBoxMsg("Can't read the file!\n");
  }

  @Override
  public void displayLoadingStatus() throws IOException {
    generateDialogueBoxMsg("Loading the file\n");
  }

  @Override
  public void displayValueStatus() throws IOException {
    generateDialogueBoxMsg("Storing the image's greyscale value component\n");
  }

  @Override
  public void displayLumaStatus() throws IOException {
    generateDialogueBoxMsg("Storing the image's greyscale luma component\n");
  }

  @Override
  public void displayIntensityStatus() throws IOException {
    generateDialogueBoxMsg("Storing the image's greyscale intensity component\n");
  }

  @Override
  public void displayHorizontalFlipStatus() throws IOException {
    generateDialogueBoxMsg("Storing the image after horizontal flip\n");
  }

  @Override
  public void displayVerticalFlipStatus() throws IOException {
    generateDialogueBoxMsg("Storing the image after vertical flip\n");
  }

  @Override
  public void displayBrightenStatus() throws IOException {
    generateDialogueBoxMsg("Brightening the image\n");
  }

  @Override
  public void displayDarkenenStatus() throws IOException {
    generateDialogueBoxMsg("Darkening the image\n");
  }

  @Override
  public void displayRunScriptStatus(String filepath) throws IOException {
    generateDialogueBoxMsg("Running the script from: " + filepath + "\n");
  }

  @Override
  public void displayRGBSplitStatus() throws IOException {
    generateDialogueBoxMsg("Splitting the image into it's Red, Green, Blue channels.\n");
  }

  @Override
  public void displayRGBCombineStatus() throws IOException {
    generateDialogueBoxMsg("combining the Red, Green, Blue channels to form an image.\n");
  }

  @Override
  public void displayInvalidValue() throws IOException {
    generateDialogueBoxMsg("Value should be a non-negative integer.\n");
  }

  @Override
  public void displayNoFileStatus() throws IOException {
    generateDialogueBoxMsg("File not found!\n");
  }

  @Override
  public void displayInvalidPPM() throws IOException {
    generateDialogueBoxMsg("Invalid PPM file: plain RAW file should begin with P3.\n");
  }

  @Override
  public void displayEmptyFileStatus() throws IOException {
    generateDialogueBoxMsg("File is Empty!\n");
  }

  @Override
  public void displayInvalidPPMNoValues() throws IOException {
    generateDialogueBoxMsg("PPM file got no values after the header. Image with 0x0 dimensions is "
        + "created\n");
  }

  @Override
  public void displayWidth(int width) throws IOException {
    generateDialogueBoxMsg("Width of image: " + width + "\n");
  }

  @Override
  public void displayHeight(int height) throws IOException {
    generateDialogueBoxMsg("Height of image: " + height + "\n");
  }

  @Override
  public void displayMaxValue(int maxValue) throws IOException {
    generateDialogueBoxMsg("Maximum value of a color in this file (usually 255): "
        + maxValue + "\n");
  }

  @Override
  public void displayBlueComponentStatus() throws IOException {
    generateDialogueBoxMsg("Creating greyscale image with blue component of the image.\n");
  }

  @Override
  public void displayRedComponentStatus() throws IOException {
    generateDialogueBoxMsg("Creating greyscale image with red component of the image.\n");
  }

  @Override
  public void displayGreenComponentStatus() throws IOException {
    generateDialogueBoxMsg("Creating greyscale image with green component of the image.\n");
  }

  @Override
  public void displayEnterValidCommand() throws IOException {
    generateDialogueBoxMsg("Enter a valid a command!\n");
  }

  @Override
  public void displayImageDoesntExist() throws IOException {
    generateDialogueBoxMsg("Image doesn't exist!\n");
  }


}
