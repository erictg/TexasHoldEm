package gui.login;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import backend.MongoControl;
import backend.User;
import gui.*;
import javax.swing.*;

//import backend.User;
//import backend.XMLcontrol;
public class LoginPanel extends JPanel implements ActionListener{

    private static final long serialVersionUID = 1L;
    Window window;
    JPanel northPanel = new JPanel();
        JPanel northLabelHolder = new JPanel();
            JLabel northLabel = new JLabel("LOGIN OR CREATE ACCOUNT");

    JPanel mainCenterPanel = new JPanel();	BoxLayout b = new BoxLayout(mainCenterPanel, BoxLayout.Y_AXIS);
        JPanel userNameLabelHolder = new JPanel();
            JLabel userNameLabel = new JLabel("USERNAME");
        JPanel userNameAreaHolder = new JPanel();
            JTextArea userNameArea = new JTextArea(1,10);
        JPanel passwordLabelHolder = new JPanel();
            JLabel passwordLabel = new JLabel("PASSWORD");
        JPanel passwordAreaHolder = new JPanel();
            JPasswordField passwordField = new JPasswordField(10);
        JPanel errorLabelHolder = new JPanel();
            JLabel errorLabel = new JLabel();
        JPanel enterButtonPanel = new JPanel();
            JButton enterButton = new JButton("ENTER");

    JPanel southPanel = new JPanel();
        JPanel newAccountButtonPanel = new JPanel();
            JButton newAccountButton = new JButton("NEW ACCOUNT");
        JPanel southLabelHolder = new JPanel();
            JLabel southLabel = new JLabel("CREATED BY ERIC SOLENDER");

    public LoginPanel(Window window){
        super(new BorderLayout());
        this.window = window;
        buildPanel();
    }

    private void buildPanel(){
        northPanel.add(northLabelHolder.add(northLabel));
        add(BorderLayout.NORTH, northPanel);

        userNameLabelHolder.add(userNameLabel);
        userNameAreaHolder.add(userNameArea);

        passwordLabelHolder.add(passwordLabel);
        passwordAreaHolder.add(passwordField);

        errorLabelHolder.add(errorLabel);
        enterButtonPanel.add(enterButton);	enterButton.addActionListener(this);
        newAccountButtonPanel.add(newAccountButton);
        mainCenterPanel.setLayout(b);
        mainCenterPanel.add(userNameLabelHolder);
        mainCenterPanel.add(userNameAreaHolder);
        mainCenterPanel.add(passwordLabelHolder);
        mainCenterPanel.add(passwordAreaHolder);
        mainCenterPanel.add(errorLabelHolder);
        mainCenterPanel.add(enterButtonPanel);
        mainCenterPanel.add(newAccountButtonPanel);	newAccountButton.addActionListener(this);
        add(BorderLayout.CENTER, mainCenterPanel);


        southPanel.add(southLabelHolder.add(southLabel));

        add(BorderLayout.SOUTH, southPanel);
        //colors

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == enterButton){
            if(isFull()){
                if(login()){
                    window.switchPanels(Panels.MainPanel);
                }else{
                    errorLabel.setText("username or password incorrect");
                    errorLabel.setForeground(Color.RED);
                }
            }else{
                errorLabel.setText("One or more boxes are not full");
                errorLabel.setForeground(Color.RED);
            }
        }

        if(e.getSource() == newAccountButton){
            window.switchPanels(Panels.NewAccount);
        }
    }

    @SuppressWarnings("deprecation")
    public boolean isFull(){
        return !userNameArea.getText().equals("") && !passwordField.getText().equals("");
    }
    @SuppressWarnings("deprecation")
    public boolean login(){
        User user = MongoControl.login(userNameArea.getText(), passwordField.getText(), Window.mongoDatabase);

        if(user != null){
            window.user = user;
            return true;
        }
        return false;
    }

}
