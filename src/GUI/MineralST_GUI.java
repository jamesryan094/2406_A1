package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Created by james on 8/10/2016. */
public class MineralST_GUI {
  public JPanel MineralST;
  private JPanel mainMenu;
  private JLabel title;
  private JButton buttonPlayGame;
  private JButton buttonInstructions;
  private JButton buttonTrumpHierarchies;
  private JButton buttonQuit;
  private JPanel instructions;
  private JPanel trumpHierarchies;
  private JButton buttonBack1;
  private JButton buttonNext1;
  private JButton buttonMenu1;
  private JButton buttonBack2;
  private JButton buttonNext2;
  private JButton buttonMenu2;
  private JPanel instructions1;
  private JPanel instructions2;
  private JLabel instructions1Label;
  private JPanel instructions3;
  private JButton buttonBack3;
  private JButton buttonMenu3;
  private JButton buttonNext3;
  private JButton buttonMenuTrump;
  private JPanel preparePlayGame;
  private JLabel prepareGameHeader;
  public JTextField usernameTextField;
  private JLabel numPlayersLabel;
  public JRadioButton a3PlayersRadioButton;
  public JRadioButton a4PlayersRadioButton;
  public JRadioButton a5PlayersRadioButton;
  private JButton prepareGameMenuButton;
  public JButton readyToPlayButton;
  private JPanel prepareGameInputPanel;
  private JPanel testCard;
  public JPanel testPanel;
  public JPanel playGame;
  public JPanel players;
  public JRadioButton[] numPlayersButtonGroup = {a3PlayersRadioButton, a4PlayersRadioButton, a5PlayersRadioButton};

  public MineralST_GUI() {

        //MENU SCREEN EVENT LISTENERS
    //For selecting Play Game from Main menu
    buttonPlayGame.addActionListener(new PreGameScreen(MineralST));

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

    //For selecting Instructions from the main menu
    //Todo: have instructions show first card after selecting from menu
    buttonInstructions.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            CardLayout MineralSTLayout = (CardLayout) MineralST.getLayout();
            MineralSTLayout.show(MineralST, "instructions");
          }
        });

    //For selecting Trump Hierarchies from the main menu
    buttonTrumpHierarchies.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            CardLayout MineralSTLayout = (CardLayout) MineralST.getLayout();
            MineralSTLayout.show(MineralST, "trumpHierarchies");
          }
        });



        //INSTRUCTIONS EVENT LISTENERS
    //For selecting Menu on instructions1
    buttonMenu1.addActionListener(new ReturnToMenu(MineralST));

      //TODO: DELETE DELETE DELETE DELETE
//      buttonMenu1.addActionListener(new RandomAction(title));
//      buttonMenu1.addActionListener(new ChangeButtonText(buttonQuit));

    //For selecting next card on instructions1
    buttonNext1.addActionListener(new InstructionsNext(instructions));

    //For selecting back on instructions2
    buttonBack2.addActionListener(new InstructionsBack(instructions));

    //For selecting menu on instructions2
    buttonMenu2.addActionListener(new ReturnToMenu(MineralST));

    //For selecting next on instructions2
    buttonNext2.addActionListener(new InstructionsNext((instructions)));

    //For selecting back on instructions3
    buttonBack3.addActionListener(new InstructionsBack(instructions));

    //For selecting menu on instructions3
    buttonMenu3.addActionListener(new ReturnToMenu(MineralST));


        //TRUMP CATEGORIES EVENT LISTENERS
    //For selecting menu from trump hierarchies
    buttonMenuTrump.addActionListener(new ReturnToMenu(MineralST));

      //PREPARE PLAY GAME EVENT LISTENERS
    prepareGameMenuButton.addActionListener(new ReturnToMenu(MineralST));


    usernameTextField.addKeyListener(new CheckNumChars(usernameTextField, numPlayersButtonGroup));
//    a4PlayersRadioButton.addActionListener(new TestRadioEvent(this));
//    prepareGameInputPanel.addContainerListener(new CheckButtonGroup(this));
    readyToPlayButton.addActionListener(new CheckReadytoPlay(this));
//    readyToPlayButton.addActionListener(new TestEvent(MineralST, testPanel));
  }

  public static void main(String[] args) {
    JFrame mineralST = new JFrame("Mineral Supertrumps!");
    mineralST.setSize(700, 700);
    mineralST.setContentPane(new MineralST_GUI().MineralST);
    mineralST.setVisible(true);
    mineralST.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }
}
