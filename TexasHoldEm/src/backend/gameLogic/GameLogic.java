package backend.gameLogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import backend.gameStuff.*;

public class GameLogic {
    //pass in an array list
    //2 pair, 3 pair, 4 pair, full house

    //works
    public static FoundHand checkPair(ArrayList<Card> cards){
        Collections.sort(cards, new CompareByCardType());
        //pair that its looking for
        Card pair = null;
        //the current type in the loop
        CardType type = null;
        //the last type in the loop(initialized to the first part of the loop)
        CardType lastType = cards.get(0).getID();
        int count = 0;
        //x initialized to 1 because last type gets 0
        for(int x = 1; x < cards.size(); x++){
            //sets the type to current spot in the loop
            type = cards.get(x).getID();
            //if the types match up the count, if not reset count
            if(lastType == type){
                count++;
            }else{
                count = 0;
            }
            lastType = type;
            //sets pair if one match is found
            if(count == 1){
                pair = cards.get(x);
            }
        }
        //if a match is found, return new FoundHand, if not return null
        if(pair != null){
            return new FoundHand(HandType.OnePair, pair.getID());
        }else{
            return null;
        }
    }

    //works
    public static FoundHand checkHighCard(ArrayList<Card> cards){
        Collections.sort(cards, new CompareByCardType());

        return new FoundHand(HandType.HighCard, cards.get(cards.size() - 1).getID());
    }

    //works
    public static FoundHand checkTwoPair(ArrayList<Card> cards){
        Collections.sort(cards, new CompareByCardType());
        Card pair = null;
        CardType type = null;
        CardType lastType = cards.get(0).getID();
        int count = 0;
        int pairsFound = 0;
        for(int x = 1; x < cards.size(); x++){
            type = cards.get(x).getID();
            System.out.println( lastType+ " == " + type);
            if(lastType == type){
                count++;
            }else{
                count = 0;
            }
            lastType = type;
            if(count == 1){
                pair = cards.get(x);
                pairsFound++;
                System.out.println(pair.getID().toString());
                System.out.println(pairsFound);
            }
        }
        if(pairsFound == 2){
            //card that it returns is the higher pair
            return new FoundHand(HandType.TwoPair, pair.getID());
        }else{
            return null;
        }
    }

    //works
    public static FoundHand checkThreeOfAKind(ArrayList<Card> cards){
        Collections.sort(cards, new CompareByCardType());
        Card pair = null;
        CardType type = null;
        CardType lastType = cards.get(0).getID();
        int count = 0;
        for(int x = 1; x < cards.size(); x++){
            type = cards.get(x).getID();
            System.out.println( lastType + " == " + type);
            if(lastType == type){
                count++;
            }else{
                count = 0;
            }
            System.out.println("Count = " + count);
            lastType = type;
            if(count == 2){
                pair = cards.get(x);
                System.out.println(pair.getID().toString());
            }
        }
        if(pair != null){
            return new FoundHand(HandType.ThreeOfAKind, pair.getID());
        }else{
            return null;
        }

    }

    //works
    public static FoundHand checkFourOfAKind(ArrayList<Card> cards){
        Collections.sort(cards, new CompareByCardType());
        Card pair = null;
        CardType type = null;
        CardType lastType = cards.get(0).getID();
        int count = 0;
        for(int x = 1; x < cards.size(); x++){
            type = cards.get(x).getID();
            System.out.println( lastType + " == " + type);
            if(lastType == type){
                count++;
            }else{
                count = 0;
            }
            System.out.println("Count = " + count);
            lastType = type;
            if(count == 3){
                pair = cards.get(x);
                System.out.println(pair.getID().toString());
            }
        }
        if(pair != null){
            return new FoundHand(HandType.FourOfAKind, pair.getID());
        }else{
            return null;
        }
    }

    //works
    public static FoundHand checkFullHouse(ArrayList<Card> cards){
        //checks to see if there are even enough cards
        if(cards.size() <= 4){
            return null;
        }

        Collections.sort(cards, new CompareByCardType());

        int[] countedCards = countCards(cards);

        boolean two = false;
        boolean three = false;

        for(int c : countedCards ){
            if(c == 3){
                three = true;
            }
            if(c == 2){
                two = true;
            }
        }

        if(two && three)
            return new FoundHand(HandType.FullHouse);
        else
            return null;

    }

    //works
    public static FoundHand checkFlush(ArrayList<Card> cards){
        if(cards.size() < 5){
            return null;
        }
        int[] sortedCards = countCardsBySuite(cards);

        for(int i : sortedCards){
            if(i >= 5){
                return new FoundHand(HandType.Flush);
            }
        }

        return null;
    }

