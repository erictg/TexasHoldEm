package backend.gameLogic.AI;


import backend.gameLogic.FoundHand;
import backend.gameLogic.GameLogic;
import backend.gameLogic.HandType;
import backend.gameLogic.PlayerStuff.Move;
import backend.gameLogic.PlayerStuff.Moves;
import backend.gameStuff.Card;

import java.util.ArrayList;
import java.util.Random;

public class AILogic {

    public static Move executeMove(ArrayList<Card> cards, double highBet, int round, double potSize, Moves lastMove){

        FoundHand topHand = GameLogic.getBestHand(cards);
        HandType handValue = topHand.getHand();
        int handID = handValue.getID();
        // <editor-fold desc = "Operation">
       if(lastMove != Moves.FOLD || lastMove == null){
            //check fold
           if((handID <= 3 && highBet >= 500) || (handID > 3 && handID < 7 && highBet >= 1000)){
               return Move.Fold();
           }
           Random r = new Random(System.currentTimeMillis());
           //check bet
           if(handID > 7){
               double bet = r.nextInt((int)potSize) + (int)potSize/2;
               return Move.Bet(bet);
           }else if(handID < 7 && handID > 3){
               double bet = r.nextInt((int)potSize/2) + (int)potSize/4;
               return Move.Bet(bet);
           }

           return Move.Check_Call();


       }
        //</editor-fold>
        return Move.Fold();
    }

}
