package gui;

import data.EventBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import net.miginfocom.swing.MigLayout;

public class Events {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Events window = new Events();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Events() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 347, 275);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNazwa = new JLabel("Nazwa");
		lblNazwa.setForeground(Color.WHITE);
		lblNazwa.setBackground(Color.WHITE);
		lblNazwa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNazwa.setBounds(12, 50, 56, 16);
		frame.getContentPane().add(lblNazwa);
		
		JLabel lblMiejsce = new JLabel("Miejsce");
		lblMiejsce.setForeground(Color.WHITE);
		lblMiejsce.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMiejsce.setBounds(12, 79, 56, 16);
		frame.getContentPane().add(lblMiejsce);
		
		JLabel lblGodzinaRozpoczcia = new JLabel("Godzina rozpocz\u0119cia");
		lblGodzinaRozpoczcia.setForeground(Color.WHITE);
		lblGodzinaRozpoczcia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGodzinaRozpoczcia.setBounds(12, 123, 155, 16);
		frame.getContentPane().add(lblGodzinaRozpoczcia);
		
		JLabel lblGodzinaZakoczenia = new JLabel("Godzina zako\u0144czenia");
		lblGodzinaZakoczenia.setForeground(Color.WHITE);
		lblGodzinaZakoczenia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGodzinaZakoczenia.setBounds(12, 152, 146, 16);
		frame.getContentPane().add(lblGodzinaZakoczenia);
		
		Checkbox checkbox = new Checkbox("Powiadomienie");
		checkbox.setForeground(Color.WHITE);
		checkbox.setBounds(10, 184, 108, 24);
		frame.getContentPane().add(checkbox);
		
		Label label = new Label("Nowe wydarzenie:");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Dialog", Font.BOLD, 17));
		label.setBounds(65, 10, 155, 24);
		frame.getContentPane().add(label);
		
		Button button = new Button("DODAJ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventBuilder builder = new EventBuilder();
				builder.setId(" ");
				builder.setTitle(lblNazwa.getText());
				builder.setDescription(lblMiejsce.getText());

				
			}
		});
		button.setFont(new Font("Dialog", Font.BOLD, 14));
		button.setActionCommand("DODAJ");
		button.setBackground(new Color(178, 34, 34));
		button.setForeground(Color.BLACK);
		button.setBounds(170, 184, 120, 24);
		frame.getContentPane().add(button);
		
		JTextArea Title = new JTextArea();
		Title.setBounds(94, 53, 196, 16);
		frame.getContentPane().add(Title);
		
		JTextArea Place = new JTextArea();
		Place.setBounds(94, 83, 196, 16);
		frame.getContentPane().add(Place);
		
		Choice startDateHours = new Choice();
		startDateHours.setBounds(172, 117, 48, 22);
		startDateHours.add("00");  
		startDateHours.add("01");  
		startDateHours.add("02");  
		startDateHours.add("03");  
		startDateHours.add("04");  
		startDateHours.add("05");  
		startDateHours.add("06");  
		startDateHours.add("07");  
		startDateHours.add("08"); 
		startDateHours.add("09");  
		startDateHours.add("10");  
		startDateHours.add("11");  
		startDateHours.add("12");  
		startDateHours.add("13");  
		startDateHours.add("14");  
		startDateHours.add("15");  
		startDateHours.add("16");  
		startDateHours.add("17");  
		startDateHours.add("18");  
		startDateHours.add("19");  
		startDateHours.add("20"); 
		startDateHours.add("21"); 
		startDateHours.add("22"); 
		startDateHours.add("23"); 
		frame.getContentPane().add(startDateHours);
		
		Choice endDateHours = new Choice();
		endDateHours.setBounds(172, 146, 48, 22);
		endDateHours.add("00");  
		endDateHours.add("01");  
		endDateHours.add("02");  
		endDateHours.add("03");  
		endDateHours.add("04");  
		endDateHours.add("05");  
		endDateHours.add("06");  
		endDateHours.add("07");  
		endDateHours.add("08"); 
		endDateHours.add("09");  
		endDateHours.add("10");  
		endDateHours.add("11");  
		endDateHours.add("12");  
		endDateHours.add("13");  
		endDateHours.add("14");  
		endDateHours.add("15");  
		endDateHours.add("16");  
		endDateHours.add("17");  
		endDateHours.add("18");  
		endDateHours.add("19");  
		endDateHours.add("20"); 
		endDateHours.add("21"); 
		endDateHours.add("22"); 
		endDateHours.add("23"); 
		
		Choice startDateMinutes = new Choice();
		startDateMinutes.setBounds(242, 117, 48, 22);
		startDateMinutes.add("00");  
		startDateMinutes.add("01");  
		startDateMinutes.add("02");  
		startDateMinutes.add("03");  
		startDateMinutes.add("04");  
		startDateMinutes.add("05");  
		startDateMinutes.add("06");  
		startDateMinutes.add("07");  
		startDateMinutes.add("08"); 
		startDateMinutes.add("09");  
		startDateMinutes.add("10");  
		startDateMinutes.add("11");  
		startDateMinutes.add("12");  
		startDateMinutes.add("13");  
		startDateMinutes.add("14");  
		startDateMinutes.add("15");  
		startDateMinutes.add("16");  
		startDateMinutes.add("17");  
		startDateMinutes.add("18");  
		startDateMinutes.add("19");  
		startDateMinutes.add("20"); 
		startDateMinutes.add("21"); 
		startDateMinutes.add("22"); 
		startDateMinutes.add("23"); 
		startDateMinutes.add("24");
		startDateMinutes.add("25");
		startDateMinutes.add("26");
		startDateMinutes.add("27");
		startDateMinutes.add("28");
		startDateMinutes.add("29");
		startDateMinutes.add("30");
		startDateMinutes.add("31"); 
		startDateMinutes.add("32"); 
		startDateMinutes.add("33"); 
		startDateMinutes.add("34");
		startDateMinutes.add("35");
		startDateMinutes.add("36");
		startDateMinutes.add("37");
		startDateMinutes.add("38");
		startDateMinutes.add("39");
		startDateMinutes.add("40");
		startDateMinutes.add("41"); 
		startDateMinutes.add("42"); 
		startDateMinutes.add("43"); 
		startDateMinutes.add("44");
		startDateMinutes.add("45");
		startDateMinutes.add("46");
		startDateMinutes.add("47");
		startDateMinutes.add("48");
		startDateMinutes.add("49");
		startDateMinutes.add("50");
		startDateMinutes.add("51"); 
		startDateMinutes.add("52"); 
		startDateMinutes.add("53"); 
		startDateMinutes.add("54");
		startDateMinutes.add("55");
		startDateMinutes.add("56");
		startDateMinutes.add("57");
		startDateMinutes.add("58");
		startDateMinutes.add("59");
		frame.getContentPane().add(startDateMinutes);
		frame.getContentPane().add(endDateHours);
		
		Choice endDateMinutes = new Choice();
		endDateMinutes.setBounds(242, 146, 48, 22);
		endDateMinutes.add("00");  
		endDateMinutes.add("01");  
		endDateMinutes.add("02");  
		endDateMinutes.add("03");  
		endDateMinutes.add("04");  
		endDateMinutes.add("05");  
		endDateMinutes.add("06");  
		endDateMinutes.add("07");  
		endDateMinutes.add("08"); 
		endDateMinutes.add("09");  
		endDateMinutes.add("10");  
		endDateMinutes.add("11");  
		endDateMinutes.add("12");  
		endDateMinutes.add("13");  
		endDateMinutes.add("14");  
		endDateMinutes.add("15");  
		endDateMinutes.add("16");  
		endDateMinutes.add("17");  
		endDateMinutes.add("18");  
		endDateMinutes.add("19");  
		endDateMinutes.add("20"); 
		endDateMinutes.add("21"); 
		endDateMinutes.add("22"); 
		endDateMinutes.add("23"); 
		endDateMinutes.add("24");
		endDateMinutes.add("25");
		endDateMinutes.add("26");
		endDateMinutes.add("27");
		endDateMinutes.add("28");
		endDateMinutes.add("29");
		endDateMinutes.add("30");
		endDateMinutes.add("31"); 
		endDateMinutes.add("32"); 
		endDateMinutes.add("33"); 
		endDateMinutes.add("34");
		endDateMinutes.add("35");
		endDateMinutes.add("36");
		endDateMinutes.add("37");
		endDateMinutes.add("38");
		endDateMinutes.add("39");
		endDateMinutes.add("40");
		endDateMinutes.add("41"); 
		endDateMinutes.add("42"); 
		endDateMinutes.add("43"); 
		endDateMinutes.add("44");
		endDateMinutes.add("45");
		endDateMinutes.add("46");
		endDateMinutes.add("47");
		endDateMinutes.add("48");
		endDateMinutes.add("49");
		endDateMinutes.add("50");
		endDateMinutes.add("51"); 
		endDateMinutes.add("52"); 
		endDateMinutes.add("53"); 
		endDateMinutes.add("54");
		endDateMinutes.add("55");
		endDateMinutes.add("56");
		endDateMinutes.add("57");
		endDateMinutes.add("58");
		endDateMinutes.add("59");
		frame.getContentPane().add(endDateMinutes);
	}
}
