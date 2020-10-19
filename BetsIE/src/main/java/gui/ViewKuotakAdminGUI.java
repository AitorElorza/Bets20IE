package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;

import com.toedter.calendar.JCalendar;

import domain.Admin;
import domain.Kuota;
import domain.Question;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;

import javax.swing.table.DefaultTableModel;


public class ViewKuotakAdminGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries")); 
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events")); 
	private final JLabel lblKuotak = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Fees"));

	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));

	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarMio = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JScrollPane scrollPaneQueries = new JScrollPane();
	private JScrollPane scrollPaneKuotak = new JScrollPane();

	private JTable tableEvents= new JTable();
	private JTable tableQueries = new JTable();
	private JTable tableKuotak = new JTable();

	private DefaultTableModel tableModelEvents;
	private DefaultTableModel tableModelQueries;
	private DefaultTableModel tableModelKuotak;

	private Integer kuotaForBet;
	
	private String[] columnNamesEvents = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("EventN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Event"), 

	};
	private String[] columnNamesQueries = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("QueryN"),
			ResourceBundle.getBundle("Etiquetas").getString("Query")

	};
	private String[] columnNamesKuotak = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("FeeN"),
			ResourceBundle.getBundle("Etiquetas").getString("Fee"),
			ResourceBundle.getBundle("Etiquetas").getString("WinEur")
	};
	private final JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("SetK"));
	private final JButton result = new JButton(ResourceBundle.getBundle("Etiquetas").getString("RightK"));
	private final JLabel lblNewLabel = new JLabel(" ");
	
	public ViewKuotakAdminGUI()
	{
		try
		{
			jbInit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	private void jbInit() throws Exception
	{

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(924, 461));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Fees"));

		jLabelEventDate.setBounds(new Rectangle(10, 11, 140, 25));
		jLabelQueries.setBounds(570, 16, 406, 14);
		jLabelEvents.setBounds(254, 15, 259, 16);

		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelQueries);
		this.getContentPane().add(jLabelEvents);

		jButtonClose.setBounds(new Rectangle(644, 380, 130, 30));

		jButtonClose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				jButton2_actionPerformed(e);
			}
		});

		this.getContentPane().add(jButtonClose, null);


		jCalendar1.setBounds(new Rectangle(10, 36, 225, 150));

		lblKuotak.setBounds(254, 197, 520, 14);
		getContentPane().add(lblKuotak);
		
		// Code for JCalendar
		this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener()
		{
			public void propertyChange(PropertyChangeEvent propertychangeevent)
			{

				if (propertychangeevent.getPropertyName().equals("locale"))
				{
					jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
				}
				else if (propertychangeevent.getPropertyName().equals("calendar"))
				{
					calendarMio = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
					jCalendar1.setCalendar(calendarMio);
					Date firstDay=UtilDate.trim(new Date(jCalendar1.getCalendar().getTime().getTime()));



					try {
						tableModelEvents.setDataVector(null, columnNamesEvents);
						tableModelEvents.setColumnCount(3); // another column added to allocate ev objects

						BLFacade facade=MainGUI.getBusinessLogic();

						Vector<domain.Event> events=facade.getEvents(firstDay);

						if (events.isEmpty() ) jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")+ ": "+dateformat1.format(calendarMio.getTime()));
						else jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events")+ ": "+dateformat1.format(calendarMio.getTime()));
						
						for (domain.Event ev:events){
							Vector<Object> row = new Vector<Object>();

							System.out.println("Events "+ev);

							row.add(ev.getEventNumber());
							row.add(ev.getDescription());
							row.add(ev); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,2)
							tableModelEvents.addRow(row);		
						}
						tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
						tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
						tableEvents.getColumnModel().removeColumn(tableEvents.getColumnModel().getColumn(2)); // not shown in JTable
					} catch (Exception e1) {

						jLabelQueries.setText(e1.getMessage());
					}

				}
				CreateQuestionGUI.paintDaysWithEvents(jCalendar1);
			} 
		});

		this.getContentPane().add(jCalendar1, null);
		
		scrollPaneEvents.setBounds(new Rectangle(254, 36, 306, 150));
		scrollPaneQueries.setBounds(new Rectangle(570, 36, 327, 150));

		tableEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=tableEvents.getSelectedRow();
				domain.Event ev=(domain.Event)tableModelEvents.getValueAt(i,2); // obtain ev object
				Vector<Question> queries=ev.getQuestions();

				tableModelQueries.setDataVector(null, columnNamesQueries);
				tableModelQueries.setColumnCount(3);
				if (queries.isEmpty()) jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("NoQueries")+": "+ev.getDescription());
				else {
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectedEvent")+" "+ev.getDescription());
				} 

				for (domain.Question q:queries){
					Vector<Object> row = new Vector<Object>();

					row.add(q.getQuestionNumber());
					row.add(q.getQuestion());
					row.add(q);
					tableModelQueries.addRow(row);	
				}
				tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
				tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
				tableQueries.getColumnModel().removeColumn(tableQueries.getColumnModel().getColumn(2));
				
				
				/*int j=tableQueries.getSelectedRow();
				domain.Question q=(domain.Question)tableModelQueries.getValueAt(j, 2);
				Vector<Kuota> kuotak=q.getKuotak();
				
				tableModelKuotak.setDataVector(null, columnNamesKuotak);
				if(kuotak.isEmpty()) lblKuotak.setText("Kuotarik ez: "+q.getQuestion());
				else lblKuotak.setText("Aukeraturiko galdera: "+q.getQuestion());
				
				for(domain.Kuota k:kuotak) {
					Vector<Object> row=new Vector<Object>();
					
					row.add(k.getKuotaNum());
					row.add(k.getDesk());
					row.add(k.getR());
					tableModelKuotak.addRow(row);
				}*/
			}
		});
		
		
		tableQueries.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int j=tableQueries.getSelectedRow();
				domain.Question q=(domain.Question)tableModelQueries.getValueAt(j, 2);
				Vector<Kuota> kuotak=q.getKuotak();
				
				tableModelKuotak.setDataVector(null, columnNamesKuotak);
				tableModelKuotak.setColumnCount(4);
				if(kuotak.isEmpty()) lblKuotak.setText("Kuotarik ez: "+q.getQuestion());
				else lblKuotak.setText("Aukeraturiko galdera: "+q.getQuestion());
				
				for(domain.Kuota k:kuotak) {
					Vector<Object> row=new Vector<Object>();
					
					row.add(k.getKuotaNum());
					row.add(k.getDesk());
					row.add(k.getR());
					row.add(k);
					tableModelKuotak.addRow(row);
				}
				tableKuotak.getColumnModel().getColumn(0).setPreferredWidth(25);
				tableKuotak.getColumnModel().getColumn(1).setPreferredWidth(268);
				tableKuotak.getColumnModel().getColumn(2).setPreferredWidth(25);
				tableKuotak.getColumnModel().removeColumn(tableKuotak.getColumnModel().getColumn(3));
				
			}
			
		});

		tableKuotak.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int j=tableKuotak.getSelectedRow();
				Integer k=(Integer)tableModelKuotak.getValueAt(j, 0);
				
				kuotaForBet=k;
				domain.Event ev = (domain.Event) tableModelEvents.getValueAt(tableEvents.getSelectedRow(), 2);
					
			}
			
		});		
		
		scrollPaneEvents.setViewportView(tableEvents);
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents);

		tableEvents.setModel(tableModelEvents);
		tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
		
		scrollPaneKuotak.setViewportView(tableKuotak);
		tableModelKuotak= new DefaultTableModel(null, columnNamesKuotak);
		tableKuotak.setModel(tableModelKuotak);
		
		tableKuotak.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableKuotak.getColumnModel().getColumn(1).setPreferredWidth(268);
		tableKuotak.getColumnModel().getColumn(2).setPreferredWidth(25);
		
		

		scrollPaneQueries.setViewportView(tableQueries);
		tableModelQueries = new DefaultTableModel(null, columnNamesQueries);

		tableQueries.setModel(tableModelQueries);
		tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);

		this.getContentPane().add(scrollPaneEvents, null);
		this.getContentPane().add(scrollPaneQueries, null);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(254, 380, 154, 30);
		
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				BLFacade b = MainGUI.getBusinessLogic();
				int i = tableQueries.getSelectedRow();
				int qid = (Integer) tableQueries.getValueAt(i, 0);
				b.setQuestion(qid);
				JFrame a = new KuotaGUI();
				a.setVisible(true);
				dispose();
			}
		});
		
		getContentPane().add(btnNewButton);
		
		
		
		//JScrollPane scrollPaneKuotak = new JScrollPane();
		scrollPaneKuotak.setBounds(254, 219, 520, 150);
		getContentPane().add(scrollPaneKuotak);
		result.setBounds(418, 380, 154, 30);
		
		getContentPane().add(result);
		lblNewLabel.setIcon(new ImageIcon(ViewKuotakAdminGUI.class.getResource("/resources/logo_s.png")));
		lblNewLabel.setBounds(62, 228, 173, 141);
		
		getContentPane().add(lblNewLabel);
		
		
		result.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade b = MainGUI.getBusinessLogic();
				b.setResult(kuotaForBet);
				b.updateUsers(kuotaForBet);
				b.updateMulUsers(kuotaForBet);
			}
		});
		
		if(MainGUI.getBusinessLogic().getErabitltzailea() instanceof Admin) {
			btnNewButton.setVisible(true);
			result.setVisible(true);
		} 
		else {
			btnNewButton.setVisible(false);
			result.setVisible(false);
		}

	}

	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}