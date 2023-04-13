package com.neu.imagemanipulation.view;

import javax.swing.JPanel;

/**
 * This class represents panes on a JPanel, basically used to set their visibility through constructir.
 */
public class Panes extends JPanel {

  /**
   * Set the visibility of a pane.
   * @param visibility argument can be true or false.
   */
  public Panes(Boolean visibility) {
    super();
    setVisible(visibility);
  }
}
