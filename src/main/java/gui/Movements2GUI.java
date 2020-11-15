package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import domain.Mugimendua;
import domain.Registered;
import table.UserAdapter;

public class Movements2GUI extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JScrollPane scrollPane ;
	private JTable table;
	private UserAdapter tableModel;






	private final JButton btnClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private final JButton btnDelete = new JButton(ResourceBundle.getBundle("Etiquetas").getString("DelBet"));

	private final BLFacade a = MainGUI.getBusinessLogic();
	private final JLabel lblCredit = new JLabel("");
	private final JButton btnDetails = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Info"));
	public Movements2GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1109, 486);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		

		Vector<Mugimendua> mugimenduak=a.getMugi();
		Registered r= (Registered) a.getUser(a.getUsername());
		tableModel = new UserAdapter(r);
		table=new JTable(tableModel);
		table.setAutoCreateRowSorter(true);
		scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
