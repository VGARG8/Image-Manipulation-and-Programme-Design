package com.neu.imagemanipulation.model.impl;

import java.util.LinkedHashSet;
import java.util.Set;

public class ModelGui extends AdvancedImageManipulationModel{
  public Set<String> getStoredImageNames() {
    Set<String> keys = new LinkedHashSet<>(imagesMap.keySet());
    return keys;
  }
}
