package com.processor;

public interface StatusListener {
    /**
     * Metoda słuchacza
     * @param s - status przetwarzania zadania
     */
    String statusChanged(Status s);
}


