package ru.vsk.Utilities;

import org.apache.camel.component.cxf.CxfPayload;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.vsk.Utilities.parsers.EasyParser;
import ru.vsk.Utilities.parsers.EasyParserException;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.locks.Lock;

/**
 * Helping class
 */
public class Utils {
    public static final String MAP_SEPARATOR = " : ";
    public static final String FINAL_SEPARATOR =
            "///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////";

    private Utils() {
    }

    /**
     * Write data to hdd/ssd
     *
     * @param builder incoming StringBuilder object of incoming data
     * @param lock    lock for sync write data
     * @param file    file for write
     */
    public static void writeData(StringBuilder builder, Lock lock, File file) {
        synchronized (lock) {
            try (OutputStream out = new FileOutputStream(file, true)) {
                out.write(builder.append(FINAL_SEPARATOR).toString().getBytes());
            } catch (IOException e) {
                System.out.println("ru.vsk.DebugProcessor --> IOException: " + System.lineSeparator() + e.getMessage());
            }
        }
    }

    /**
     * get double separator =)
     *
     * @return StringBuilder object of DS
     */
    public static StringBuilder getDoubleSeparator() {
        return new StringBuilder().
                append(System.lineSeparator()).
                append(System.lineSeparator());
    }

    /**
     * get current Gregorian Calendar 'dd.MM.yyyy @ hh:mm:ss'
     *
     * @return return data in String
     */
    public static String getFormattedGregorianCalendar() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy @ hh:mm:ss");
        Calendar calendar = new GregorianCalendar();
        format.setCalendar(calendar);
        return format.format(calendar.getTime());
    }

    /**
     * Unknown object transform to String
     *
     * @param o incoming Object
     * @return return String represent of Object
     */
    public static String elementToString(Object o) {
        if (o == null) return "@NULL";
        StringBuilder builder = new StringBuilder();
        try {
            if (o instanceof Document) {
                builder.append("IT IS DOM: ").
                        append(System.lineSeparator()).
                        append(EasyParser.getParser((Document) o));
            } else if (o instanceof CxfPayload) {
                builder.append("IT IS CxfPayload: ").
                        append(System.lineSeparator()).
                        append(cxfPayloadToString((CxfPayload) o));
            } else if (o instanceof byte[]) {
                builder.append(EasyParser.getParser((byte[]) o));
            } else {
                builder.append(EasyParser.getParser(o.toString()));
            }
        } catch (EasyParserException e) {
            builder.append(o);
        }
        return builder.toString();
    }

    private static String cxfPayloadToString(CxfPayload cxfPayload) throws EasyParserException {
        List<Element> elements = cxfPayload.getBody();
        StringBuilder builder = new StringBuilder();
        int count = 1;
        for (Element element : elements) {
            builder.append(count++).append("# ---->").
                    append(System.lineSeparator()).
                    append(EasyParser.getParser(element)).
                    append(System.lineSeparator());
        }
        return builder.toString();
    }
}
