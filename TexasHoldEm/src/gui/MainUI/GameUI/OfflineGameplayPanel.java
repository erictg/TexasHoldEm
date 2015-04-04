package gui.MainUI.GameUI;

import backend.User;
import backend.gameLogic.OfflineMode.AIUser;
import backend.gameLogic.OfflineMode.OfflineController;
import backend.gameLogic.OfflineMode.OfflineUser;
import backend.gameLogic.OfflineMode.Player;
import backend.gameLogic.PlayerStuff.Move;
import gui.Window;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class OfflineGameplayPanel extends GameplayPanel {



    Border active, notActive;
    //game rotation variables
    Timer t = new Timer(1000, this);
    final int USER_TIMER_MAX = 30;
    final int AI_TIMER_MAX = 5;
    int timerCount;
    int currentTimerMax;

    OfflineController controller;

    public OfflineGameplayPanel(Window w, int aiCount,double pot, User user){
        super(w, false);
        controller = new OfflineController(pot, aiCount, user);
        updatePanels();
        active= BorderFactory.createLineBorder(Color.RED);
        notActive = BorderFactory.createEmptyBorder();
        playerPanels[controller.activePlayerID].setBorder(active);
        currentTimerMax = USER_TIMER_MAX;
        timerCount = 0;
        t.start();
    }

    public void updatePanels(){
        for(int x = 0; x < controller.players.length; x++){
            playerPanels[x].updateLabels(controller.players[x]);
        }
    }

    @Override
    public void fold() {
        if(controller.isUserPlayerTurn()){
            controller.executeMove(Move.Fold());
        }
        executeAIRotation();
    }

    @Override
    public void bet() {
        if(controller.isUserPlayerTurn()){
            controller.executeMove(Move.Bet(Double.parseDouble(betArea.getText())));
        }
        executeAIRotation();
    }

    @Override
    public void check() {
        if(controller.isUserPlayerTurn()){
            controller.executeMove(Move.Check_Call());
        }
        executeAIRotation();
    }

    @Override
    public void tick(){
        timerCount++;
        if(controller.isUserPlayerTurn()){
            if(currentTimerMax == USER_TIMER_MAX && timerCount >= USER_TIMER_MAX) {
                controller.executeMove(Move.Fold());
                executeAIRotation();
            }
        }else{
            if(currentTimerMax == AI_TIMER_MAX && timerCount >= AI_TIMER_MAX){
                controller.executeAIMove();
                setActiveBorder();
                updatePanels();
                if(controller.activePlayerID == 0){
                    betArea.setEnabled(true);
                    betButton.setEnabled(true);
                    foldButton.setEnabled(true);
                    checkButton.setEnabled(true);
                    timerCount = 0;
                    currentTimerMax = USER_TIMER_MAX;
                    t.start();
                }else{
                    //another ai
                    timerCount = 0;
                    t.start();
                }

            }

        }
        System.out.println(controller.activePlayerID + " = activePlayerID");
        timeArea.setText(Integer.toString(timerCount));
        potArea.setText(Double.toString(controller.pot));
    }

    public void executeAIRotation(){
        betArea.setEnabled(false);
        betButton.setEnabled(false);
        foldButton.setEnabled(false);
        checkButton.setEnabled(false);
        setActiveBorder();
        timerCount = 0;
        currentTimerMax = AI_TIMER_MAX;
        t.start();
        updatePanels();
    }

    private void setActiveBorder(){
        for(int x = 0; x < playerPanels.length; x++){
            if(x == controller.activePlayerID){
                playerPanels[x].setBorder(active);
            }else {
                playerPanels[x].setBorder(notActive);
            }
        }
    }

}
