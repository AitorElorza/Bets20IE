package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;

import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class ATMGUI extends JFrame {

	private JPanel contentPane;
	private JSpinner spinner = new JSpinner();

	/**
	 * Create the frame.
	 */
	public ATMGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 198, 124);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		spinner.setBounds(108, 11, 64, 25);
		contentPane.add(spinner);
		
		JButton btnAddMoney = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AddMoney"));
		btnAddMoney.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BLFacade a = MainGUI.getBusinessLogic();
				
				Integer amount = (Integer) spinner.getValue();
				
				Date data = new Date();
				
				a.addAmount(a.getUsername(), (float) amount, data);
				dispose();
				
			}
		});
		btnAddMoney.setBounds(36, 47, 111, 23);
		contentPane.add(btnAddMoney);
		
		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Amount"));
		lblNewLabel.setBounds(10, 16, 83, 14);
		contentPane.add(lblNewLabel);
	}
}
