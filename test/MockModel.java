import com.neu.imagemanipulation.model.entity.Image;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationInterface;


/**
 * This class is used to test isolated controller. this is a mock Model.
 */
public class MockModel implements AdvancedImageManipulationInterface {
  private final StringBuilder log;

  /**
   * This is a constructor class. initialises the log.
   *
   * @param log is a StringBuilder to keep track of strings.
   */
  public MockModel(StringBuilder log) {
    this.log = log;
  }

  @Override
  public Image flipImageHorizontally(Image image) {
    log.append("Storing the image after horizontal flip\n");
    return null;
  }

  @Override
  public Image flipImageVertically(Image image) {
    log.append("Storing the image after vertical flip\n");
    return null;
  }

  @Override
  public Image brightenImage(Image image, int value) {
    log.append("Brightening the image\n");
    return null;
  }

  @Override
  public Image darkenImage(Image image, int value) {
    log.append("Darkening the image\n");
    return null;
  }

  @Override
  public Image createValueComponentOfImage(Image image) {
    log.append("Storing the image's greyscale value component\n");
    return null;
  }

  @Override
  public Image createIntensityComponentOfImage(Image image) {
    log.append("Storing the image's greyscale intensity component\n");
    return null;
  }

  @Override
  public Image createLumaComponentOfImage(Image image) {
    log.append("Storing the image's greyscale luma component\n");
    return null;
  }

  @Override
  public Image[] splitIntoLIV(Image image) {
    return new Image[0];
  }

  @Override
  public Image createRedComponentOfImage(Image image) {
    log.append("Creating greyscale image with red component of the image.\n");
    return null;
  }

  @Override
  public Image createGreenComponentOfImage(Image image) {
    log.append("Creating greyscale image with green component of the image.\n");
    return null;
  }

  @Override
  public Image createBlueComponentOfImage(Image image) {
    log.append("Creating greyscale image with blue component of the image.\n");
    return null;
  }

  @Override
  public Image combineRGBImages(Image[] images) {
    log.append("combining the Red, Green, Blue channels to form an image.\n");
    return null;
  }

  @Override
  public Image[] splitIntoRGBImages(Image image) {
    log.append("Splitting the image into it's Red, Green, Blue channels.\n");
    return null;
  }

  @Override
  public void storeImages(String name, Image image) {
    log.append("Image is stored.\n");
  }

  @Override
  public Image getImages(String name) {
    log.append("getting image\n");
    return null;
  }

  @Override
  public Boolean containsImages(String name) {
    log.append("checking if image exists.\n");
    return true;
  }

  @Override
  public Image blur(Image image) {
    log.append("blurring the image.\n");
    return null;
  }

  @Override
  public Image sharpen(Image image) {
    log.append("sharpening the image.\n");
    return null;
  }

  @Override
  public Image greyscale(Image image) {
    log.append("greyscale tone of the image.\n");
    return null;
  }

  @Override
  public Image sepiaTone(Image image) {
    log.append("sepia tone of the image.\n");
    return null;
  }

  @Override
  public Image dither(Image image) {
    log.append("dithering the image.\n");
    return null;
  }
}
