package businessLogic;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import dataAccess.DataAccess;
import domain.User;

public class TestFacadeImplementation {
	
	private User e = new User("erabiltzaile","erabiltzaile");

	protected EntityManager db;
	protected EntityManagerFactory emf;
	
	
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
