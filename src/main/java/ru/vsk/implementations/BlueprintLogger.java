package ru.vsk.implementations;

import org.apache.camel.Exchange;
import ru.vsk.Utilities.Utils;
import ru.vsk.interfaces.CustomLogger;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class BlueprintLogger implements CustomLogger {
    private static final Lock lock = new ReentrantLock();
    private final File file;

    public BlueprintLogger(String fileName) {
        file = new File("C:\\logs\\" + fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("ru.vsk.DebugProcessor --> IOException: " + System.lineSeparator() + e.getMessage());
            }
        }
    }


    @Override
    public void logData(String comment, int i, Exchange exchange) {
        if (exchange == null) return;
        StringBuilder builder = new StringBuilder().
                append(Utils.getDoubleSeparator()).
                append(Utils.getFormattedGregorianCalendar()).
                append(Utils.getDoubleSeparator());
        if (i > 0) {
            builder.append("line number '" + i + "' in blueprint").
                    append(Utils.getDoubleSeparator());
        }
        builder.append(comment).
                append(Utils.getDoubleSeparator());
        if (i == 0) {
            builder.append(getHeadInformation(exchange)).
                    append(Utils.getDoubleSeparator()).
                    append(writeMapToOutputStream(exchange.getIn().getHeaders())).
                    append(Utils.getDoubleSeparator()).
                    append(getBodyToString(exchange)).
                    append(Utils.getDoubleSeparator());
        }
        if (i == -1) {
            builder.append(getHeadInformation(exchange)).
                    append(Utils.getDoubleSeparator()).
                    append(writeMapToOutputStream(exchange.getIn().getHeaders())).
                    append(Utils.getDoubleSeparator());
        }
        if (i == -2) {
            builder.append(getBodyToString(exchange)).
                    append(Utils.getDoubleSeparator());
        }
        Utils.writeData(builder, lock, file);
    }

    private StringBuilder getHeadInformation(Exchange exchange) {
        return new StringBuilder().
                append("In Exchange ID: ").append(exchange.getExchangeId()).append(System.lineSeparator()).
                append("From endpoint: ").append(exchange.getFromEndpoint()).append(System.lineSeparator()).
                append("From Route ID: ").append(exchange.getFromRouteId());
    }

    private StringBuilder writeMapToOutputStream(Map<String, Object> map) {
        if ((map == null) || map.isEmpty()) return new StringBuilder();
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, Object> pair : map.entrySet()) {
            builder.append(pair.getKey()).
                    append(Utils.MAP_SEPARATOR).
                    append(Utils.elementToString(pair.getValue())).
                    append(System.lineSeparator());
        }
        return builder;
    }

    private String getBodyToString(Exchange exchange) {
        return Utils.elementToString(exchange.getIn().getBody());
    }
}
