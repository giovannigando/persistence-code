package persistence.writer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PersonPersister {
	
	public void serialisePerson(Person personToStore) throws IOException{
		BufferedWriter store = new BufferedWriter(new FileWriter("Store.txt"));
		
		store.write(personToStore.name);
		store.newLine();
		
		store.write(personToStore.address);
		store.newLine();
		
		store.write(personToStore.email);
		store.newLine();
		
		store.write(personToStore.telephone);
		store.newLine();
		
		store.close();
	}
	
	public Person deserialisePerson() throws IOException{
		BufferedReader store = new BufferedReader(new FileReader("Store.txt"));
		
		Person storedPerson = new Person();
		
		storedPerson.name = store.readLine();
		storedPerson.address = store.readLine();
		storedPerson.email = store.readLine();
		storedPerson.telephone = store.readLine();
		
		store.close();
		
		return storedPerson;
	}
}
