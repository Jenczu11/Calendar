package gui;

import javax.swing.*;
import java.awt.*;

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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		Choice choice_1 = new Choice();
		choice_1.setBounds(242, 117, 48, 22);
		choice_1.add("00");  
		choice_1.add("01");  
		choice_1.add("02");  
		choice_1.add("03");  
		choice_1.add("04");  
		choice_1.add("05");  
		choice_1.add("06");  
		choice_1.add("07");  
		choice_1.add("08"); 
		choice_1.add("09");  
		choice_1.add("10");  
		choice_1.add("11");  
		choice_1.add("12");  
		choice_1.add("13");  
		choice_1.add("14");  
		choice_1.add("15");  
		choice_1.add("16");  
		choice_1.add("17");  
		choice_1.add("18");  
		choice_1.add("19");  
		choice_1.add("20"); 
		choice_1.add("21"); 
		choice_1.add("22"); 
		choice_1.add("23"); 
		choice_1.add("24");
		choice_1.add("25");
		choice_1.add("26");
		choice_1.add("27");
		choice_1.add("28");
		choice_1.add("29");
		choice_1.add("30");
		choice_1.add("31"); 
		choice_1.add("32"); 
		choice_1.add("33"); 
		choice_1.add("34");
		choice_1.add("35");
		choice_1.add("36");
		choice_1.add("37");
		choice_1.add("38");
		choice_1.add("39");
		choice_1.add("40");
		choice_1.add("41"); 
		choice_1.add("42"); 
		choice_1.add("43"); 
		choice_1.add("44");
		choice_1.add("45");
		choice_1.add("46");
		choice_1.add("47");
		choice_1.add("48");
		choice_1.add("49");
		choice_1.add("50");
		choice_1.add("51"); 
		choice_1.add("52"); 
		choice_1.add("53"); 
		choice_1.add("54");
		choice_1.add("55");
		choice_1.add("56");
		choice_1.add("57");
		choice_1.add("58");
		choice_1.add("59");
		frame.getContentPane().add(choice_1);
		
		Button button = new Button("DODAJ");
		button.setFont(new Font("Dialog", Font.BOLD, 14));
		button.setActionCommand("DODAJ");
		button.setBackground(new Color(178, 34, 34));
		button.setForeground(Color.BLACK);
		button.setBounds(170, 184, 120, 24);
		frame.getContentPane().add(button);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(94, 83, 196, 16);
		frame.getContentPane().add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(94, 53, 196, 16);
		frame.getContentPane().add(textArea_1);
		
		Choice choice = new Choice();
		choice.setBounds(172, 117, 48, 22);
		choice.add("00");  
		choice.add("01");  
		choice.add("02");  
		choice.add("03");  
		choice.add("04");  
		choice.add("05");  
		choice.add("06");  
		choice.add("07");  
		choice.add("08"); 
		choice.add("09");  
		choice.add("10");  
		choice.add("11");  
		choice.add("12");  
		choice.add("13");  
		choice.add("14");  
		choice.add("15");  
		choice.add("16");  
		choice.add("17");  
		choice.add("18");  
		choice.add("19");  
		choice.add("20"); 
		choice.add("21"); 
		choice.add("22"); 
		choice.add("23"); 
		frame.getContentPane().add(choice);
		
		Choice choice_2 = new Choice();
		choice_2.setBounds(172, 146, 48, 22);
		choice_2.add("00");  
		choice_2.add("01");  
		choice_2.add("02");  
		choice_2.add("03");  
		choice_2.add("04");  
		choice_2.add("05");  
		choice_2.add("06");  
		choice_2.add("07");  
		choice_2.add("08"); 
		choice_2.add("09");  
		choice_2.add("10");  
		choice_2.add("11");  
		choice_2.add("12");  
		choice_2.add("13");  
		choice_2.add("14");  
		choice_2.add("15");  
		choice_2.add("16");  
		choice_2.add("17");  
		choice_2.add("18");  
		choice_2.add("19");  
		choice_2.add("20"); 
		choice_2.add("21"); 
		choice_2.add("22"); 
		choice_2.add("23"); 
		frame.getContentPane().add(choice_2);
		
		Choice choice_3 = new Choice();
		choice_3.setBounds(242, 146, 48, 22);
		choice_3.add("00");  
		choice_3.add("01");  
		choice_3.add("02");  
		choice_3.add("03");  
		choice_3.add("04");  
		choice_3.add("05");  
		choice_3.add("06");  
		choice_3.add("07");  
		choice_3.add("08"); 
		choice_3.add("09");  
		choice_3.add("10");  
		choice_3.add("11");  
		choice_3.add("12");  
		choice_3.add("13");  
		choice_3.add("14");  
		choice_3.add("15");  
		choice_3.add("16");  
		choice_3.add("17");  
		choice_3.add("18");  
		choice_3.add("19");  
		choice_3.add("20"); 
		choice_3.add("21"); 
		choice_3.add("22"); 
		choice_3.add("23"); 
		choice_3.add("24");
		choice_3.add("25");
		choice_3.add("26");
		choice_3.add("27");
		choice_3.add("28");
		choice_3.add("29");
		choice_3.add("30");
		choice_3.add("31"); 
		choice_3.add("32"); 
		choice_3.add("33"); 
		choice_3.add("34");
		choice_3.add("35");
		choice_3.add("36");
		choice_3.add("37");
		choice_3.add("38");
		choice_3.add("39");
		choice_3.add("40");
		choice_3.add("41"); 
		choice_3.add("42"); 
		choice_3.add("43"); 
		choice_3.add("44");
		choice_3.add("45");
		choice_3.add("46");
		choice_3.add("47");
		choice_3.add("48");
		choice_3.add("49");
		choice_3.add("50");
		choice_3.add("51"); 
		choice_3.add("52"); 
		choice_3.add("53"); 
		choice_3.add("54");
		choice_3.add("55");
		choice_3.add("56");
		choice_3.add("57");
		choice_3.add("58");
		choice_3.add("59");
		frame.getContentPane().add(choice_3);
	}
}
