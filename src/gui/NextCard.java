package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Gets the next card in the players hand and displays it in user hand (titled "Cards") section of
 * the Play Game screen.
 * Created by james on 19/10/2016.
 */
public class NextCard implements ActionListener {
  MineralST_GUI gui;
  JPanel cardContainer;
  CardLayout cardCardLayout;

  public NextCard(MineralST_GUI gui) {
    this.gui = gui;
    cardContainer = gui.userCards;
    cardCardLayout = (CardLayout) cardContainer.getLayout();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    cardCardLayout.next(cardContainer);
  }
}
