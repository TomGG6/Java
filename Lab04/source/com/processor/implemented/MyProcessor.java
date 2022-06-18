package com.processor.implemented;

import com.gui.ApplicationGUI;
import com.processor.Processor;
import com.processor.Status;
import com.processor.StatusListener;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MyProcessor implements Processor {
    private Object object;
    private Method method;
    private ApplicationGUI window;
    private int nextTaskId = 0;


    public MyProcessor(ApplicationGUI window) {
        this.window = window;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public int getNextTaskId() {return this.nextTaskId;}

    @Override
    public boolean submitTask(String task, StatusListener sl) {
        nextTaskId++;
        window.stageLabel.setText("W toku...");
        AtomicInteger ai = new AtomicInteger(0);

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(
                ()->{
                    ai.incrementAndGet();
                    window.statusLabel.setText(sl.statusChanged(new Status(nextTaskId, ai.get())));
                },
                1, 10, TimeUnit.MILLISECONDS);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (ai.get() >= 100) {
                    window.stageLabel.setText("Ukończono");
                    executorService.shutdown();
                    executor.shutdown();

                    method = object.getClass().getDeclaredMethod(task, String.class);
                    method.invoke(object, window.inputField.getText());
                    sl.statusChanged(new Status(nextTaskId, 100));
                    return true;
                }
            }
        });
        return false;
    }

    @Override
    public String getInfo() {
        try {
            return (String) object.getClass().getField("info").get(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public String getResult() {
        try {
            return (String) object.getClass().getField("result").get(object);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return "";
    }

}
