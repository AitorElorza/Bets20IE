package gui;

import java.awt.Color;
import java.net.URL;
import java.util.Locale;

import javax.swing.UIManager;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import factory.FacadeFactory;
import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;

public class ApplicationLauncher {



	public static void main(String[] args) {

		ConfigXML c=ConfigXML.getInstance();

		System.out.println(c.getLocale());

		Locale.setDefault(new Locale(c.getLocale()));

		System.out.println("Locale: "+Locale.getDefault());



		LoginGUI a=new LoginGUI();
		a.setVisible(true);
		
		FacadeFactory factory;



		try {

			BLFacade appFacadeInterface;
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			
			factory = new FacadeFactory();
			
			if (c.isBusinessLogicLocal()) {

				DataAccess da= new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
				
				

				//appFacadeInterface=new BLFacadeImplementation(da);
				appFacadeInterface=factory.createBLF(da);

				//appFacadeInterface.register("admin", "admin", "admin");
				//to insert a new admin must be done here
			}

			else { //If remote

				String serviceName= "http://"+c.getBusinessLogicNode() +":"+ c.getBusinessLogicPort()+"/ws/"+c.getBusinessLogicName()+"?wsdl";

				//URL url = new URL("http://localhost:1098/ws/Bets?wsdl");
				URL url = new URL(serviceName);


				//1st argument refers to wsdl document above
				//2nd argument is service name, refer to wsdl document above
				//		        QName qname = new QName("http://businessLogic/", "FacadeImplementationWSService");
				QName qname = new QName("http://businessLogic/", "BLFacadeImplementationService");

				Service service = Service.create(url, qname);

				appFacadeInterface = service.getPort(BLFacade.class);
			} 
			/*if (c.getDataBaseOpenMode().equals("initialize")) 
				appFacadeInterface.initializeBD();
			 */
			MainGUI.setBussinessLogic(appFacadeInterface);




		}catch (Exception e) {
			//a.lblGelchrome.setText("Error: "+e.toString());
			//a.lblGelchrome.setForeground(Color.RED);		
			System.out.println("Error in ApplicationLauncher: "+e.toString());
		}
		//a.pack();


	}

}
