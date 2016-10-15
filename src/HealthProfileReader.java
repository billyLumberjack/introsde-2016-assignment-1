import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import model.Person;
import dao.PeopleStore;

public class HealthProfileReader {  	
	public static PeopleStore people = new PeopleStore();

	public static void main(String[] args) throws Exception {
		System.out.println(getHeight(7));
		/*
		JAXBContext jc = JAXBContext.newInstance(PeopleStore.class);
        System.out.println();
        System.out.println("Output from our XML File: ");
        Unmarshaller um = jc.createUnmarshaller();
        PeopleStore people = (PeopleStore) um.unmarshal(new FileReader("people.xml"));
        List<Person> list = people.getData();
        for (Person person : list) {
          System.out.println("Person: " + person.getFirstname() + " born "
              + person.getBirthdate());
        }
        */

    }
	
	public static void printPeopleList(){
		
	}
	
	public static double getWeight(int id){
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
		    builder = builderFactory.newDocumentBuilder();
		    Document document = builder.parse(new FileInputStream("people.xml"));
		    
		    XPath xPath =  XPathFactory.newInstance().newXPath();
		    return Double.parseDouble(xPath.compile("/people/peopleList/person[@id="+id+"]/healthprofile/weight").evaluate(document));
		    }
		catch (ParserConfigurationException e) {
		    e.printStackTrace();  
		}catch (SAXException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public static double getHeight(int id){
		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
		    Document document = builder.parse(new FileInputStream("people.xml"));
		    XPath xPath =  XPathFactory.newInstance().newXPath();
		    
		    return Double.parseDouble(
		    		xPath.compile("/people/peopleList/person[@id="+id+"]/healthprofile/height").evaluate(document)
		    		);
		    }
		catch (ParserConfigurationException e) {
		    e.printStackTrace();  
		}catch (SAXException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}	
}
