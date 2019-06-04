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
	 * Initialize the contents of the frame.
	 */
	/**
	 * Utworzenie okna z dwoma przyciskami, po ktorych kliknieciu ukazuja sie okna z dodatkowymi infomacjami
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 303, 149);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JButton bJencz = new JButton("Bart\u0142omiej Jencz");
		bJencz.setForeground(new Color(255, 215, 0));
		bJencz.setBackground(new Color(25, 25, 112));
		bJencz.setBounds(0, 38, 138, 59);
		bJencz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 JOptionPane.showMessageDialog(frame, "NR INDEKSU: 216783\nADRES EMAIL: 216783@edu.p.lodz.pl");
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(bJencz);
		
		JButton jWlodarczyk = new JButton("Julita W\u0142odarczyk");
		jWlodarczyk.setForeground(new Color(255, 215, 0));
		jWlodarczyk.setBackground(new Color(25, 25, 112));
		jWlodarczyk.setBounds(148, 38, 141, 59);
		jWlodarczyk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "NR INDEKSU: 216921 \nADRES EMAIL: 216921@edu.p.lodz.pl");
			}
		});
		frame.getContentPane().add(jWlodarczyk);
		
		JLabel lblNewLabel = new JLabel("O nas!");
		lblNewLabel.setForeground(new Color(105, 105, 105));
		lblNewLabel.setBounds(0, 5, 289, 23);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel);
	}

}
