package persistence.keyvalues;

import java.io.File;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class JaxbPersonCataloguePersister {
	private JAXBContext jaxbContext = null;
	private Marshaller marshaller = null;
	private Unmarshaller unmarshaller = null;
	private File storeFile = null;
	
	public JaxbPersonCataloguePersister(String fileName) throws JAXBException{
		jaxbContext = JAXBContext.newInstance(PersonCatalogue.class);
		
		marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		unmarshaller = jaxbContext.createUnmarshaller();
		
		storeFile = new File(fileName);
	}
	public void personCatalogueSerializer(PersonCatalogue personCatalogue2store) throws JAXBException {
		marshaller.marshal(personCatalogue2store, storeFile);
	}
	
	public PersonCatalogue personCatalogueDeserializer() throws JAXBException {
		return (PersonCatalogue) unmarshaller.unmarshal(storeFile);
		
	}
	public static void main(String[] args) throws JAXBException {
		PersonCatalogue school = new PersonCatalogue();
		Person jackieBrown = new Person();
		jackieBrown.name = "Jackie Brown";
		jackieBrown.address = "345 NE Street";
		jackieBrown.email = "jackiebrown@hotmail.com";
		jackieBrown.telephone = "343485098";
		
		Person lisaBern = new Person();
		lisaBern.name = "Lisa Bern";
		lisaBern.address = "04 NW Avenue";
		lisaBern.email = "lizzylizzy@gmail.com";
		lisaBern.telephone = "39843983489";
		
		school.catalogue.add(jackieBrown);
		school.catalogue.add(lisaBern);
		
		JaxbPersonCataloguePersister persister = new JaxbPersonCataloguePersister("Store.jaxb");
		persister.personCatalogueSerializer(school);
		
		
		
		
		
	}
}
