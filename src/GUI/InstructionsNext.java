package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Created by james on 9/10/2016. */
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