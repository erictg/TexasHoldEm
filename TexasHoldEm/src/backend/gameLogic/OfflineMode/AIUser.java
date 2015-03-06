package backend.gameLogic.OfflineMode;


import backend.gameLogic.AI.AILogic;
import backend.gameLogic.PlayerStuff.Move;

import java.util.Random;

public class AIUser extends Player {

    public AIUser(double pot) {
        super(pot, "");
        setName(thingy());
    }

    @Override
    public Move executeMove(double highBet, int round){
        lastMove = AILogic.executeMove(deck, highBet, round, pot, lastMove.getMove());
        return lastMove;
    }

    @Override
    public Move executeMove(Move move) {
        return null;
    }

    public String thingy(){
        String[] first = {"Eric", "Sarah", "Max", "Bart", "Homer", "Lisa", "Marge", "Maggie", "Juan"};
        String[] last = {"Cartman", "Marsh", "Equadore", "Kim", "Broflovski", "Marsh"};

        Random r = new Random(System.nanoTime());
        return first[r.nextInt(first.length)] + " " + last[r.nextInt(last.length)];
    }
}
