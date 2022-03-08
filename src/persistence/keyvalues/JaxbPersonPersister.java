package persistence.keyvalues;

import java.io.File;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class JaxbPersonPersister {
	
	private JAXBContext jaxbContext = null;
	private Marshaller marshaller = null;
	private Unmarshaller unmarshaller = null;
	private File storeFile = null;
	
	public JaxbPersonPersister(String filename) throws Exception{
		jaxbContext = JAXBContext.newInstance(Person.class);
		marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		unmarshaller = jaxbContext.createUnmarshaller();
		storeFile = new File(filename);
	}
	public void personSerializer(Person person2store) throws JAXBException{
		marshaller.marshal(person2store, storeFile);
		
	}
	public RobotPart persondeserializer() throws JAXBException{
		return (RobotPart) unmarshaller.unmarshal(storeFile);
	}
	
	
}
