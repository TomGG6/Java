package com.biblioteka;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class modulBiblioteki {
    ArrayList<String> fileNamesData = new ArrayList<String>();
    ArrayList<String> fileCheckSumData = new ArrayList<String>();

    public String getFileSum(File file) throws NoSuchAlgorithmException, IOException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        FileInputStream fs = new FileInputStream(file);
        BufferedInputStream bs = new BufferedInputStream(fs);
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = bs.read(buffer, 0, buffer.length)) != -1) {
            md.update(buffer, 0, bytesRead);
        }
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte bite : digest) {
            sb.append(String.format("%02x", bite & 0xff));
        }
        return sb.toString();
    }

    public String checkForEquality(String fileName, File directory) throws NoSuchAlgorithmException, IOException
    {
        String currentCheckSum = getFileSum(new File(directory.getName() + "/" + fileName));
        String previousCheckSum = fileCheckSumData.get(fileNamesData.indexOf(fileName));
        if(currentCheckSum.equals(previousCheckSum)){
            return "Plik " + fileName + " nie został zmieniony.";
        } else {
            return "Pliku " + fileName + " zmienił się.";
        }
    }

    public void createDataBase(File directory) throws NoSuchAlgorithmException, IOException {
        fileNamesData = new ArrayList<>();
        fileCheckSumData = new ArrayList<>();
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                fileNamesData.add(file.getName());
                fileCheckSumData.add(getFileSum(new File(directory.getName() + "/" + file.getName())));
            }
        }
    }

    public String showListFiles(){
        String list = "";
        for(String fileName : fileNamesData){
            list += fileName + "\n";
        }
        return list;
    }

    public void updateFileCheckSum(File file, File directory) throws NoSuchAlgorithmException, IOException {
        fileCheckSumData.set(fileNamesData.indexOf(file.getName()), getFileSum(new File(directory.getName() + "/" + file.getName())));
    }

    public ArrayList<String> getFileNamesData(){return fileNamesData;}
}
