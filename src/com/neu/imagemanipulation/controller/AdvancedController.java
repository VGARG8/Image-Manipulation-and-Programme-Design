package com.neu.imagemanipulation.controller;

import com.neu.imagemanipulation.Constants;
import com.neu.imagemanipulation.model.entity.Image;
import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.entity.Pixel;
import com.neu.imagemanipulation.model.entity.PixelInterface;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;
import com.neu.imagemanipulation.view.AdvancedViewInterface;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class AdvancedController extends Controller implements AdvancedControllerInterface {

  /**
   * Constructs a new Controller object with the specified input and output streams, model, and
   * view.
   *
   * @param in    the input stream to read from
   * @param out   the output stream to write to
   * @param model the ImageManipulationInterface model to use for image manipulation operations
   * @param view  the ViewInterface view to use for displaying the manipulated images
   * @throws NullPointerException if the model parameter is null
   */
  public AdvancedController(Readable in, Appendable out, AdvancedImageManipulationInterface model,
      AdvancedViewInterface view) {
    super(in, out, model, view);
  }

  @Override
  public void callViewforMain() throws IOException {
    view.displayFileNotSpecified();
  }

  @Override
  public void runCommand(String command) throws IOException {
    String[] tokens = command.split("\\s+");
    ImageInterface result_image;

    switch (tokens[0].toLowerCase()) {
      case Constants.LOAD:
        if (tokens.length == 3) {
          String filename = tokens[1];
          String fileExtension = getFileExtension(filename);

          if (fileExtension.equalsIgnoreCase(Constants.PNG) ||
              fileExtension.equalsIgnoreCase(Constants.JPG) ||
              fileExtension.equalsIgnoreCase(Constants.BMP)) {
            try {
              result_image = loadStandardFormat(filename);
              model.storeImages(tokens[2], result_image);
              view.displaySaveStatus(fileExtension);
            } catch (IOException e) {
              System.out.println("Exception occurred :" + e.getMessage());
            }
          } else {
            super.runCommand(command);
          }
        }
        break;

      case Constants.SAVE:
        if (tokens.length == 3) {
          String filename = tokens[1];
          String fileExtension = getFileExtension(filename);

          if (fileExtension.equalsIgnoreCase(Constants.PNG) ||
              fileExtension.equalsIgnoreCase(Constants.JPG) ||
              fileExtension.equalsIgnoreCase(Constants.BMP)) {
            generateImage(model.getImages(tokens[2]), filename);
          } else {
            super.runCommand(command);
          }
        }
        break;

      case Constants.BLUR:
        view.displayBlurStatus();
        if (!model.containsImages(tokens[1])) {
          view.displayImageDoesntExist();
          break;
        }
        result_image = model.blur(model.getImages(tokens[1]));
        model.storeImages(tokens[2], result_image);
        break;
      case Constants.SHARPEN:
        view.displaySharpenStatus();
        if (!model.containsImages(tokens[1])) {
          view.displayImageDoesntExist();
          break;
        }
        result_image = model.sharpen(model.getImages(tokens[1]));
        model.storeImages(tokens[2], result_image);
        break;
      case Constants.GREYSCALE_TONE:
        view.displayGreyScaleStatus();
        if (!model.containsImages(tokens[1])) {
          view.displayImageDoesntExist();
          break;
        }
        result_image = model.greyscale(model.getImages(tokens[1]));
        model.storeImages(tokens[2], result_image);
        break;
      case Constants.SEPIA_TONE:
        view.displaySepiaStatus();
        if (!model.containsImages(tokens[1])) {
          view.displayImageDoesntExist();
          break;
        }
        result_image = model.sepiaTone(model.getImages(tokens[1]));
        model.storeImages(tokens[2], result_image);
        break;
      case Constants.DITHER:
        view.displayDitherStatus();
        if (!model.containsImages(tokens[1])) {
          view.displayImageDoesntExist();
          break;
        }
        result_image = model.dither(model.getImages(tokens[1]));
        model.storeImages(tokens[2], result_image);
        break;

      default:
        super.runCommand(command);
    }
  }

  @Override
  public ImageInterface loadStandardFormat(String filename) throws IOException {
    BufferedImage bufferedImage = ImageIO.read(new File(filename));

    int width = bufferedImage.getWidth();
    int height = bufferedImage.getHeight();
    int maxValue = 255;

    Pixel[][] pixelData = new Pixel[height][width];
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Color color = new Color(bufferedImage.getRGB(x, y));
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        pixelData[y][x] = new Pixel(red, green, blue);
      }
    }

    Image image = new Image(height, width, maxValue);
    image.setPixel(pixelData);

    return image;
  }

  public void generateImage(ImageInterface image, String filename) {
    List<Integer> pixels = new ArrayList<>();
    PixelInterface[][] pixelArray = image.getPixel();
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        int r = pixelArray[i][j].getRed();
        int g = pixelArray[i][j].getGreen();
        int b = pixelArray[i][j].getBlue();
        Color color = new Color(r, g, b);

        pixels.add(color.getRGB());
      }
    }
    BufferedImage outputImg = new BufferedImage(image.getWidth(), image.getHeight(),
        BufferedImage.TYPE_INT_RGB);
    int[] outputImagePixelData = ((DataBufferInt) outputImg.getRaster().getDataBuffer()).getData();

    for (int i = 0; i < pixels.size(); i++) {
      outputImagePixelData[i] = pixels.get(i);
    }
    try {
      ImageIO.write(outputImg, getFileExtension(filename),
          new File("Res/" + filename));
    } catch (IOException e) {
      System.out.println("Exception occurred :" + e.getMessage());
    }
  }
}
