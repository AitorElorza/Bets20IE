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
import domain.Kuota;
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

public class DetailsGUI extends JFrame {

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
	/**
	 * Create the frame.
	 */
	public DetailsGUI() {
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
		
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		
		MultipleBet m=b.bilatuM(b.getDetails());
		KuotaBek=m.getMugimenduak();

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
				dispose();
			}
		});
	}
}
