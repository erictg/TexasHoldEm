package backend.gameLogic.OfflineMode;


import backend.gameLogic.PlayerStuff.Move;
import backend.gameStuff.Card;

import java.util.ArrayList;

public abstract class Player {

    protected String name;
    protected double pot;
    protected ArrayList<Card> hand;
    protected Move lastMove;
    protected int round;
    private boolean isMyTurn;
    protected ArrayList<Card> combinedDeck;

    protected Player(double pot, String name){
        this.pot = pot;
        hand = new ArrayList<Card>(2);
        lastMove = null;
        round = 0;
        combinedDeck = new ArrayList<Card>();
        this.name = name;
    }

    public boolean isMyTurn() {
        return isMyTurn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMyTurn(boolean isMyTurn) {
        this.isMyTurn = isMyTurn;
    }

    public void giveNewCards(Card c1, Card c2){
        hand.clear();
        hand.add(c1);
        hand.add(c2);
    }

    public void setCombinedDeck(ArrayList<Card> card){
        combinedDeck.clear();
        combinedDeck.addAll(card);
        combinedDeck.addAll(hand);
    }

    public double getPot() {
        return pot;
    }

    public void setPot(double pot) {
        this.pot = pot;
    }

    public ArrayList<Card> getDeck() {
        return hand;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.hand = deck;
    }

    public Move getLastMove() {
        return lastMove;
    }

    public void setLastMove(Move lastMove) {
        this.lastMove = lastMove;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public void addToPot(double d){
        pot+=d;
    }

    public double subtractFromPot(double d){
        pot -= d;
        if(pot < 0){
            double p = Math.abs(pot);
            pot = 0;
            return p;
        }else{
            return d;
        }
    }
    public abstract Move executeMove(ArrayList<Card> table, double pot, int round);

    public abstract Move executeMove(Move move);
}
