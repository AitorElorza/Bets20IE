package test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.User;
import test.bussinesLogic.TestFacadeImplementation;

public class DataAccessTest {

	static DataAccess sut=new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));;
	static TestFacadeImplementation testBL=new TestFacadeImplementation();;


	private boolean emaitza;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//
		
	}


	//Try logIn but user null
	@Test
	public void test1() {
		try {
			emaitza =sut.LogIn(null, "erabiltzaile");
			assertTrue(!emaitza);
		}catch(Exception e) {
			fail();
		}

	}
	//Try logIn but psw null
	@Test
	public void test2() {
		try {
			emaitza = sut.LogIn("erabiltzaile",null);
			assertTrue(!emaitza);
		}catch(Exception e) {
			fail();
		}

	}


	//Try logIn but user not in db && psw not in ddb
	@Test
	public void test3() {
		try {
			emaitza =sut.LogIn("", "");
			assertTrue(!emaitza);
		}catch(Exception e) {
			fail();
		}
	}
	//Try logIn but psw not of usr
	@Test
	public void test4() {
		try {
			emaitza =sut.LogIn("erabiltzaile", "");
			assertTrue(!emaitza);
		}catch(Exception e) {
			assertTrue(true);
		}
	}
	//Try logIn with usr and its psw in db
	@Test
	public void test5() {
		try {
			sut.register("Erabiltzaile", "Erabiltzaile");
			emaitza =sut.LogIn("erabiltzaile", "erabiltzaile");
			assertTrue(emaitza);
		}catch(Exception e) {
			assertTrue(true);
		}
	}




}
