package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Displays the Prepare Game Screen to the user
 * Created by james on 14/10/2016. */
class PreGameScreen implements ActionListener {
  private JPanel mineralSTContainer;
  private CardLayout mineralSTLayout;

  PreGameScreen(JPanel mineralST) {
    mineralSTContainer = mineralST;
    mineralSTLayout = (CardLayout) mineralST.getLayout();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    mineralSTLayout.show(mineralSTContainer, "preparePlayGame");
  }
}
