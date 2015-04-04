package backend.gameLogic.OfflineMode;


import backend.gameLogic.AI.AILogic;
import backend.gameLogic.PlayerStuff.Move;
import backend.gameStuff.Card;

import java.util.ArrayList;
import java.util.Random;


public class AIUser extends Player {

    public AIUser(double pot) {
        super(pot, "");
        setName(thingy());
    }

    @Override
    public Move executeMove(ArrayList<Card> table, double highBet, int round){
        System.out.println("checking for everything");
        //lastMove = AILogic.executeMove(deck, highBet, round, pot, lastMove.getMove());
        System.out.println(lastMove + " = lastmove");
        ArrayList<Card> combined = new ArrayList<Card>();
        if(!table.isEmpty()){
            combined.addAll(table);
        }
        combined.addAll(hand);
        System.out.println(combined.size() + " size of combined");
        for(Card c : combined){
            System.out.println(c.getID().toString());
        }
        System.out.println(highBet + "\n" + round + "\n" + pot);
        System.out.print("\n" + lastMove + "\nEndCheckingEverything");

        Move m = AILogic.executeMove(combined, highBet, round, pot);
        System.out.println(m);
        return m;
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
