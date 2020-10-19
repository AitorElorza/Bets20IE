package gui;

import java.text.DateFormat;
import java.util.*;

import javax.swing.*;

import com.toedter.calendar.JCalendar;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Event;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

public class CreateEventGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	DefaultComboBoxModel<Event> modelEvents = new DefaultComboBoxModel<Event>();
	private JLabel jLabelQuery = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Event"));
	private JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));

	private JTextField jTextFieldQuery = new JTextField();
	private JCalendar jCalendar = new JCalendar();
	private Calendar calendarMio = null;
	private Date eData;

	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JLabel jLabelMsg = new JLabel();
	private JLabel jLabelError = new JLabel();
	private final JButton btnCreateEvent = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateEv")); //$NON-NLS-1$ //$NON-NLS-2$

	public CreateEventGUI(Vector<domain.Event> v) {
		try {
			jbInit(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit(Vector<domain.Event> v) throws Exception {

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(263, 395));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateEv"));
		jLabelQuery.setBounds(new Rectangle(10, 242, 75, 20));
		jTextFieldQuery.setBounds(new Rectangle(10, 275, 225, 30));

		jCalendar.setBounds(new Rectangle(10, 35, 225, 196));
		scrollPaneEvents.setBounds(new Rectangle(25, 44, 346, 116));
		jButtonClose.setBounds(new Rectangle(160, 314, 75, 30));
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
			}
		});

		jLabelMsg.setBounds(new Rectangle(283, 158, 305, 20));
		jLabelMsg.setForeground(Color.red);
		// jLabelMsg.setSize(new Dimension(305, 20));

		jLabelError.setBounds(new Rectangle(283, 226, 305, 20));
		jLabelError.setForeground(Color.red);

		this.getContentPane().add(jLabelMsg, null);
		this.getContentPane().add(jLabelError, null);

		this.getContentPane().add(jButtonClose, null);
		btnCreateEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BLFacade BLF = MainGUI.getBusinessLogic();
				
				System.out.println("");
				System.out.println("");
				System.out.println("");
				
				System.out.println(eData.toString());
				
				System.out.println("");
				System.out.println("");
				System.out.println("");
					BLF.createEvent(eData,jTextFieldQuery.getText()); 
				
			}
		});
		btnCreateEvent.setBounds(10, 315, 140, 28);
		
		getContentPane().add(btnCreateEvent);
		this.getContentPane().add(jTextFieldQuery, null);
		this.getContentPane().add(jLabelQuery, null);

		this.getContentPane().add(jCalendar, null);

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelEventDate.setBounds(10, 11, 140, 25);
		getContentPane().add(jLabelEventDate);

		// Code for JCalendar
				this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
					public void propertyChange(PropertyChangeEvent propertychangeevent) {
//						this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
//							public void propertyChange(PropertyChangeEvent propertychangeevent) {
						if (propertychangeevent.getPropertyName().equals("locale")) {
							jCalendar.setLocale((Locale) propertychangeevent.getNewValue());
						} else if (propertychangeevent.getPropertyName().equals("calendar")) {
							calendarMio = (Calendar) propertychangeevent.getNewValue();
							DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar.getLocale());
							jCalendar.setCalendar(calendarMio);
							Date firstDay = UtilDate.trim(new Date(jCalendar.getCalendar().getTime().getTime()));

							eData=calendarMio.getTime();

						}
						
						paintDaysWithEvents(jCalendar);
					}
				});
			}

	
	public static void paintDaysWithEvents(JCalendar jCalendar) {
		// For each day in current month, it is checked if there are events, and in that
		// case, the background color for that day is changed.

		BLFacade facade = MainGUI.getBusinessLogic();

		Vector<Date> dates=facade.getEventsMonth(jCalendar.getDate());
			
		Calendar calendar = jCalendar.getCalendar();
		
		int month = calendar.get(Calendar.MONTH);
		//int today=calendar.get(Calendar.DAY_OF_MONTH);

		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int offset = calendar.get(Calendar.DAY_OF_WEEK);

		if (Locale.getDefault().equals(new Locale("es")))
			offset += 4;
		else
			offset += 5;
		
		
	 	for (Date d:dates){

	 		calendar.setTime(d);
	 		//System.out.println(d);
	 		

			
			// Obtain the component of the day in the panel of the DayChooser of the
			// JCalendar.
			// The component is located after the decorator buttons of "Sun", "Mon",... or
			// "Lun", "Mar"...,
			// the empty days before day 1 of month, and all the days previous to each day.
			// That number of components is calculated with "offset" and is different in
			// English and Spanish
//			    		  Component o=(Component) jCalendar.getDayChooser().getDayPanel().getComponent(i+offset);; 
			Component o = (Component) jCalendar.getDayChooser().getDayPanel()
					.getComponent(calendar.get(Calendar.DAY_OF_MONTH) + offset);
			o.setBackground(Color.CYAN);
	 	}
	 	
	 		calendar.set(Calendar.DAY_OF_MONTH, 1);
	 		calendar.set(Calendar.MONTH, month);
	 	
	}

	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
