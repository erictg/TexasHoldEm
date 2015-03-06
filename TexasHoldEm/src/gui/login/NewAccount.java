package gui.login;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import backend.MongoControl;
import backend.User;
import gui.*;
import gui.Window;

import javax.swing.*;

//import backend.Logger;
//import backend.User;
//import backend.XMLcontrol;
public class NewAccount extends JPanel implements ActionListener{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    Window window;
    User u = null;
    JPanel northPanel = new JPanel();
    JPanel northLabelHolder = new JPanel();
    JLabel northLabel = new JLabel("CREATE ACCOUNT");

    JPanel mainCenterPanel = new JPanel(); BoxLayout b = new BoxLayout(mainCenterPanel, BoxLayout.Y_AXIS);
        JPanel userNameLabelHolder = new JPanel();
            JLabel userNameLabel = new JLabel("USERNAME");
        JPanel userNameAreaHolder = new JPanel();
            JTextArea userNameArea = new JTextArea(1,10);
        JPanel passwordLabelHolder = new JPanel();
            JLabel passwordLabel = new JLabel("PASSWORD");
        JPanel passwordAreaHolder = new JPanel();
            JPasswordField passwordField = new JPasswordField(10);
        JPanel passwordLabelHolder1 = new JPanel();
            JLabel passwordLabel1 = new JLabel("CONFIRM PASSWORD");
        JPanel passwordAreaHolder1 = new JPanel();
            JPasswordField passwordField1 = new JPasswordField(10);
        JPanel errorLabelHolder = new JPanel();
            JLabel errorLabel = new JLabel();
        JPanel enterButtonPanel = new JPanel();
            JButton enterButton = new JButton("ENTER");
        JPanel backButtonPanel = new JPanel();
            JButton backButton = new JButton("BACK");

    JPanel southPanel = new JPanel();
        JPanel southLabelHolder = new JPanel();
            JLabel southLabel = new JLabel("Texas Hold 'Em");

    public NewAccount(Window window){
        super(new BorderLayout());
        this.window = window;
        construct();
    }

    private void construct(){
        northPanel.add(northLabelHolder.add(northLabel));
        add(BorderLayout.NORTH, northPanel);

        userNameLabelHolder.add(userNameLabel);
        userNameAreaHolder.add(userNameArea);

        passwordLabelHolder.add(passwordLabel);
        passwordAreaHolder.add(passwordField);

        passwordLabelHolder1.add(passwordLabel1);
        passwordAreaHolder1.add(passwordField1);

        errorLabelHolder.add(errorLabel);
        enterButtonPanel.add(enterButton);	enterButton.addActionListener(this);
        backButtonPanel.add(backButton);
        mainCenterPanel.setLayout(b);
        mainCenterPanel.add(userNameLabelHolder);
        mainCenterPanel.add(userNameAreaHolder);
        mainCenterPanel.add(passwordLabelHolder);
        mainCenterPanel.add(passwordAreaHolder);
        mainCenterPanel.add(passwordLabelHolder1);
        mainCenterPanel.add(passwordAreaHolder1);
        mainCenterPanel.add(errorLabelHolder);
        mainCenterPanel.add(enterButtonPanel);
        mainCenterPanel.add(backButtonPanel);	backButton.addActionListener(this);
        add(BorderLayout.CENTER, mainCenterPanel);

        southPanel.add(southLabelHolder.add(southLabel));
        add(BorderLayout.SOUTH, southPanel);

        //colors
    }




    @SuppressWarnings("deprecation")
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == enterButton){
            showError("checking...");
            if(checkAllFields()){
                if(!checkNameInUse()){
                    if(checkMatchingPasswords()){
                        u = new User(userNameArea.getText(), passwordField.getText());
                        window.user = u;
                        MongoControl.addUser(u, Window.mongoDatabase);
                        window.switchPanels(Panels.MainPanel);
                    }else{
                        showError("passwords do not match");
                    }
                }else{
                    showError("name in use");
                }
            }else{
                showError("not all fields are filled");
            }
        }

        if(e.getSource() == backButton){
            window.switchPanels(Panels.LoginPanel);
        }
    }

    public boolean checkNameInUse(){

        if(MongoControl.checkUserName(userNameArea.getText(), Window.mongoDatabase)){
            return true;
        }

        return false;
    }

    @SuppressWarnings("deprecation")
    public boolean checkMatchingPasswords(){
        return passwordField.getText().equals(passwordField1.getText());
    }

    @SuppressWarnings("deprecation")
    public boolean checkAllFields(){
        return !userNameArea.getText().equals("") && !passwordField.getText().equals("") && !passwordField1.getText().equals("");
    }

    public void showError(String text){
        errorLabel.setText(text);
        errorLabel.setForeground(Color.RED);
    }
//
//    public class saveThread extends Thread{
//        public void run(){
//            System.out.println("thread started");
//            try {
//                XMLcontrol.serializeUserToXML(u, false);
//            } catch (Exception e1) {
//                e1.printStackTrace();
//            }
//            Logger.addUser(u);
//            System.out.println("thread ended");
//        }
//    }
}
