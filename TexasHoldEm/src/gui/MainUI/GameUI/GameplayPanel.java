package gui.MainUI.GameUI;

import backend.User;
import backend.gameLogic.OfflineMode.Player;
import gui.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class GameplayPanel extends JPanel implements ActionListener {

    PlayerPanel[] playerPanels = new PlayerPanel[7];
    Window window;

    //north
    JPanel mainNorthPanel = new JPanel();   BoxLayout northBox = new BoxLayout(mainNorthPanel, BoxLayout.Y_AXIS);
        JPanel northLabelPanel = new JPanel();
            JLabel northLabel = new JLabel("Texas Hold 'Em");
        JPanel northPlayerPanel = new JPanel(); BoxLayout northPlayerBox = new BoxLayout(northPlayerPanel, BoxLayout.X_AXIS);

    //west
    JPanel mainWestPanel = new JPanel();    BoxLayout westBox = new BoxLayout(mainWestPanel, BoxLayout.Y_AXIS);

    //east
    JPanel mainEastPanel = new JPanel();    BoxLayout eastBox = new BoxLayout(mainEastPanel, BoxLayout.Y_AXIS);

    //south panel
    JPanel southPanel = new JPanel();   BoxLayout southBox = new BoxLayout(southPanel, BoxLayout.X_AXIS);
        JPanel foldButtonPanel = new JPanel();
            JButton foldButton = new JButton("FOLD");
        JPanel betButtonPanel = new JPanel();
            JButton betButton = new JButton("BET");
        JPanel betAreaPanel = new JPanel();
            JTextArea betArea = new JTextArea(1,6);
        JPanel checkButtonPanel = new JPanel();
            JButton checkButton = new JButton("CHECK");

    //centerPanel
    JPanel centerPanel = new JPanel();  BoxLayout centerBox = new BoxLayout(centerPanel, BoxLayout.Y_AXIS);
        JPanel centerHolder = new JPanel(); BoxLayout centerHolderBox = new BoxLayout(centerHolder, BoxLayout.Y_AXIS);
            JPanel cardHolder = new JPanel();   BoxLayout cardBox = new BoxLayout(cardHolder, BoxLayout.X_AXIS);
                JPanel[] cardPanel = new JPanel[5];
                    JLabel[] cards = new JLabel[5];
            JPanel dataPanel = new JPanel();    BoxLayout dataBox = new BoxLayout(dataPanel, BoxLayout.Y_AXIS);
                JPanel potPanel = new JPanel(); BoxLayout potBox = new BoxLayout(potPanel, BoxLayout.Y_AXIS);
                    JPanel potAreaPanel = new JPanel();
                        JTextArea potArea = new JTextArea(1,8);
                    JPanel potLabelPanel = new JPanel();
                        JLabel potLabel = new JLabel("POT");
                    JPanel lastWinnerPanel = new JPanel();
                        JLabel lastWinnerLabel = new JLabel("null");
                JPanel timerPanel = new JPanel();   BoxLayout timerBox = new BoxLayout(timerPanel, BoxLayout.Y_AXIS);
                    JPanel timeAreaPanel = new JPanel();
                        JTextArea timeArea = new JTextArea(1,5);
                    JPanel timerLabelPanel = new JPanel();
                        JLabel timerLabel = new JLabel("TIMER");

    ImageIcon blankCard = new ImageIcon(Window.prop.getProperty("app.imageFolder") + "//blank_card.png");
    ChatBox chatBox = new ChatBox();

    public GameplayPanel(Window window){
        super(new BorderLayout());
        this.window = window;
        construct();
    }

    private void construct(){
        playerPanels[0] = new PlayerPanel(PlayerPanel.HORIZONTAL, null);
        playerPanels[1] = new PlayerPanel(PlayerPanel.VERTICAL, null);
        playerPanels[2] = new PlayerPanel(PlayerPanel.VERTICAL, null);
        playerPanels[3] = new PlayerPanel(PlayerPanel.HORIZONTAL, null);
        playerPanels[4] = new PlayerPanel(PlayerPanel.HORIZONTAL, null);
        playerPanels[5] = new PlayerPanel(PlayerPanel.VERTICAL, null);
        playerPanels[6] = new PlayerPanel(PlayerPanel.VERTICAL, null);

        //north assembly
        mainNorthPanel.setLayout(northBox);
        mainNorthPanel.add(northLabelPanel.add(northLabel));
        northPlayerPanel.setLayout(northPlayerBox);
        northPlayerPanel.add(playerPanels[3]);
        northPlayerPanel.add(playerPanels[4]);
        mainNorthPanel.add(northPlayerPanel);
        add(BorderLayout.NORTH, mainNorthPanel);

        //west
        mainWestPanel.setLayout(westBox);
        mainWestPanel.add(playerPanels[1]);
        mainWestPanel.add(playerPanels[2]);
        add(BorderLayout.WEST, mainWestPanel);

        //east
        mainEastPanel.setLayout(eastBox);
        mainEastPanel.add(playerPanels[5]);
        mainEastPanel.add(playerPanels[6]);
        add(BorderLayout.EAST, mainEastPanel);

        //south
        foldButtonPanel.add(foldButton);    foldButton.addActionListener(this);
        betButtonPanel.add(betButton);  betButton.addActionListener(this);
        betAreaPanel.add(betArea);
        checkButtonPanel.add(checkButton);  checkButton.addActionListener(this);
        southPanel.setLayout(southBox);
        southPanel.add(foldButtonPanel);
        southPanel.add(betButtonPanel);
        southPanel.add(betAreaPanel);
        southPanel.add(checkButtonPanel);
        add(BorderLayout.SOUTH, southPanel);

        //center
        centerPanel.setLayout(centerBox);
        centerHolder.setLayout(centerHolderBox);

        cardHolder.setLayout(cardBox);
        for(int x = 0; x < 5; x++){
            cards[x] = new JLabel(blankCard);
            cardPanel[x] = new JPanel();
            cardPanel[x].add(cards[x]);
            cardHolder.add(cardPanel[x]);
        }

        centerHolder.add(cardHolder);

        dataPanel.setLayout(dataBox);
        potPanel.setLayout(potBox);
        potAreaPanel.add(potArea);
        potLabelPanel.add(potLabel);
        lastWinnerPanel.add(lastWinnerLabel);
        potPanel.add(potAreaPanel);
        potPanel.add(potLabelPanel);
        potPanel.add(lastWinnerPanel);
        dataPanel.add(potPanel);

        timerPanel.setLayout(timerBox);
        timeAreaPanel.add(timeArea);
        timerLabelPanel.add(timerLabel);
        timerPanel.add(timeAreaPanel);
        timerPanel.add(timerLabelPanel);
        dataPanel.add(timerPanel);
        centerHolder.add(dataPanel);
        centerPanel.add(playerPanels[0]);
        centerPanel.add(chatBox);
        add(BorderLayout.CENTER, centerPanel);

    }




    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == foldButton){
            fold();
        }

        if(e.getSource() == betButton){
            bet();
        }

        if(e.getSource() == checkButton){
            check();
        }

        tick();
    }

    public abstract void fold();

    public abstract void bet();

    public abstract void check();

    public abstract void tick();
}
