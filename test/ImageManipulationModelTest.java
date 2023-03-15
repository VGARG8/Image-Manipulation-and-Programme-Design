import com.neu.image_manipulation.model.entity.Image;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ImageManipulationModelTest extends AbstractTestSetup{

  @Test
  public void testBrightenImageByPositiveValue(){

    try {
      convert.convertPngToPPM("./images/Koala-brighter-by-50.png","./images/Koala-brighter-by-50.ppm");
      Image brightImg = model.brightenImage(controller.loadImageInPPM("./Resources/Koala.ppm"),50);
      Image refImg = controller.loadImageInPPM("./images/Koala-brighter-by-50.ppm");

      assertEquals(brightImg.getHeight(), refImg.getHeight());
      assertEquals(brightImg.getWidth(), refImg.getWidth());
      assertEquals(brightImg.getMaxValue(), refImg.getMaxValue());
      assertTrue(comparePixels(brightImg,refImg));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testHorizontalFlip(){

    try {
      convert.convertPngToPPM("./images/Koala-horizontal.png","./images/Koala-horizontal.ppm");
      Image horizontalFlipImg = model.flipImageHorizontally((controller.loadImageInPPM("./Resources/Koala.ppm")));
      controller.generateImage("koala-horizontal",horizontalFlipImg);
      Image refImg = controller.loadImageInPPM("./images/Koala-horizontal.ppm");
      assertEquals(horizontalFlipImg.getHeight(), refImg.getHeight());
      assertEquals(horizontalFlipImg.getWidth(), refImg.getWidth());
      assertEquals(horizontalFlipImg.getMaxValue(), refImg.getMaxValue());
      assertTrue(comparePixels(horizontalFlipImg,refImg));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testVerticalFlip(){
    try {
      convert.convertPngToPPM("./images/Koala-vertical.png","./images/Koala-vertical.ppm");
      Image verticalFlipImg = model.flipImageVertically((controller.loadImageInPPM("./Resources/Koala.ppm")));
      controller.generateImage("koala-vertical",verticalFlipImg);
      Image refImg = controller.loadImageInPPM("./images/Koala-vertical.ppm");
      controller.generateImage("koala-vertical-ref",refImg);
      assertEquals(verticalFlipImg.getHeight(), refImg.getHeight());
      assertEquals(verticalFlipImg.getWidth(), refImg.getWidth());
      assertEquals(verticalFlipImg.getMaxValue(), refImg.getMaxValue());
      assertTrue(comparePixels(verticalFlipImg,refImg));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testBlueGreyScale(){
    try {
      convert.convertPngToPPM("./images/Koala-blue-greyscale.png","./images/Koala-blue-greyscale.ppm");
      Image blueGreyscaleImg = model.createBlueComponentOfImage(controller.loadImageInPPM("./Resources/Koala.ppm"));
      controller.generateImage("koala-blue-greyscale",blueGreyscaleImg);
      Image refImg = controller.loadImageInPPM("./images/Koala-blue-greyscale.ppm");
      controller.generateImage("koala-blue-greyscale-ref",refImg);
      assertEquals(blueGreyscaleImg.getHeight(), refImg.getHeight());
      assertEquals(blueGreyscaleImg.getWidth(), refImg.getWidth());
      assertEquals(blueGreyscaleImg.getMaxValue(), refImg.getMaxValue());
      assertTrue(comparePixels(blueGreyscaleImg,refImg));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testGreenGreyScale(){
    try {
      convert.convertPngToPPM("./images/Koala-green-greyscale.png","./images/Koala-green-greyscale.ppm");
      Image greenGreyscaleImg = model.createGreenComponentOfImage(controller.loadImageInPPM("./Resources/Koala.ppm"));
      controller.generateImage("koala-green-greyscale",greenGreyscaleImg);
      Image refImg = controller.loadImageInPPM("./images/Koala-green-greyscale.ppm");
      controller.generateImage("koala-green-greyscale-ref",refImg);
      assertEquals(greenGreyscaleImg.getHeight(), refImg.getHeight());
      assertEquals(greenGreyscaleImg.getWidth(), refImg.getWidth());
      assertEquals(greenGreyscaleImg.getMaxValue(), refImg.getMaxValue());
      assertTrue(comparePixels(greenGreyscaleImg,refImg));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testRedGreyScale(){
    try {
      convert.convertPngToPPM("./images/Koala-red-greyscale.png","./images/Koala-red-greyscale.ppm");
      Image redGreyscaleImg = model.createRedComponentOfImage(controller.loadImageInPPM("./Resources/Koala.ppm"));
      controller.generateImage("koala-red-greyscale",redGreyscaleImg);
      Image refImg = controller.loadImageInPPM("./images/Koala-red-greyscale.ppm");
      controller.generateImage("koala-red-greyscale-ref",refImg);
      assertEquals(redGreyscaleImg.getHeight(), refImg.getHeight());
      assertEquals(redGreyscaleImg.getWidth(), refImg.getWidth());
      assertEquals(redGreyscaleImg.getMaxValue(), refImg.getMaxValue());
      assertTrue(comparePixels(redGreyscaleImg,refImg));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testLumaGreyScale(){
    try {
      convert.convertPngToPPM("./images/Koala-luma-greyscale.png","./images/Koala-luma-greyscale.ppm");
      Image lumaGreyscaleImg = model.createLumaComponentOfImage(controller.loadImageInPPM("./Resources/Koala.ppm"));
      controller.generateImage("koala-luma-greyscale",lumaGreyscaleImg);
      Image refImg = controller.loadImageInPPM("./images/Koala-luma-greyscale.ppm");
      controller.generateImage("koala-luma-greyscale-ref",refImg);
      assertEquals(lumaGreyscaleImg.getHeight(), refImg.getHeight());
      assertEquals(lumaGreyscaleImg.getWidth(), refImg.getWidth());
      assertEquals(lumaGreyscaleImg.getMaxValue(), refImg.getMaxValue());
      assertTrue(comparePixels(lumaGreyscaleImg,refImg));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testValueGreyScale(){
    try {
      convert.convertPngToPPM("./images/Koala-value-greyscale.png","./images/Koala-value-greyscale.ppm");
      Image valueGreyscaleImg = model.createValueComponentOfImage(controller.loadImageInPPM("./Resources/Koala.ppm"));
      controller.generateImage("koala-value-greyscale",valueGreyscaleImg);
      Image refImg = controller.loadImageInPPM("./images/Koala-value-greyscale.ppm");
      controller.generateImage("koala-value-greyscale-ref",refImg);
      assertEquals(valueGreyscaleImg.getHeight(), refImg.getHeight());
      assertEquals(valueGreyscaleImg.getWidth(), refImg.getWidth());
      assertEquals(valueGreyscaleImg.getMaxValue(), refImg.getMaxValue());
      assertTrue(comparePixels(valueGreyscaleImg,refImg));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testIntensityGreyScale(){
    try {
      convert.convertPngToPPM("./images/Koala-intensity-greyscale.png","./images/Koala-intensity-greyscale.ppm");
      Image intensityGreyscaleImg = model.createIntensityComponentOfImage(controller.loadImageInPPM("./Resources/Koala.ppm"));
      controller.generateImage("koala-intensity-greyscale",intensityGreyscaleImg);
      Image refImg = controller.loadImageInPPM("./images/Koala-intensity-greyscale.ppm");
      controller.generateImage("koala-intensity-greyscale-ref",refImg);
      assertEquals(intensityGreyscaleImg.getHeight(), refImg.getHeight());
      assertEquals(intensityGreyscaleImg.getWidth(), refImg.getWidth());
      assertEquals(intensityGreyscaleImg.getMaxValue(), refImg.getMaxValue());
      assertTrue(comparePixels(intensityGreyscaleImg,refImg));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }



}