package com.gui;

import com.encryptors.EncryptorAES;
import com.encryptors.EncryptorRSA;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class ApplicationGUI implements ActionListener {
    EncryptorRSA encryptorRSA;
    EncryptorAES encryptorAES;
    File sourceFile, destinationFile, privateKeyFile, publicKeyFile, secretKeyFile;
    private final JFrame frame;
    private JPanel panel;
    private JLabel chooseAlgorithmLabel, asymmetricKeysLabel, symmetricKeyLabel, privateKeyLabel,
            publicKeyLabel, secretKeyLabel, sourceFileLabel, destinationFileLabel, versionLabel;
    private JButton loadPrivateKeyButton, loadPublicKeyButton, loadSecretKeyButton, encryptButton, decryptButton;
    private JComboBox<String> algorithmsList;
    private JTextField sourceFileField, destinationFileField;
    FileDialog fileDialog;

    ApplicationGUI() {
        encryptorRSA = new EncryptorRSA();
        encryptorAES = new EncryptorAES();

        frame = new JFrame("Lab09");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);

        chooseAlgorithmLabel = new JLabel("Choose algorithm: ");
        chooseAlgorithmLabel.setBounds(10, 10, 150, 25);
        panel.add(chooseAlgorithmLabel);

        String[] comboBoxContent = {"RSA", "AES"};
        algorithmsList = new JComboBox<>(comboBoxContent);
        algorithmsList.setBounds(120, 10, 80, 20);
        panel.add(algorithmsList);

        asymmetricKeysLabel = new JLabel("Asymmetric keys: ");
        asymmetricKeysLabel.setBounds(10, 40, 100, 25);
        panel.add(asymmetricKeysLabel);

        privateKeyLabel = new JLabel("private key: none");
        privateKeyLabel.setBounds(120, 60, 290, 25);
        panel.add(privateKeyLabel);

        loadPrivateKeyButton = new JButton("Load key");
        loadPrivateKeyButton.setBounds(10, 65, 100, 20);
        loadPrivateKeyButton.addActionListener(this);
        panel.add(loadPrivateKeyButton);

        publicKeyLabel = new JLabel("public key: none");
        publicKeyLabel.setBounds(120, 80, 290, 25);
        panel.add(publicKeyLabel);

        loadPublicKeyButton = new JButton("Load key");
        loadPublicKeyButton.setBounds(10, 85, 100, 20);
        loadPublicKeyButton.addActionListener(this);
        panel.add(loadPublicKeyButton);

        symmetricKeyLabel = new JLabel("Symmetric key: ");
        symmetricKeyLabel.setBounds(10, 120, 100, 25);
        panel.add(symmetricKeyLabel);

        secretKeyLabel = new JLabel("key: none");
        secretKeyLabel.setBounds(120, 140, 290, 25);
        panel.add(secretKeyLabel);

        loadSecretKeyButton = new JButton("Load");
        loadSecretKeyButton.setBounds(10, 145, 100, 20);
        loadSecretKeyButton.addActionListener(this);
        panel.add(loadSecretKeyButton);

        sourceFileLabel = new JLabel("Path to source file: ");
        sourceFileLabel.setBounds(10, 180, 150, 25);
        panel.add(sourceFileLabel);

        sourceFileField = new JTextField();
        sourceFileField.setBounds(10, 210, 250, 20);
        panel.add(sourceFileField);

        destinationFileLabel = new JLabel("Path to destination file: ");
        destinationFileLabel.setBounds(10, 230, 150, 25);
        panel.add(destinationFileLabel);

        destinationFileField = new JTextField();
        destinationFileField.setBounds(10, 260, 250, 20);
        panel.add(destinationFileField);

        encryptButton = new JButton("Encrypt");
        encryptButton.setBounds(10, 285, 100, 20);
        encryptButton.addActionListener(this);
        panel.add(encryptButton);

        decryptButton = new JButton("Decrypt");
        decryptButton.setBounds(158, 285, 100, 20);
        decryptButton.addActionListener(this);
        panel.add(decryptButton);

        versionLabel = new JLabel("Java version: " + System.getProperty("java.version"));
        versionLabel.setBounds(10, 330, 300, 25);
        panel.add(versionLabel);

        frame.setVisible(true);
    }

    public static void main(String[] args){
        new ApplicationGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == loadPrivateKeyButton){
            fileDialog = new FileDialog(frame, "Load private key", FileDialog.LOAD);
            fileDialog.setVisible(true);
            privateKeyFile = new File(fileDialog.getFile());
            privateKeyLabel.setText("private key: " + fileDialog.getFile());
        }else if(source == loadPublicKeyButton){
            fileDialog = new FileDialog(frame, "Load public key", FileDialog.LOAD);
            fileDialog.setVisible(true);
            publicKeyFile = new File(fileDialog.getFile());
            publicKeyLabel.setText("public key: " + fileDialog.getFile());
        }else if(source == loadSecretKeyButton){
            fileDialog = new FileDialog(frame, "Load key", FileDialog.LOAD);
            fileDialog.setVisible(true);
            secretKeyFile = new File(fileDialog.getFile());
            secretKeyLabel.setText("key: " + fileDialog.getFile());
        }else if(source == encryptButton){
            sourceFile = new File(sourceFileField.getText());
            destinationFile = new File(destinationFileField.getText());
            sourceFileField.setText("");
            destinationFileField.setText("");
            if(algorithmsList.getSelectedItem().equals("RSA")){
                try {
                    encryptorRSA.setPrivateKey(privateKeyFile);
                    encryptorRSA.encryptFile(sourceFile, destinationFile);
                } catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | InvalidKeySpecException ex) {
                    ex.printStackTrace();
                }
            } else if(algorithmsList.getSelectedItem().equals("AES")){
                try {
                    encryptorAES.setSecretKey(secretKeyFile);
                    encryptorAES.encryptFile(sourceFile, destinationFile);
                } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException ex) {
                    ex.printStackTrace();
                }
            }
        }else if(source == decryptButton){
            sourceFile = new File(sourceFileField.getText());
            destinationFile = new File(destinationFileField.getText());
            sourceFileField.setText("");
            destinationFileField.setText("");
            if(algorithmsList.getSelectedItem().equals("RSA")){
                try {
                    encryptorRSA.setPublicKey(publicKeyFile);
                    encryptorRSA.decryptFile(sourceFile, destinationFile);
                } catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | InvalidKeySpecException ex) {
                    ex.printStackTrace();
                }
            } else if(algorithmsList.getSelectedItem().equals("AES")){
                try {
                    encryptorAES.setSecretKey(secretKeyFile);
                    encryptorAES.decryptFile(sourceFile, destinationFile);
                } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException | InvalidAlgorithmParameterException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
