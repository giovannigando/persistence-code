package persistence.jdbc;

public class PersonJDBC {
	int ID;
	public String name;
	public String address;
	public String telephone;
	public String email;
	public String toString() {
		return "Person [name=" + name + ", address=" + address + ", email=" + email + ", telephone=" + telephone + "]";
	}

}
