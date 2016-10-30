package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Takes user to previous instruction card in Main Menu option Instructions
 * Created by james on 12/10/2016. */
class InstructionsBack implements ActionListener {
  private JPanel instructionsContainer;
  private CardLayout instructionsLayout;

  InstructionsBack(JPanel instructions) {
    instructionsContainer = instructions;
    instructionsLayout = (CardLayout) instructions.getLayout();
  }

  public void actionPerformed(ActionEvent e) {
    instructionsLayout.previous(instructionsContainer);
  }
}
