package backend.gameLogic.OfflineMode;

import backend.gameLogic.PlayerStuff.Move;
import backend.gameLogic.PlayerStuff.Moves;
import backend.gameStuff.Deck;

public class OfflineController {

    public Deck deck;
    public double pot;
    public Player[] players;
    public int[] turn; // 0 for hasnt gone, 1 for has -1 for not in
    public int activePlayerID;
    public int round;
    public double highBet;


}
