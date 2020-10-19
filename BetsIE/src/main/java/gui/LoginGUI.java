package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;

import java.lang.Class;
import domain.Event;
import domain.Registered;
import domain.User;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class LoginGUI extends JFrame {
	JTextField txtUsername;
	JPasswordField passwordField;
    JLabel lblGelchrome = new JLabel("Ongi etorri");;
    JLabel lblUsername = new JLabel("Erabiltzaile izena");
    JLabel lblPassaaaaaa = new JLabel("Pasahitza");
    JComboBox comboBox = new JComboBox();
    JButton btnOmaeWaMou = new JButton("Sartu");
    JButton btnReyister = new JButton("Erregistratu");
    private User logged;
    private String etiquetas="Etiquetas";
    private final JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle(etiquetas).getString("LoginGUI.lblNewLabel.text")); //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		lblNewLabel.setBounds(286, 52, 107, 100);
		getContentPane().add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(LoginGUI.class.getResource("/resources/logo_s.png")));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		panel_1.setForeground(Color.BLACK);
		panel_1.setBounds(311, 0, 123, 191);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Euskara", "Castellano", "English"}));
		comboBox.setBounds(10, 11, 88, 20);
		getContentPane().add(comboBox);
		comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String s = (String) comboBox.getSelectedItem().toString();
            	switch (s) {
            	case "Euskara":
            		 Locale.setDefault(new Locale("eus"));
            		 redibujar();
            		 break;
            	case "Castellano":
            		 Locale.setDefault(new Locale("es"));
            		 redibujar();
            		 break;
            	case "English":
            		 Locale.setDefault(new Locale("en"));
            		 redibujar();
            		 break;
            	default:
            		 Locale.setDefault(new Locale("eus"));
            		 break;
            		 
            	}
            }
           });
		
		
		
		txtUsername = new JTextField();
		txtUsername.setToolTipText("");
		txtUsername.setBounds(10, 132, 118, 20);
		getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(10, 188, 118, 20);
		getContentPane().add(passwordField);
		
		
		//lblUsername.setText(ResourceBundle.getBundle("Etiquetas").getString("Username"));
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setBounds(10, 107, 106, 14);
		getContentPane().add(lblUsername);
		
		
		//lblPassaaaaaa.setText(ResourceBundle.getBundle("Etiquetas").getString("Password"));
		lblPassaaaaaa.setForeground(Color.WHITE);
		lblPassaaaaaa.setBounds(10, 163, 118, 14);
		getContentPane().add(lblPassaaaaaa);
		
		
		//btnOmaeWaMou.setText(ResourceBundle.getBundle("Etiquetas").getString("Login"));
		btnOmaeWaMou.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 BLFacade BLF = MainGUI.getBusinessLogic();
				 String usr = txtUsername.getText();
				 String psw = new String(passwordField.getPassword());
				
				if(!BLF.logIN(usr, psw)) {
					lblGelchrome.setText(ResourceBundle.getBundle(etiquetas).getString("Wrongpass"));
					//Egokitu interfaze grafikora
				}else {
					
						BLF.setUser(usr);
						JFrame a = new MainGUI();
						logged = BLF.getUser(usr);
						dispose();
 					
						a.setVisible(true);					
				}
				
			}
		});
		btnOmaeWaMou.setBounds(9, 227, 89, 23);
		getContentPane().add(btnOmaeWaMou);
		
		
		btnReyister.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				dispose();
				JFrame a = new RegisterGUI();
				a.setVisible(true);
				
			}
		});
		btnReyister.setBounds(110, 227, 89, 23);
		getContentPane().add(btnReyister);
		
		lblGelchrome = new JLabel();
		lblGelchrome.setText(ResourceBundle.getBundle(etiquetas).getString("Welcome"));
		lblGelchrome.setForeground(Color.WHITE);
		lblGelchrome.setFont(new Font("Arial", Font.PLAIN, 12));
		lblGelchrome.setBounds(220, 226, 258, 23);
		getContentPane().add(lblGelchrome);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(215, 227, 209, 23);
		getContentPane().add(panel);
		
		
		
		JLabel label = new JLabel(" ");
		label.setIcon(new ImageIcon(LoginGUI.class.getResource("/resources/login.jpg")));
		label.setBounds(0, 0, 434, 261);
		getContentPane().add(label);
		
	}
	
	private void redibujar() {
		lblUsername.setText(ResourceBundle.getBundle(etiquetas).getString("Username"));
		lblGelchrome.setText(ResourceBundle.getBundle(etiquetas).getString("Welcome"));
		lblPassaaaaaa.setText(ResourceBundle.getBundle(etiquetas).getString("Password"));
		btnOmaeWaMou.setText(ResourceBundle.getBundle(etiquetas).getString("Login"));
		btnReyister.setText(ResourceBundle.getBundle(etiquetas).getString("Register"));
	}
}
