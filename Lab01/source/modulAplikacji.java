package com.aplikacja;

import com.biblioteka.modulBiblioteki;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;

public class modulAplikacji implements ActionListener{
    modulBiblioteki biblioteka = new modulBiblioteki();
    private File currentDirectory, currentFile;
    private final String respond = "";

    private final JButton confirmDirectoryButton, confirmFileButton, confirmUpdateButton;
    private final JPanel panel;
    private final JFrame frame;
    private final JLabel directoryNameText, respondText;
    private final JLabel filesText;
    private final JTextField directoryNameField;
    private final JList filesList;
    public modulAplikacji() {
        panel = new JPanel();
        frame = new JFrame();
        frame.setTitle("Lab01");
        frame.setSize(400,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(panel);

        panel.setLayout(null);

        directoryNameText = new JLabel("Nazwa katalogu: ");
        directoryNameText.setBounds(10, 20, 100, 25);
        panel.add(directoryNameText);

        directoryNameField = new JTextField(20);
        directoryNameField.setBounds(115, 20, 160, 25);
        panel.add(directoryNameField);

        confirmDirectoryButton = new JButton();
        confirmDirectoryButton.setBounds(280, 20, 90, 25);
        confirmDirectoryButton.setText("Wybierz");
        confirmDirectoryButton.addActionListener(this);
        panel.add(confirmDirectoryButton);

        filesText = new JLabel("Lista plików:");
        filesText.setBounds(10, 50, 100, 25);
        panel.add(filesText);

        filesList = new JList();
        filesList.setBounds(10, 80, 360, 280);
        panel.add(filesList);

        confirmFileButton = new JButton();
        confirmFileButton.setBounds(10, 365, 90, 25);
        confirmFileButton.setText("Sprawdź");
        confirmFileButton.addActionListener(this);
        panel.add(confirmFileButton);

        confirmUpdateButton = new JButton();
        confirmUpdateButton.setBounds(110, 365, 90, 25);
        confirmUpdateButton.setText("Aktualizuj");
        confirmUpdateButton.addActionListener(this);
        panel.add(confirmUpdateButton);

        respondText = new JLabel(respond);
        respondText.setBounds(10, 430, 400, 25);
        panel.add(respondText);

        frame.setVisible(true);
    }

    public static void main(String[] args){
        new modulAplikacji();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == confirmDirectoryButton){
            respondText.setText("Wczytano katalog " + directoryNameField.getText());
            Path path = Paths.get(directoryNameField.getText());
            currentDirectory = path.toFile();
            try {
                biblioteka.createDataBase(currentDirectory);
                filesList.setListData(biblioteka.getFileNamesData().toArray());
            } catch (NoSuchAlgorithmException | IOException ex) {
                ex.printStackTrace();
            }
        }
        else if(e.getSource() == confirmFileButton){
            try {
                respondText.setText(biblioteka.checkForEquality(filesList.getSelectedValue().toString(), currentDirectory));
            } catch (NoSuchAlgorithmException | IOException ex) {
                ex.printStackTrace();
            }
        }
        else if(e.getSource() == confirmUpdateButton){
            Path path = Paths.get(filesList.getSelectedValue().toString());
            currentFile = path.toFile();
            try {
                biblioteka.updateFileCheckSum(currentFile, currentDirectory);
                respondText.setText("Zaktualizowano kod pliku.");
            } catch (NoSuchAlgorithmException | IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
