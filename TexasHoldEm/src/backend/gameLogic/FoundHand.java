package backend.gameLogic;

import backend.gameStuff.Card;
import backend.gameStuff.CardType;

public class FoundHand {

    private HandType hand;
    private CardType highCard;

    public FoundHand(HandType hand, CardType highCard) {
        this.hand = hand;
        this.highCard = highCard;
    }
    //not all found hands need to know about the high card, therefor we have an overload method for that situation
    public FoundHand(HandType hand){
        this.hand = hand;
        highCard = null;
    }

    public HandType getHand() {
        return hand;
    }

    public void setHand(HandType hand) {
        this.hand = hand;
    }

    public CardType getHighCard() {
        return highCard;
    }

    public void setHighCard(CardType highCard) {
        this.highCard = highCard;
    }
}
