package gui.MainUI.SinglePlayer;
import gui.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;

public class SinglePlayerOptions extends JPanel implements ActionListener {

    Window window;

    JPanel mainNorthPanel = new JPanel();
        JPanel northLabelHolder = new JPanel();
            JLabel northLabel = new JLabel("Singleplayer Setup");
    JPanel centerPanel = new JPanel();  BoxLayout box = new BoxLayout(centerPanel, BoxLayout.Y_AXIS);
        JPanel aiCountLabelPanel = new JPanel();
            JLabel aiCountLabel = new JLabel("AI Count");
        JPanel aiCountDropDownPanel = new JPanel();
            String[] aiCount = {"1", "2", "3", "4", "5", "6"};
            JComboBox<String> aiCountDrop = new JComboBox<String>(aiCount);
        JPanel startingMoneyPanel = new JPanel();
            JLabel startingMoneyLabel = new JLabel("Starting Money");
        JPanel startingMoneyDropDownPanel = new JPanel();
            String[] startingMoney;
            JComboBox<String> startingMoneyDropDown;
        JPanel difficultyLabelPanel = new JPanel();
            JLabel difficultyLabel = new JLabel("Difficulty");
        JPanel difficultyDropDownPanel = new JPanel();
            String[] difficulty = {"Beginner", "Easy", "Medium", "Hard"};
            JComboBox<String> difficultyDropDown = new JComboBox<String>(difficulty);
        JPanel goButtonPanel = new JPanel();
            JButton goButton = new JButton("GO");

    public SinglePlayerOptions(Window window){
        super(new BorderLayout());
        this.window = window;
        construct();
    }

    private void construct(){
        aiCountLabelPanel.add(aiCountLabel);
        aiCountDropDownPanel.add(aiCountDrop);
        startingMoneyPanel.add(startingMoneyLabel);

        startingMoney = new String[31];

        for(int x = 1; x <= 30; x++){
            startingMoney[x] = Integer.toString(x*100);
        }

        startingMoneyDropDown = new JComboBox<String>(startingMoney);
        startingMoneyDropDownPanel.add(startingMoneyDropDown);

        difficultyLabelPanel.add(difficultyLabel);
        difficultyDropDownPanel.add(difficultyDropDown);
        goButtonPanel.add(goButton);    goButton.addActionListener(this);

        centerPanel.setLayout(box);
        centerPanel.add(aiCountLabelPanel);
        centerPanel.add(aiCountDropDownPanel);
        centerPanel.add(startingMoneyPanel);
        centerPanel.add(startingMoneyDropDownPanel);
        centerPanel.add(difficultyLabelPanel);
        centerPanel.add(difficultyDropDownPanel);
        centerPanel.add(goButtonPanel);
        add(BorderLayout.CENTER, centerPanel);

        mainNorthPanel.add(northLabelHolder.add(northLabel));
        add(BorderLayout.NORTH, mainNorthPanel);
        JPanel southPanel = new JPanel();
            JPanel southLabelHolder = new JPanel();
                JLabel label = new JLabel("Texas Hold 'Em");
        southPanel.add(southLabelHolder.add(label));
        add(BorderLayout.SOUTH, southPanel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == goButton){
            double pot = Double.parseDouble(startingMoneyDropDown.getItemAt(startingMoneyDropDown.getSelectedIndex()));
            int x = Integer.parseInt(aiCountDrop.getItemAt(aiCountDrop.getSelectedIndex()));
            System.out.println(pot + ", " + x);
            window.startOfflineGame(x, pot);
        }
    }
}
