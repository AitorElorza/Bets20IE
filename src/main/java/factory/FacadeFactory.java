package factory;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;

public class FacadeFactory {
	public BLFacade createBLF(DataAccess da) {
		return new BLFacadeImplementation(da);
	}
	public BLFacade createBLF() {
		return new BLFacadeImplementation();
	}

}
