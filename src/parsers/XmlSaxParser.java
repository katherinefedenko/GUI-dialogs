package parsers;

import org.xml.sax.helpers.DefaultHandler;

import model.Tournament;
import model.Winner;
import java.time.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.*;

public class XmlSaxParser extends DefaultHandler {
	private List<Tournament> tournamentList = new ArrayList<>();
	private Tournament tournament;
	private String name;
	private LocalDate date;
	//private String date;
	private String sport;
	private int prizeAmount;
	private String firstName;
	private String lastName;
	private String lastElementName;
	private int income;

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		lastElementName = qName;
		if (lastElementName.equals("name")) {
			tournament = new Tournament();
			tournamentList.add(tournament);
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String content = new String(ch, start, length);
		content = content.replace("\n", "").trim();
		if (!content.isEmpty()) {
			if (lastElementName.equals(parsers.XMLConst.NAME))
				name = content;
			else if (lastElementName.equals(parsers.XMLConst.DATE))
				date = LocalDate.parse(content);
			else if (lastElementName.equals(parsers.XMLConst.SPORT))
				sport = content;
			else if (lastElementName.equals(parsers.XMLConst.PRIZE))
				prizeAmount = new Integer(content);
			else if (lastElementName.equals(parsers.XMLConst.FIRST_NAME))
				firstName = content;
			else if (lastElementName.equals(parsers.XMLConst.LAST_NAME))
				lastName = content;
			//else if (lastElementName.equals(parsers.XMLConst.INCOME))
				//income = new Integer(content);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if ((name != null && !name.isEmpty()) && (sport != null && !sport.isEmpty()) && (prizeAmount != 0)
				&& (firstName != null && !firstName.isEmpty()) && (lastName != null && !lastName.isEmpty())
				&& (prizeAmount != 0)) {
			tournament.setName(name);
			tournament.setDate(date);
			tournament.setSport(sport);
			tournament.setPrizeAmount(prizeAmount);
			tournament.setFirstName(firstName);
			tournament.setLastName(lastName);
			tournament.setIncome(1);

			name = null;
			date = null;
			sport = null;
			prizeAmount = 0;
			firstName = null;
			lastName = null;
			//income = 0;
		} else
			return;
	}

	public List<Tournament> getTournamentList() {
		return tournamentList;
	}

}
