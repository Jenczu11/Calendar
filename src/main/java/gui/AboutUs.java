package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * Klasa wyswietlajaca informacje o autorach (po kliknieciu przycisku z nazwiskiem pojawiaja sie
 * infomacje o nr indeksu i adresie mailowym)
 *
 */

public class AboutUs {

	private JFrame frame;

	/**
	 * Launch the application.
	 * @param args set to null
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AboutUs window = new AboutUs();
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
	public AboutUs() {
		initialize();
	}

	/**
	 * Utworzenie okna z dwoma przyciskami, po ktorych kliknieciu ukazuja sie okna z dodatkowymi infomacjami
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 323, 184);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JButton bJencz = new JButton("Bart\u0142omiej Jencz");
		bJencz.setBounds(10, 38, 138, 59);
		bJencz.setForeground(new Color(255, 215, 0));
		bJencz.setBackground(new Color(25, 25, 112));
		bJencz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 JOptionPane.showMessageDialog(frame, "NR INDEKSU: 216783\nADRES EMAIL: 216783@edu.p.lodz.pl");
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(bJencz);
		
		JButton jWlodarczyk = new JButton("Julita W\u0142odarczyk");
		jWlodarczyk.setBounds(157, 38, 141, 59);
		jWlodarczyk.setForeground(new Color(255, 215, 0));
		jWlodarczyk.setBackground(new Color(25, 25, 112));
		jWlodarczyk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "NR INDEKSU: 216921 \nADRES EMAIL: 216921@edu.p.lodz.pl");
			}
		});
		frame.getContentPane().add(jWlodarczyk);
		
		JLabel lblNewLabel = new JLabel("O autorach i programie!");
		lblNewLabel.setBounds(9, 10, 289, 23);
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Informacje o programie");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "WERSJA PROGRAMU: 1.0");
			}
		});
		btnNewButton.setForeground(new Color(25, 25, 112));
		btnNewButton.setBackground(new Color(255, 215, 0));
		btnNewButton.setBounds(65, 116, 164, 21);
		frame.getContentPane().add(btnNewButton);
		
	}
}
