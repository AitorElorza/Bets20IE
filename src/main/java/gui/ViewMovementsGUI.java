package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import domain.Admin;
import domain.Apustua;
import domain.MoneyTrans;
import domain.Mugimendua;
import domain.MultipleBet;

import javax.swing.JButton;
import javax.swing.JLabel;

public class ViewMovementsGUI extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane = new JScrollPane();
	private JTable table = new JTable();
	private DefaultTableModel tableModel;
	
	private Integer BetToDel;
	
	private String[] columnNames = new String[] { 
			"#",
			ResourceBundle.getBundle("Etiquetas").getString("Desk"),
			ResourceBundle.getBundle("Etiquetas").getString("Date"),
			ResourceBundle.getBundle("Etiquetas").getString("Amount"),
			ResourceBundle.getBundle("Etiquetas").getString("Event"),
			ResourceBundle.getBundle("Etiquetas").getString("Query"),
			ResourceBundle.getBundle("Etiquetas").getString("Fee"),
			ResourceBundle.getBundle("Etiquetas").getString("State")
		};
	private final JButton btnClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private final JButton btnDelete = new JButton(ResourceBundle.getBundle("Etiquetas").getString("DelBet"));
	
	private final BLFacade a = MainGUI.getBusinessLogic();
	private final JLabel lblCredit = new JLabel("");
	private final JButton btnDetails = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Info"));
	
	/**
	 * Create the frame.
	 */
	public ViewMovementsGUI() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1109, 486);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		scrollPane.setBounds(5, 5, 1083, 395);
		
		scrollPane.setViewportView(table);
		tableModel = new DefaultTableModel(null, columnNames);
		tableModel.setColumnCount(9);
		table.setModel(tableModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(25);
		table.getColumnModel().getColumn(1).setPreferredWidth(40);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
		table.getColumnModel().getColumn(4).setPreferredWidth(200);
		table.getColumnModel().getColumn(5).setPreferredWidth(200);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.getColumnModel().getColumn(7).setPreferredWidth(40);
		table.getColumnModel().removeColumn(table.getColumnModel().getColumn(8));
		contentPane.setLayout(null);
		getContentPane().add(scrollPane);
		btnClose.setBounds(981, 411, 107, 25);
		
		contentPane.add(btnClose);
		btnDelete.setBounds(821, 411, 150, 25);
		btnDelete.setEnabled(false);
		
		
		contentPane.add(btnDelete);
		
		JLabel label = new JLabel(" ");
		label.setBounds(5, 411, 475, 25);
		contentPane.add(label);
		lblCredit.setBounds(658, 411, 179, 25);
		
		contentPane.add(lblCredit);
		
		lblCredit.setText(ResourceBundle.getBundle("Etiquetas").getString("Creditp")+" "+a.getCredit()+"€");
		btnDetails.setBounds(490, 411, 150, 23);
		contentPane.add(btnDetails);
		
		btnDetails.setEnabled(false);
		
		Vector<Mugimendua> mugimenduak=a.getMugi();
		
		if(mugimenduak.isEmpty()) label.setText("Mugimedurik ez.");
		else label.setText(a.getUsername()+" erabiltzailearen mugimenduak.");
		
		
		
		for(domain.Mugimendua m:mugimenduak) {
			Vector<Object> row=new Vector<Object>();
			
			row.add(m.getId());
			if(m instanceof MoneyTrans) row.add(ResourceBundle.getBundle("Etiquetas").getString("AddMoney"));
			else if(m instanceof Apustua) row.add(ResourceBundle.getBundle("Etiquetas").getString("Bet2"));
			else if(m instanceof MultipleBet) row.add(ResourceBundle.getBundle("Etiquetas").getString("MulBet"));
			row.add(m.getData().toString());
			row.add(m.getAmount());
			if(m instanceof Apustua) {
				Integer i=m.getId();
				Apustua bet=a.bilatuB(i);
				
				row.add(bet.getKuota().getQuestion().getEvent().getDescription());
				row.add(bet.getKuota().getQuestion().getQuestion());
				row.add(bet.getKuota().getDesk());
				if(bet.isLose()) row.add("Lost");
				else if(bet.isWin()) row.add("Won");
				else if(bet.isTbd()) row.add("TBD");
				
				if(bet.getKuota().getQuestion().getEvent().isCancelled()) setBackground(Color.red);
			}
			else if(m instanceof MoneyTrans){
				row.add("");
				row.add("");
				row.add("");
				row.add("");
				
			}
			else if(m instanceof MultipleBet) {
				Integer i=m.getId();
				MultipleBet bet=a.bilatuM(i);
				
				row.add("");
				row.add("");
				row.add("");
				if(bet.isLose()) row.add("Lost");
				else if(bet.isWin()) row.add("Won");
				else if(bet.isTbd()) row.add("TBD");
			}
			row.add(m);
			tableModel.addRow(row);
		}
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=table.getSelectedRow();
				String state=(String) tableModel.getValueAt(i, 7);
				Mugimendua mLag=(Mugimendua) tableModel.getValueAt(i, 8);
				if(mLag instanceof Apustua && state.equals("TBD")) {
					btnDelete.setEnabled(true);
					BetToDel=(Integer) tableModel.getValueAt(i, 0);
				} 
				else btnDelete.setEnabled(false);
				
				if(mLag instanceof MultipleBet) {
					btnDetails.setEnabled(true);
					if(state.equals("TBD")) {
						btnDelete.setEnabled(true);
						BetToDel=(Integer) tableModel.getValueAt(i, 0);
					}
					else btnDelete.setEnabled(false);
				}
				else btnDetails.setEnabled(false);
				
			}
			
		});		
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tableModel.getValueAt(table.getSelectedRow(), 8) instanceof Apustua) a.deleteBet(BetToDel);
				else if(tableModel.getValueAt(table.getSelectedRow(), 8) instanceof MultipleBet) a.deleteMulBet(BetToDel);
				dispose();
				JFrame a=new ViewMovementsGUI();
				a.setVisible(true);
				
				
			}
		});
		
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a.setDetails((Integer) tableModel.getValueAt(table.getSelectedRow(), 0));
				JFrame b=new DetailsGUI();
				b.setVisible(true);
			}
		});
		
	}
}
