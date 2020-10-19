package gui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Apustua;
import domain.Kuota;
import domain.Mugimendua;
import domain.Registered;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Rectangle;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TopGUI extends JFrame {

	private String etiquetas="Etiquetas";
	private JPanel contentPane;
	private JButton btnBet = new JButton(ResourceBundle.getBundle(etiquetas).getString("Close"));
	private final JLabel warn = new JLabel(" ");
	private final BLFacade b = MainGUI.getBusinessLogic();
	private JTable table = new JTable();
	private Vector<Kuota> KuotaBek;
	private DefaultTableModel tableModel;
	private Registered toRep;
	
	
	private String[] columnNames = new String[] {
			ResourceBundle.getBundle(etiquetas).getString("User"), 
			ResourceBundle.getBundle(etiquetas).getString("Amount")
	};
	private final JButton viewBets = new JButton(ResourceBundle.getBundle(etiquetas).getString("ViewBets")); //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Create the frame.
	 */
	public TopGUI() {
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
		tableModel.setColumnCount(3);
		table.setModel(tableModel);
		viewBets.setBounds(10, 252, 206, 23);
		
		contentPane.add(viewBets);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().removeColumn(table.getColumnModel().getColumn(2));
		
		Vector<Registered> users=b.bilatuTop();

		for (domain.Registered r:users){
			Vector<Object> row = new Vector<Object>();

			row.add(r.getUsername());
			row.add(r.getWinnings());
			row.add(r);
			tableModel.addRow(row);	
		}
//		table.getColumnModel().getColumn(0).setPreferredWidth(100);
//		table.getColumnModel().getColumn(1).setPreferredWidth(100);
//		table.getColumnModel().removeColumn(table.getColumnModel().getColumn(2));
		
		btnBet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		viewBets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b.setUsertorep(toRep);
				JFrame a = new ErreplikatuGUI();
				a.setVisible(true);
			}
		});
		
		viewBets.setEnabled(false);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=table.getSelectedRow();
				viewBets.setEnabled(false);
				toRep=(Registered) tableModel.getValueAt(i, 2);
				Vector<Mugimendua> movs=toRep.getMugimenduak();
				for(Mugimendua m:movs) if(m instanceof Apustua && ((Apustua) m).isTbd()) viewBets.setEnabled(true);			
			}
			
		});		
	}
}