    //works
    public static FoundHand checkStraight(ArrayList<Card> cards){
        if(cards.size() < 5){
            return null;
        }

        int[] cardsCount = countCards(cards);

        //beginning number can only go up to 9
        //do seperate if for A,2,3,4,5 && 9,10,J,Q,K,A

        if(cardsCount[12] != 0 && cardsCount[0] != 0 && cardsCount[1] != 0 && cardsCount[2] != 0 && cardsCount[3] != 0){
            return new FoundHand(HandType.Straight);
        }else if(cardsCount[12] != 0 && cardsCount[8] != 0 && cardsCount[9] != 0 && cardsCount[10] != 0 && cardsCount[11] != 0){
            return new FoundHand(HandType.Straight);
        }
        for(int x = 0; x < 7; x++){
            int count = 0;
            for(int y = 0; y < 6; y++){
                System.out.println("cards count '" + cardsCount[y+x] + "', " + (y+x) + ", y = " + y);
                if(cardsCount[y+x] == 0){
                    break;
                }else{
                    count++;
                }

                if(y == 4){
                    return new FoundHand(HandType.Straight);
                }
            }
        }

        return null;
    }

    //working
    public static FoundHand checkStraightFlush(ArrayList<Card> cards){

        int[] cardsWithSuite = getSuiteForCards(cards);
        int[] cardsCount = countCards(cards);
        if(cardsCount[12] != 0 && cardsCount[0] != 0 && cardsCount[1] != 0 && cardsCount[2] != 0 && cardsCount[3] != 0){
            int x = cardsWithSuite[12];
            if(cardsWithSuite[12] == x && cardsWithSuite[0] == x && cardsWithSuite[1] == x && cardsWithSuite[2] == x && cardsWithSuite[3] == x){
                return new FoundHand(HandType.StraightFlush);
            }
        }else if(cardsCount[12] != 0 && cardsCount[8] != 0 && cardsCount[9] != 0 && cardsCount[10] != 0 && cardsCount[11] != 0){
            int x = cardsWithSuite[12];
            if(cardsWithSuite[12] == x && cardsWithSuite[8] == x && cardsWithSuite[9] == x && cardsWithSuite[10] == x && cardsWithSuite[11] == x){
                return new FoundHand(HandType.RoyalFlush);
            }
        }
        for(int x = 0; x < 7; x++){
            int count = 0;
            int suiteID = cardsWithSuite[x];
            for(int y = 0; y < 6; y++){
                System.out.println("cards count '" + cardsCount[y+x] + "', " + (y+x) + ", y = " + y);
                if(!(cardsCount[y+x] != 0 && cardsWithSuite[y+x] == suiteID)){
                    break;
                }

                if(y == 4){
                    return new FoundHand(HandType.StraightFlush);
                }
                suiteID = cardsWithSuite[y+x];
            }
        }

        return null;
    }

    public static FoundHand getBestHand(ArrayList<Card> cards){

        FoundHand h;

        //straight flush
        h = checkStraightFlush(cards);
        if(h != null){
            return h;
        }

        //four of a kind
        h = checkFourOfAKind(cards);
        if(h != null){
            return h;
        }

        //full house
        h = checkFullHouse(cards);
        if(h != null){
            return h;
        }

        //flush
        h = checkFlush(cards);
        if(h != null){
            return h;
        }

        //straight
        h = checkStraight(cards);
        if(h != null){
            return h;
        }

        //three of a kind
        h = checkThreeOfAKind(cards);
        if(h != null){
            return h;
        }

        //two pair
        h = checkTwoPair(cards);
        if(h != null){
            return h;
        }

        //pair
        h = checkPair(cards);
        if(h != null){
            return h;
        }

        //if all else fails, return high card
        return checkHighCard(cards);
    }


    //good
    public static int[] getSuiteForCards(ArrayList<Card> cards){
        int[] cardsWithSuite = new int[13];

        for(Card c : cards){
            cardsWithSuite[c.getID().getID()] = c.getSuite().getID();
        }

        return cardsWithSuite;
    }

    //good
    public static int[] countCards(ArrayList<Card> cards){
        int[] c = new int[14];
        for(Card card : cards){
            c[card.getID().ID]++;
        }
        return c;
    }

    //good
    public static int[] countCardsBySuite(ArrayList<Card> cards){
        int[] c = new int[4];

        for(Card card : cards){
            c[card.getSuite().getID()]++;
        }

        return c;
    }

    //sorts by card type
    public static class CompareByCardType implements Comparator<Card> {
        @Override
        public int compare(Card o1, Card o2) {
            return o1.getID().compareTo(o2.getID());
        }
    }

    //sorts by card suite
    public static class CompareByCardSuite implements Comparator<Card> {
        @Override
        public int compare(Card o1, Card o2) {
            return o1.getSuite().compareTo(o2.getSuite());
        }
    }
}
