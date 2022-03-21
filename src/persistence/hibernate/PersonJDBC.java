package persistence.hibernate;

public class PersonJDBC {
	int id;
	public String name;
	public String address;
	public String telephone;
	public String email;
	public String toString() {
		return "Person [name=" + name + ", address=" + address + ", email=" + email + ", telephone=" + telephone + "]";
	}

}
