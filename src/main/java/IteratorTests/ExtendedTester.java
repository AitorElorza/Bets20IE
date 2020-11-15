package IteratorTests;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.UIManager;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import businessLogic.BLFacade;
import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Event;
import factory.FacadeFactory;
import gui.MainGUI;
import iterator.ExtendedIterator;

public class ExtendedTester {
	public static void main(String[] args) {
		//obtener el objeto Facade local
		int isLocal=1;
		ConfigXML c=ConfigXML.getInstance();
		FacadeFactory factory;
		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		
		try {

			BLFacade appFacadeInterface;
			
			factory = new FacadeFactory();
			
			if (c.isBusinessLogicLocal()) {

				DataAccess da= new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
				
				

				//appFacadeInterface=new BLFacadeImplementation(da);
				appFacadeInterface=factory.createBLF(da);
				
				date = sdf.parse("16/11/2020");
				
				ExtendedIterator it = (ExtendedIterator) appFacadeInterface.getEvents2(date);
				
				Event e;
				
				
				
				it.goLast();
				System.out.println("-----------------Atzetik aurrera-----------------");
				
				while(it.hasPrevious()) {
					e=(Event) it.previous();
					System.out.println(e);
				}
				
				it.goFirst();
				System.out.println("----------------Aurretik atzera------------------");
				while(it.hasNext()) {
					e=(Event)it.next();
					System.out.println(e);
				}
				
				
				
			}

			

			

		}catch (Exception e) {
			//a.lblGelchrome.setText("Error: "+e.toString());
			//a.lblGelchrome.setForeground(Color.RED);		
			System.out.println("Error in the test");
		}
	}

}
