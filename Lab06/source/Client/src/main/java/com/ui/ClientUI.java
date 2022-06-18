package com.ui;

import bilboards.IClient;
import bilboards.IManager;
import bilboards.Order;
import java.rmi.RemoteException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientUI {
    private IClient client;
    private IManager manager;
    private ArrayList<Order> orders;

    public ClientUI(IClient client, IManager manager, ArrayList<Integer> orderIDs){
        this.client = client;
        this.manager = manager;
        this.orders = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        String inputData;
        boolean run = true;
        while(run){
            showMenu();
            inputData = input.nextLine();
            if(inputData.equals("1")){
                Order order = new Order();
                System.out.println("Enter advert's text: ");
                inputData = input.nextLine();
                order.advertText = inputData;
                System.out.println("Enter advert's time to display: ");
                inputData = input.nextLine();
                order.displayPeriod = Duration.ofSeconds(Long.parseLong(inputData));
                order.client = client;
                try {
                    if (!manager.placeOrder(order)){
                        System.out.println("Unable to place order!");
                    } else {
                        orders.add(order);
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            } else if(inputData.equals("2")){
                for(Order order : orders){
                    System.out.println("Text: " + order.advertText +
                                       "\nTime: " + order.displayPeriod.getSeconds() + "s");
                }
            } else if(inputData.equals("3")){
                try {
                    System.out.println("Enter order's id: ");
                    inputData = input.nextLine();
                    manager.withdrawOrder(Integer.parseInt(inputData));
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            } else if(inputData.equals("0")){
                run = false;
            } else{
                System.out.println("Wrong number!");
            }
        }
    }

    public void showMenu(){
        System.out.println("=== Client Menu ===");
        System.out.println("1. Add order");
        System.out.println("2. Show order");
        System.out.println("3. Delete order");
        System.out.println("0. Quit");
        System.out.println("Enter: ");
    }


}
