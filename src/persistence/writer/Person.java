package persistence.writer;

import java.io.IOException;

public class Person {
	public String name;
	public String address;
	public String email;
	public String telephone;
	@Override
	public String toString() {
		return "Person [name=" + name + ", address=" + address + ", email=" + email + ", telephone=" + telephone + "]";
	}
	public static void main(String[] args) throws IOException {
		Person jackiebrown = new Person();
		
		jackiebrown.name = "Jackie Brown";
		jackiebrown.address = "506 NE University Avenue 9th Street";
		jackiebrown.email = "jackiebrown@uw.edu";
		jackiebrown.telephone = "+1(495)593-43";
		
		PersonPersister store = new PersonPersister();
		store.serialisePerson(jackiebrown);
		
		Person storedPerson = new Person();
		storedPerson = store.deserialisePerson();
		
		if (!jackiebrown.toString().equals(storedPerson.toString())) {
			new RuntimeException("Store failed! "+storedPerson+" does not match "+jackiebrown);
		}else {
			System.out.println("Everything is fine");
		}
		
	}
}

