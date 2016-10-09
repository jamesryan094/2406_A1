package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Created by james on 8/10/2016. */
public class MineralST_GUI {
  private JPanel MineralST;
  private JPanel mainMenu;
  private JLabel title;
  private JButton buttonPlayGame;
  private JButton buttonInstructions;
  private JButton buttonTrumpHierarchies;
  private JButton buttonQuit;
  private JPanel instructions;
  private JPanel trumpHierarchies;
  private JButton buttonBack1;
  private JButton buttonNext;
  private JButton buttonMenu;
  private JButton buttonBack2;
  private JButton button2;
  private JButton button3;

  public MineralST_GUI() {

      //For selecting Quit from the main menu
      buttonQuit.addActionListener(
              new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      int quitChoice =
                              JOptionPane.showConfirmDialog(
                                      null,
                                      "Are you sure you would like to quit?",
                                      "Quit Confirmation",
                                      JOptionPane.YES_NO_OPTION);
                      if (quitChoice == JOptionPane.YES_OPTION) {
                          System.exit(0);
                      }
                  }
              });

      //For selecting Instructions  from the main menu
      buttonInstructions.addActionListener(
              new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      CardLayout MineralSTLayout = (CardLayout) MineralST.getLayout();
                      MineralSTLayout.show(MineralST, "instructions");
                  }
              });

      //For selecting Trump Hierarchies from te main menu
      buttonTrumpHierarchies.addActionListener(
              new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      CardLayout MineralSTLayout = (CardLayout) MineralST.getLayout();
                      MineralSTLayout.show(MineralST, "trumpHierarchies");
                  }
              });

      //For selecting next card on instructions1
      buttonNext.addActionListener(new InstructionsNext(instructions));


      //For selecting Menu on instructions1
      buttonMenu.addActionListener(new InstructionsMenu(MineralST));
  }

  public static void main(String[] args) {
    JFrame mineralST = new JFrame("Mineral Supertrumps!");
    mineralST.setSize(700, 700);
    mineralST.setContentPane(new MineralST_GUI().MineralST);
    mineralST.setVisible(true);
    mineralST.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }
}
