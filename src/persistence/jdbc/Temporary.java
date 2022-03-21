package persistence.jdbc;

import java.sql.SQLException;

public class Temporary {
	public static void main(String[] args) throws SQLException{
		String url = "jdbc:sqlite:./RobotShop.db";
		JDBCPersonPersister persister = new JDBCPersonPersister(url);
		PersonJDBC jackie = new PersonJDBC();
		persister.createCustomerTable();
		jackie.name = "Jackie";
		jackie.address = "4938 Street";
		jackie.telephone = "3498348934";
		jackie.email = "jackie@gmail.com";
		persister.createCustomer(jackie);
		
		System.out.println(persister.readCustomer(1));
	}
}
