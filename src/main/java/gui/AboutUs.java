package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 303, 149);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JButton bJencz = new JButton("Bart\u0142omiej Jencz");
		bJencz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 JOptionPane.showMessageDialog(frame, "NR INDEKSU: 216783\nADRES EMAIL: 216783@edu.p.lodz.pl");
			}
		});
		frame.getContentPane().add(bJencz, BorderLayout.WEST);
		
		JButton jWlodarczyk = new JButton("Julita W\u0142odarczyk");
		jWlodarczyk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "NR INDEKSU: 216921 \nADRES EMAIL: 216921@edu.p.lodz.pl");
			}
		});
		frame.getContentPane().add(jWlodarczyk, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("O nas!");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel, BorderLayout.NORTH);
	}

}
