package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by james on 19/10/2016.
 */
public class PreviousCard implements ActionListener {
    MineralST_GUI gui;
    JPanel cardContainer;
    CardLayout cardCardLayout;

    public PreviousCard(MineralST_GUI gui) {
        this.gui = gui;
        cardContainer = gui.userCards;
        cardCardLayout = (CardLayout)cardContainer.getLayout();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        cardCardLayout.previous(cardContainer);
    }
}
