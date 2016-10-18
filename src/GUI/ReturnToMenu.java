package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Created by james on 9/10/2016. */
class ReturnToMenu implements ActionListener {
  private JPanel mineralSTContainer;
  private CardLayout mineralSTLayout;

  ReturnToMenu(JPanel mineralST) {
    mineralSTContainer = mineralST;
    mineralSTLayout = (CardLayout) mineralST.getLayout();
  }

  public void actionPerformed(ActionEvent e) {

    mineralSTLayout.show(mineralSTContainer, "mainMenu");
  }
}

