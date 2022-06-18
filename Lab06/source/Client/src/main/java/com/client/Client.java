package com.client;


import bilboards.IClient;
import bilboards.IManager;
import com.ui.ClientUI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
public class Client implements IClient {

    private ArrayList<Integer> orderIDs = new ArrayList<>();

    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(args[0], Integer.parseInt(args[1]));
        IManager manager = (IManager) registry.lookup(args[2]);
        Client client = new Client();
        IClient iClient = (IClient) UnicastRemoteObject.exportObject(client,0);
        ClientUI ui = new ClientUI(client, manager, client.orderIDs);
        System.exit(0);
    }

    @Override
    public void setOrderId(int orderId) throws RemoteException {
        orderIDs.add(orderId);
    }
}

