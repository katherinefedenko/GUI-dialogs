package view;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import controller.DataController;
import model.TournamentDataBase;

public class Main {
		
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		
		TournamentDataBase dataBase = new TournamentDataBase();
		DataController controller = new DataController(dataBase);
		new MainFrame(controller);
	}
}
