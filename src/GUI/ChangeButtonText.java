package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by james on 13/10/2016.
 */
public class ChangeButtonText implements ActionListener {
    private final JButton quitButton;

    public ChangeButtonText(JButton title) {
        this.quitButton = title;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Gobbledeogook");
        quitButton.setText("SECOND STRING");
    }
}
