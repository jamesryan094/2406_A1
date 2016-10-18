package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by james on 18/10/2016.
 */
public class CheckReadytoPlay implements ActionListener {
    JRadioButton[] numPlayersGroup;
    private final JPanel testPanel;
    private JPanel mineralSTContainer;
    private CardLayout mineralSTLayout;
    MineralST_GUI gui;

    public CheckReadytoPlay(MineralST_GUI mineralST_gui) {
        numPlayersGroup = mineralST_gui.numPlayersButtonGroup;
        mineralSTContainer = mineralST_gui.MineralST;
        mineralSTLayout = (CardLayout) mineralSTContainer.getLayout();
        this.testPanel = mineralST_gui.testPanel;
        gui = mineralST_gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int numPlayers = -1;
        for(int i = 0; i<numPlayersGroup.length;++i){
            if (numPlayersGroup[i].isSelected()){
                System.out.println("okay");
                numPlayers = i;
            }
        }
        if ( CheckNumChars.wordNotBlank(gui.usernameTextField.getText())) {
            System.out.println("You Selected " + (numPlayers + 3));
            changeScreens();
        }
    }

    private void changeScreens() {
        JLabel myTestLabel = new JLabel("COOLBANANAZZZ");
        testPanel.setLayout(new GridLayout());
        testPanel.add(myTestLabel);
        mineralSTLayout.show(mineralSTContainer, "testCard");
    }


}
