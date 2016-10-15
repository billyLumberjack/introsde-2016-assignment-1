import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import model.HealthProfile;
import model.Person;
import dao.PeopleStore;

public class HealthProfileWriter {  	
	public static PeopleStore people = new PeopleStore();

	public static void initializeDB() {
		Person pallino = new Person();
		Person pallo = new Person(new Long(1), "Pallo", "Pinco", "1984-06-21");
		HealthProfile hp = new HealthProfile(68.0, 1.72);
		Person john = new Person(new Long(2), "John", "Doe", "1985-03-20", hp);
		people.getData().add(pallino);
		people.getData().add(pallo);
		people.getData().add(john);
		
		String[] names = {"Giuseppe","Maria","Giovanni","Anna","Antonio","Giuseppina","Mario","Rosa","Luigi","Angela","Francesco","Giovanna","Angelo","Teresa","Vincenzo","Lucia","Pietro","Carmela","Salvatore","Caterina"};
		String[] surnames = {"Rossi","Russo","Ferrari","Esposito","Bianchi","Romano","Colombo","Ricci","Marino","Greco","Bruno","Gallo","Conti","De","Mancini","Costa","Giordano","Rizzo","Lombardi","Moretti"};
		
		GregorianCalendar gc = new GregorianCalendar();
		int year, dayOfYear;
		double weight, height;

		
		for(int c=0; c<20; c++){
			year = randBetween(1900, 2016);
			dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));
			weight = 10 + (Math.random() * 150); 
			height = (50 +(Math.random() * 200))/100; 
			
			weight = Math.floor(weight * 100) / 100;
			height = Math.floor(height * 100) / 100;
			
			gc.set(gc.YEAR, year);
			gc.set(gc.DAY_OF_YEAR, dayOfYear);
			
			hp = new HealthProfile(weight, height);
			Person p = new Person(new Long(c+3), names[c], surnames[c], gc.get(gc.YEAR) + "-" + (gc.get(gc.MONTH) + 1) + "-" + gc.get(gc.DAY_OF_MONTH), hp);
			people.getData().add(p);
		}
	}	
	
    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }	

	public static void main(String[] args) throws Exception {
		
		initializeDB();
		
		JAXBContext jc = JAXBContext.newInstance(PeopleStore.class);
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        
        m.marshal(people,new File("people.xml")); // marshalling into a file
        m.marshal(people, System.out);			  // marshalling into the system default output
    }
}
