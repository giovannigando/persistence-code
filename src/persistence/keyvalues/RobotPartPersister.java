package persistence.keyvalues;

import java.io.File;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class RobotPartPersister {
	private JAXBContext jaxbContext = null;
	private Marshaller marshaller = null;
	private Unmarshaller unmarshaller = null;
	private File storeFile = null;
	
	public RobotPartPersister(String filename) throws JAXBException {
		jaxbContext = JAXBContext.newInstance(RobotPart.class);
		
		marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		unmarshaller = jaxbContext.createUnmarshaller();

		
		storeFile = new File(filename);
		
	}
	public void serialisePart(RobotPart part2store) throws JAXBException{
		marshaller.marshal(part2store,  storeFile);
		
	}
	
	public RobotPart deserialisePart() throws JAXBException{
		return (RobotPart) unmarshaller.unmarshal(storeFile);
		
	}
}
