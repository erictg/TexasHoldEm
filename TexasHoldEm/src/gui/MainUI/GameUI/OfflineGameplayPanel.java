package gui.MainUI.GameUI;

import backend.User;
import backend.gameLogic.OfflineMode.AIUser;
import backend.gameLogic.OfflineMode.OfflineUser;
import backend.gameLogic.OfflineMode.Player;
import gui.Window;

public class OfflineGameplayPanel extends GameplayPanel {

    Player[] players;

    public OfflineGameplayPanel(Window w, int aiCount,double pot, User user){
        super(w);
        players = new Player[aiCount + 1];
        players[0] = new OfflineUser(pot, user);
        for(int x = 1; x < players.length; x++){
            players[x] = new AIUser(pot);
        }
        updatePanels();
    }

    public void updatePanels(){
        for(int x = 0; x < players.length; x++){
            System.out.println(playerPanels[x]);
            playerPanels[x].updateLabels(players[x]);
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
