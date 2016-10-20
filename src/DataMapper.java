package model;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import dao.PeopleStore;

public class DataMapper {

	public static void main(String[] args) throws JAXBException {
		// TODO Auto-generated method stub
		marshall();
	}
	
	public static void marshall() throws JAXBException{
		PeopleStore p = null;
		
		JAXBContext jc = JAXBContext.newInstance(PeopleStore.class);
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		
		m.marshal(p, new File("new_people-xml"));
		m.marshal(p, System.out);
	}

}
