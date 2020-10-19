package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;

public class RegisterGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField CreditCardtext;
	private JTextField Nametext;
	private JTextField SecondNametext;
	private JTextField emailtext;
	private JLabel label_1;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 332);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 36, 114, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(10, 136, 114, 20);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(147, 136, 114, 20);
		contentPane.add(passwordField_1);
		
		label_1 = new JLabel("");
		label_1.setBounds(171, 475, 329, 14);
		contentPane.add(label_1);
		
		JLabel lblUsername = new JLabel("Erabiltzaile izena");
		lblUsername.setText(ResourceBundle.getBundle("Etiquetas").getString("Username"));
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setBounds(10, 11, 114, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Pasahitza");
		lblPassword.setText(ResourceBundle.getBundle("Etiquetas").getString("Password"));
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setBounds(10, 111, 114, 14);
		contentPane.add(lblPassword);
		
		JLabel lblRipitPassword = new JLabel("Errepikatu pasahitza");
		lblRipitPassword.setText(ResourceBundle.getBundle("Etiquetas").getString("RePassword"));
		lblRipitPassword.setForeground(Color.WHITE);
		lblRipitPassword.setBounds(147, 111, 133, 14);
		contentPane.add(lblRipitPassword);
		
		JButton btnReyister = new JButton("Erregistratu");
		btnReyister.setText(ResourceBundle.getBundle("Etiquetas").getString("Register"));
		btnReyister.setBounds(10, 222, 114, 23);
		contentPane.add(btnReyister);
		btnReyister.addActionListener(new java.awt.event.ActionListener() {
  			

			public void actionPerformed(java.awt.event.ActionEvent e) {
  				 //DataAccess db=new DataAccess();
  				 BLFacade a = MainGUI.getBusinessLogic();
  				 String usr;
  				 String pas1, pas2;
  				 String email,nam,snam;
  				 Integer cc;
  				 usr= textField.getText();
  				 pas1=new String(passwordField.getPassword());
  				 pas2=new String(passwordField_1.getPassword());
  				 email = emailtext.getText();
  				 nam = Nametext.getText();
  				 snam = SecondNametext.getText();
  				 cc= new Integer(CreditCardtext.getText());
  				 
  				 
  				boolean asist=false;
  				 if(usr.equals("") || pas1.equals("") || pas2.equals("") || email.equals("") || nam.equals("") || snam.equals("") || CreditCardtext.getText().equals("")) {
  					label_1.setText(ResourceBundle.getBundle("Etiquetas").getString("EmptyFields"));
  				 }
  				else {
  				
	  				 try {
	  					 asist = a.register(usr, pas1, pas2,email,nam,snam,cc);
	  					 }
	  				 catch (Exception error){
	  				
						label_1.setText(ResourceBundle.getBundle("Etiquetas").getString("UserTaken"));
	  				 }
  				}
  				 
  				 //db.close();
  				 if(asist) {
  					 dispose();
  					 JFrame b=new LoginGUI();
  					 b.setVisible(true);
  				 }
  			 }
  		 });
		
		JButton btnReturn = new JButton("Itzuli");
		btnReturn.setText(ResourceBundle.getBundle("Etiquetas").getString("Return"));
		btnReturn.setBounds(10, 256, 114, 23);
		contentPane.add(btnReturn);
		
		CreditCardtext = new JTextField();
		CreditCardtext.setColumns(10);
		CreditCardtext.setBounds(10, 191, 114, 20);
		contentPane.add(CreditCardtext);
		
		Nametext = new JTextField();
		Nametext.setColumns(10);
		Nametext.setBounds(10, 87, 114, 20);
		contentPane.add(Nametext);
		
		SecondNametext = new JTextField();
		SecondNametext.setColumns(10);
		SecondNametext.setBounds(147, 87, 114, 20);
		contentPane.add(SecondNametext);
		
		JLabel lblCreditcard = new JLabel("Kreditu txartel zenbakia");
		lblCreditcard.setText(ResourceBundle.getBundle("Etiquetas").getString("CCNum"));
		lblCreditcard.setForeground(Color.WHITE);
		lblCreditcard.setBounds(10, 167, 159, 14);
		contentPane.add(lblCreditcard);
		
		JLabel lblName = new JLabel("Izena");
		lblName.setText(ResourceBundle.getBundle("Etiquetas").getString("Name"));
		lblName.setForeground(Color.WHITE);
		lblName.setBounds(10, 67, 93, 14);
		contentPane.add(lblName);
		
		JLabel lblSecondName = new JLabel("Abizena");
		lblSecondName.setText(ResourceBundle.getBundle("Etiquetas").getString("SName"));
		lblSecondName.setForeground(Color.WHITE);
		lblSecondName.setBounds(147, 67, 93, 14);
		contentPane.add(lblSecondName);
		
		emailtext = new JTextField();
		emailtext.setColumns(10);
		emailtext.setBounds(147, 36, 114, 20);
		contentPane.add(emailtext);
		
		JLabel Email = new JLabel("Helbide elektronikoa");
		Email.setText(ResourceBundle.getBundle("Etiquetas").getString("Email"));
		Email.setForeground(Color.WHITE);
		Email.setBounds(147, 11, 133, 14);
		contentPane.add(Email);
		
		JLabel lblNewLabel = new JLabel(" ");
		lblNewLabel.setIcon(new ImageIcon(RegisterGUI.class.getResource("/resources/logo_s.png")));
		lblNewLabel.setBounds(157, 180, 123, 101);
		contentPane.add(lblNewLabel);
		
		
		btnReturn.addActionListener(new java.awt.event.ActionListener() {
 			 public void actionPerformed(java.awt.event.ActionEvent e) {
 				dispose();
				JFrame a = new LoginGUI();
				a.setVisible(true);
 			 }
 		 });
		
		
	}
}
