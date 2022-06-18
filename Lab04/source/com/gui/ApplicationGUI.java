package com.gui;

import com.loader.MyClassLoader;
import com.processor.Status;
import com.processor.implemented.MyProcessor;
import com.processor.implemented.MyStatusListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class ApplicationGUI extends JFrame implements ActionListener {
    private MyClassLoader classLoader;
    private Class<?> currentClass;
    private Object object;
    private String path;
    private final MyProcessor processor = new MyProcessor(this);
    private MyStatusListener statusListener = new MyStatusListener(this);
    private ArrayList<String> filesList = new ArrayList<>();

    private final JFrame frame;
    private final JPanel panel;
    private JLabel classPathLabel, classListLabel, inputLabel, outputLabel, typeLabel;
    public JLabel statusLabel, stageLabel;
    public JTextField pathField;
    private JList classList;
    private JButton loadButton, unloadButton, confirmDirectoryButton, performButton;
    public JTextArea inputField, outputField;


    public ApplicationGUI() {
        frame = new JFrame("Lab04");
        frame.setSize(780, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        classPathLabel = new JLabel("Podaj ścieżkę do folderu:");
        classPathLabel.setBounds(10, 0, 150, 25);
        panel.add(classPathLabel);

        pathField = new JTextField();
        pathField.setBounds(155, 0, 150, 25);
        panel.add(pathField);

        confirmDirectoryButton = new JButton("Wybierz");
        confirmDirectoryButton.setBounds(310, 0, 100, 25);
        confirmDirectoryButton.addActionListener(this);
        panel.add(confirmDirectoryButton);

        classListLabel = new JLabel("Klasy");
        classListLabel.setBounds(10, 25, 100, 25);
        panel.add(classListLabel);

        classList = new JList();
        classList.setBounds(10, 50, 200, 320);
        panel.add(classList);

        loadButton = new JButton("Załaduj");
        loadButton.setBounds(10, 375, 100, 25);
        loadButton.addActionListener(this);
        panel.add(loadButton);

        unloadButton = new JButton("Wyładuj");
        unloadButton.setBounds(110, 375, 100, 25);
        unloadButton.addActionListener(this);
        panel.add(unloadButton);

        inputLabel = new JLabel("Polecenie");
        inputLabel.setBounds(250, 50, 100, 25);
        panel.add(inputLabel);

        inputField = new JTextArea();
        inputField.setBounds(250, 80, 500, 80);
        panel.add(inputField);

        outputLabel = new JLabel("Wynik");
        outputLabel.setBounds(250, 170, 100, 25);
        panel.add(outputLabel);

        outputField = new JTextArea();
        outputField.setBounds(250, 200, 500, 80);
        panel.add(outputField);

        performButton = new JButton("Wykonaj");
        performButton.setBounds(250, 290, 100, 25);
        performButton.addActionListener(this);
        panel.add(performButton);

        typeLabel = new JLabel();
        typeLabel.setBounds(10, 415, 200, 25);
        panel.add(typeLabel);

        statusLabel = new JLabel();
        statusLabel.setBounds(10, 445, 200, 25);
        panel.add(statusLabel);

        stageLabel = new JLabel();
        stageLabel.setBounds(10, 475, 200, 25);
        panel.add(stageLabel);

        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == confirmDirectoryButton){
            confirmDirectoryAction();
        }
        if (source == loadButton) {
            classLoadAction();
        }
        if (source == unloadButton) {
            classUnloadAction();
        }
        if (source == performButton) {
            processorAction();
        }
    }

    private void processorAction() {
        typeLabel.setText("Wykonanie metody");
        processor.submitTask(currentClass.getDeclaredMethods()[0].getName(), statusListener);
        outputField.setText(processor.getResult());
    }

    private void confirmDirectoryAction() {
        typeLabel.setText("");
        statusLabel.setText("");
        stageLabel.setText("");
        inputField.setText("");
        outputField.setText("");
        filesList.clear();
        File[] files = new File(pathField.getText()).listFiles();

        for(File file : files){
            filesList.add(file.getName());
        }
        classList.setListData(filesList.toArray());
        path = pathField.getText();
    }

    private void classLoadAction() {
        classLoader = new MyClassLoader();
        classLoader.setPath(path);
        try {
            typeLabel.setText("Załadowanie klasy");
            currentClass = classLoader.findClass(classList.getSelectedValue().toString());
            statusLabel.setText(statusListener.statusChanged(new Status(processor.getNextTaskId(), 100)));
            object = currentClass.newInstance();
            processor.setObject(object);
            outputField.setText(processor.getInfo());

        } catch (Exception e) {
            e.printStackTrace();
        }
        statusListener.statusChanged(new Status(processor.getNextTaskId(), 100));
        stageLabel.setText("Ukończono");
    }

    private void classUnloadAction() {
        typeLabel.setText("Wyładowanie klasy");
        inputField.setText("");
        outputField.setText("");
        classLoader = null;
        currentClass = null;
        System.gc();
        statusListener.statusChanged(new Status(processor.getNextTaskId(), 100));
        stageLabel.setText("");
    }

    public static void main(String[] args){
        ApplicationGUI window = new ApplicationGUI();
    }
}
