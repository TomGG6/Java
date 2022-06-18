package com.processor.implemented;

import com.gui.ApplicationGUI;
import com.processor.Status;
import com.processor.StatusListener;

public class MyStatusListener implements StatusListener {
    private ApplicationGUI window;

    public MyStatusListener(ApplicationGUI window) {
        this.window = window;
    }

    @Override
    public String statusChanged(Status status) {
        return "Zadanie " + status.getTaskId() + ": " + status.getProgress() + "%";
    }

}
