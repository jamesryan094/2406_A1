package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by james on 18/10/2016.
 */
public class TestRadioEvent implements ActionListener {
    JRadioButton threePlayer;
    JRadioButton fourPlayer;
    JRadioButton fivePlayer;

    public TestRadioEvent(MineralST_GUI mineralST) {
        threePlayer = mineralST.a3PlayersRadioButton;
        fourPlayer = mineralST.a4PlayersRadioButton;
        fivePlayer = mineralST.a5PlayersRadioButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (threePlayer.isSelected()){

        }
    }
}
