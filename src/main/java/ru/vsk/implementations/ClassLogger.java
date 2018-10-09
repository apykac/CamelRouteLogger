package ru.vsk.implementations;

import org.apache.camel.Exchange;
import ru.vsk.Utilities.Utils;
import ru.vsk.interfaces.CustomLogger;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ClassLogger implements CustomLogger {
    private static final Lock lock = new ReentrantLock();
    private final File file;

    public ClassLogger(String fileName) {
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
        Utils.writeData(new StringBuilder().
                append(Utils.getDoubleSeparator()).
                append(Utils.getFormattedGregorianCalendar()).
                append(Utils.getDoubleSeparator()).
                append(comment).
                append(Utils.getDoubleSeparator()),lock,file);
    }
}
