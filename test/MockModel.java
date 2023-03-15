import com.neu.image_manipulation.model.entity.Image;
import com.neu.image_manipulation.model.impl.ImageManipulationInterface;

public class MockModel implements ImageManipulationInterface {
  private StringBuilder log;

  public MockModel(StringBuilder log) {
    this.log = log;
  }

  @Override
  public Image flipImageHorizontally(Image image) {
    return null;
  }

  @Override
  public Image flipImageVertically(Image image) {
    return null;
  }

  @Override
  public Image brightenImage(Image image, int value) {
    return null;
  }

  @Override
  public Image darkenImage(Image image, int value) {
    return null;
  }

  @Override
  public Image createValueComponentOfImage(Image image) {
    return null;
  }

  @Override
  public Image createIntensityComponentOfImage(Image image) {
    return null;
  }

  @Override
  public Image createLumaComponentOfImage(Image image) {
    return null;
  }

  @Override
  public Image[] splitIntoLIV(Image image) {
    return new Image[0];
  }

  @Override
  public Image createRedComponentOfImage(Image image) {
    return null;
  }

  @Override
  public Image createGreenComponentOfImage(Image image) {
    return null;
  }

  @Override
  public Image createBlueComponentOfImage(Image image) {
    return null;
  }

  @Override
  public Image combineRGBImages(Image[] images) {
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
