package com.neu.image_manipulation.model;

import com.neu.image_manipulation.model.entity.Image;
import java.io.File;
import java.io.IOException;

/**
 * This interface list the image modification methods we are going to provide to the user.
 */
public interface ImageManipulation {

  /**
   * This method loads a ppm image.
   * @param filename ppm image file location.
   */
  Image loadImageInPPM(String filename) throws IOException;




  void generateImage(Image image, String filename);


  void runScript(String filename);





}
