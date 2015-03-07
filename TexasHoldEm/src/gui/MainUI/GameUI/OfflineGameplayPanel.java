package gui.MainUI.GameUI;

import backend.User;
import backend.gameLogic.OfflineMode.AIUser;
import backend.gameLogic.OfflineMode.OfflineController;
import backend.gameLogic.OfflineMode.OfflineUser;
import backend.gameLogic.OfflineMode.Player;
import backend.gameLogic.PlayerStuff.Move;
import gui.Window;
import javax.swing.Timer;
public class OfflineGameplayPanel extends GameplayPanel {


    //game rotation variables
    Timer t = new Timer(1000, this);
    final int USER_TIMER_MAX = 30;
    final int AI_TIMER_MAX = 5;
    int timerCount;
    int currentTimerMax;

    OfflineController controller;

    public OfflineGameplayPanel(Window w, int aiCount,double pot, User user){
        super(w, false);

        updatePanels();
    }

    public void updatePanels(){
        for(int x = 0; x < controller.players.length; x++){
            System.out.println(playerPanels[x]);
            playerPanels[x].updateLabels(controller.players[x]);
        }
    }

    @Override
    public void fold() {

    }

    @Override
    public void bet() {

    }

    @Override
    public void check() {

    }

    @Override
    public void tick(){

    }

}
