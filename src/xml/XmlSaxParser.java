package xml;

import org.xml.sax.helpers.DefaultHandler;

import model.Tournament;
import model.Winner;
import java.time.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.*; 

public class XmlSaxParser {
	private static List<Tournament> tournamentList = new ArrayList<>();
	private static Tournament tournament;
	
	public List<Tournament> getTournamentList() throws ParserConfigurationException, SAXException, IOException{
		SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
   	 	File file = new File("G:\\Downloads\\eclipse-workspace\\Dialog UI\\tournamentDataBase.xml");
        saxParser.parse(file, handler);
		return tournamentList;
	}
	
	private static class XMLHandler extends DefaultHandler {
		private String name;
		//private LocalDate date;
		private String date;
		private String sport;
		private int prizeAmount;
		private String firstName;
		private String lastName;
		private String lastElementName;
		
		@Override 
		public void startDocument() throws SAXException { 
		  //System.out.println("Start parse XML..."); 
		} 
		
		@Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            lastElementName = qName;
            //System.out.println("Start Element :" + qName);
        }
		
		@Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String content = new String(ch, start, length);
            content = content.replace("\n", "").trim();
            if (!content.isEmpty()) {
                if (lastElementName.equals(xml.XMLConst.NAME))
                	name = content;
                else if (lastElementName.equals(xml.XMLConst.DATE))
                	date = content;
                else if (lastElementName.equals(xml.XMLConst.SPORT))
                	sport = content;
                else if (lastElementName.equals(xml.XMLConst.PRIZE))
                	prizeAmount = new Integer(content);
                		
                /*if (lastElementName.equals(xml.XMLConst.FIRST_NAME))
                    firstName = content;
                if (lastElementName.equals(xml.XMLConst.LAST_NAME))
                    lastName = content;
                 if (lastElementName.equals(xml.XMLConst.INCOME))
                    income = content;
                   */ 
           
            }
		}
		@Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if ((name != null && !name.isEmpty()) 
            	&& 	(sport != null && !sport.isEmpty()) 
            	&&	(prizeAmount != 0))
            	/*&& (firstName != null && !firstName.isEmpty())
                    && (lastName != null && !lastName.isEmpty()));*/
               {
            		tournamentList.add(new Tournament(name, sport, prizeAmount, date));
            		name = null;
            		date = null;
            		sport = null;
            		prizeAmount = 0;
             
               }
               else return;
		}
	}
}
	
