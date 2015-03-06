package gui.MainUI.GameUI;


import backend.gameLogic.OfflineMode.Player;
import gui.Window;

import javax.swing.*;

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
    ImageIcon blankCard = new ImageIcon(Window.prop.getProperty("app.imageFolder") + "//blank_card.png");
    public PlayerPanel(int layout, Player player){
        for(int x = 0; x < 2; x++){
            cardHolder[x] = new JPanel();
            cardLabel[x] = new JLabel(blankCard);
            cardHolder[x].add(cardLabel[x]);
            cardPanel.add(cardHolder[x]);
        }
        cardPanel.setLayout(cardBox);
        lastMovePanel.add(lastLabel);
        namePanel.add(nameLabel);
        moneyPanel.add(moneyLabel);

        if(layout == HORIZONTAL){
            mainBox = new BoxLayout(this, BoxLayout.X_AXIS);
            setLayout(mainBox);
            JPanel right = new JPanel();    BoxLayout rightBox = new BoxLayout(right, BoxLayout.Y_AXIS);
            JPanel left = new JPanel(); BoxLayout leftBox = new BoxLayout(left, BoxLayout.Y_AXIS);
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
        lastLabel.setText(player.getLastMove().toString());
        moneyLabel.setText("$" + Double.toString(player.getPot()));
    }
}
