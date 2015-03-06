package backend;

import java.util.Random;

public class User {

    private String name;
    private double money;
    private String password;
    private double totalMoneyGained;
    private double totalMoneyLost;


    private int ID;




    // private ImageIcon profilePicture;
    public User(){
        name = "";
        password = "";
        totalMoneyGained = 0;
        totalMoneyLost = 0;
       // profilePicture = null;
        money = 0;
        ID = 0;
    }

    public User(String name, String password, double money, double totalMoneyGained, double totalMoneyLost) {
        this.name = name;
        this.password = password;
        this.money = money;
        this.totalMoneyGained = totalMoneyGained;
        this.totalMoneyLost = totalMoneyLost;
        //profilePicture = new ImageIcon(imagePath);
        Random random = new Random(System.nanoTime());
        this.ID = random.nextInt(Integer.MAX_VALUE);
    }

    public User(String name, String password){
        this(name, password, 0, 0, 0);
    }

    public void setTotalMoneyGained(double totalMoneyGained) {
        this.totalMoneyGained = totalMoneyGained;
    }

    public void setTotalMoneyLost(double totalMoneyLost) {
        this.totalMoneyLost = totalMoneyLost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void addMoney(double moneyToAdd){
        money += moneyToAdd;
    }

    public void subtractMoney(double moneyToSubtract){
        money -= moneyToSubtract;
    }

    public double getTotalMoneyGained() {
        return totalMoneyGained;
    }

    public void addToTotalMoneyGained(double moneyToAdd) {
        totalMoneyGained += moneyToAdd;
    }

    public double getTotalMoneyLost() {
        return totalMoneyLost;
    }

    public void addToTotalMoneyLost(double moneyToAdd) {
        totalMoneyLost += moneyToAdd;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //public ImageIcon getProfilePicture() {
       // return profilePicture;
  //  }

    //public void setProfilePicture(ImageIcon profilePicture) {
//        this.profilePicture = profilePicture;
//    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Created by Gretchen on 2/14/2015.
     */
    public static class GameUser {
    }
}
