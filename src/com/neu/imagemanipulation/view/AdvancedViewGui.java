package com.neu.imagemanipulation.view;

import com.neu.imagemanipulation.controller.GuiControllerInterface;

import javax.swing.*;

import javax.swing.border.Border;


import java.awt.*;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.SortedMap;


public class AdvancedViewGui extends AdvancedView implements ViewGuiInterface {
  private JFrame frame;
  private String selectedCommand;

  private final Panes loadPanel;
  private final Panes imagePanel;
  private final Panes filterPanel;
  private final Panes savePanel;
  private final Panes activityPanel;
  private ImageIcon imageIcon;
  private BufferedImage image;
  private DefaultComboBoxModel<String> model;
  private JComboBox<String> subCommandComboBox;
  private JComboBox<String> commandComboBox;
  private JScrollPane imageScrollPane;
  private JPanel controlsPanel;
  private JTextArea statusLabel;
  private JLabel imageLabel;
  private JTextField value;
  private File fileToSave;
  private JButton fileChooserButton;
  private JFileChooser chooser;
  private JButton applyButton;
  private JButton saveFileButton;
  private JComboBox<String> selectImages;
  private JButton loadButton;
  private JButton zoomInButton;
  private JButton zoomOutButton;
  private String commandString;
  private String newImageName;
  private String liveStatus;
  private JTextField referenceName;
  private String filePath;
  String selectedImage;


