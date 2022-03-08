package persistence.writer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class RobotPartPersister {
	
	public void serialisePart(RobotPart partToStore) throws IOException{
		BufferedWriter store = new BufferedWriter(new FileWriter("Store.txt"));
		
		store.write(partToStore.name);
		store.newLine();
		
		store.write(partToStore.description);
		store.newLine();
		
		store.write(partToStore.supplier);
		store.newLine();
		
		store.write(partToStore.weight);
		store.newLine();
		
		store.close();
	}
	
	public RobotPart deserialisePart() throws IOException {
		BufferedReader read = new BufferedReader(new FileReader("Store.txt"));
		
		RobotPart part = new RobotPart();
		part.name = read.readLine();
		part.description = read.readLine();
		part.supplier = read.readLine();
		part.weight = Integer.parseInt(read.readLine());
		
		read.close();
		
		return part;
	}
}