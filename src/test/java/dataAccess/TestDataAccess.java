package dataAccess;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.BeforeClass;
import org.junit.Test;

import configuration.ConfigXML;
import domain.Admin;
import domain.User;

public class TestDataAccess {
	private User e = new User("erabiltzaile","erabiltzaile");
	
	protected  EntityManager  db;
	protected  EntityManagerFactory emf;
	

	ConfigXML  c=ConfigXML.getInstance();


	public TestDataAccess()  {

		System.out.println("Creating TestDataAccess instance");

		open();
		

	}

	public void open(){

		System.out.println("Opening TestDataAccess instance ");

		String fileName=c.getDbFilename();

		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", c.getUser());
			properties.put("javax.persistence.jdbc.password", c.getPassword());

			emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			db = emf.createEntityManager();
			
		}

	}
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}
	
	private void register() {
		db.getTransaction().begin();
		db.persist(e);
		db.getTransaction().commit();
	}
	



	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
