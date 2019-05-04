package xml;

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
import xml.XMLConst;

public class XmlDomParser {

	private final String fileName;
	private List<Tournament> tournamentsdb; 

	public XmlDomParser(String fileName, List<Tournament> tournaments) {
		this.fileName = fileName + ".xml";
		tournamentsdb = tournaments;
	}

	public void parseInto() {
		Document document = createDocument();
		try {
			Transformer tr = TransformerFactory.newInstance().newTransformer();
			DOMSource source = new DOMSource((Node) document);
			File file = new File(fileName);
			StreamResult result = new StreamResult(file);
			tr.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace(System.out);
		}

	}

	private Document createDocument() {
		try {
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            Element data = document.createElement(xml.XMLConst.TOURNAMENT_LIST);
            document.appendChild(data);
            
            for (Tournament tournament : tournamentsdb) {
            	Winner winner = tournament.getWinner();
            	Element node = document.createElement(xml.XMLConst.TOURNAMENT);
                data.appendChild(node);
                
                Element name = document.createElement(xml.XMLConst.NAME);
                name.setTextContent(tournament.getName());
                node.appendChild(name);
                
                Element date = document.createElement(xml.XMLConst.DATE);
                date.setTextContent(tournament.getDate().toString());
                node.appendChild(date);
                
                Element sport = document.createElement(xml.XMLConst.SPORT);
                sport.setTextContent(tournament.getSport());
                node.appendChild(sport);
                
                Element prizeAmount = document.createElement(xml.XMLConst.PRIZE);
                prizeAmount.setTextContent(Integer.toString(tournament.getPrizeAmount()));
                node.appendChild(prizeAmount);
                
                Element win = document.createElement(xml.XMLConst.WINNER);
                node.appendChild(win);
                
                Element firstName = document.createElement(xml.XMLConst.FIRST_NAME);
                firstName.setTextContent(winner.getFirstName());
                win.appendChild(firstName);
                
                Element lastName = document.createElement(xml.XMLConst.LAST_NAME);
                lastName.setTextContent(winner.getLastName());
                win.appendChild(lastName);
                
                Element income = document.createElement(xml.XMLConst.INCOME);
                income.setTextContent(Integer.toString(winner.getIncome()));
                win.appendChild(income);
            }
               return document; 
		}	catch (ParserConfigurationException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Ошибка сохранения файла!");
        }
		return null;
	}
	
	

}
