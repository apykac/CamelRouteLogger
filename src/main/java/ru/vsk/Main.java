package ru.vsk;

import org.xml.sax.SAXException;
import ru.vsk.Utilities.parsers.EasyParser;
import ru.vsk.Utilities.parsers.EasyParserException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, EasyParserException {
        String str = "_IS_JSON : true\n" +
                "T_MESSAGE_ID : 1\n" +
                "T_REQUEST_BODY : <sch:getRisksForPolicyRequest xmlns:sch=\"http://sl/ignite/schema\"\n" +
                "                              xmlns:clm=\"http://sl/claims/schema\"\n" +
                "                              xmlns:cc=\"http://sl/clientCache/schema\"\n" +
                "                              xmlns:msg=\"http://sl/messaging/schema\">\n" +
                "   <claimId>8271</claimId>\n" +
                "   <messageId>1</messageId>\n" +
                "   <policyId>2164351</policyId>\n" +
                "   <sessionId>13</sessionId>\n" +
                "   <systemId>1</systemId>\n" +
                "</sch:getRisksForPolicyRequest>\n" +
                "\n" +
                "T_SESSION_ID : 13\n" +
                "T_SYSTEM_ID : 1";



        System.out.println(EasyParser.getParser(str));
    }

}
