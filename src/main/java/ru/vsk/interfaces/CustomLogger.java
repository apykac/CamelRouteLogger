package ru.vsk.interfaces;

import org.apache.camel.Exchange;

public interface CustomLogger {
    void logData(String comment, int i, Exchange exchange);
}
