package backend.gameLogic.PlayerStuff;

public class Move {

    private Moves move;
    private double bet; //-1 for non bet move

    private Move(Moves move, double bet){
        this.move = move;
        this.bet = bet;
    }

    private Move(Moves move){
        this.move = move;
        bet = -1;
    }


    public static Move Fold(){
       return new Move(Moves.FOLD);
    }

    public static Move Check_Call(){
        return new Move(Moves.CHECK_CALL);
    }

    public static Move Bet(double bet){
        return new Move(Moves.BET, bet);
    }

    public double getBet() {
        return bet;
    }

    public void setBet(double bet) {
        this.bet = bet;
    }

    public Moves getMove() {
        return move;
    }

    public void setMove(Moves move) {
        this.move = move;
    }
}
