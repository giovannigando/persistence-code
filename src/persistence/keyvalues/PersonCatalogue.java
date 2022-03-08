package persistence.keyvalues;

import java.util.ArrayList;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PersonCatalogue {
	public ArrayList <Person> catalogue = new ArrayList<Person>();
	
	
}
