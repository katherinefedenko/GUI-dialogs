package parsers;

import java.util.List;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.Document;

import model.Tournament;
import model.Winner;
import parsers.XMLConst;

public class XmlDomParser {

	private File fileName;
	private List<Tournament> tournaments; 

	public XmlDomParser(List<Tournament> tournaments) {
		this.tournaments = tournaments;
	}

	public void setFile(File fileName) {
        this.fileName = fileName;
    }
	

	public void write() throws TransformerException, ParserConfigurationException {
        if (fileName != null && tournaments != null) {
        	Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            Element data = document.createElement(parsers.XMLConst.TOURNAMENT_LIST);
            document.appendChild(data);
            
            for (Tournament tournament : tournaments) {
            	Winner winner = tournament.getWinner();
            	Element node = document.createElement(parsers.XMLConst.TOURNAMENT);
                data.appendChild(node);
                
                Element name = document.createElement(parsers.XMLConst.NAME);
                name.setTextContent(tournament.getName());
                node.appendChild(name);
                
                Element date = document.createElement(parsers.XMLConst.DATE);
                date.setTextContent(tournament.getDate().toString());
                node.appendChild(date);
                
                Element sport = document.createElement(parsers.XMLConst.SPORT);
                sport.setTextContent(tournament.getSport());
                node.appendChild(sport);
                
                Element prizeAmount = document.createElement(parsers.XMLConst.PRIZE);
                prizeAmount.setTextContent(Integer.toString(tournament.getPrizeAmount()));
                node.appendChild(prizeAmount);
                
                Element win = document.createElement(parsers.XMLConst.WINNER);
                node.appendChild(win);
                
                Element firstName = document.createElement(parsers.XMLConst.FIRST_NAME);
                firstName.setTextContent(winner.getFirstName());
                win.appendChild(firstName);
                
                Element lastName = document.createElement(parsers.XMLConst.LAST_NAME);
                lastName.setTextContent(winner.getLastName());
                win.appendChild(lastName);
                
                Element income = document.createElement(parsers.XMLConst.INCOME);
                income.setTextContent(Integer.toString(winner.getIncome()));
                win.appendChild(income);
            }
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(fileName);
            transformer.transform(domSource, streamResult);
        }
	}
}
