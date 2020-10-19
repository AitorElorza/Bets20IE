package dataAccess;


import java.util.Calendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.crypto.Data;

//import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Admin;
import domain.Apustua;
import domain.Event;
import domain.Kuota;
import domain.MoneyTrans;
import domain.Mugimendua;
import domain.MultipleBet;
import domain.Question;
import domain.Registered;
import domain.User;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess  {
	protected static EntityManager  db;
	protected static EntityManagerFactory emf;


	ConfigXML c;

	public DataAccess(boolean initializeMode)  {

		c=ConfigXML.getInstance();

		System.out.println("Creating DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		String fileName=c.getDbFilename();
		if (initializeMode)
			fileName=fileName+";drop";

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

	public DataAccess()  {	
		new DataAccess(false);
	}



	public void open(boolean initializeMode){

		System.out.println("Opening DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		String fileName=c.getDbFilename();
		if (initializeMode) {
			fileName=fileName+";drop";
			System.out.println("Deleting the DataBase");
		}

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


	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){

		db.getTransaction().begin();
		try {


			Calendar today = Calendar.getInstance();

			int month=today.get(Calendar.MONTH);
			month+=1;
			int year=today.get(Calendar.YEAR);
			if (month==12) { month=0; year+=1;}  

			Event ev1=new Event(1, "Atlético-Athletic", UtilDate.newDate(year,month,17));
			Event ev2=new Event(2, "Eibar-Barcelona", UtilDate.newDate(year,month,17));
			Event ev3=new Event(3, "Getafe-Celta", UtilDate.newDate(year,month,17));
			Event ev4=new Event(4, "Alavés-Deportivo", UtilDate.newDate(year,month,17));
			Event ev5=new Event(5, "Español-Villareal", UtilDate.newDate(year,month,17));
			Event ev6=new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year,month,17));
			Event ev7=new Event(7, "Malaga-Valencia", UtilDate.newDate(year,month,17));
			Event ev8=new Event(8, "Girona-Leganés", UtilDate.newDate(year,month,17));
			Event ev9=new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year,month,17));
			Event ev10=new Event(10, "Betis-Real Madrid", UtilDate.newDate(year,month,17));

			Event ev11=new Event(11, "Atletico-Athletic", UtilDate.newDate(year,month,1));
			Event ev12=new Event(12, "Eibar-Barcelona", UtilDate.newDate(year,month,1));
			Event ev13=new Event(13, "Getafe-Celta", UtilDate.newDate(year,month,1));
			Event ev14=new Event(14, "Alavés-Deportivo", UtilDate.newDate(year,month,1));
			Event ev15=new Event(15, "Español-Villareal", UtilDate.newDate(year,month,1));
			Event ev16=new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year,month,1));


			Event ev17=new Event(17, "Málaga-Valencia", UtilDate.newDate(year,month,28));
			Event ev18=new Event(18, "Girona-Leganés", UtilDate.newDate(year,month,28));
			Event ev19=new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year,month,28));
			Event ev20=new Event(20, "Betis-Real Madrid", UtilDate.newDate(year,month,28));

			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;

			if (Locale.getDefault().equals(new Locale("es"))) {
				q1=ev1.addQuestion("¿Quién ganará el partido?",1);
				q2=ev1.addQuestion("¿Quién meterá el primer gol?",2);
				q3=ev11.addQuestion("¿Quién ganará el partido?",1);
				q4=ev11.addQuestion("¿Cuántos goles se marcarán?",2);
				q5=ev17.addQuestion("¿Quién ganará el partido?",1);
				q6=ev17.addQuestion("¿Habrá goles en la primera parte?",2);
			}
			else if (Locale.getDefault().equals(new Locale("en"))) {
				q1=ev1.addQuestion("Who will win the match?",1);
				q2=ev1.addQuestion("Who will score first?",2);
				q3=ev11.addQuestion("Who will win the match?",1);
				q4=ev11.addQuestion("How many goals will be scored in the match?",2);
				q5=ev17.addQuestion("Who will win the match?",1);
				q6=ev17.addQuestion("Will there be goals in the first half?",2);
			}			
			else {
				q1=ev1.addQuestion("Zeinek irabaziko du partidua?",1);
				q2=ev1.addQuestion("Zeinek sartuko du lehenengo gola?",2);
				q3=ev11.addQuestion("Zeinek irabaziko du partidua?",1);
				q4=ev11.addQuestion("Zenbat gol sartuko dira?",2);
				q5=ev17.addQuestion("Zeinek irabaziko du partidua?",1);
				q6=ev17.addQuestion("Golak sartuko dira lehenengo zatian?",2);

			}


			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6);


			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);			

			db.getTransaction().commit();
			System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws  QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);

		Event ev = db.find(Event.class, event.getEventNumber());

		if (ev.DoesQuestionExists(question)) throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));

		db.getTransaction().begin();
		Question q = ev.addQuestion(question, betMinimum);
		q.setEvent(ev);
		db.persist(q);
		db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
		// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		db.getTransaction().commit();
		return q;

	}

	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();	
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1",Event.class);   
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
		for (Event ev:events){
			System.out.println(ev.toString());		 
			res.add(ev);
		}
		return res;
	}

	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public Vector<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();	

		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);


		TypedQuery<Date> query = db.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2",Date.class);   
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
		for (Date d:dates){
			System.out.println(d.toString());		 
			res.add(d);
		}
		return res;
	}

	public boolean LogIn(String usn, String psw) {
		TypedQuery<User> query = db.createQuery("SELECT u FROM User u WHERE u.username=?1 AND u.password=?2",User.class);		
		query.setParameter(1, usn);
		query.setParameter(2, psw);

		if(query.getResultList().size()==1) {
			return true;
		}else {
			return false;
		}
	}

	/*public boolean LogIn(String usn, String psw) {
		User lag = getUserByName(usn);

		if(lag != null) {
			if(psw.equals(lag.getPassword())) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}*/


	public void register(String usn, String psw) {
		User lag = new Admin(usn,psw);
		db.getTransaction().begin();
		db.persist(lag);
		db.getTransaction().commit();


	}
	public void register(String usn, String psw, String email, String nam, String snam, Integer cc) {
		Registered lag = new Registered(usn,psw,cc,email,nam,snam);
		db.getTransaction().begin();
		db.persist(lag);
		db.getTransaction().commit();


	}

	public User getUserByName(String usr){
		return db.find(User.class, usr);
	}


	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}

	public Integer getMaxEvent() {

		Integer a = (Integer) db.createQuery("SELECT MAX(e.eventNumber) FROM Event e").getSingleResult();
		if(a==null) return 0;
		else return a;
	}

	public Integer getMaxKuota() {

		Integer a = (Integer) db.createQuery("SELECT MAX(k.kuotaNum) FROM Kuota k").getSingleResult();
		if(a==null) return 0;
		else return a;
	}

	public Integer getMaxMugi() {

		Integer a = (Integer) db.createQuery("SELECT MAX(m.movId) FROM Mugimendua m").getSingleResult();
		if(a==null) return 0;
		else return a;
	}

	public void createEvent(Date data, String desk) {
		//System.out.println(getMaxEvent());
		Event e = new Event(getMaxEvent()+1,desk, data);
		db.getTransaction().begin();
		db.persist(e);
		db.getTransaction().commit();
	}

	//	public void createKuota(String desk, Integer r, Question q) {
	//		Kuota k = new Kuota(getMaxKuota()+1,desk,r,q);
	//		q.addKuota(k);
	//		
	//		db.getTransaction().begin();
	//		db.persist(k);
	//		//db.persist(q);
	//		db.getTransaction().commit();
	//	}

	public Question bilatuQ (int id) {

		return db.find(Question.class, id);
	}

	public Kuota createKuota(Question q, String kuotadesk, Integer money) {

		Question ques = db.find(Question.class,q.getQuestionNumber());

		db.getTransaction().begin();
		Kuota k = ques.addKuota(getMaxKuota()+1, kuotadesk, money);
		db.persist(ques);

		db.getTransaction().commit();

		return k;
	}

	public void addAmount(String user, float amount, Date data) {
		Registered u = db.find(Registered.class, user);

		MoneyTrans m = new MoneyTrans(getMaxMugi()+1, data, amount, u, false);

		db.getTransaction().begin();
		float lag = u.getCredit();
		lag+=amount;
		u.setCredit(lag);

		u.addMugimendua(m);
		db.persist(m);
		db.persist(u);
		db.getTransaction().commit();

	}

	public Kuota bilatuK(Integer kuotaForBet) {
		return db.find(Kuota.class, kuotaForBet);
	}

	public void bet(String user, Kuota kuota, float betValue, Date data) {
		Registered u = db.find(Registered.class, user);

		Apustua a = new Apustua(getMaxMugi()+1, data, betValue, u, kuota);

		db.getTransaction().begin();
		float lag = u.getCredit();
		lag-=betValue;
		u.setCredit(lag);
		u.addMugimendua(a);
		db.persist(a);
		db.persist(u);
		db.getTransaction().commit();

	}

	public Vector<Mugimendua> mugimenduak(User user) {
		
		return db.find(Registered.class, user).getMugimenduak();

	}

	public Apustua bilatuB(Integer i) {
		return db.find(Apustua.class, i);
	}

	public void deleteBet(String username, Integer betToDel) {
		Registered u=db.find(Registered.class, username);
		Apustua a=db.find(Apustua.class, betToDel);
		db.getTransaction().begin();
		u.getMugimenduak().remove(a);
		float lag=a.getAmount();
		lag+=u.getCredit();
		u.setCredit(lag);
		db.persist(u);
		db.getTransaction().commit();

	}

	public void setResult(Integer kuotaForBet) {
		Kuota k = db.find(Kuota.class, kuotaForBet);
		Question q = k.getQuestion();
		db.getTransaction().begin();

		for(Kuota lag:q.getKuotak()) {
			if(lag.getKuotaNum()==kuotaForBet) {
				lag.setResult(true);
			}
			else lag.setResult(false);
			db.persist(lag);
		}
		db.persist(q);
		db.getTransaction().commit();

	}

	public void cancelEvent(Event event) {
		Integer eNum=event.getEventNumber();
		Event e = db.find(Event.class, eNum);
		db.getTransaction().begin();
		e.setCancelled(true);
		db.persist(e);
		db.getTransaction().commit();
		TypedQuery<Registered> query= db.createQuery("SELECT reg FROM Registered reg", Registered.class);
		List<Registered> erregistratuak = query.getResultList();
		for (Registered r:erregistratuak){
			TypedQuery<Mugimendua> queryMov= db.createQuery("SELECT m FROM Mugimendua m WHERE m.user.username=?1", Mugimendua.class);
			queryMov.setParameter(1, r.getUsername());
			List<Mugimendua> mugimenduak = queryMov.getResultList();
			for(Mugimendua m:mugimenduak) {
				if(m instanceof Apustua && ((Apustua) m).getKuota().getQuestion().getEvent().getDescription().equals(e.getDescription())) {
					if(((Apustua) m).getKuota().getResult()==null) addAmount(r.getUsername(), m.getAmount(), new Date());
				}
				if(m instanceof MultipleBet) {
					List<Kuota> kuotak=((MultipleBet) m).getMugimenduak();
					for(Kuota k:kuotak) if(k.getQuestion().getEvent().getDescription().equals(e.getDescription()) && k.getResult()==null) addAmount(r.getUsername(), m.getAmount(), new Date());
				}
			}

		}

	}

	public String getCredit(String username) {
		Registered u = db.find(Registered.class, username);
		return String.valueOf(u.getCredit());
	}

	public void mulBet(String username, Vector<Kuota> kuotak, float betValue, Date data) {
		Registered u = db.find(Registered.class, username);

		MultipleBet m = new MultipleBet(getMaxMugi()+1, data, betValue, u, kuotak);

		db.getTransaction().begin();
		float lag = u.getCredit();
		lag-=betValue;
		u.setCredit(lag);
		u.addMugimendua(m);
		db.persist(m);
		db.persist(u);
		db.getTransaction().commit();

	}

	public MultipleBet bilatuM(Integer i) {
		return db.find(MultipleBet.class, i);
	}

	public void deleteMulBet(String username, Integer betToDel) {
		Registered u=db.find(Registered.class, username);
		MultipleBet a=db.find(MultipleBet.class, betToDel);
		db.getTransaction().begin();
		u.getMugimenduak().remove(a);
		float lag=a.getAmount();
		lag+=u.getCredit();
		u.setCredit(lag);
		db.persist(u);
		db.getTransaction().commit();		
	}

	public void updateUsers(Integer kuotaForBet) {
		TypedQuery<Registered> query= db.createQuery("SELECT reg FROM Registered reg", Registered.class);
		List<Registered> erregistratuak = query.getResultList();

		for(Registered r:erregistratuak) {
			TypedQuery<Mugimendua> queryMov= db.createQuery("SELECT m FROM Mugimendua m WHERE m.user.username=?1", Mugimendua.class);
			queryMov.setParameter(1, r.getUsername());
			List<Mugimendua> mugimenduak = queryMov.getResultList();
			for(Mugimendua m:mugimenduak) {
				if(m instanceof Apustua) {
					if(((Apustua) m).getKuota().getResult()!=null && ((Apustua) m).getKuota().getResult()==true) {

						db.getTransaction().begin();
						((Apustua) m).setWin(true);
						((Apustua) m).setTbd(false);
						db.persist(m);
						db.persist(r);
						db.getTransaction().commit();

						float lag=m.getAmount()*((Apustua) m).getKuota().getR();
						addAmount(r.getUsername(), lag, new Date());
						win(r.getUsername(), lag);
					}
					else if(((Apustua) m).getKuota().getResult()!=null && ((Apustua) m).getKuota().getResult()==false) {
						db.getTransaction().begin();
						((Apustua) m).setLose(true);
						((Apustua) m).setTbd(false);
						db.persist(m);
						db.persist(r);
						db.getTransaction().commit();
					}
				}

				//				if(m instanceof Apustua && ((Apustua) m).getKuota().getKuotaNum()==kuotaForBet) {
				//					float lag=m.getAmount()*((Apustua) m).getKuota().getR();
				//					addAmount(r.getUsername(), lag, new Date());
				//					win(r.getUsername(), lag);
				//				}
			}
		}

	}

	private void win(String username, float win) {
		Registered u = db.find(Registered.class, username);
		db.getTransaction().begin();
		float w=u.getWinnings();
		u.setWinnings(w+win);
		db.persist(u);
		db.getTransaction().commit();
	}

	public void updateMulUsers(Integer kuotaForBet) {
		TypedQuery<Registered> query= db.createQuery("SELECT reg FROM Registered reg", Registered.class);
		List<Registered> erregistratuak = query.getResultList();
		for(Registered r:erregistratuak) {
			TypedQuery<Mugimendua> queryMov= db.createQuery("SELECT m FROM Mugimendua m WHERE m.user.username=?1", Mugimendua.class);
			queryMov.setParameter(1, r.getUsername());
			List<Mugimendua> mugimenduak = queryMov.getResultList();
			for(Mugimendua m:mugimenduak) {
				if(m instanceof MultipleBet) {
					List<Kuota> kuotak=((MultipleBet) m).getMugimenduak();
					int i=0;
					int c=1;
					for(Kuota k:kuotak) {
						c=c*k.getR();
						if(k.getResult()!=null && k.getResult()==false) {
							db.getTransaction().begin();
							((MultipleBet) m).setLose(true);
							((MultipleBet) m).setTbd(false);
							db.persist(m);
							db.persist(r);
							db.getTransaction().commit();
						}
						else if(k.getResult()!=null && k.getResult()==true) i++;
					}
					if(i==kuotak.size()) {
						db.getTransaction().begin();
						((MultipleBet) m).setWin(true);
						((MultipleBet) m).setTbd(false);
						((MultipleBet) m).setPaid(true);
						db.persist(m);
						db.persist(r);
						db.getTransaction().commit();
						if(!((MultipleBet) m).isPaid()) {
							float lag=m.getAmount()*c;
							addAmount(r.getUsername(), lag, new Date());
							win(r.getUsername(), lag);
						}	
					}



				}
			}
		}
	}

	public Vector<Registered> bilatuTop() {
		TypedQuery<Registered> query= db.createQuery("SELECT reg FROM Registered reg ORDER BY reg.winnings DESC", Registered.class);
		List<Registered> erregistratuak = query.getResultList();
		Vector<Registered> lag=new Vector<Registered>();
		if(erregistratuak.size()<=10) for(Registered r:erregistratuak) lag.addElement(r);
		else for(int i=0; i<10; i++) lag.addElement(erregistratuak.get(i));

		return lag;
	}



}
