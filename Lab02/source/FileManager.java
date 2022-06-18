package com.company;
import java.io.File;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileManager {
    private File directory;
    private File file;
    private String fileExtension;
    private final static WeakHashMap<String, String> textFilesWeakHashMap = new WeakHashMap<>();
    private final static WeakHashMap<String, ImagePanel> imageFilesWeakHashMap = new WeakHashMap<>();

    FileManager(File directory){
        this.directory = directory;
    }

    ArrayList<String> getListOfFilesAndDirectories(){
        ArrayList<String> listOfDirectories = new ArrayList<String>();
        File[] files = directory.listFiles();
        for (File file : files) {
            listOfDirectories.add(file.getName());
        }
        return listOfDirectories;
    }

    public String getStringTextFile() throws IOException {
        String line = null, text = "";
        BufferedReader reader = new BufferedReader(new FileReader(file));
        try {
            while ((line = reader.readLine()) != null) {
                text += line;
            }
        } finally {
            reader.close();
        }
        return text;
    }

    public void setFile(File file){
        this.file = file;
        fileExtension = "";
        int lastIndex = file.getPath().length();
        int findDot = lastIndex - 3;
        for(int i = findDot; i < lastIndex; i++)
        {
            fileExtension += file.getPath().charAt(i);
        }
    }

    public void setDirectory(File file){
        this.directory = file;
    }

    public void addTextFileWeakHashMap(String newFile) throws IOException {
        textFilesWeakHashMap.put(newFile, getStringTextFile());
    }

    public void addImageFileWeakHashMap(String newFile) throws IOException {
        imageFilesWeakHashMap.put(newFile, new ImagePanel(file));
    }

    public String getDirectoryName(){
        return directory.getPath();
    }

    public String getFilePath(){
        return file.getPath();
    }

    public String getFileExtension(){
        return fileExtension;
    }

    public WeakHashMap<String, String> getTextFilesWeakHashMap(){
        return textFilesWeakHashMap;
    }

    public WeakHashMap<String, ImagePanel> getImageFilesWeakHashMap(){
        return imageFilesWeakHashMap;
    }
}

