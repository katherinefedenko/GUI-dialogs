package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerException;

import org.eclipse.swt.widgets.*;
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

	public void addTournament(String tournament, String sport, int prize, String date) {
		List<Tournament> addTournament = dataBase.getRecordList();
		addTournament.add(new Tournament(tournament, sport, prize, date));
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
	
	public List<Tournament> findByTournamentName(String findTournament, String findDate) {
		List<Tournament> tournamentSearch = new ArrayList<>();
		for (Tournament tournament : dataBase.getRecordList()) {
			if (findTournament.equals(tournament.getName()) || findDate.equals(tournament.getDate())) {
				tournamentSearch.add(tournament);	
			}
		}
		//dataBase.reInitDataBase(tournamentSearch);
		return tournamentSearch;
	}
	
	public List<Tournament> findBySport(String findSport) {
		List<Tournament> tournamentSearch = new ArrayList<>();
		for (Tournament tournament : dataBase.getRecordList()) {
			if (findSport.equals(tournament.getSport())) {
				tournamentSearch.add(tournament);	
			}
		}
		//	dataBase.reInitDataBase(tournamentSearch);
		return tournamentSearch;
		
	}
	
	public List<Tournament> findByPrize(int findPrize) {
		List<Tournament> tournamentSearch = new ArrayList<>();
		for (Tournament tournament : dataBase.getRecordList()) {
			if (findPrize == tournament.getPrizeAmount()) {
				tournamentSearch.add(tournament);	
			}
		}
		//	dataBase.reInitDataBase(tournamentSearch);
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
