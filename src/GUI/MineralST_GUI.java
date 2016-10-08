package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by james on 8/10/2016.
 */
public class MineralST_GUI {
    private JPanel MineralST;
    private JPanel mainMenu;
    private JLabel title;
    private JButton buttonPlayGame;
    private JButton buttonInstructions;
    private JButton buttonTrumpHierarchies;
    private JButton buttonQuit;

    public MineralST_GUI() {
    buttonQuit.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            int quitChoice = JOptionPane.showConfirmDialog(
                null, "Are you sure you would like to quit?", "Quit Confirmation", JOptionPane.YES_NO_OPTION);
              if(quitChoice == JOptionPane.YES_OPTION){
                  System.exit(0);
              }
          }
        });
    }

    public static void main(String[] args) {
      JFrame mineralST = new JFrame("Mineral Supertrumps!");
      mineralST.setSize(700, 700);
      mineralST.setContentPane(new MineralST_GUI().MineralST);
      mineralST.setVisible(true);
      mineralST.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }
}
