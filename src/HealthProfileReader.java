import generated.*;

import java.io.FileInputStream;
import java.io.FileReader;
import java.text.ParseException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class HealthProfileReader {  	
	public static People people = null;

	public static void main(String[] args) throws Exception {
		
		System.out.println("--- Printing all people in list with details ---");
		printPeopleList();
		System.out.println("--- Printing Healthprofile from person's id = 5 ---");
		printHealthprofile(5);
		System.out.println("--- Printing people whose weight is > 90kg ---");
		printPeopleByWeight(90, ">");
    }
	
	public static void printPeopleByWeight(double weight, String operator){
		// checks wether the given operator is handled
		if(operator.equals("<") || operator.equals(">") || operator.equals("=")){
			try 
			{
				DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = builderFactory.newDocumentBuilder();
			    Document document = builder.parse(new FileInputStream("people.xml"));
			    XPath xPath =  XPathFactory.newInstance().newXPath();		
			    // retrieve the result with a modified xpath based on the given data
			    NodeList nodes = (NodeList) xPath.evaluate("//person[healthprofile/weight"+operator+String.valueOf(weight)+"]", document, XPathConstants.NODESET);
			   
			    // print all result of the xpath evaluation
			    for(int c=0; c<nodes.getLength(); c++)
			    {
			    	Element e = (Element) nodes.item(c);
			    	System.out.println("Person id: "+ e.getAttribute("id"));
			    	System.out.println(
			    			"first name and last name: "+
			    			e.getElementsByTagName("firstname").item(0).getTextContent() +
			    			" "+
			    			e.getElementsByTagName("lastname").item(0).getTextContent()
			    			);
		        	System.out.println("Birthdate: "+e.getElementsByTagName("birthdate").item(0).getTextContent());
		        	System.out.println("Healthprofile:");
		        	System.out.println("Lastupdate: "+e.getElementsByTagName("lastupdate").item(0).getTextContent());
		        	System.out.println("Weight: "+e.getElementsByTagName("weight").item(0).getTextContent());
		        	System.out.println("Height: "+e.getElementsByTagName("height").item(0).getTextContent());
		        	System.out.println("BMI: "+e.getElementsByTagName("bmi").item(0).getTextContent()+"\n");
			    }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			System.out.println("inserted wrong operator");
			return;
		}
		
		
	}
	
	public static void printHealthprofile(int id) throws DatatypeConfigurationException, ParseException{
		// print the health profile given a certain id
		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
		    Document document = builder.parse(new FileInputStream("people.xml"));
		    XPath xPath =  XPathFactory.newInstance().newXPath();
		    
		    String lastUpdateString =  xPath.evaluate("//person[@id="+id+"]/healthprofile/lastupdate", document);
		    double weight = Double.parseDouble(xPath.evaluate("//person[@id="+id+"]/healthprofile/weight", document));
		    double height = Double.parseDouble(xPath.evaluate("//person[@id="+id+"]/healthprofile/height", document));
		    
		    //create a new health profile from the retrieved data
		    Healthprofile hp = new Healthprofile(weight, height, lastUpdateString);
		    System.out.println(hp.toString());
		    }
		catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	public static void printPeopleList(){
		// print all people listed in the people.xml
		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance(People.class);
	        System.out.println("Output from our XML File: ");
	        Unmarshaller um = jc.createUnmarshaller();
	        People p = (People) um.unmarshal(new FileReader("people.xml"));
	        List<Person> list = p.getPerson();
	        for (Person person : list) {
	        	System.out.println("Person id: "+person.getId());
	        	System.out.println("first name and last name: "+ person.getFirstname()+" "+person.getLastname());
	        	System.out.println("Birthdate: "+person.getBirthdate());
	        	System.out.println("Healthprofile:");
	        	System.out.println(person.getHealthprofile().toString() + "\n");
	        }				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static double getWeight(int id){
		// create an xpath based on the given id and retrieved the weight of the associated person
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
		    builder = builderFactory.newDocumentBuilder();
		    Document document = builder.parse(new FileInputStream("people.xml"));
		    
		    XPath xPath =  XPathFactory.newInstance().newXPath();
		    return Double.parseDouble(xPath.compile("//person[@id="+id+"]/healthprofile/weight").evaluate(document));
		    }
		catch (Exception e) {
		    e.printStackTrace();  
		}
		// return -1 in case of errors
		return -1;
	}
	
	public static double getHeight(int id){
		// create an xpath based on the given id and retrieved the height of the associated person
		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
		    Document document = builder.parse(new FileInputStream("people.xml"));
		    XPath xPath =  XPathFactory.newInstance().newXPath();
		    
		    return Double.parseDouble(
		    		xPath.compile("//person[@id="+id+"]/healthprofile/height").evaluate(document)
		    		);
		    }
		catch (Exception e) {
		    e.printStackTrace();  
		}
		// return -1 in case of errors
		return -1;
	}	
}
