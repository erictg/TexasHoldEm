package gui.MainUI.GameUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatBox extends JPanel implements ActionListener{

    //center panel

    //south panel
    JPanel southPanel = new JPanel();   BoxLayout southBox = new BoxLayout(southPanel, BoxLayout.X_AXIS);
        JTextArea input = new JTextArea(1, 30);
        JButton sendButton = new JButton();

    public ChatBox(){
        super(new BorderLayout());
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
