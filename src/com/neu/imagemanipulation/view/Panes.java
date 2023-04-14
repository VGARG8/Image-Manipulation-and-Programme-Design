package com.neu.imagemanipulation.view;

import javax.swing.JPanel;


/**
 * This class extends the {@link JPanel} class to provide a customized panel that can be shown or hidden.
 */
public class Panes extends JPanel {

  /**
   * Constructs a new {@code Panes} object with the specified visibility.
   *
   * @param visibility {@code true} to make the panel visible; {@code false} to hide it.
   */
  public Panes(Boolean visibility) {
    super();
    setVisible(visibility);
  }
}

