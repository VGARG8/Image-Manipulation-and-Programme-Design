import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.neu.imagemanipulation.model.entity.ImageInterface;
import com.neu.imagemanipulation.model.impl.AdvancedImageManipulationModel;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

/**
 * Class to test different methods of the Model Class.
 */
public class ImageManipulationModelTest extends AbstractTestSetup {

  @Before
  public void testSetupModel() {
    model = new AdvancedImageManipulationModel();

  }


  @Test
  public void testBrightenImageByPositiveValue() {

    try {
      ImageInterface brightImg = model.brightenImage(
          controller.loadImageInPPM("./Res/gamecontroller.ppm"), 10);
      ImageInterface refImg = controller.loadImageInPPM("./Res/gamecontroller-brighter.ppm");

      assertEquals(brightImg.getHeight(), refImg.getHeight());
      assertEquals(brightImg.getWidth(), refImg.getWidth());
      assertEquals(brightImg.getMaxValue(), refImg.getMaxValue());
      assertTrue(comparePixels(brightImg, refImg));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testBrightenPngImage() {

    try {
      ImageInterface brightImg = model.brightenImage(
          controller.loadStandardFormat("./Res/face.png"), 20);
      ImageInterface refImg = controller.loadStandardFormat("./Res/face-brightenImage-20.png");

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
      ImageInterface brightImg = model.brightenImage(
          controller.loadImageInPPM("./Res/gamecontroller.ppm"), 0);
      ImageInterface refImg = controller.loadImageInPPM("./Res/gamecontroller.ppm");

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
      ImageInterface horizontalFlipImg = model.flipImageHorizontally((
          controller.loadImageInPPM("./Res/gamecontroller.ppm")));
      ImageInterface refImg = controller.loadImageInPPM("./res/gamecontroller-horizontal.ppm");
      assertEquals(horizontalFlipImg.getHeight(), refImg.getHeight());
      assertEquals(horizontalFlipImg.getWidth(), refImg.getWidth());
      assertEquals(horizontalFlipImg.getMaxValue(), refImg.getMaxValue());
      assertTrue(comparePixels(horizontalFlipImg, refImg));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testHorizontalFlipFace() {

    try {
      ImageInterface horizontalFlipImg = model.flipImageHorizontally((
          controller.loadStandardFormat("./Res/face.png")));
      ImageInterface refImg = controller.loadStandardFormat("./res/face-flip-horizontal.png");
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
      ImageInterface verticalFlipImg = model.flipImageVertically((
          controller.loadImageInPPM("./Res/gamecontroller.ppm")));
      ImageInterface refImg = controller.loadImageInPPM("./Res/gamecontroller-vertical.ppm");
      assertEquals(verticalFlipImg.getHeight(), refImg.getHeight());
      assertEquals(verticalFlipImg.getWidth(), refImg.getWidth());
      assertEquals(verticalFlipImg.getMaxValue(), refImg.getMaxValue());
      assertTrue(comparePixels(verticalFlipImg, refImg));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testVerticalFlipFace() {

    try {
      ImageInterface verticalFlipImg = model.flipImageVertically((
          controller.loadStandardFormat("./Res/face.png")));
      ImageInterface refImg = controller.loadStandardFormat("./res/face-flip-vertical.png");
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

      ImageInterface blueGreyscaleImg = model.createBlueComponentOfImage(
          controller.loadImageInPPM("./Res/gamecontroller.ppm"));

      ImageInterface refImg = controller.loadImageInPPM("./Res/gamecontroller-blue.ppm");

      assertEquals(blueGreyscaleImg.getHeight(), refImg.getHeight());
      assertEquals(blueGreyscaleImg.getWidth(), refImg.getWidth());
      assertEquals(blueGreyscaleImg.getMaxValue(), refImg.getMaxValue());
      assertTrue(comparePixels(blueGreyscaleImg, refImg));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testBlueGreyScaleFace() {
    try {

      ImageInterface blueGreyscaleImg = model.createBlueComponentOfImage(
          controller.loadStandardFormat("./Res/face.png"));

      ImageInterface refImg = controller.loadStandardFormat("./Res/face-blue.png");

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

      ImageInterface greenGreyscaleImg = model.createGreenComponentOfImage(
          controller.loadImageInPPM("./Res/gamecontroller.ppm"));

      ImageInterface refImg = controller.loadImageInPPM("./Res/gamecontroller-green.ppm");

      assertEquals(greenGreyscaleImg.getHeight(), refImg.getHeight());
      assertEquals(greenGreyscaleImg.getWidth(), refImg.getWidth());
      assertEquals(greenGreyscaleImg.getMaxValue(), refImg.getMaxValue());
      assertTrue(comparePixels(greenGreyscaleImg, refImg));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testGreenGreyScaleFace() {
    try {

      ImageInterface greenGreyscaleImg = model.createGreenComponentOfImage(
          controller.loadStandardFormat("./Res/face.png"));

      ImageInterface refImg = controller.loadStandardFormat("./Res/face-green.png");

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

      ImageInterface redGreyscaleImg = model.createRedComponentOfImage(
          controller.loadImageInPPM("./Res/gamecontroller.ppm"));

      ImageInterface refImg = controller.loadImageInPPM("./Res/gamecontroller-red.ppm");

      assertEquals(redGreyscaleImg.getHeight(), refImg.getHeight());
      assertEquals(redGreyscaleImg.getWidth(), refImg.getWidth());
      assertEquals(redGreyscaleImg.getMaxValue(), refImg.getMaxValue());
      assertTrue(comparePixels(redGreyscaleImg, refImg));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testRedGreyScaleFace() {
    try {

      ImageInterface redGreyscaleImg = model.createRedComponentOfImage(
          controller.loadStandardFormat("./Res/face.png"));

      ImageInterface refImg = controller.loadStandardFormat("./Res/face-red.png");

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

      ImageInterface lumaGreyscaleImg = model.createLumaComponentOfImage(
          controller.loadImageInPPM("./Res/gamecontroller.ppm"));

      ImageInterface refImg = controller.loadImageInPPM("./Res/gamecontroller-luma.ppm");

      assertEquals(lumaGreyscaleImg.getHeight(), refImg.getHeight());
      assertEquals(lumaGreyscaleImg.getWidth(), refImg.getWidth());
      assertEquals(lumaGreyscaleImg.getMaxValue(), refImg.getMaxValue());
      assertTrue(comparePixels(lumaGreyscaleImg, refImg));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testLumaGreyScaleFace() {
    try {

      ImageInterface lumaGreyscaleImg = model.createLumaComponentOfImage(
          controller.loadStandardFormat("./Res/face.png"));

      ImageInterface refImg = controller.loadStandardFormat("./Res/face-luma.png");

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

      ImageInterface valueGreyscaleImg = model.createValueComponentOfImage(
          controller.loadImageInPPM("./Res/gamecontroller.ppm"));

      ImageInterface refImg = controller.loadImageInPPM("./Res/gamecontroller-value.ppm");

      assertEquals(valueGreyscaleImg.getHeight(), refImg.getHeight());
      assertEquals(valueGreyscaleImg.getWidth(), refImg.getWidth());
      assertEquals(valueGreyscaleImg.getMaxValue(), refImg.getMaxValue());
      assertTrue(comparePixels(valueGreyscaleImg, refImg));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testValueGreyScaleFace() {
    try {

      ImageInterface valueGreyscaleImg = model.createValueComponentOfImage(
          controller.loadStandardFormat("./Res/face.png"));

      ImageInterface refImg = controller.loadStandardFormat("./Res/face-value.png");

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

      ImageInterface intensityGreyscaleImg = model.createIntensityComponentOfImage(
          controller.loadImageInPPM("./Res/gamecontroller.ppm"));

      ImageInterface refImg = controller.loadImageInPPM("./Res/gamecontroller-intensity.ppm");

      assertEquals(intensityGreyscaleImg.getHeight(), refImg.getHeight());
      assertEquals(intensityGreyscaleImg.getWidth(), refImg.getWidth());
      assertEquals(intensityGreyscaleImg.getMaxValue(), refImg.getMaxValue());
      assertTrue(comparePixels(intensityGreyscaleImg, refImg));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testIntensityGreyScaleFace() {
    try {

      ImageInterface intensityGreyscaleImg = model.createIntensityComponentOfImage(
          controller.loadStandardFormat("./Res/face.png"));

      ImageInterface refImg = controller.loadStandardFormat("./Res/face-intensity.png");

      assertEquals(intensityGreyscaleImg.getHeight(), refImg.getHeight());
      assertEquals(intensityGreyscaleImg.getWidth(), refImg.getWidth());
      assertEquals(intensityGreyscaleImg.getMaxValue(), refImg.getMaxValue());
      assertTrue(comparePixels(intensityGreyscaleImg, refImg));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testGreyScaleFace() {
    try {

      ImageInterface greyscaleImg = model.greyscale(
          controller.loadStandardFormat("./Res/face.png"));

      ImageInterface refImg = controller.loadStandardFormat("./Res/face-grey.png");

      assertEquals(greyscaleImg.getHeight(), refImg.getHeight());
      assertEquals(greyscaleImg.getWidth(), refImg.getWidth());
      assertEquals(greyscaleImg.getMaxValue(), refImg.getMaxValue());
      assertTrue(comparePixels(greyscaleImg, refImg));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testSepiaFace() {
    try {

      ImageInterface sepiaImg = model.sepiaTone(
          controller.loadStandardFormat("./Res/face.png"));

      ImageInterface refImg = controller.loadStandardFormat("./Res/face-sepia.png");

      assertEquals(sepiaImg.getHeight(), refImg.getHeight());
      assertEquals(sepiaImg.getWidth(), refImg.getWidth());
      assertEquals(sepiaImg.getMaxValue(), refImg.getMaxValue());
      assertTrue(comparePixels(sepiaImg, refImg));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testBlurFace() {
    try {

      ImageInterface blurImg = model.blur(
          controller.loadStandardFormat("./Res/face.png"));

      ImageInterface refImg = controller.loadStandardFormat("./Res/face-blur.png");

      assertEquals(blurImg.getHeight(), refImg.getHeight());
      assertEquals(blurImg.getWidth(), refImg.getWidth());
      assertEquals(blurImg.getMaxValue(), refImg.getMaxValue());
      assertTrue(comparePixels(blurImg, refImg));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testSharpenFace() {
    try {

      ImageInterface sharpenImg = model.sharpen(
          controller.loadStandardFormat("./Res/face.png"));

      ImageInterface refImg = controller.loadStandardFormat("./Res/face-sharpen.png");

      assertEquals(sharpenImg.getHeight(), refImg.getHeight());
      assertEquals(sharpenImg.getWidth(), refImg.getWidth());
      assertEquals(sharpenImg.getMaxValue(), refImg.getMaxValue());
      assertTrue(comparePixels(sharpenImg, refImg));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testDitherFace() {
    try {

      ImageInterface ditherImg = model.dither(
          controller.loadStandardFormat("./Res/face.png"));

      ImageInterface refImg = controller.loadStandardFormat("./Res/face-dither.png");

      assertEquals(ditherImg.getHeight(), refImg.getHeight());
      assertEquals(ditherImg.getWidth(), refImg.getWidth());
      assertEquals(ditherImg.getMaxValue(), refImg.getMaxValue());
      assertTrue(comparePixels(ditherImg, refImg));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


}