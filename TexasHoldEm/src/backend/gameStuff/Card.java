package backend.gameStuff;


import javax.swing.*;

public class Card {
    private String name;
    private CardType ID;
    private Suite suite;
    private ImageIcon image;

    public Card() {
        ID = null;
        suite = null;
        image = null;
        name = "";
    }

    public Card(CardType ID, Suite suite, String imagePath, String name){
        this.ID = ID;
        this.suite = suite;
        image = new ImageIcon(imagePath);
        this.name = name;
    }

    public Suite getSuite() {
        return suite;
    }

    public void setSuite(Suite suite) {
        this.suite = suite;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public CardType getID() {
        return ID;
    }

    public void setID(CardType ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
