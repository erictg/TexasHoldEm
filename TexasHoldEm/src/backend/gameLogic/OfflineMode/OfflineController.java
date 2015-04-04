package backend.gameLogic.OfflineMode;

import backend.User;
import backend.gameLogic.PlayerStuff.Move;
import backend.gameLogic.PlayerStuff.Moves;
import backend.gameStuff.Card;
import backend.gameStuff.Deck;

import java.util.ArrayList;

public class OfflineController {

    public Deck deck; //deck the table has
    public ArrayList<Card> table; //table cards
    public double pot; //pot
    public Player[] players; // players in the game, max ai is 6
    public enum RoundPosition{HAS_GONE, YET_TO_GO, NOT_IN};//enum to determine player round position
    public RoundPosition[] turn; //parallels players
    public boolean[] bet;//parallels players
    public int activePlayerID; //the id of the active player
    public int startingID; //id of the round starting player
    public int round; //round count
    public double highBet; //highest bet on the table


    public OfflineController(double initialPot, int aiCount, User user){
        initializeEssentials(aiCount, initialPot, user);

        startupGameVariables(aiCount);
        nextRound();
    }

    //constructor methods
    private void initializeEssentials(int aiCount, double initialPot, User user){
        deck = new Deck();
        table = new ArrayList<Card>(5);
        players = new Player[aiCount + 1];
        turn = new RoundPosition[aiCount + 1];
        players[0] = new OfflineUser(initialPot, user);
        turn[0] = RoundPosition.YET_TO_GO;
        for(int x = 1; x < players.length; x++){
            players[x] = new AIUser(initialPot);
            turn[x] = RoundPosition.YET_TO_GO;
        }
    }

    private void startupGameVariables(int aiCount){
        startingID = 0;
        activePlayerID = 0;
        round = -1;
        pot = 0d;
        highBet = 0d;
        bet = new boolean[++aiCount];
    }

    //end constructor methods

    public void nextRound(){
        round++; //goes to the next round
        if(round == 0){
            System.out.println("giving out cards");
            for(Player p : players){
                p.giveNewCards(deck.getNextCard(), deck.getNextCard());
            }
        }else if(round == 1){
            for(int x = 0; x < 3; x++){
                table.add(deck.getNextCard());
            }
        }else if(round == 2 || round == 3){
            table.add(deck.getNextCard());
        }else if(round == 4){
            //do something to decide winner and reset
            reset();
        }
    }

    public void reset(){
        round = -1;
        table.clear();
        pot = 0;
        highBet = 0d;
        for(RoundPosition x : turn){
            x = RoundPosition.YET_TO_GO;
        }
        if(startingID + 1 != players.length){
            startingID = 0;
        }else{
            startingID++;
        }
    }

    public boolean executeMove(Move move){
        if(move.getMove() == Moves.CHECK_CALL){
            double d = players[activePlayerID].subtractFromPot(highBet);
            pot += d;
            turn[activePlayerID] = RoundPosition.HAS_GONE;
        }else if(move.getMove() == Moves.FOLD){
            turn[activePlayerID] = RoundPosition.NOT_IN;
        }else{//bet
            double bet = move.getBet();
            //if the bet exceeds the players total pot
            if(bet + highBet > players[activePlayerID].getPot() && !this.bet[activePlayerID]){
                double d = players[activePlayerID].subtractFromPot(players[activePlayerID].getPot());
                pot += d;
                turn[activePlayerID] = RoundPosition.HAS_GONE;
                this.bet[activePlayerID] = true;
            //this is saying that if they player hasnt already bet this round
            }else if(!this.bet[activePlayerID]){
                double d = players[activePlayerID].subtractFromPot(bet + highBet);
                pot += d;
                turn[activePlayerID] = RoundPosition.HAS_GONE;
                this.bet[activePlayerID] = true;
            //if the player did bet this round then fold
            }else {
                executeMove(Move.Fold());
            }
            for(RoundPosition x : turn){
                x = RoundPosition.YET_TO_GO;
            }
        }
        checkRoundOver();
        //ups active player id
        System.out.println(move.getMove().toString() + "was the executed move");
        nextPlayer();
       return false;

    }

    public void checkRoundOver(){
        for(RoundPosition x: turn){
            if(x == RoundPosition.YET_TO_GO){
                return;
            }
        }
        nextRound();
    }

    public boolean isUserPlayerTurn(){
        return activePlayerID == 0;
    }

    public void executeAIMove(){
        executeMove(players[activePlayerID].executeMove(table, pot, round));
    }

    public void nextPlayer(){
        if(++activePlayerID != players.length){
            activePlayerID++;
            if(turn[activePlayerID] == RoundPosition.NOT_IN){
                if(checkAllFold()){
                    return;
                }else{
                    nextPlayer();
                }
            }
        }else {
            activePlayerID = 0;
        }
    }

    public boolean checkAllFold(){
        for(RoundPosition r : turn){
            if(r != RoundPosition.NOT_IN){
                return false;
            }
        }

        return true;
    }
}
