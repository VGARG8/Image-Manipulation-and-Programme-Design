package com.neu.imagemanipulation.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

public class GuiView extends JFrame implements GuiViewInterface{
  MainPanel mainPanel;
  private JFrame frame;
  private JPanel panel1;
  private JPanel panel2;
  public GuiView() {
    super();
    setTitle("Image Manipulation Tool");
    mainPanel = new MainPanel();
    add(mainPanel);
    pack();
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }



  private void generateDialogueBoxMsg(String msg){
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
    generateDialogueBoxMsg("Height of image: "+ height + "\n");
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

  @Override
  public void displayInvalidSyntax() throws IOException {
    generateDialogueBoxMsg("Invalid Syntax!\n");
  }

  @Override
  public String getCommandString() {
    return mainPanel.getCommandStringGui();
  }
}
