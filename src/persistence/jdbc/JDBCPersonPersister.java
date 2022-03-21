package persistence.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCPersonPersister {
	private Connection connection = null;
	
	public JDBCPersonPersister(String url) throws SQLException{
		try {
			connection = DriverManager.getConnection(url);
		} catch(Throwable th) {
			throw new SQLException(th+" attempting to connect JDBC url: "+url,th);
		}
	}
	
	
	public void createCustomerTable() throws SQLException{
		Statement statement = connection.createStatement();
		statement.execute("CREATE TABLE IF NOT EXISTS Customers("
				+ "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "Name VARCHAR(50) NOT NULL,"
				+ "Address VARCHAR(20),"
				+ "Telephone VARCHAR(15),"
				+ "Email VARCHAR(30));");
		
	}
	
	public void createCustomer(PersonJDBC aCustomer) throws SQLException{
		PreparedStatement statement = connection.prepareStatement(
				"insert into Customers(Name, Address, Telephone, Email) values (?,?,?,?)",
				Statement.RETURN_GENERATED_KEYS);
		statement.setString(1,aCustomer.name);
		statement.setString(2,aCustomer.address);
		statement.setString(3,aCustomer.telephone);
		statement.setString(4,aCustomer.email);
		statement.executeUpdate();
		
		ResultSet results = statement.getGeneratedKeys();
		
		if (results.next()) {
			aCustomer.ID= results.getInt(1);
		}

	}
	public PersonJDBC readCustomer(int customerID) throws SQLException{
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM Customers WHERE ID = ?");
		statement.setInt(1, customerID);
		
		ResultSet resultSet = statement.executeQuery();
		
		resultSet.next();
		PersonJDBC person = new PersonJDBC();
		person.ID = resultSet.getInt("ID");
		person.name= resultSet.getString("Name");
		person.address = resultSet.getString("Address");
		person.email = resultSet.getString("Email");
		person.telephone = resultSet.getString("Telephone");
		
		return person;
		
	}
	public List<PersonJDBC> listCustomers(String name) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM Customers WHERE Name LIKE '%?%'");
		statement.setString(1,name);
		ResultSet resultSet = statement.executeQuery();
		
		List<PersonJDBC> matches = new ArrayList<PersonJDBC>();
		while(resultSet.next()) {
			PersonJDBC listed = new PersonJDBC();
			listed.ID = resultSet.getInt("ID");
			listed.name= resultSet.getString("Name");
			listed.address = resultSet.getString("Address");
			listed.email = resultSet.getString("Email");
			listed.telephone = resultSet.getString("Telephone");
			matches.add(listed);
		}

		return matches;
	}
	public void updateCustomer(PersonJDBC aCustomer) throws SQLException{
		PreparedStatement statement = connection.prepareStatement("UPDATE Customers SET Name=?, Address=?, Telephone=?, Email=? WHERE ID=?");
		statement.setString(1, aCustomer.name);
		statement.setString(2, aCustomer.address);
		statement.setString(3, aCustomer.telephone);
		statement.setInt(4, aCustomer.ID);
		
		statement.executeUpdate();
	}
	public void deleteCustomer(int CustomerID) throws SQLException{
		PreparedStatement statement = connection.prepareStatement("DELETE Customers WHERE ID=?");
		statement.setInt(1, CustomerID);
		statement.executeUpdate();
	}
	
}

