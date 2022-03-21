package persistence.hibernate;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernatePersonPersister {
	private SessionFactory sessionFactory = null;
	
	public HibernatePersonPersister() {
		File configFile = new File("hibernate.cfg.xml");
		
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure(configFile)
				.build();
		
		sessionFactory = new MetadataSources(registry)
				.buildMetadata()
				.buildSessionFactory();
		
	}
	
	
	public void createCustomer (PersonJDBC aCustomer) {
		updateCustomer(aCustomer);
	}
	
	public void updateCustomer(PersonJDBC aCustomer) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(aCustomer);
		session.getTransaction().commit();
		session.close();
	}
	public void deleteCustomer(PersonJDBC aCustomer) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(aCustomer);
		session.getTransaction().commit();
		session.close();
	}
	public PersonJDBC readCustomer(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		PersonJDBC customer = session.find(PersonJDBC.class, id);
		session.getTransaction().commit();
		session.close();
		return customer;
	}

	public static void main(String[] args) throws IOException, SQLException{
		PersonJDBC samjones = new PersonJDBC();
		samjones.name = "Sam Jones";
		samjones.address = "13 Letsbe Avenue";
		samjones.email = "sam.jones@openclassrooms.co.uk";
		samjones.telephone = "349034983439";
		
		HibernatePersonPersister persister = new HibernatePersonPersister();
		persister.updateCustomer(samjones);
		PersonJDBC storedSam = persister.readCustomer(samjones.id);
		if (!storedSam.toString().equals(samjones.toString())) throw new RuntimeException("Stored Sam is not equal to original Sam");
		System.out.println(storedSam.toString());
		System.out.println(samjones.toString());
		
	}
}
