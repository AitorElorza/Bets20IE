package gui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Kuota;
import domain.Registered;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.ScrollPane;
import java.awt.Rectangle;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BetMulGUI extends JFrame {

	private JPanel contentPane;
	private JSpinner spinner = new JSpinner();
	private JLabel lblNewLabel_2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MinimumBetPrice"));
	private JLabel minBet = new JLabel(" ");
	private JButton btnBet = new JButton("BET");
	private final JLabel warn = new JLabel(" ");
	private final float betMinimum;
	private final BLFacade b = MainGUI.getBusinessLogic();
	private final JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Return"));
	private JTable table = new JTable();
	private Vector<Kuota> KuotaBek;
	private float minBetValue;
	private DefaultTableModel tableModel;
	
	private String[] columnNames = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("Event"), 
			ResourceBundle.getBundle("Etiquetas").getString("Query"), 
			ResourceBundle.getBundle("Etiquetas").getString("Fee")
	};
	/**
	 * Create the frame.
	 */
	public BetMulGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 325);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		spinner.setBounds(142, 253, 86, 20);
		contentPane.add(spinner);
		
		lblNewLabel_2.setBounds(10, 256, 94, 14);
		contentPane.add(lblNewLabel_2);
		
		
		minBet.setBounds(98, 256, 130, 14);
		contentPane.add(minBet);
		
		betMinimum=b.getKuota().getQuestion().getBetMinimum();
		String lag=Float.toString(betMinimum);
		minBet.setText(lag);
		
		btnBet.setBounds(335, 252, 89, 23);
		contentPane.add(btnBet);
		warn.setBounds(34, 191, 329, 14);
		
		contentPane.add(warn);
		btnNewButton.setBounds(236, 252, 89, 23);
		
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPaneEvents = new JScrollPane();
		scrollPaneEvents.setBounds(new Rectangle(41, 258, 363, 150));
		scrollPaneEvents.setBounds(10, 11, 414, 231);
		contentPane.add(scrollPaneEvents);
		
		scrollPaneEvents.setViewportView(table);
		tableModel = new DefaultTableModel(null, columnNames);
		tableModel.setColumnCount(4);
		table.setModel(tableModel);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		KuotaBek=b.getKuotak();
		minBetValue=b.getMinBet();
		
		for (domain.Kuota k:KuotaBek){
			Vector<Object> row = new Vector<Object>();

			row.add(k.getQuestion().getEvent().getDescription());
			row.add(k.getQuestion().getQuestion());
			row.add(k.getDesk());
			row.add(k);
			tableModel.addRow(row);	
		}
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().removeColumn(table.getColumnModel().getColumn(3));
		
		btnBet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer lag=(Integer) spinner.getValue();
				float betValue=(float) lag;
				
				Registered u=(Registered) b.getUser(b.getUsername());
				
				if(betValue<betMinimum) warn.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorMin"));
				else if(u.getCredit()<betValue) warn.setText(ResourceBundle.getBundle("Etiquetas").getString("NoCredit"));
				else {
					Date data = new Date();
					
					b.mulBet(betValue, data);
					dispose();
				}	
			}
		});
	}
}
