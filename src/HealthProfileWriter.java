import generated.*;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;

public class HealthProfileWriter {
	
	private static People people;

	public static void initializeDB() throws ParseException, DatatypeConfigurationException {
		// creates 20 random people and put them in the static object people
		
		people = new People();
		String[] names = {"Giuseppe","Maria","Giovanni","Anna","Antonio","Giuseppina","Mario","Rosa","Luigi","Angela","Francesco","Giovanna","Angelo","Teresa","Vincenzo","Lucia","Pietro","Carmela","Salvatore","Caterina"};
		String[] surnames = {"Rossi","Russo","Ferrari","Esposito","Bianchi","Romano","Colombo","Ricci","Marino","Greco","Bruno","Gallo","Conti","De","Mancini","Costa","Giordano","Rizzo","Lombardi","Moretti"};
		
		GregorianCalendar gc = new GregorianCalendar();
		Healthprofile hp = null;
		Person p = null;
    	DateFormat df=new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
    	
		int year, dayOfYear;
		double weight, height;
		String dateStr;

		for(int c=0; c<20; c++){
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
			
			// new istance of person from random data
			p = new Person(String.valueOf(c) ,names[c], surnames[c], dateStr, hp);
			// add new random inìstance to people List<person> field
			people.getPerson().add(p);
		}
	}	
	
    public static double randBetween(int start, int end) {
    	double rnd = start + (int)Math.round(Math.random() * (end - start)); 
    	return Math.floor(rnd * 100) / 100;
    }	

	public static void main(String[] args) throws Exception {
		//put in static variable people 20 random istances
		initializeDB();
		// marshall generated people inside people.xml
		JAXBContext jc = JAXBContext.newInstance(People.class);
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        
        m.marshal(people,new File("people.xml")); // marshalling into a file
        System.out.println("created people.xml");
        
    }
}
