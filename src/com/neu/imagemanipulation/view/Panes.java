package com.neu.imagemanipulation.view;

import java.awt.*;

import javax.swing.*;


public class Panes extends JPanel {

  public Panes(Boolean visibility) {
    super();
    setVisible(visibility);
    setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

  }

}
