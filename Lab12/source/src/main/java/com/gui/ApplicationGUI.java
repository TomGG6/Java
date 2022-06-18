package com.gui;

import com.controller.ScriptController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ApplicationGUI extends Thread implements ActionListener {
    ScriptController controller;
    private final JFrame frame;
    private JPanel panel;
    private AutomatonPanel drawingPanel;
    private JLabel fileNameLabel, rowsLabel, columnsLabel;
    private JTextField fileNameField, rowsField, columnsField;
    private JButton loadDataLabel;

    public ApplicationGUI() throws FileNotFoundException {
        frame = new JFrame("Lab12");
        frame.setSize(420, 120);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 600, 150);
        frame.add(panel);
        fileNameLabel = new JLabel("File name:");
        fileNameLabel.setBounds(5, 5, 100, 25);
        panel.add(fileNameLabel);

        fileNameField = new JTextField();
        fileNameField.setBounds(5, 35, 150, 25);
        panel.add(fileNameField);

        rowsLabel = new JLabel("Rows: ");
        rowsLabel.setBounds(160, 5, 100, 25);
        panel.add(rowsLabel);

        rowsField = new JTextField();
        rowsField.setBounds(160, 35, 60, 25);
        panel.add(rowsField);

        columnsLabel = new JLabel("Columns: ");
        columnsLabel.setBounds(225, 5, 100, 25);
        panel.add(columnsLabel);

        columnsField = new JTextField();
        columnsField.setBounds(225, 35, 60, 25);
        panel.add(columnsField);

        loadDataLabel = new JButton("Load");
        loadDataLabel.setBounds(295, 35, 100, 25);
        loadDataLabel.addActionListener(this);
        panel.add(loadDataLabel);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loadDataLabel){
            JFrame automatonFrame = new JFrame("Automaton");
            automatonFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            controller = new ScriptController();
            controller.setScript("scripts/cellular_automaton.js");
            controller.evalScript();
            try {
                controller.loadInput(fileNameField.getText(), Integer.parseInt(rowsField.getText()), Integer.parseInt(columnsField.getText()));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }

            drawingPanel = new AutomatonPanel(controller.getInput());
            automatonFrame.getContentPane().add(drawingPanel);
            automatonFrame.pack();
            automatonFrame.setLocationByPlatform(true);
            automatonFrame.setVisible(true);

            /*
            for(int i = 0; i < 13; i++){
                controller.invokeNextStep(controller.getInput().length, controller.getInput()[0].length);
                drawingPanel = new AutomatonPanel(controller.getInput());
                drawingPanel.validate();
                drawingPanel.repaint();
            }
            */
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        new ApplicationGUI();
    }
}
