package gui;

import backend.User;
import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import gui.MainUI.SinglePlayer.SinglePlayerOptions;
import gui.login.LoginPanel;
import gui.login.NewAccount;
import gui.MainUI.*;
import javax.swing.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;


//mongo shit


public class Window extends JFrame{

    public static Properties prop;
    Panels currentPanel;
    public static MongoDatabase mongoDatabase;
    public User user;
    public boolean connected;
    public Window()throws Exception{
        super("Texas Hold Em'");
        prop = new Properties();
        String filePath = System.getProperty("user.dir") + "\\app.config";
        InputStream is = new FileInputStream(filePath);
        prop.load(is);
        is.close();
        currentPanel = Panels.LoginPanel;
        add(new LoginPanel(this));
        setVisible(true);
        connected = establishMongoConnection();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,300);
        setLocationRelativeTo(null);
    }


    public static void run(){
        try {
            new Window();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchPanels(Panels p){
       getContentPane().removeAll();
        switch(p){
            case LoginPanel:
                add(new LoginPanel(this));
                break;
            case NewAccount:
                add(new NewAccount(this));
                break;
            case MainPanel:
                add(new MainPanel(this));
                break;
            case SinglePlayerOptions:
                add(new SinglePlayerOptions(this));
                break;
        }
        ((JPanel)getContentPane()).updateUI();
    }


    public boolean establishMongoConnection() {
        try{
            MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://erictg97:sabrefan69@ds041651.mongolab.com:41651/texas_hold_em"));
            mongoDatabase = mongoClient.getDatabase("texas_hold_em");
            return true;
        }catch(MongoException e){
            return false;
        }

    }
}
