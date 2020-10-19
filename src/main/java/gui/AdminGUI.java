package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;

import domain.Admin;
import domain.Event;
import businessLogic.BLFacade;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class AdminGUI extends JFrame {
	
	private static final String ETIQUETAS = "Etiquetas";

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton jButtonCreateQuery = null;
	private JButton jButtonQueryQueries = null;

    private static BLFacade appFacadeInterface;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}

	private JLabel label;
	
	/**
	 * This is the default constructor
	 */
	public AdminGUI() {
		super();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					//if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});

		initialize();
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(495, 381);
		this.setContentPane(getJContentPane());
		this.setTitle("Bets 2020");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setBackground(Color.WHITE);
			jContentPane.setLayout(null);
			
			JLabel lblNewLabel = new JLabel(" ");
			lblNewLabel.setIcon(new ImageIcon(AdminGUI.class.getResource("/resources/logo_s.png")));
			lblNewLabel.setBounds(46, 14, 172, 118);
			jContentPane.add(lblNewLabel);
			
			JButton jButtonKuotak = new JButton();
			jButtonKuotak.setText(ResourceBundle.getBundle(ETIQUETAS).getString("SetK")); //$NON-NLS-1$ //$NON-NLS-2$
			jButtonKuotak.setBounds(10, 299, 174, 32);
			jContentPane.add(jButtonKuotak);
			boolean t = true;
			jButtonKuotak.addActionListener(new ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new ViewKuotakAdminGUI();
					a.setVisible(t);
				}
			});
			
			JButton moreWin = new JButton(ResourceBundle.getBundle(ETIQUETAS).getString("MoreWin")); //$NON-NLS-1$ //$NON-NLS-2$
			moreWin.setBounds(295, 187, 172, 32);
			jContentPane.add(moreWin);
			moreWin.addActionListener(new ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new TopGUI();
					a.setVisible(t);
				}
			});
			
			JButton btnMulBet = new JButton(ResourceBundle.getBundle(ETIQUETAS).getString("MulBet")); //$NON-NLS-1$ //$NON-NLS-2$
			btnMulBet.setBounds(295, 144, 172, 32);
			jContentPane.add(btnMulBet);
			btnMulBet.addActionListener(new ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new MultipleBetGUI();
					a.setVisible(t);
				}
			});
			
			
			JButton btnCancelEvent = new JButton(ResourceBundle.getBundle(ETIQUETAS).getString("CEvent")); //$NON-NLS-1$ //$NON-NLS-2$
			btnCancelEvent.setBounds(10, 170, 174, 32);
			jContentPane.add(btnCancelEvent);
			btnCancelEvent.addActionListener(new ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new CancelEventGUI(new Vector<domain.Event>());
					a.setVisible(t);
				}
			});
			
			JButton ViewMov = new JButton("View Movements");
			ViewMov.setText(ResourceBundle.getBundle(ETIQUETAS).getString("ViewMov"));
			ViewMov.setBounds(295, 100, 174, 32);
			jContentPane.add(ViewMov);
			ViewMov.addActionListener(new ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new ViewMovementsGUI();
					a.setVisible(t);
				}
			});
			
			
			JButton btnDiruaSartu = new JButton("Dirua Sartu");
			btnDiruaSartu.setText(ResourceBundle.getBundle(ETIQUETAS).getString("AddMoney"));
			btnDiruaSartu.setBounds(295, 57, 174, 32);
			jContentPane.add(btnDiruaSartu);
			jContentPane.add(getBoton3());
			jContentPane.add(getBoton2());
			
			JButton btnCreateEvent = new JButton(ResourceBundle.getBundle(ETIQUETAS).getString("CreateEv")); //$NON-NLS-1$ //$NON-NLS-2$
			btnCreateEvent.addActionListener(new ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new CreateEventGUI(new Vector<Event>());
					a.setVisible(t);
				}
			});
			
			btnDiruaSartu.addActionListener(new ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new ATMGUI();
					a.setVisible(t);
				}
			});
			
			btnCreateEvent.setBounds(10, 213, 174, 32);
			jContentPane.add(btnCreateEvent);
			jContentPane.add(getLabel());
			
			
			if(appFacadeInterface.getErabitltzailea() instanceof Admin) {
				btnCreateEvent.setVisible(t);
				jButtonCreateQuery.setVisible(t);
				btnCancelEvent.setVisible(t);
				btnDiruaSartu.setVisible(false);
				ViewMov.setVisible(false);
				btnMulBet.setVisible(false);
				jButtonKuotak.setVisible(t);
				jButtonQueryQueries.setVisible(false);
			} 
			else {
				btnCreateEvent.setVisible(false);
				jButtonCreateQuery.setVisible(false);
				btnCancelEvent.setVisible(false);
				btnDiruaSartu.setVisible(t);
				ViewMov.setVisible(t);
				btnMulBet.setVisible(t);
				jButtonKuotak.setVisible(false);
				jButtonQueryQueries.setVisible(t);
			} 
			
		}
		return jContentPane;
	}


	/**
	 * This method initializes boton1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton2() {
		if (jButtonCreateQuery == null) {
			jButtonCreateQuery = new JButton();
			jButtonCreateQuery.setBounds(10, 256, 174, 32);
			jButtonCreateQuery.setText(ResourceBundle.getBundle(ETIQUETAS).getString("CreateQuery")); //$NON-NLS-1$ //$NON-NLS-2$
			jButtonCreateQuery.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new CreateQuestionGUI(new Vector<Event>());
					a.setVisible(true);
				}
			});
		}
		return jButtonCreateQuery;
	}
	
	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton3() {
		if (jButtonQueryQueries == null) {
			jButtonQueryQueries = new JButton();
			jButtonQueryQueries.setBounds(295, 14, 174, 32);
			jButtonQueryQueries.setText(ResourceBundle.getBundle(ETIQUETAS).getString("QueryQueries"));
			jButtonQueryQueries.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new ViewKuotakGUI();

					a.setVisible(true);
				}
			});
		}
		return jButtonQueryQueries;
	}
	
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel(ResourceBundle.getBundle(ETIQUETAS).getString("MainGUI.label.text")); //$NON-NLS-1$ //$NON-NLS-2$
			label.setIcon(new ImageIcon(AdminGUI.class.getResource("/resources/main.PNG")));
			label.setBounds(46, 119, 551, 265);
		}
		return label;
	}
} // @jve:decl-index=0:visual-constraint="0,0"


