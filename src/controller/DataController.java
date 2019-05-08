package controller;

import java.io.File;
import java.io.IOException;
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
    
    public void addTournament(String tournament, String sport, int prize, String date){
    	//dataBase.addRecord();
    	List<Tournament> addTournament = dataBase.getRecordList();
    	addTournament.add(new Tournament(tournament, sport, prize, date));
    	dataBase.setRecordList(addTournament);
    }
    
    public boolean save(File file) {
        if (domParser == null)
            domParser = new XmlDomParser(dataBase.getRecordList());
        domParser.setFile(file);
        try {
            domParser.write();
            return true;
        } catch (TransformerException | ParserConfigurationException e) {
            return false;
        }
    }
   
    
    public void open(File file) throws ParserConfigurationException, SAXException, IOException {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XmlSaxParser saxParser = new XmlSaxParser();
            parser.parse(file, saxParser);
            dataBase.setRecordList(saxParser.getTournamentList());
            //return saxParser.getTournamentList();
    }
    
    public void cleardDB(){
        dataBase.removeAllTournaments();
    }

    public List<Tournament> getListOfTournaments(){
       return dataBase.getRecordList();
    }
}
