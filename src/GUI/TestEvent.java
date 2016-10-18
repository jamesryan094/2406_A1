package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by james on 14/10/2016.
 */
public class TestEvent implements ActionListener {
    private final JPanel testPanel;
    private JPanel mineralSTContainer;
    private CardLayout mineralSTLayout;

    TestEvent(JPanel mineralST, JPanel testPanel) {
        mineralSTContainer = mineralST;
        mineralSTLayout = (CardLayout) mineralST.getLayout();
        this.testPanel = testPanel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JLabel myTestLabel = new JLabel("COOLBANANAZZZ");
        Icon testPic = new ImageIcon("GUI/images/playerIcon.jpg");
        JLabel testPicLabel = new JLabel(testPic);
        testPanel.setLayout(new GridLayout());
        testPanel.add(myTestLabel);
        testPanel.add(testPicLabel);
        mineralSTLayout.show(mineralSTContainer, "testCard");
    }
}
