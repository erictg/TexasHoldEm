package backend.gameLogic.OfflineMode;

import backend.User;
import backend.gameLogic.PlayerStuff.Move;

public class OfflineUser extends Player {

    private User user;

    public OfflineUser(double pot, User user){
        super(pot, user.getName());
        this.user = user;

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public Move executeMove(double pot, int round) {
        return null;
    }

    @Override
    public Move executeMove(Move move) {
        return move;
    }
}