  public AdvancedViewGui() throws HeadlessException, IOException {
    super();
    setTitle("Image Manipulation Tool");
    setLocationRelativeTo(null);

    loadPanel = new Panes(true);
    loadPanel.setLayout(new FlowLayout());
    loadPanel.setBorder(BorderFactory.createTitledBorder("Load Image here"));
    imagePanel = new Panes(true);
    imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));
    imagePanel.setBorder(BorderFactory.createTitledBorder("Select the images"));
    filterPanel = new Panes(true);
    filterPanel.setLayout(new FlowLayout());
    filterPanel.setBorder(BorderFactory.createTitledBorder("Select the filters"));
    savePanel = new Panes(true);
    savePanel.setLayout(new FlowLayout());
    savePanel.setBorder(BorderFactory.createTitledBorder("Save the selected image"));
    activityPanel = new Panes(true);
    activityPanel.setLayout(new FlowLayout());
    activityPanel.setBorder(BorderFactory.createTitledBorder("Activity"));


    setLoadPanel();
    setImagePanel();
    setFilterPanel();
    setSavePanel();
    setActivityPanel();


    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(loadPanel, BorderLayout.NORTH);
    getContentPane().add(imagePanel, BorderLayout.CENTER);
    getContentPane().add(filterPanel, BorderLayout.WEST);
    getContentPane().add(savePanel, BorderLayout.EAST);
    getContentPane().add(activityPanel, BorderLayout.SOUTH);

    pack();
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  private void setActivityPanel() {
    statusLabel = new JTextArea();
    statusLabel.setPreferredSize(new Dimension(800,150));
    statusLabel.setEditable(false);
    statusLabel.setBackground(Color.LIGHT_GRAY);
    liveStatus = "Live Status:\n";
    statusLabel.setText(liveStatus);
    statusLabel.setVisible(true);
    activityPanel.add(statusLabel);

  }

  private String chooseFile() {
    JFileChooser chooser = new JFileChooser();
    int returnVal = chooser.showOpenDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      return chooser.getSelectedFile().getPath();
    }
    return null;
  }

  private void setLoadPanel() {

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

    loadPanel.add(fileChooserButton);
    loadPanel.add(referenceName);
    loadPanel.add(loadButton);

  }


  private void setImagePanel() {
    String[] options = {"select an image"};
    selectImages = new JComboBox<>(options);
    imagePanel.add(selectImages);
    imageLabel = new JLabel("image will be displayed here", SwingConstants.CENTER);
    imageLabel.setPreferredSize(new Dimension(400, 400));
    imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    controlsPanel = new JPanel(new FlowLayout());

    zoomInButton = new JButton("Zoom In");
    controlsPanel.add(zoomInButton);
    zoomOutButton = new JButton("Zoom Out");
    controlsPanel.add(zoomOutButton);
    controlsPanel.setVisible(false);
    imagePanel.add(controlsPanel);


    imageScrollPane = new JScrollPane();
    imageScrollPane.setViewportView(imageLabel);
    imageScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    imageScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    imageScrollPane.setPreferredSize(new Dimension(400, 400));
    imagePanel.add(imageScrollPane);

  }

  private void setFilterPanel() {
    String[] options = {"select a command"};
    commandComboBox = new JComboBox<>(options);
    commandComboBox.setSize(150,25);


    filterPanel.add(commandComboBox);
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
//    pack();
  }

  private void setSavePanel() {
    saveFileButton = new JButton("Save Image");
    saveFileButton.setPreferredSize(new Dimension(150,25));
    selectedImage = selectImages.getSelectedItem().toString();
    savePanel.add(saveFileButton);
  }

  private void zoomImage(double scaleFactor) {
    ImageIcon currentIcon = (ImageIcon) imageLabel.getIcon();
    Image currentImage = currentIcon.getImage();
    Image newImage = currentImage.getScaledInstance((int) (currentImage.getWidth(null) * scaleFactor),
            (int) (currentImage.getHeight(null) * scaleFactor),
            Image.SCALE_SMOOTH);
    imageLabel.setIcon(new ImageIcon(newImage));
    imageLabel.setPreferredSize(new Dimension(newImage.getWidth(null), newImage.getHeight(null)));
    imageScrollPane.revalidate();
    imageScrollPane.repaint();
  }

  private void handleLoadButtonAction(GuiControllerInterface guiController) {
    loadButton.addActionListener(e -> {
      commandString = "load " + filePath + " " + referenceName.getText();
      DefaultComboBoxModel<String> modelCommands = new DefaultComboBoxModel<>(guiController.getCommandKeys().toArray(new String[0]));
      commandComboBox.setModel(modelCommands);
      try {
        guiController.runCommand(commandString);
        model = new DefaultComboBoxModel<>(guiController.getKeys().toArray(new String[0]));
        selectImages.setModel(model);

      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
      referenceName.setForeground(Color.GRAY);
      referenceName.setText("Enter the image name for reference");
      System.out.println(liveStatus);
      statusLabel.setText(liveStatus);
      statusLabel.repaint();
      statusLabel.revalidate();
      activityPanel.revalidate();
      activityPanel.repaint();
    });
  }

  private void handleSelectImagesAction(GuiControllerInterface guiController) {
    selectImages.repaint();
    selectImages.revalidate();
    selectImages.addActionListener(e -> {
      selectedImage = selectImages.getSelectedItem().toString();
      liveStatus = liveStatus + selectedImage + " image is selected\n";
      image = guiController.getImage(Objects.
              requireNonNull(selectImages.getSelectedItem()).toString());
      imageIcon = new ImageIcon(image);
      imageLabel.setIcon(imageIcon);
      imageLabel.setText("");
      controlsPanel.setVisible(true);


      imagePanel.revalidate();
      imagePanel.repaint();
      statusLabel.setText(liveStatus);
      activityPanel.revalidate();
      activityPanel.repaint();
    });
  }

  private void handleZoomInButtonAction() {
    zoomInButton.addActionListener(e -> {
      zoomImage(2.0);
      liveStatus = liveStatus + "zooming in\n";
      statusLabel.setText(liveStatus);
      activityPanel.revalidate();
      activityPanel.repaint();
    });
  }

  private void handleZoomOutButtonAction() {
    zoomOutButton.addActionListener(e -> {
      zoomImage(0.5);
      liveStatus = liveStatus + "zooming out\n";
      statusLabel.setText(liveStatus);
      activityPanel.revalidate();
      activityPanel.repaint();
    });
  }

  private void handleCommandComboBoxAction() {
    commandComboBox.addActionListener(e -> {
      filterPanel.remove(subCommandComboBox);
      filterPanel.remove(value);

      selectedCommand = commandComboBox.getSelectedItem().toString();
      newImageName = selectImages.getSelectedItem().toString();
      switch (selectedCommand) {
        case "greyscale":
          filterPanel.add(subCommandComboBox);
          newImageName = newImageName + subCommandComboBox.getSelectedItem().toString();
          commandString = "greyscale " + subCommandComboBox.getSelectedItem().toString() + " " +
                  selectImages.getSelectedItem().toString() + " " +
                  newImageName;
          liveStatus = liveStatus + "generating " + selectedCommand + " " +
                  subCommandComboBox.getSelectedItem().toString() + " of " +
                  selectImages.getSelectedItem().toString() + "\n";
          break;
        case "horizontal-flip":
          newImageName = newImageName + selectedCommand;
          commandString = "horizontal-flip " + selectImages.getSelectedItem().toString() + " " +
                  newImageName;
          liveStatus = liveStatus + "generating " + selectedCommand + " of " +
                  selectImages.getSelectedItem().toString() + "\n";
          break;
        case "vertical-flip":
          newImageName = newImageName + selectedCommand;
          commandString = "vertical-flip " + selectImages.getSelectedItem().toString() + " " +
                  newImageName;
          liveStatus = liveStatus + "generating " + selectedCommand + " of " +
                  selectImages.getSelectedItem().toString() + "\n";
          break;
        case "blur":
          newImageName = newImageName + selectedCommand;
          commandString = "blur " + selectImages.getSelectedItem().toString() + " " +
                  newImageName;
          liveStatus = liveStatus + "generating " + selectedCommand + " of " +
                  selectImages.getSelectedItem().toString() + "\n";
          break;
        case "sharpen":
          newImageName = newImageName + selectedCommand;
          commandString = "sharpen " + selectImages.getSelectedItem().toString() + " " +
                  newImageName;
          liveStatus = liveStatus + "generating " + selectedCommand + " of " +
                  selectImages.getSelectedItem().toString() + "\n";
          break;
        case "dither":
          newImageName = newImageName + selectedCommand;
          commandString = "dither " + selectImages.getSelectedItem().toString() + " " +
                  newImageName;
          liveStatus = liveStatus + "generating " + selectedCommand + " of " +
                  selectImages.getSelectedItem().toString() + "\n";
          break;
        case "sepia-tone":
          newImageName = newImageName + selectedCommand;
          commandString = "sepia-tone " + selectImages.getSelectedItem().toString() + " " +
                  newImageName;
          liveStatus = liveStatus + "generating " + selectedCommand + " of " +
                  selectImages.getSelectedItem().toString() + "\n";
          break;
        case "greyscale-tone":
          newImageName = newImageName + selectedCommand;
          commandString = "greyscale-tone " + selectImages.getSelectedItem().toString() + " " +
                  newImageName;
          liveStatus = liveStatus + "generating " + selectedCommand + " of " +
                  selectImages.getSelectedItem().toString() + "\n";
          break;
        case "brighten":
        case "darken":
          filterPanel.add(value);
          break;
      }
      if (!filterPanel.isAncestorOf(applyButton)) {
        filterPanel.add(applyButton);
      }
      filterPanel.revalidate();
      filterPanel.repaint();

      statusLabel.setText(liveStatus);
      activityPanel.revalidate();
      activityPanel.repaint();
    });
  }

  private void handleApplyButtonAction(GuiControllerInterface guiController) {
    applyButton.addActionListener(e -> {
      selectedCommand = commandComboBox.getSelectedItem().toString();
      if (selectedCommand.equals("brighten")) {
        commandString = "brighten " + value.getText() + " " +
                selectImages.getSelectedItem().toString() + " " +
                selectImages.getSelectedItem().toString() + "brighten";
        newImageName = newImageName + selectedCommand;
        liveStatus = liveStatus + "brightening " +
                selectImages.getSelectedItem().toString() + "\n";
      } else if (selectedCommand.equals("darken")) {
        commandString = "darken " + value.getText() + " " +
                selectImages.getSelectedItem().toString() + " " +
                selectImages.getSelectedItem().toString() + "darken";
        newImageName = newImageName + selectedCommand;
        liveStatus = liveStatus + "darkening " +
                selectImages.getSelectedItem().toString() + "\n";
      }

      try {
        guiController.runCommand(commandString);
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
      image = guiController.getImage(newImageName);
      imageIcon = new ImageIcon(image);
      imageLabel.setIcon(imageIcon);
      imageLabel.setText("");


      DefaultComboBoxModel<String> updatedModel = new DefaultComboBoxModel<>(guiController.
              getKeys().toArray(new String[0]));
      selectImages.setModel(updatedModel);
      selectImages.setSelectedItem(newImageName);

      imagePanel.revalidate();
      imagePanel.repaint();
      commandComboBox.setSelectedIndex(0);


      filterPanel.remove(subCommandComboBox);
      filterPanel.remove(value);


      filterPanel.revalidate();
      filterPanel.repaint();
      statusLabel.setText(liveStatus);
      activityPanel.revalidate();
      activityPanel.repaint();
    });
  }

  private void handleSaveButtonAction(GuiControllerInterface guiController) {
    saveFileButton.addActionListener(e -> {
      chooser = new JFileChooser();
      int returnVal = chooser.showSaveDialog(this);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        fileToSave = chooser.getSelectedFile();
        commandString = "save " + fileToSave.getAbsolutePath() + " " + selectedImage;
        System.out.println(commandString);
        try {
          guiController.runCommand(commandString);
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
      }
      liveStatus = liveStatus + "saving the image at: " + fileToSave.getAbsolutePath() +"\n";
      statusLabel.setText(liveStatus);
      activityPanel.revalidate();
      activityPanel.repaint();
    });
  }


  @Override
  public void addFeatures(GuiControllerInterface guiController) {
    handleLoadButtonAction(guiController);
    handleSelectImagesAction(guiController);
    handleZoomInButtonAction();
    handleZoomOutButtonAction();
    handleCommandComboBoxAction();
    handleApplyButtonAction(guiController);
    handleSaveButtonAction(guiController);
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
    liveStatus = liveStatus + "Loading the file\n";
//    generateDialogueBoxMsg("Loading the file\n");
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
