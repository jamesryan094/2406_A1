package GUI;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by james on 18/10/2016.
 */
public class CheckNumChars implements KeyListener {
    JPanel inputPanel;
    JTextField usernameTextField;
    JRadioButton threePlayer;
    JRadioButton fourPlayer;
    JRadioButton fivePlayer;

//    public CheckNumChars(JPanel inputPanel) {
//        this.inputPanel=inputPanel;
//        usernameText = inputPanel.get;
//    }

    public CheckNumChars(JTextField usernameTextField, JRadioButton[] numPlayersButtonGroup) {
        this.usernameTextField = usernameTextField;
        threePlayer = numPlayersButtonGroup[0];
        fourPlayer = numPlayersButtonGroup[1];
        fivePlayer = numPlayersButtonGroup[2];
    }

    @Override
    public void keyTyped(KeyEvent e) {

        char myChar = e.getKeyChar();
        System.out.println(myChar);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        String currentWord = usernameTextField.getText();
        if (wordNotBlank(currentWord)){
            threePlayer.setEnabled(true);
            fourPlayer.setEnabled(true);
            fivePlayer.setEnabled(true);
        }
        else{
            threePlayer.setEnabled(false);
            fourPlayer.setEnabled(false);
            fivePlayer.setEnabled(false);
        }
        System.out.println("Current Word(Key Released): " + currentWord);
    }

    public static boolean wordNotBlank(String userName){
//        boolean hasLetters = false;
        String strippedWord = userName.replaceAll("\\s+", "");
        return(strippedWord.length()>0);


    }
}
