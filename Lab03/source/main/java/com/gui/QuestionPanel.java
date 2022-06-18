package com.gui;

import com.api.API;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

public class QuestionPanel extends JPanel implements ActionListener {
    private int questionId, result;
    private String firstAnswerBuilder, secondAnswerBuilder;
    ResourceBundle rb;
    private API api;
    private JLabel questionLabel, questionMarkLabel, answerLabel, firstAnswerLabel, secondAnswerLabel;
    private JTextField answerField;
    private JComboBox<String> options;
    private JButton confirmButton;

    public QuestionPanel(int Id, int result, ResourceBundle resourceBundle){
        questionId = Id;
        rb = resourceBundle;

        setLayout(null);
        api = new API();
        if(questionId == 1){
            questionLabel = new JLabel(rb.getString("FirstQuestion"));
            questionLabel.setBounds(5, 5, 320, 25);
            add(questionLabel);

            String[] firstQuestion = {rb.getString("Africa"), rb.getString("Antarctica"),
                                      rb.getString("Asia"), rb.getString("Europe"),
                                      rb.getString("NorthAmerica"), rb.getString("Oceania"),
                                      rb.getString("SouthAmerica")};
            options = new JComboBox<>(firstQuestion);
            options.setBounds(230, 5, 100, 25);
            add(options);

            questionMarkLabel = new JLabel("?");
            questionMarkLabel.setBounds(335, 5, 15, 25);
            add(questionMarkLabel);

            answerLabel = new JLabel(rb.getString("UserAnswer"));
            answerLabel.setBounds(5, 50, 100, 25);
            add(answerLabel);

            answerField = new JTextField();
            answerField.setBounds(90, 50, 150, 25);
            add(answerField);

            confirmButton = new JButton(rb.getString("Confirm"));
            confirmButton.setBounds(240, 50, 100, 25);
            confirmButton.addActionListener(this);
            add(confirmButton);
        }
        validate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == confirmButton){
            confirmButtonAction();
        }
    }

    public void confirmButtonAction(){
        if(questionId == 0){
            System.exit(0);
        } else if(questionId == 1){
            if(api.getResponse("continents", "countries", api.getContinentId(options.getSelectedItem().toString())).equals(answerField.getText())){
                result++;
                firstAnswerBuilder = rb.getString("AcceptedFirstAnswer");
                firstAnswerBuilder = firstAnswerBuilder.replace("...", options.getSelectedItem().toString());
                firstAnswerBuilder = firstAnswerBuilder.replace(",,,", answerField.getText());
            }else{
                firstAnswerBuilder = rb.getString("RejectedFirstAnswer");
                firstAnswerBuilder = firstAnswerBuilder.replace("...", options.getSelectedItem().toString());
                firstAnswerBuilder = firstAnswerBuilder.replace(",,,", api.getResponse("continents", "countries", api.getContinentId(options.getSelectedItem().toString())));
            }
            questionId++;
            questionLabel.setText(rb.getString("SecondQuestion"));

            String[] secondQuestion = {rb.getString("Poland"), rb.getString("UnitedStates"),
                                       rb.getString("UnitedKingdom"), rb.getString("Sweden"),
                                       rb.getString("Spain"), rb.getString("Germany")};
            remove(options);
            options = new JComboBox<>(secondQuestion);
            options.setBounds(330, 5, 100, 25);
            add(options);

            questionMarkLabel.setBounds(435, 5, 10, 25);
            validate();
            repaint();
        }else if(questionId == 2) {
            if (api.getResponse("countries", "admin1_divisions", api.getCountryId(options.getSelectedItem().toString())).equals(answerField.getText())) {
                result++;
                secondAnswerBuilder = rb.getString("AcceptedSecondAnswer");
                secondAnswerBuilder = secondAnswerBuilder.replace("...", options.getSelectedItem().toString());
                secondAnswerBuilder = secondAnswerBuilder.replace(",,,", answerField.getText());
            } else {
                secondAnswerBuilder = rb.getString("RejectedSecondAnswer");
                secondAnswerBuilder = secondAnswerBuilder.replace("...", options.getSelectedItem().toString());
                secondAnswerBuilder = secondAnswerBuilder.replace(",,,", api.getResponse("countries", "admin1_divisions", api.getCountryId(options.getSelectedItem().toString())));
            }

            removeAll();
            questionLabel.setBounds(5, 5, 200, 25);
            questionLabel.setText(rb.getString("FirstResult") + " " + result + rb.getString("SecondResult"));
            add(questionLabel);
            firstAnswerLabel = new JLabel(firstAnswerBuilder);
            firstAnswerLabel.setBounds(5, 40, 300, 25);
            add(firstAnswerLabel);

            secondAnswerLabel = new JLabel(secondAnswerBuilder);
            secondAnswerLabel.setBounds(5, 75, 300, 25);
            add(secondAnswerLabel);
            questionId = 0;
            confirmButton.setText(rb.getString("Exit"));
            confirmButton.setBounds(200, 150, 100, 25);
            add(confirmButton);
            validate();
            repaint();
        }
    }
}
