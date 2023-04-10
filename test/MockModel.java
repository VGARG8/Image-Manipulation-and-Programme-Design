import com.neu.imagemanipulation.model.entity.Image;
import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;

import java.util.HashMap;
import java.util.Map;


/**
 * This class is used to test isolated controller. this is a mock Model.
 */
public class MockModel implements AdvancedImageManipulationInterface {
  private final StringBuilder log;
  private final Map<String, ImageInterface> imagesMap = new HashMap<>();

  /**
   * This is a constructor class. initialises the log.
   *
   * @param log is a StringBuilder to keep track of strings.
   */
  public MockModel(StringBuilder log) {
    this.log = log;
  }

  @Override
  public ImageInterface flipImageHorizontally(ImageInterface image) {
    log.append("Storing the image after horizontal flip\n");
    return null;
  }

  @Override
  public ImageInterface flipImageVertically(ImageInterface image) {
    log.append("Storing the image after vertical flip\n");
    return null;
  }

  @Override
  public ImageInterface brightenImage(ImageInterface image, int value) {
    log.append("Brightening the image\n");
    return null;
  }

  @Override
  public ImageInterface darkenImage(ImageInterface image, int value) {
    log.append("Darkening the image\n");
    return null;
  }

  @Override
  public ImageInterface createValueComponentOfImage(ImageInterface image) {
    log.append("Storing the image's greyscale value component\n");
    return null;
  }

  @Override
  public ImageInterface createIntensityComponentOfImage(ImageInterface image) {
    log.append("Storing the image's greyscale intensity component\n");
    return null;
  }

  @Override
  public ImageInterface createLumaComponentOfImage(ImageInterface image) {
    log.append("Storing the image's greyscale luma component\n");
    return null;
  }

  @Override
  public ImageInterface[] splitIntoLIV(ImageInterface image) {
    return new Image[0];
  }

  @Override
  public ImageInterface createRedComponentOfImage(ImageInterface image) {
    log.append("Creating greyscale image with red component of the image.\n");
    return null;
  }

  @Override
  public ImageInterface createGreenComponentOfImage(ImageInterface image) {
    log.append("Creating greyscale image with green component of the image.\n");
    return null;
  }

  @Override
  public ImageInterface createBlueComponentOfImage(ImageInterface image) {
    log.append("Creating greyscale image with blue component of the image.\n");
    return null;
  }

  @Override
  public ImageInterface combineRGBImages(ImageInterface[] images) {
    log.append("combining the Red, Green, Blue channels to form an image.\n");
    return null;
  }

  @Override
  public ImageInterface[] splitIntoRGBImages(ImageInterface image) {
    log.append("Splitting the image into it's Red, Green, Blue channels.\n");
    return null;
  }

  @Override
  public void storeImages(String name, ImageInterface image) {
    log.append("Image is stored.\n");
      imagesMap.put(name, image);
  }

  @Override
  public ImageInterface getImages(String name) {
    log.append("getting image\n");
    return null;
  }

  @Override
  public Boolean containsImages(String name) {
    log.append("checking if image exists.\n");
    return true;
  }

  @Override
  public ImageInterface blur(ImageInterface image) {
    log.append("blurring the image.\n");
    return null;
  }

  @Override
  public ImageInterface sharpen(ImageInterface image) {
    log.append("sharpening the image.\n");
    return null;
  }

  @Override
  public ImageInterface greyscale(ImageInterface image) {
    log.append("greyscale tone of the image.\n");
    return null;
  }

  @Override
  public ImageInterface sepiaTone(ImageInterface image) {
    log.append("sepia tone of the image.\n");
    return null;
  }

  @Override
  public ImageInterface dither(ImageInterface image) {
    log.append("dithering the image.\n");
    return null;
  }
}
