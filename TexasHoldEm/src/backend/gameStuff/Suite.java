package backend.gameStuff;

public enum Suite {
    Clubs(0), Diamonds(1), Hearts(2), Spades(3);

    public final int ID;

    private Suite(int ID){
        this.ID = ID;
    }

    public int getID(){
        return ID;
    }
}
