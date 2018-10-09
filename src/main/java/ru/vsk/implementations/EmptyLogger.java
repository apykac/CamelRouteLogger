package ru.vsk.implementations;

import org.apache.camel.Exchange;
import ru.vsk.interfaces.CustomLogger;

public class EmptyLogger implements CustomLogger {
    @Override
    public void logData(String comment, int i, Exchange exchange) {
        return;
    }
}
