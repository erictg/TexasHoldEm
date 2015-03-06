package gui.MainUI;


import gui.Panels;
import gui.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel implements ActionListener {

    Window window;

    JPanel northPanel = new JPanel();
        JPanel northLabelHolder = new JPanel();
            JLabel northLabel = new JLabel();

    JPanel centerPanel = new JPanel();
        JPanel buttonHolder = new JPanel(new GridLayout(4,1,4,4));
            JButton multiplayerButton = new JButton("MULTIPLAYER");
            JButton singlePlayerButton = new JButton("SINGLE PLAYER");
            JButton optionsButton = new JButton("OPTIONS");
            JButton logoutButton = new JButton("LOG OUT");


    public MainPanel(Window window){
        super(new BorderLayout());
        this.window = window;
        construct();
    }

    private void construct(){
        northLabel.setText("Welcome back " + window.user.getName() + "!");
        northPanel.add(northLabelHolder.add(northLabel));
        add(BorderLayout.NORTH, northPanel);

        buttonHolder.add(new JPanel().add(singlePlayerButton));
        buttonHolder.add(new JPanel().add(multiplayerButton)); multiplayerButton.addActionListener(this);
        singlePlayerButton.addActionListener(this);
        buttonHolder.add(new JPanel().add(optionsButton)); optionsButton.addActionListener(this);
        buttonHolder.add(new JPanel().add(logoutButton));  logoutButton.addActionListener(this);
        centerPanel.add(buttonHolder);
        add(BorderLayout.CENTER, centerPanel);
        JPanel southPanel = new JPanel();
            JPanel southLabelHolder = new JPanel();
                JLabel label = new JLabel("Texas Hold 'Em");
        southPanel.add(southLabelHolder.add(label));
        add(BorderLayout.SOUTH, southPanel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == singlePlayerButton){
            window.switchPanels(Panels.SinglePlayerOptions);
        }

        if(e.getSource() == multiplayerButton){
            window.switchPanels(Panels.Multiplayer);
        }
    }

}
