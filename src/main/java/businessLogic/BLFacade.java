package businessLogic;

import java.util.Vector;
import java.util.Date;
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

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Interface that specifies the business logic.
 */



@WebService
public interface BLFacade  {
	

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
	@WebMethod Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist;
	
	@WebMethod public Kuota createKuota(Event event,Question q, String kuotadesk, Integer money) throws EventFinished;
	
	
	/**
	 * This method retrieves the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod public Vector<Event> getEvents(Date date);
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date);
	
	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public void initializeBD();
	
	
	@WebMethod public boolean registerOld(String usr, String psw1,String psw2);
	
	@WebMethod public boolean register(String usr,String pas1,String pas2, String email,String nam,String snam, Integer cc);
	
	@WebMethod public boolean logIN(String usr, String psw);
	
	@WebMethod public User getUser(String usr);
	
	@WebMethod public User getErabitltzailea();
	
	@WebMethod public Vector<Kuota> getKuotak();
	
	@WebMethod public void setKuotak(Vector<Kuota> kuotak);
	
	@WebMethod public float getMinBet();
	
	@WebMethod public void setMinBet(float minBet);
	
	@WebMethod public String getUsername();
	
	@WebMethod public void setUser(String usr);

	@WebMethod public void setQuestion(int id);
	
	@WebMethod public Question getQuestion();
	
	@WebMethod public void createEvent(Date data, String event);
	
	//@WebMethod public void createKuota(String desk, Integer r, Question q);

	@WebMethod void addAmount(String user, float amount, Date data);

	@WebMethod void setKuota(Integer kuotaForBet);
	
	@WebMethod public Kuota getKuota();

	@WebMethod public Vector<Mugimendua> getMugi();
	
	@WebMethod void bet(float betValue, Date data);

	@WebMethod public Apustua bilatuB(Integer i);

	@WebMethod public void deleteBet(Integer betToDel);

	@WebMethod public void setResult(Integer kuotaForBet);

	@WebMethod public void cancelEvent(Event event);
	
	@WebMethod public String getCredit();

	@WebMethod void mulBet(float betValue, Date data);

	@WebMethod MultipleBet bilatuM(Integer i);

	@WebMethod void deleteMulBet(Integer betToDel);
	
	@WebMethod public void setDetails(Integer details);
	
	@WebMethod public Integer getDetails();

	@WebMethod public void updateUsers(Integer kuotaForBet);

	@WebMethod public void updateMulUsers(Integer kuotaForBet);

	@WebMethod public Vector<Registered> bilatuTop();

	@WebMethod public void setUsertorep(Registered usertorep);
	
	@WebMethod public Registered getUsertorep();

	@WebMethod public void erreplikatu(Vector<Apustua> apustuak, Vector<MultipleBet> mbets);

	
}
