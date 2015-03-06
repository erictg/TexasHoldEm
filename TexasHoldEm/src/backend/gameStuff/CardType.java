package backend.gameStuff;

/**
 * Created by Gretchen on 1/27/2015.
 */
public enum CardType {
    TWO(0), THREE(1), FOUR(2), FIVE(3), SIX(4), SEVEN(5), EIGHT(6), NINE(7), TEN(8), JACK(9), QUEEN(10), KING(11), ACE(12);
    public final int ID;
    private CardType(int ID){
        this.ID = ID;
    }

    public int getID(){
        return ID;
    }

    public int size = 13;
}
