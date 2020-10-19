package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Event;
import domain.Question;
import exceptions.EventFinished;

import javax.swing.JTextPane;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

public class KuotaGUI extends JFrame {

	private JPanel contentPane;
	private JSpinner spinner_2;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KuotaGUI frame = new KuotaGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public KuotaGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 322, 138);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnItzuliMenura = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Return"));
		btnItzuliMenura.setBounds(191, 64, 105, 23);
		contentPane.add(btnItzuliMenura);
		btnItzuliMenura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				JFrame a = new ViewKuotakGUI();
				a.setVisible(true);	
				
			}
		});
		
		JLabel lblX = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Desk"));
		lblX.setBounds(10, 11, 119, 14);
		contentPane.add(lblX);
		
		JLabel lblR = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("WinEur"));
		lblR.setBounds(10, 36, 90, 14);
		contentPane.add(lblR);
		
		spinner_2 = new JSpinner();
		spinner_2.setBounds(139, 33, 60, 20);
		contentPane.add(spinner_2);
		
		JButton btnGordeKuota = new JButton(ResourceBundle.getBundle("Etiquetas").getString("SaveK"));
		btnGordeKuota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BLFacade a = MainGUI.getBusinessLogic();
				
				String desk = textField.getText();
				Integer r = (Integer) spinner_2.getValue();
				Question q=a.getQuestion();
				Event ev = q.getEvent();
				
				try {
					a.createKuota(ev,q,desk, r);
				} catch (EventFinished e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		btnGordeKuota.setBounds(10, 64, 171, 23);
		contentPane.add(btnGordeKuota);
		
		textField = new JTextField();
		textField.setToolTipText("");
		textField.setBounds(139, 8, 157, 20);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}
