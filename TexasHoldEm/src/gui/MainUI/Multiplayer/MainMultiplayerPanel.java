package gui.MainUI.Multiplayer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMultiplayerPanel extends JPanel implements ActionListener{

    JPanel northPanel = new JPanel();
        JPanel northLabelHolder = new JPanel();
            JLabel northLabel = new JLabel("MULTIPLAYER MENU");

    JPanel centerPanel = new JPanel();
        JPanel buttonHolders = new JPanel();    BoxLayout centerBox = new BoxLayout(buttonHolders, BoxLayout.Y_AXIS);


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
