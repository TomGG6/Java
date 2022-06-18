package com.loader;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class MyClassLoader extends ClassLoader {
    private String path;
    private Map<String, Class> classes = new HashMap<>();

    public void setPath(String path) {
        this.path = path;
    }

    public Class<?> findClass(String className) {
        if (classes.containsKey(className)) {
            return classes.get(className);
        }
        byte[] bt = new byte[0];
        try {
            bt = loadClassData(className);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        classes.put(className, defineClass(className.substring(0, className.indexOf('.')), bt, 0, bt.length));
        return classes.get(className);
    }

    private byte[] loadClassData(String className) throws FileNotFoundException {
        File file = new File(path + "/"  + className);
        InputStream inputStream = new FileInputStream(file);
        ByteArrayOutputStream byteSt = new ByteArrayOutputStream();
        Integer lenght = 0;
        try {
            while ((lenght = inputStream.read()) != -1) {
                byteSt.write(lenght);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byteSt.toByteArray();
    }

}
