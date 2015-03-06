package backend.gameLogic.OfflineMode;


import backend.gameLogic.PlayerStuff.Move;
import backend.gameStuff.Card;

import java.util.ArrayList;

public abstract class Player {

    protected String name;
    protected double pot;
    protected ArrayList<Card> deck;
    protected Move lastMove;
    protected int round;
    private boolean isMyTurn;
    protected ArrayList<Card> combinedDeck;

    protected Player(double pot, String name){
        this.pot = pot;
        deck = new ArrayList<Card>(2);
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
        deck.clear();
        deck.add(c1);
        deck.add(c2);
    }

    public void setCombinedDeck(ArrayList<Card> card){
        combinedDeck.clear();
        combinedDeck.addAll(card);
        combinedDeck.addAll(deck);
    }

    public double getPot() {
        return pot;
    }

    public void setPot(double pot) {
        this.pot = pot;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
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

    public abstract Move executeMove(double pot, int round);

    public abstract Move executeMove(Move move);
}
