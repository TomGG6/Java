package com.ui;

import com.sorter.Sorter;

public class ApplicationUI {
    public static void main(String[] args)
    {
        Sorter object = new Sorter();
        Double[] a = {5.0, 2.0, 3.0, 10.0, 12.0, 1.0};
        String array = "Przed sortowaniem: ";
        for(int i = 0; i < a.length; i++){
            array += a[i] + ", ";
        }
        System.out.println(array);

        array = "Po sortowaniu: ";
        Boolean order = true;
        Double[] b = object.sort01(a, order);
        for(int i = 0; i < 6; i++){
            array += b[i] + ", ";
        }
        System.out.println(array);
    }
}
