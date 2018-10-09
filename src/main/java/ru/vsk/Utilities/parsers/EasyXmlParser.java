package ru.vsk.Utilities.parsers;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class EasyXmlParser extends EasyParser {
    private String xml;

    public EasyXmlParser(Node node) throws TransformerException {
        this.xml = nodeToPrettyString(node);
    }

    public EasyXmlParser(String xml) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        this.xml = nodeToPrettyString(xmlToDoc(xml));
    }

    private static Document xmlToDoc(String xml) throws ParserConfigurationException, IOException, SAXException {
        BufferedReader in = new BufferedReader(new StringReader(xml));
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(in));
        return document;
    }

    public static String nodeToPrettyString(Node node) throws TransformerException {
        StringWriter writer = new StringWriter();
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");

        transformer.transform(new DOMSource(node), new StreamResult(writer));
        return writer.toString();
    }

    @Override
    public String toString() {
        return xml;
    }
}
