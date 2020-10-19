package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Apustua;
import domain.Kuota;
import domain.Mugimendua;
import domain.MultipleBet;
import domain.Registered;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.ScrollPane;
import java.awt.Rectangle;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ErreplikatuGUI extends JFrame {

	private JPanel contentPane;
	private JButton btnBet = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private final JLabel warn = new JLabel(" ");
	private final BLFacade b = MainGUI.getBusinessLogic();
	private JTable table = new JTable();
	private Vector<Kuota> KuotaBek;
	private DefaultTableModel tableModel;
	
	private String[] columnNames = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("Event"), 
			ResourceBundle.getBundle("Etiquetas").getString("Query"),
			ResourceBundle.getBundle("Etiquetas").getString("Fee")
	};
	private final JButton erreplikatu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Errep")); //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Create the frame.
	 */
	public ErreplikatuGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 325);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnBet.setBounds(335, 252, 89, 23);
		contentPane.add(btnBet);
		warn.setBounds(34, 191, 329, 14);
		
		contentPane.add(warn);
		
		JScrollPane scrollPaneEvents = new JScrollPane();
		scrollPaneEvents.setBounds(new Rectangle(41, 258, 363, 150));
		scrollPaneEvents.setBounds(10, 11, 414, 231);
		contentPane.add(scrollPaneEvents);
		
		scrollPaneEvents.setViewportView(table);
		tableModel = new DefaultTableModel(null, columnNames);
		tableModel.setColumnCount(4);
		table.setModel(tableModel);
		erreplikatu.setBounds(10, 252, 168, 23);
		
		contentPane.add(erreplikatu);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().removeColumn(table.getColumnModel().getColumn(3));
		
		Vector<Mugimendua> movs=b.getUsertorep().getMugimenduak();
		
		Vector<Apustua> apustuak=new Vector<Apustua>();
		Vector<MultipleBet> mbets=new Vector<MultipleBet>();
		float minCredit=0;

		for (domain.Mugimendua m:movs){
			Vector<Object> row = new Vector<Object>();
			
			if(m instanceof Apustua && ((Apustua)m).isTbd() ){
				row.add(((Apustua)m).getKuota().getQuestion().getEvent().getDescription());
				row.add(((Apustua)m).getKuota().getQuestion().getQuestion());
				row.add(((Apustua)m).getKuota().getDesk());
				row.add(m);
				tableModel.addRow(row);
				apustuak.addElement((Apustua) m);
				minCredit+=m.getAmount();
			}
			else if(m instanceof MultipleBet && ((MultipleBet) m).isTbd()) {
				row.add("-------------------------");
				row.add("--- "+ResourceBundle.getBundle("Etiquetas").getString("MulBet")+" ---");
				row.add("-------------------------");
				row.add(m);
				tableModel.addRow(row);
				mbets.addElement((MultipleBet) m);
				minCredit+=m.getAmount();
				
				for(Kuota k:((MultipleBet) m).getMugimenduak()) {
					Vector<Object> row2 = new Vector<Object>();
					
					row2.add(k.getQuestion().getEvent().getDescription());
					row2.add(k.getQuestion().getQuestion());
					row2.add(k.getDesk());
					row2.add(m);
					tableModel.addRow(row2);
				}
				Vector<Object> row3 = new Vector<Object>();
				row3.add("-------------------------");
				row3.add("-------------------------");
				row3.add("-------------------------");
				row3.add(m);
				tableModel.addRow(row3);
			}
				
		}
//		table.getColumnModel().getColumn(0).setPreferredWidth(100);
//		table.getColumnModel().getColumn(1).setPreferredWidth(100);
//		table.getColumnModel().getColumn(2).setPreferredWidth(100);
//		table.getColumnModel().removeColumn(table.getColumnModel().getColumn(3));
		
		if(((Registered) b.getErabitltzailea()).getCredit()>=minCredit) erreplikatu.setEnabled(true);
		else erreplikatu.setEnabled(false);
		
		btnBet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		erreplikatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b.erreplikatu(apustuak, mbets);
				dispose();
			}
		});
	}
}
