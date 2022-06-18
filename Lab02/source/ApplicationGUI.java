package com.company;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ApplicationGUI implements ActionListener{
    private FileManager fileManager;
    private final File startingDirectory;

    private final JPanel panelLeft, panelRight;
    private ImagePanel imagePanel;
    private final JFrame frame;
    private final JButton confirmDirectoryButton, backToStartButton;
    private final JLabel directoryNameText, fileLoadRespondText;
    private final JList filesList;
    private final JTextField directoryNameField;
    private final JTextArea textFileContains;

    public ApplicationGUI() {
        startingDirectory = new File(System.getProperty("user.dir"));
        this.fileManager = new FileManager(startingDirectory);
        panelLeft = new JPanel();
        panelRight = new JPanel();
        frame = new JFrame();
        frame.setTitle("Lab02");
        frame.setSize(1200, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);
        panelLeft.setBounds(0, 0, 370, 500);
        frame.add(panelLeft);
        panelRight.setBounds(380, 0, 800, 900);
        frame.add(panelRight);


        //lewy panel
        panelLeft.setLayout(null);
        directoryNameText = new JLabel("Nazwa katalogu: ");
        directoryNameText.setBounds(10, 10, 100, 25);
        panelLeft.add(directoryNameText);


        directoryNameField = new JTextField(20);
        directoryNameField.setBounds(105, 10, 160, 25);
        panelLeft.add(directoryNameField);

        confirmDirectoryButton = new JButton();
        confirmDirectoryButton.setBounds(270, 10, 90, 25);
        confirmDirectoryButton.setText("Wybierz");
        confirmDirectoryButton.addActionListener(this);
        panelLeft.add(confirmDirectoryButton);

        backToStartButton = new JButton();
        backToStartButton.setBounds(270, 50, 90, 25);
        backToStartButton.setText("Wróć");
        backToStartButton.addActionListener(this);
        panelLeft.add(backToStartButton);

        filesList = new JList();
        filesList.setBounds(10, 50, 255, 280);
        filesList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    return;
                }

                fileManager.setFile(new File(fileManager.getDirectoryName() + "/" + filesList.getSelectedValue()));
                clearRightPanel();
                System.out.println(fileManager.getTextFilesWeakHashMap().containsKey(fileManager.getFilePath()));
                if (fileManager.getFileExtension().equals("txt")) {
                    if (!fileManager.getTextFilesWeakHashMap().containsKey(fileManager.getFilePath())) {
                        fileLoadRespondText.setText("Wczytono nowy plik");
                        try {
                            fileManager.addTextFileWeakHashMap(fileManager.getFilePath());
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    } else{
                        fileLoadRespondText.setText("Wczytono plik z pamięci");
                    }

                    textFileContains.setBounds(0, 50, 500, 500);
                    textFileContains.setText(fileManager.getTextFilesWeakHashMap().get(fileManager.getFilePath()));
                    panelRight.add(textFileContains);
                    panelRight.validate();
                    panelRight.repaint();

                } else if(fileManager.getFileExtension().equals("png")) {
                    if (!fileManager.getImageFilesWeakHashMap().containsKey(fileManager.getFilePath())) {
                        fileLoadRespondText.setText("Wczytono nowy plik");
                        try {
                            fileManager.addImageFileWeakHashMap(fileManager.getFilePath());
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        fileLoadRespondText.setText("Wczytono plik z pamięci");
                    }

                    textFileContains.setBounds(0, 50, 200, 200);
                    imagePanel = fileManager.getImageFilesWeakHashMap().get(fileManager.getFilePath());
                    imagePanel.setBounds(0, 50, 900, 900);
                    panelRight.add(imagePanel);
                    panelRight.validate();
                    panelRight.repaint();
                }
            }
        });
        panelLeft.add(filesList);
        filesList.setListData(fileManager.getListOfFilesAndDirectories().toArray());

        //prawy panel
        panelRight.setLayout(null);
        fileLoadRespondText = new JLabel("");
        fileLoadRespondText.setBounds(0,10,150,25);
        panelRight.add(fileLoadRespondText);

        textFileContains = new JTextArea();

        frame.setVisible(true);
    }

    public void clearRightPanel(){
        if (textFileContains != null) {
            panelRight.remove(textFileContains);
        }
        if(imagePanel != null){
            panelRight.remove(imagePanel);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == confirmDirectoryButton) {
            fileManager.setDirectory(new File(directoryNameField.getText()));
            filesList.setListData(fileManager.getListOfFilesAndDirectories().toArray());
        }
        else if(e.getSource() == backToStartButton){
            fileManager = new FileManager(startingDirectory);
            clearRightPanel();
            fileLoadRespondText.setText("");
            panelRight.validate();
            panelRight.repaint();
            filesList.setListData(fileManager.getListOfFilesAndDirectories().toArray());
        }
    }

    public static void main(String[] args) {

        new ApplicationGUI();
    }
}
