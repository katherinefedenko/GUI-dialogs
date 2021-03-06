package controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

import model.Tournament;
import model.TournamentDataBase;
import parsers.XmlDomParser;
import parsers.XmlSaxParser;
import view.MainFrame;

public class DataController {

	private final TournamentDataBase dataBase;
	private XmlDomParser domParser;
	private XmlSaxParser saxParser;

	public DataController(TournamentDataBase dataBase) {
		this.dataBase = dataBase;
	}

	public void addTournament(String tournament, String sport, int prize, LocalDate date, String firstName, String lastName) {
		List<Tournament> addTournament = dataBase.getRecordList();
		Tournament tour = new Tournament();
		tour.setName(tournament);
		tour.setSport(sport);
		tour.setPrizeAmount(prize);
		tour.setDate(date);
		tour.setFirstName(firstName);
		tour.setLastName(lastName);
		tour.setIncome();
		addTournament.add(tour);
		dataBase.setRecordList(addTournament);
	}
	
	public void open(File file) throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		XmlSaxParser saxParser = new XmlSaxParser();
		parser.parse(file, saxParser);
		dataBase.setRecordList(saxParser.getTournamentList());
		//dataBase.setRecordList(saxParser.getWinnerList());
	}

	public void save(File file) throws TransformerException, ParserConfigurationException {
		domParser = new XmlDomParser(dataBase.getRecordList());
		domParser.setFile(file);
		domParser.write();
	}
	
	public List<Tournament> findByTournamentName(String findTournament, LocalDate findDate) {
		List<Tournament> tournamentSearch = new ArrayList<>();
		for (Tournament tournament : dataBase.getRecordList()) {
			if (findTournament.equals(tournament.getName()) || findDate.equals(tournament.getDate())) {
				tournamentSearch.add(tournament);	
			}
		}
		return tournamentSearch;
	}
	
	public List<Tournament> findBySport(String findSport, String firstName, String lastName) {
		List<Tournament> tournamentSearch = new ArrayList<>();
		for (Tournament tournament : dataBase.getRecordList()) {
			if (findSport.equals(tournament.getSport()) || firstName.equals(tournament.getFirstName()) || lastName.equals(tournament.getLastName())) {
				tournamentSearch.add(tournament);	
			}
		}
		return tournamentSearch;
		
	}
	
	public List<Tournament> findByPrize(int lowerPrize, int upperPrize, int lowerIncome, int upperIncome) {
		List<Tournament> tournamentSearch = new ArrayList<>();
		for (Tournament tournament : dataBase.getRecordList()) {
			if ((tournament.getPrizeAmount() >= lowerPrize && tournament.getPrizeAmount() <= upperPrize) || (tournament.getIncome() >= lowerIncome && tournament.getIncome() <= upperIncome)) {
				tournamentSearch.add(tournament);	
			}
		}
		return tournamentSearch;
		
	}

	public int removeTournament(List<Tournament> delTournament) {
		List<Tournament> newTournament = dataBase.getRecordList();
		int size = newTournament.size();
		for (Tournament tournament : delTournament) {
			newTournament.remove(tournament);
		}
		dataBase.setRecordList(newTournament);
		return size - newTournament.size();
	}	
	public void cleardDB() {
		dataBase.removeAllTournaments();
	}

	public List<Tournament> getListOfTournaments() {
		return dataBase.getRecordList();
	}

	/*public void calculateIncome() {
		double income = 0;
		for(Tournament tournament : getListOfTournaments()) {
			tournament
		}
	}*/
	

}
