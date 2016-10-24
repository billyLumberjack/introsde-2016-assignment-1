import generated.Healthprofile;
import generated.People;
import generated.Person;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

public class DataMapper {
	
	
	//private static PeopleStore pt = null;
	private static People people = null;

	public static void main(String[] args) throws JAXBException, JsonGenerationException, JsonMappingException, IOException, ParseException, DatatypeConfigurationException {
		// TODO Auto-generated method stub

		try {
			// generate 3 random people and marshall them to people_new.xml
			System.out.println("--- Initialized variable people as null ---");
			people = generatePeople();
			System.out.println("--- generated 3 random people inside the variable ---");
			marshall(people, "people_new.xml");
			System.out.println("--- marshalled people to 'people_new.xml' ---");
			
			//clean its local variable and unmarshall the previous created people from people_new.xml
			people = new People();
			System.out.println("cleaned people variable");
			System.out.println("people size:\t" + people.getPerson().size());
			people = unmarshall("people_new.xml");
			System.out.println("--- unmarshalled from 'people_new.xml' ---");
			System.out.println("people size:\t" + people.getPerson().size());
			
			//generate other 3 random people and marshall them to people_new.json
			people = new People();
			System.out.println("--- cleaned variable people ---");
			System.out.println("people size:\t" + people.getPerson().size());
			people = generatePeople();
			System.out.println("--- generated 3 random people inside the variable ---");
			marshallJSON(people, "people_new.json");
			System.out.println("--- marshalled people to 'people_new.json' ---");
			
			//clean its local variable and unmarshall the previous created people from people_new.json
			people = new People();
			System.out.println("cleaned people variable");
			System.out.println("people size:\t" + people.getPerson().size());
			people = unmarshallJSON("people_new.json");
			System.out.println("--- unmarshalled from 'people_new.json' ---");
			System.out.println("people size:\t" + people.getPerson().size());
			
		} catch (NumberFormatException | XPathExpressionException
				| ParserConfigurationException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	private static People generatePeople() throws ParseException, DatatypeConfigurationException, NumberFormatException, XPathExpressionException, ParserConfigurationException, FileNotFoundException, SAXException, IOException{
		// generates 3 random people and return a People object
		People pp = new People();
		
		int nextId = 0;
		String[] names = {"Giuseppe","Maria","Giovanni"};
		String[] surnames = {"Rossi","Russo","Ferrari"};
		
		GregorianCalendar gc = new GregorianCalendar();
		Healthprofile hp = null;
		Person p = null;
    	DateFormat df=new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
    	
		int year, dayOfYear;
		double weight, height;
		String dateStr;

		
		for(int c=0; c<3; c++){
			// generating random weight and height
			weight = randBetween(10, 150); 
			height = randBetween(50, 200);			
			
			// generating random date for healthprofile's lastupdate
			year = (int) randBetween(1900, 2016);
			dayOfYear = (int) randBetween(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));
			gc.set(Calendar.YEAR, year);
			gc.set(Calendar.DAY_OF_YEAR, dayOfYear);
			df.setCalendar(gc);
			dateStr = df.format(gc.getTime());
			// creating random healthprofile
			hp = new Healthprofile(weight, height, dateStr);
			
			// generating random date for person's birthday			
			year = (int) randBetween(1900, 2016);
			dayOfYear = (int) randBetween(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));
			gc.set(Calendar.YEAR, year);
			gc.set(Calendar.DAY_OF_YEAR, dayOfYear);
			df.setCalendar(gc);
			dateStr = df.format(gc.getTime());			
			
			int rnd = (int)randBetween(0,2);
			p = new Person(String.valueOf(nextId) ,names[rnd], surnames[rnd], dateStr, hp);
			rnd = (int)randBetween(0,2);
			pp.getPerson().add(p);
			nextId++;
		}
		return pp;
	}
	
    private static double randBetween(int start, int end) {
    	double rnd = start + (int)Math.round(Math.random() * (end - start)); 
    	return Math.floor(rnd * 100) / 100;
    }	

	public static void marshallJSON(People pp, String filename) throws JAXBException, JsonGenerationException, JsonMappingException, IOException{
		/*
		 * given a People istance and a filename the function marshall the object into the indicated file in json format
		 */
		
		// Jackson Object Mapper 
		ObjectMapper mapper = new ObjectMapper();
		
		// Adding the Jackson Module to process JAXB annotations
        JaxbAnnotationModule module = new JaxbAnnotationModule();
        
		// configure as necessary
		mapper.registerModule(module);
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);

        mapper.writeValue(new File(filename), pp);
        mapper.writeValue(System.out, pp);
	}
	
	public static People unmarshallJSON(String filename) throws JAXBException, IOException{
		/*
		 * given a filename the function unmarshall from a json format
		 * return the People associated istance
		 */		
		// Jackson Object Mapper 
		ObjectMapper mapper = new ObjectMapper();
		
		// Adding the Jackson Module to process JAXB annotations
        JaxbAnnotationModule module = new JaxbAnnotationModule();
        
		// configure as necessary
		mapper.registerModule(module);
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        
        return mapper.readValue(new File(filename), People.class);	
	}
	
	
	public static void marshall(People p, String filename) throws JAXBException, FileNotFoundException{
		/*
		 * given a People istance and a filename the function marshall the object into the indicated file in xml format
		 */		
		JAXBContext jc = JAXBContext.newInstance(People.class);
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		
		m.marshal(p, new File(filename));
		m.marshal(p, System.out);
		
	}
	
	public static People unmarshall(String filename) throws JAXBException, FileNotFoundException{
		/*
		 * given a filename the function unmarshall from an xml format
		 * return the People associated istance
		 */		
		
		JAXBContext jc = JAXBContext.newInstance(People.class);
		Unmarshaller um = jc.createUnmarshaller();
		
		return (People) um.unmarshal(new FileReader(filename)) ;

	}	

}
