package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Takes user to next instruction card in Main Menu option Instructions.
 * Created by james on 9/10/2016.
 */
class InstructionsNext implements ActionListener {
  private JPanel instructionsContainer;
  private CardLayout instructionsLayout;

  InstructionsNext(JPanel instructions) {
    instructionsContainer = instructions;
    instructionsLayout = (CardLayout) instructions.getLayout();
  }

  public void actionPerformed(ActionEvent e) {
    instructionsLayout.next(instructionsContainer);
  }
}
