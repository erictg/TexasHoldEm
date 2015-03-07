package gui.MainUI.GameUI;


import backend.gameLogic.OfflineMode.Player;
import gui.Window;

import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends JPanel{
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    BoxLayout mainBox;

    JPanel cardPanel = new JPanel(); BoxLayout cardBox = new BoxLayout(cardPanel, BoxLayout.X_AXIS);
        JPanel[] cardHolder = new JPanel[2];
            JLabel[] cardLabel = new JLabel[2];
    JPanel lastMovePanel = new JPanel();
        JLabel lastLabel = new JLabel("no move");

    JPanel namePanel = new JPanel();
        JLabel nameLabel = new JLabel("no player");

    JPanel moneyPanel = new JPanel();
        JLabel moneyLabel = new JLabel("null");
    ImageIcon blankCard = new ImageIcon(Window.prop.getProperty("app.imageFolder") + "//blank.jpg");


    public PlayerPanel(int layout, Player player, Color color){
        for(int x = 0; x < 2; x++){
            cardHolder[x] = new JPanel();
            cardHolder[x].setBackground(color);
            cardLabel[x] = new JLabel(blankCard);
            cardHolder[x].add(cardLabel[x]);
            cardPanel.add(cardHolder[x]);
        }
        cardPanel.setBackground(color);
        cardPanel.setLayout(cardBox);
        lastMovePanel.add(lastLabel);
        lastMovePanel.setBackground(color);
        namePanel.add(nameLabel);
        namePanel.setBackground(color);
        moneyPanel.add(moneyLabel);
        moneyPanel.setBackground(color);
        setBackground(color);
        if(layout == HORIZONTAL){
            mainBox = new BoxLayout(this, BoxLayout.X_AXIS);
            setLayout(mainBox);
            JPanel right = new JPanel();    BoxLayout rightBox = new BoxLayout(right, BoxLayout.Y_AXIS);    right.setBackground(color);
            JPanel left = new JPanel(); BoxLayout leftBox = new BoxLayout(left, BoxLayout.Y_AXIS);  left.setBackground(color);
            left.setLayout(leftBox);
            left.add(cardPanel);
            left.add(lastMovePanel);
            right.setLayout(rightBox);
            right.add(namePanel);
            right.add(moneyPanel);
            add(left);
            add(right);
        }else if(layout == VERTICAL){
            mainBox = new BoxLayout(this, BoxLayout.Y_AXIS);
            setLayout(mainBox);
            add(cardPanel);
            add(namePanel);
            add(lastMovePanel);
            add(moneyPanel);
        }
        if(player != null){
            nameLabel.setText(player.getName());
            lastLabel.setText("none");
            moneyLabel.setText("$" + Double.toString(player.getPot()));
        }

    }

    public void updateLabels(Player player){
        System.out.println(player.getLastMove() == null);
        if(player.getLastMove() != null){
            lastLabel.setText(player.getLastMove().toString());
        }else{
            lastLabel.setText("none");
        }
        moneyLabel.setText("$" + Double.toString(player.getPot()));

        nameLabel.setText(player.getName());
    }
}
