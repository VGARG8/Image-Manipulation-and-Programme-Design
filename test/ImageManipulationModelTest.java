import com.neu.imagemanipulation.model.entity.Image;
import com.neu.imagemanipulation.model.impl.ImageManipulationModel;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Class to test different methods of the Model Class.
 */
public class ImageManipulationModelTest extends AbstractTestSetup {

  @Before
  public void testSetupModel() {
    model = new ImageManipulationModel();
    assertTrue(true);
  }


  @Test
  public void testBrightenImageByPositiveValue() {

    try {
      Image brightImg = model.brightenImage(
              controller.loadImageInPPM("./Res/gamecontroller.ppm"), 10);
      Image refImg = controller.loadImageInPPM("./Res/gamecontroller-brighter.ppm");

      assertEquals(brightImg.getHeight(), refImg.getHeight());
      assertEquals(brightImg.getWidth(), refImg.getWidth());
      assertEquals(brightImg.getMaxValue(), refImg.getMaxValue());
      assertTrue(comparePixels(brightImg, refImg));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testBrightenImageByZero() {

    try {
      Image brightImg = model.brightenImage(
              controller.loadImageInPPM("./Res/gamecontroller.ppm"), 0);
      Image refImg = controller.loadImageInPPM("./Res/gamecontroller.ppm");

      assertEquals(brightImg.getHeight(), refImg.getHeight());
      assertEquals(brightImg.getWidth(), refImg.getWidth());
      assertEquals(brightImg.getMaxValue(), refImg.getMaxValue());
      assertTrue(comparePixels(brightImg, refImg));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testHorizontalFlipGameController() {

    try {
      Image horizontalFlipImg = model.flipImageHorizontally((
              controller.loadImageInPPM("./Res/gamecontroller.ppm")));
      Image refImg = controller.loadImageInPPM("./res/gamecontroller-horizontal.ppm");
      assertEquals(horizontalFlipImg.getHeight(), refImg.getHeight());
      assertEquals(horizontalFlipImg.getWidth(), refImg.getWidth());
      assertEquals(horizontalFlipImg.getMaxValue(), refImg.getMaxValue());
      assertTrue(comparePixels(horizontalFlipImg, refImg));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testVerticalFlipGameController() {
    try {
      Image verticalFlipImg = model.flipImageVertically((
              controller.loadImageInPPM("./Res/gamecontroller.ppm")));
      Image refImg = controller.loadImageInPPM("./Res/gamecontroller-vertical.ppm");
      assertEquals(verticalFlipImg.getHeight(), refImg.getHeight());
      assertEquals(verticalFlipImg.getWidth(), refImg.getWidth());
      assertEquals(verticalFlipImg.getMaxValue(), refImg.getMaxValue());
      assertTrue(comparePixels(verticalFlipImg, refImg));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testBlueGreyScale() {
    try {

      Image blueGreyscaleImg = model.createBlueComponentOfImage(
              controller.loadImageInPPM("./Res/gamecontroller.ppm"));

      Image refImg = controller.loadImageInPPM("./Res/gamecontroller-blue.ppm");

      assertEquals(blueGreyscaleImg.getHeight(), refImg.getHeight());
      assertEquals(blueGreyscaleImg.getWidth(), refImg.getWidth());
      assertEquals(blueGreyscaleImg.getMaxValue(), refImg.getMaxValue());
      assertTrue(comparePixels(blueGreyscaleImg, refImg));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testGreenGreyScale() {
    try {

      Image greenGreyscaleImg = model.createGreenComponentOfImage(
              controller.loadImageInPPM("./Res/gamecontroller.ppm"));

      Image refImg = controller.loadImageInPPM("./Res/gamecontroller-green.ppm");

      assertEquals(greenGreyscaleImg.getHeight(), refImg.getHeight());
      assertEquals(greenGreyscaleImg.getWidth(), refImg.getWidth());
      assertEquals(greenGreyscaleImg.getMaxValue(), refImg.getMaxValue());
      assertTrue(comparePixels(greenGreyscaleImg, refImg));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testRedGreyScale() {
    try {

      Image redGreyscaleImg = model.createRedComponentOfImage(
              controller.loadImageInPPM("./Res/gamecontroller.ppm"));

      Image refImg = controller.loadImageInPPM("./Res/gamecontroller-red.ppm");

      assertEquals(redGreyscaleImg.getHeight(), refImg.getHeight());
      assertEquals(redGreyscaleImg.getWidth(), refImg.getWidth());
      assertEquals(redGreyscaleImg.getMaxValue(), refImg.getMaxValue());
      assertTrue(comparePixels(redGreyscaleImg, refImg));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testLumaGreyScale() {
    try {

      Image lumaGreyscaleImg = model.createLumaComponentOfImage(
              controller.loadImageInPPM("./Res/gamecontroller.ppm"));

      Image refImg = controller.loadImageInPPM("./Res/gamecontroller-luma.ppm");

      assertEquals(lumaGreyscaleImg.getHeight(), refImg.getHeight());
      assertEquals(lumaGreyscaleImg.getWidth(), refImg.getWidth());
      assertEquals(lumaGreyscaleImg.getMaxValue(), refImg.getMaxValue());
      assertTrue(comparePixels(lumaGreyscaleImg, refImg));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testValueGreyScale() {
    try {

      Image valueGreyscaleImg = model.createValueComponentOfImage(
              controller.loadImageInPPM("./Res/gamecontroller.ppm"));

      Image refImg = controller.loadImageInPPM("./Res/gamecontroller-value.ppm");

      assertEquals(valueGreyscaleImg.getHeight(), refImg.getHeight());
      assertEquals(valueGreyscaleImg.getWidth(), refImg.getWidth());
      assertEquals(valueGreyscaleImg.getMaxValue(), refImg.getMaxValue());
      assertTrue(comparePixels(valueGreyscaleImg, refImg));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testIntensityGreyScale() {
    try {

      Image intensityGreyscaleImg = model.createIntensityComponentOfImage(
              controller.loadImageInPPM("./Res/gamecontroller.ppm"));

      Image refImg = controller.loadImageInPPM("./Res/gamecontroller-intensity.ppm");

      assertEquals(intensityGreyscaleImg.getHeight(), refImg.getHeight());
      assertEquals(intensityGreyscaleImg.getWidth(), refImg.getWidth());
      assertEquals(intensityGreyscaleImg.getMaxValue(), refImg.getMaxValue());
      assertTrue(comparePixels(intensityGreyscaleImg, refImg));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}