package test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;

@RunWith(MockitoJUnitRunner.class)
public class FacadeMocTest {

	DataAccess dataAccess=Mockito.mock(DataAccess.class);

	@InjectMocks
	BLFacade sut=new BLFacadeImplementation(dataAccess);

	private boolean emaitza;

	//Try logIn but user null
	@Test
	public void test1() {
		try {
			Mockito.doReturn(false).when(dataAccess).LogIn(null,Mockito.anyString());
			emaitza =sut.logIN(null, "erabiltzaile");
			assertTrue(!emaitza);
		}catch(Exception e) {
			fail();
		}

	}
	//Try logIn but psw null
	@Test
	public void test2() {
		try {
			Mockito.doReturn(false).when(dataAccess).LogIn(Mockito.anyString(),null);
			emaitza = sut.logIN("erabiltzaile",null);
			
			assertTrue(!emaitza);
		}catch(Exception e) {
			fail();
		}

	}


	//Try logIn but user not in db && psw not in ddb
	@Test
	public void test3() {
		try {
			Mockito.doReturn(false).when(dataAccess).LogIn(Mockito.anyString(),Mockito.anyString());
			emaitza =sut.logIN("", "");
			assertTrue(!emaitza);
		}catch(Exception e) {
			fail();
		}
	}
	//Try logIn but psw not of usr
	@Test
	public void test4() {
		try {
			Mockito.doReturn(false).when(dataAccess).LogIn(Mockito.anyString(),Mockito.anyString());
			emaitza =sut.logIN("erabiltzaile", "");
			assertTrue(!emaitza);
		}catch(Exception e) {
			assertTrue(true);
		}
	}
	//Try logIn with usr and its psw in db
	@Test
	public void test5() {
		try {
			Mockito.doReturn(true).when(dataAccess).LogIn("erabiltzaile","erabiltzaile");
			emaitza =sut.logIN("erabiltzaile", "erabiltzaile");
			assertTrue(emaitza);
		}catch(Exception e) {
			assertTrue(true);
		}
	}
}
