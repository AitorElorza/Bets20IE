package businessLogic;

import java.util.Date;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Question;
import domain.Registered;
import domain.User;
import domain.Apustua;
import domain.Event;
import domain.Kuota;
import domain.Mugimendua;
import domain.MultipleBet;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {

	private String username;
	private User erabiltzailea;
	private Question question;
	private Kuota kuota;
	private Vector<Kuota> kuotak;
	private float minBet;
	private Integer details;
	private Registered usertorep;
	private DataAccess dbManager;



	public BLFacadeImplementation(DataAccess da) {
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c=ConfigXML.getInstance();
		if (c.getDataBaseOpenMode().equals("initialize")) {
			da.open(true);
			da.initializeDB();
			da.close();
		}
		dbManager=da;
	}

	@WebMethod
	public Integer getDetails() {
		return details;
	}

	@WebMethod
	public void setDetails(Integer details) {
		this.details = details;
	}

	@WebMethod
	public float getMinBet() {
		return minBet;
	}

	@WebMethod
	public void setMinBet(float minBet) {
		this.minBet = minBet;
	}

	@WebMethod
	public Vector<Kuota> getKuotak() {
		return kuotak;
	}

	@WebMethod
	public void setKuotak(Vector<Kuota> kuotak) {
		this.kuotak = kuotak;
	}

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c=ConfigXML.getInstance();

		if (c.getDataBaseOpenMode().equals("initialize")) {
			DataAccess dbManager=new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
			dbManager.initializeDB();
			dbManager.close();
		}

	}


	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	@WebMethod
	public Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist{

		//The minimum bed must be greater than 0
		DataAccess dBManager=new DataAccess();
		Question qry=null;


		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));


		qry=dBManager.createQuestion(event,question,betMinimum);		

		dBManager.close();

		return qry;
	};



	@WebMethod
	public Kuota createKuota(Event event,Question q, String kuotadesk, Integer money) throws EventFinished{

		//The minimum bed must be greater than 0
		DataAccess dBManager=new DataAccess();
		Kuota qry=null;



		/*if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));*/


		qry=dBManager.createKuota(q,kuotadesk,money);		

		dBManager.close();

		return qry;
	};

	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod	
	public Vector<Event> getEvents(Date date)  {
		DataAccess dbManager=new DataAccess();
		Vector<Event>  events=dbManager.getEvents(date);
		dbManager.close();
		return events;
	}


	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date) {
		DataAccess dbManager=new DataAccess();
		Vector<Date>  dates=dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}




	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod	
	public void initializeBD(){
		DataAccess dBManager=new DataAccess();
		dBManager.initializeDB();
		dBManager.close();
	}

	@WebMethod
	public boolean registerOld(String usr, String psw1, String psw2) {
		if(psw1.equals(psw2)) {
			dbManager.open(false);
			dbManager.register(usr, psw1);
			dbManager.close();
			return true;
		}else {
			return false;
		}

	}


	@WebMethod
	public boolean register(String usr,String pas1,String pas2, String email,String nam,String snam,Integer cc) {
		if(pas1.equals(pas2)) {
			dbManager.open(false);
			dbManager.register(usr, pas1, email, nam, snam , cc);
			dbManager.close();
			return true;
		}else {
			return false;
		}
	}

	@WebMethod
	public boolean logIN(String usr, String psw) {
		dbManager.open(false);
		boolean correct= dbManager.LogIn(usr, psw);

		dbManager.close();
		return correct;
	}

	@WebMethod
	public User getUser(String usr) {
		dbManager.open(false);
		User lag = dbManager.getUserByName(usr);

		dbManager.close();
		return lag;
	}

	@WebMethod
	public void createEvent(Date data, String event) {
		DataAccess db = new DataAccess();

		db.createEvent(data, event);

		db.close();
	}

	@WebMethod
	public String getUsername() {
		return this.username;
	}
	@WebMethod
	public User getErabitltzailea() {
		return this.erabiltzailea;
	}

	@WebMethod
	public void setUser(String usr) {
		this.username=usr;
		this.erabiltzailea = this.getUser(usr);

	}

	@WebMethod
	public void setQuestion(int id) {
		DataAccess db = new DataAccess();
		this.question=db.bilatuQ(id);
		db.close();
	}

	@WebMethod
	public Question getQuestion() {

		return this.question;
	}

	//    @WebMethod
	//	public void createKuota(String desk, Integer r, Question q) {
	//		DataAccess db = new DataAccess();
	//		
	//		db.createKuota(desk,r,q);
	//		db.close();
	//	}

	@WebMethod
	public void addAmount(String user, float amount, Date data) {
		DataAccess db = new DataAccess();

		db.addAmount(user, amount, data);
		db.close();

	}

	@WebMethod
	public void setKuota(Integer kuotaForBet) {
		DataAccess db = new DataAccess();
		this.kuota=db.bilatuK(kuotaForBet);
		db.close();

	}

	@WebMethod
	public Kuota getKuota() {
		return this.kuota;
	}

	@WebMethod
	public void bet(float betValue, Date data) {
		DataAccess db = new DataAccess();
		db.bet(this.username, this.kuota, betValue, data);
		db.close();

	}

	@WebMethod
	public Vector<Mugimendua> getMugi() {
		DataAccess db = new DataAccess();
		Vector<Mugimendua> v=db.mugimenduak(this.erabiltzailea);
		db.close();
		return v;
	}

	@WebMethod
	public Apustua bilatuB(Integer i) {
		DataAccess db=new DataAccess();
		return db.bilatuB(i);
	}

	@WebMethod
	public MultipleBet bilatuM(Integer i) {
		DataAccess db=new DataAccess();
		return db.bilatuM(i);
	}

	@WebMethod
	public void deleteBet(Integer betToDel) {
		DataAccess db=new DataAccess();
		db.deleteBet(this.username,betToDel);
		db.close();

	}

	@WebMethod
	public void setResult(Integer kuotaForBet) {
		DataAccess db=new DataAccess();
		db.setResult(kuotaForBet);
		db.close();

	}

	@WebMethod
	public void cancelEvent(Event event) {
		DataAccess db=new DataAccess();
		db.cancelEvent(event);
		db.close();

	}

	@WebMethod
	public String getCredit() {
		DataAccess db=new DataAccess();
		String a= db.getCredit(this.username);
		db.close();
		return a;
	}


	@WebMethod
	public void mulBet(float betValue, Date data) {
		DataAccess db = new DataAccess();
		db.mulBet(this.username, this.kuotak, betValue, data);
		db.close();

	}


	@WebMethod
	public void deleteMulBet(Integer betToDel) {
		DataAccess db=new DataAccess();
		db.deleteMulBet(this.username,betToDel);
		db.close();
	}


	@WebMethod
	public void updateUsers(Integer kuotaForBet) {
		DataAccess db=new DataAccess();
		db.updateUsers(kuotaForBet);
		db.close();

	}


	@WebMethod
	public void updateMulUsers(Integer kuotaForBet) {
		DataAccess db=new DataAccess();
		db.updateMulUsers(kuotaForBet);
		db.close();

	}


	@WebMethod
	public Vector<Registered> bilatuTop() {
		DataAccess db=new DataAccess();
		return db.bilatuTop();
	}

	@WebMethod
	public Registered getUsertorep() {
		return usertorep;
	}

	@WebMethod
	public void setUsertorep(Registered usertorep) {
		this.usertorep = usertorep;
	}


	@WebMethod
	public void erreplikatu(Vector<Apustua> apustuak, Vector<MultipleBet> mbets) {
		DataAccess db=new DataAccess();
		for(Apustua a:apustuak) db.bet(username, a.getKuota(), a.getAmount(), new Date());
		for(MultipleBet m:mbets) db.mulBet(username, m.getMugimenduak(), m.getAmount(), new Date()); 
		db.close();

	}

}

