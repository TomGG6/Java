package com.ui;

import bilboards.IBillboard;
import bilboards.Order;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.*;

public class ManagerUI {
    private ArrayList<Order> ordersList;
    private Map<Integer, IBillboard> billboards;
    private Map<Integer, Order> orders;

    public ManagerUI(Map<Integer, IBillboard> billboards, Map<Integer, Order> orders) {
        this.billboards = billboards;
        this.orders = orders;
        this.ordersList = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        String inputData;
        boolean run = true;
        while(run) {
            showMenu();
            inputData = input.nextLine();
            if(inputData.equals("1")){
                Order order = orders.get(Integer.parseInt(inputData));
                for (Map.Entry<Integer, IBillboard> currentBillboard : billboards.entrySet())
                {
                    int index = 1;
                    for(Map.Entry<Integer, Order> currentOrder : orders.entrySet()){
                        try {
                            currentBillboard.getValue().addAdvertisement(currentOrder.getValue().advertText, currentOrder.getValue().displayPeriod, currentOrder.getKey());
                        } catch (RemoteException ex) {
                            ex.printStackTrace();
                        }
                        index++;
                    }
                }
            } else if(inputData.equals("0")){
                run = false;
            }else {
                System.out.println("Wrong number!");
            }
        }
    }

    public void showMenu(){
        System.out.println("=== Manager Menu ===");
        System.out.println("1. Set advertisements");
        System.out.println("0. Quit");
        System.out.println("Enter: ");
    }
}

