package ru.vsk.Utilities.parsers;

import org.w3c.dom.Node;

public abstract class EasyParser {
    public static EasyParser getParser(String source) throws EasyParserException {
        try {
            if (source == null)
                throw new EasyParserException("Can't convert data");
            source = source.trim();
            if (source.charAt(0) == '{') {
                return new EasyJsonParser(source);
            } else if (source.charAt(0) == '<') {
                return new EasyXmlParser(source);
            } else {
                return new EasyEmptyParser(source);
            }
        } catch (EasyParserException e) {
            throw e;
        } catch (Throwable e) {
            throw new EasyParserException("Can't convert data");
        }
    }

    public static EasyParser getParser(Node source) throws EasyParserException {
        try {
            if (source == null)
                throw new EasyParserException("Can't convert data");
            return new EasyXmlParser(source);
        } catch (EasyParserException e) {
            throw e;
        } catch (Throwable e) {
            throw new EasyParserException("Can't convert data");
        }
    }

    public static EasyParser getParser(byte[] source) throws EasyParserException {
        try {
            String byteToString = null;
            byteToString = new String(source, "UTF-8");
            return getParser(byteToString);
        } catch (EasyParserException e) {
            throw e;
        } catch (Throwable e) {
            throw new EasyParserException("Can't convert data");
        }
    }

    public abstract String toString();
}
