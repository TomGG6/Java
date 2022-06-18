package com.gui;

import com.api.API;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

public class ApplicationGUI implements ActionListener {
    private ResourceBundle rb = ResourceBundle.getBundle("MessagesBundle_PL", new Locale("pl", "PL"));
    private final JFrame frame;
    private JPanel panel;
    private JLabel titleLabel, languageInfoLabel;
    private JButton changeLanguageButton, startButton;
    ApplicationGUI(){
        frame = new JFrame("Lab03");
        frame.setSize(600, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);

        titleLabel = new JLabel(rb.getString("Title"));
        titleLabel.setBounds(180, 5, 200, 25);
        panel.add(titleLabel);

        startButton = new JButton(rb.getString("Start"));
        startButton.setBounds(210, 35, 100, 25);
        startButton.addActionListener(this);
        panel.add(startButton);

        languageInfoLabel = new JLabel(rb.getString("LanguageInfo"));
        languageInfoLabel.setBounds(5, 150, 200, 25);
        panel.add(languageInfoLabel);

        changeLanguageButton = new JButton(rb.getString("Change"));
        changeLanguageButton.setBounds(210, 150, 100, 25);
        changeLanguageButton.addActionListener(this);
        panel.add(changeLanguageButton);



        frame.setVisible(true);
        API api = new API();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == changeLanguageButton){
            changeLanguageAction();
        }else if(source == startButton){
            startButtonAction();
        }
    }

    public void changeLanguageAction(){
        if(rb.getString("Control").equals("PL")){
            rb = ResourceBundle.getBundle("MessagesBundle_EN", new Locale("en", "US"));
        }
        else{
            rb = ResourceBundle.getBundle("MessagesBundle_PL", new Locale("pl", "PL"));
        }
        titleLabel.setText(rb.getString("Title"));
        startButton.setText(rb.getString("Start"));
        languageInfoLabel.setText(rb.getString("LanguageInfo"));
        changeLanguageButton.setText(rb.getString("Change"));
        panel.validate();
        panel.repaint();
    }

    public void startButtonAction(){
        frame.remove(panel);
        panel = new QuestionPanel(1, 0, rb);
        frame.add(panel);
        frame.validate();
        frame.repaint();
    }

    public static void main(String[] args){
        ApplicationGUI application = new ApplicationGUI();
    }
}
