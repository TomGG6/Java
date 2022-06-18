package com.gui;

import bilboards.IBillboard;
import bilboards.IManager;
import com.billboard.DisplayedAdvertisement;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.rmi.RemoteException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Map;

public class BillboardGUI {
    private IManager manager;
    private IBillboard billboard;
    private Map<Integer, DisplayedAdvertisement> adverts;
    private Duration displayInterval;
    private JFrame frame;
    private JPanel panel;
    private JTextArea advertContent;


    public BillboardGUI(IManager manager, IBillboard billboard, Map<Integer, DisplayedAdvertisement> adverts, Duration displayInterval) throws RemoteException, BadLocationException {
        this.manager = manager;
        this.billboard = billboard;
        this.adverts = adverts;
        this.displayInterval = displayInterval;
        this.frame = new JFrame("Lab06");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(500, 200);
        frame.setLayout(null);
        this.panel = new JPanel();
        panel.setBounds(0, 0, 500, 200);
        panel.setLayout(null);
        advertContent = new JTextArea();
        advertContent.setBounds(0, 0, 500, 200);
        advertContent.setEditable(false);
        advertContent.setLineWrap(true);
        advertContent.setWrapStyleWord(true);
        advertContent.setWrapStyleWord(false);
        panel.add(advertContent);
        frame.add(panel);
        frame.setVisible(true);

        manager.bindBillboard(billboard);

        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()){
                for (Iterator<Map.Entry<Integer,DisplayedAdvertisement>> it = adverts.entrySet().iterator(); it.hasNext();) {
                    Map.Entry<Integer,DisplayedAdvertisement> advert = it.next();
                    advertContent.setText(advert.getValue().getAdvertText());
                    if (displayInterval.toMillis() >= advert.getValue().getDisplayPeriod().toMillis())
                    {
                        it.remove();
                    }
                    else{
                        advert.getValue().setDisplayPeriod(advert.getValue().getDisplayPeriod().minus(displayInterval));
                    }
                    try {
                        Thread.sleep(displayInterval.toMillis());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        });

        thread.start();
    }
}

