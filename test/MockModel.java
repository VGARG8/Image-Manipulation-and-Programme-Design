import com.neu.image_manipulation.model.entity.Image;
import com.neu.image_manipulation.model.impl.ImageManipulationInterface;

public class MockModel implements ImageManipulationInterface {
  private StringBuilder log;

  public MockModel(StringBuilder log) {
    this.log = log;
  }

  @Override
  public Image flipImageHorizontally(Image image) {
    log.append("Storing the image after horizontal flip");
    return null;
  }

  @Override
  public Image flipImageVertically(Image image) {
    log.append("Storing the image after vertical flip");
    return null;
  }

  @Override
  public Image brightenImage(Image image, int value) {
    log.append("Brightening the image");
    return null;
  }

  @Override
  public Image darkenImage(Image image, int value) {
    log.append("Darkening the image");
    return null;
  }

  @Override
  public Image createValueComponentOfImage(Image image) {
    log.append("Storing the image's greyscale value component");
    return null;
  }

  @Override
  public Image createIntensityComponentOfImage(Image image) {
    log.append("Storing the image's greyscale intensity component");
    return null;
  }

  @Override
  public Image createLumaComponentOfImage(Image image) {
    log.append("Storing the image's greyscale luma component");
    return null;
  }

  @Override
  public Image[] splitIntoLIV(Image image) {
    return new Image[0];
  }

  @Override
  public Image createRedComponentOfImage(Image image) {
    log.append("Creating greyscale image with red component of the image.");
    return null;
  }

  @Override
  public Image createGreenComponentOfImage(Image image) {
    log.append("Creating greyscale image with green component of the image.");
    return null;
  }

  @Override
  public Image createBlueComponentOfImage(Image image) {
    log.append("Creating greyscale image with blue component of the image.");
    return null;
  }

  @Override
  public Image combineRGBImages(Image[] images) {
    log.append("combining the Red, Green, Blue channels to form an image.");
    return null;
  }

  @Override
  public Image[] splitIntoRGBImages(Image image) {
    log.append("Splitting the image into it's Red, Green, Blue channels.");
    return new Image[0];
  }

  @Override
  public void storeImages(String name, Image image) {

  }

  @Override
  public Image getImages(String name) {
    return null;
  }

  @Override
  public Boolean containsImages(String name) {
    return null;
  }
}