package backend.gameStuff;

import gui.Window;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
public class Deck {
    Stack<Card> cards;

    String[] names = {"ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN", "JACK", "QUEEN", "KING", "ACE"};

    public Deck(){
        cards = new Stack<Card>();
        generateDeck();
    }

    private void generateDeck(){
        ArrayList<Card> cardsA = new ArrayList<Card>();
        for(int x = 0; x < 4; x++){
            for(int y = 0; y < 14; y++){
                Card c;

                CardType ct = getType(y);
                Suite s = suiteID(x);
                String name = names[y] + " of " + s.toString();
                //String imagePath = Window.prop.getProperty("app.imageFolder") + names[y] + "_of_" + s.toString().toLowerCase() + ".png";
                c = new Card(ct,s,"", name);
                cardsA.add(c);
            }
        }
        Collections.shuffle(cardsA);

        for(Card c : cardsA){
            cards.push(c);
        }
    }

    private CardType getType(int typeID){

        switch(typeID){
            case 0:
                return CardType.TWO;
            case 1:
                return CardType.THREE;
            case 2:
                return CardType.FOUR;
            case 3:
                return CardType.FIVE;
            case 4:
                return CardType.SIX;
            case 5:
                return CardType.SEVEN;
            case 6:
                return CardType.EIGHT;
            case 7:
                return CardType.NINE;
            case 8:
                return CardType.TEN;
            case 9:
                return CardType.JACK;
            case 10:
                return CardType.QUEEN;
            case 11:
                return CardType.KING;
            case 12:
                return CardType.ACE;
            default:
                return null;
        }
    }

    private Suite suiteID(int id){

        switch(id){
            case 0:
                return Suite.Clubs;
            case 1:
                return Suite.Diamonds;
            case 2:
                return Suite.Hearts;
            case 3:
                return Suite.Spades;
            default:
                return null;
        }
    }

    public Card getNextCard(){
        if(!cards.empty()){
            return cards.pop();
        }else{
            generateDeck();
            return cards.pop();
        }
    }
}
