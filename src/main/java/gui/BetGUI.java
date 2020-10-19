package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Kuota;
import domain.Registered;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JButton;

public class BetGUI extends JFrame {

	private JPanel contentPane;

	private JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Event"));
	private JLabel event = new JLabel(" ");
	private JLabel lblQuestion = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Query"));
	private JLabel question = new JLabel(" ");
	private JLabel lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Fee"));
	private JLabel kuota = new JLabel(" ");
	private JSpinner spinner = new JSpinner();
	private JLabel lblNewLabel_2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MinimumBetPrice"));
	private JLabel minBet = new JLabel(" ");
	private JButton btnBet = new JButton("BET");
	private final JLabel warn = new JLabel(" ");
	private final float betMinimum;
	private final BLFacade b = MainGUI.getBusinessLogic();
	private final JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Return"));
	/**
	 * Create the frame.
	 */
	public BetGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 279, 251);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Kuota k=b.getKuota();
		
		lblNewLabel.setBounds(34, 26, 94, 14);
		contentPane.add(lblNewLabel);
		
		event.setBounds(138, 26, 205, 14);
		contentPane.add(event);
		
		event.setText(k.getQuestion().getEvent().getDescription());
		
		lblQuestion.setBounds(34, 51, 94, 14);
		contentPane.add(lblQuestion);
		
		
		question.setBounds(138, 51, 205, 14);
		contentPane.add(question);
		
		question.setText(k.getQuestion().getQuestion());
		
		lblNewLabel_1.setBounds(34, 76, 67, 14);
		contentPane.add(lblNewLabel_1);
		
		
		kuota.setBounds(138, 76, 205, 14);
		contentPane.add(kuota);
		
		kuota.setText(k.getDesk());
		
		spinner.setBounds(34, 126, 193, 20);
		contentPane.add(spinner);
		
		lblNewLabel_2.setBounds(34, 101, 94, 14);
		contentPane.add(lblNewLabel_2);
		
		
		minBet.setBounds(138, 101, 130, 14);
		contentPane.add(minBet);
		
		betMinimum=b.getKuota().getQuestion().getBetMinimum();
		String lag=Float.toString(betMinimum);
		minBet.setText(lag);
		
		btnBet.setBounds(138, 157, 89, 23);
		contentPane.add(btnBet);
		warn.setBounds(34, 191, 329, 14);
		
		contentPane.add(warn);
		btnNewButton.setBounds(34, 157, 89, 23);
		
		contentPane.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnBet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer lag=(Integer) spinner.getValue();
				float betValue=(float) lag;
				
				Registered u=(Registered) b.getUser(b.getUsername());
				
				if(betValue<betMinimum) warn.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorMin"));
				else if(u.getCredit()<betValue) warn.setText(ResourceBundle.getBundle("Etiquetas").getString("NoCredit"));
				else {
					Date data = new Date();
					
					b.bet(betValue, data);
					dispose();
				}	
			}
		});
	}

}
