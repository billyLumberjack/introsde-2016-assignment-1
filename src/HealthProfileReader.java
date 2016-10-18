import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import model.HealthProfile;
import model.Person;
import dao.PeopleStore;

public class HealthProfileReader {  	
	public static PeopleStore people = new PeopleStore();

	public static void main(String[] args) throws Exception {
		printPeopleByWeight(26.93,"<");
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
	
	public static void printPeopleByWeight(double weight, String operator){
		if(operator.equals("<") || operator.equals(">") || operator.equals("=")){
		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
		    Document document = builder.parse(new FileInputStream("people.xml"));
		    XPath xPath =  XPathFactory.newInstance().newXPath();		
		    NodeList nodes = (NodeList) xPath.evaluate("/people/peopleList/person[healthprofile/weight"+operator+String.valueOf(weight)+"]", document, XPathConstants.NODESET);
		   
		    for(int c=0; c<nodes.getLength(); c++){
		    	Element e = (Element) nodes.item(c);
		    	System.out.println("Person id: "+ e.getAttribute("id"));
	        
	        	
		    	System.out.println("first name and last name: "+
		    			e.getElementsByTagName("firstname").item(0).getTextContent() +
		    			" "+
		    			e.getElementsByTagName("lastname").item(0).getTextContent()
		    			);
	        	System.out.println("Birthdate: "+e.getElementsByTagName("birthdate").item(0).getTextContent());
	        	System.out.println("HealthProfile:");
	        	System.out.println("Weight: "+e.getElementsByTagName("weight").item(0).getTextContent());
	        	System.out.println("Height: "+e.getElementsByTagName("height").item(0).getTextContent());
	        	System.out.println("BMI: "+e.getElementsByTagName("bmi").item(0).getTextContent()+"\n");
	        
		    }
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else{
			System.out.println("inserted wrong operator");
			return;
		}
		
		
	}
	
	public static void printHealthProfile(int id){

		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
		    Document document = builder.parse(new FileInputStream("people.xml"));
		    XPath xPath =  XPathFactory.newInstance().newXPath();
		    
		    double weight = Double.parseDouble(xPath.evaluate("/people/peopleList/person[@id="+id+"]/healthprofile/weight", document));
		    double height = Double.parseDouble(xPath.evaluate("/people/peopleList/person[@id="+id+"]/healthprofile/height", document));
		    
		    HealthProfile hp = new HealthProfile(weight, height);
		    hp.print();
		    
		    }
		catch (IOException e) {
		    e.printStackTrace();
		}catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void printPeopleList(){
		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance(PeopleStore.class);
	        System.out.println();
	        System.out.println("Output from our XML File: ");
	        Unmarshaller um = jc.createUnmarshaller();
	        PeopleStore people = (PeopleStore) um.unmarshal(new FileReader("people.xml"));
	        List<Person> list = people.getData();
	        for (Person person : list) {
	        	System.out.println("Person id: "+person.getPersonId());
	        	System.out.println("first name and last name: "+ person.getFirstname()+" "+person.getLastname());
	        	System.out.println("Birthdate: "+person.getBirthdate());
	        	System.out.println("HealthProfile:");
	        	System.out.println("Weight: "+person.getHProfile().getWeight());
	        	System.out.println("Height: "+person.getHProfile().getHeight());
	        	System.out.println("BMI: "+person.getHProfile().getBMI()+"\n");
	        }				
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
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
