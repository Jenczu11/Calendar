package gui;

import com.toedter.calendar.JDayChooser;
import service.DataService;
import service.TimestampUtil;
import service.Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class DeleteEvents extends JFrame implements Runnable {

	int month;
	int year;
	private JPanel contentPane;
	private DataService dService;

	/**
	 * Launch the application.
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
//		DeleteEvents frame = new DeleteEvents(month,year);
//		frame.setVisible(true);
		Utils.pInfo("Otwarto okno DeleteEvents");
		this.setVisible(true);
	}
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public DeleteEvents(int monthPassed,int yearPassed) {
		dService = DataService.getInstance();
		this.month=monthPassed;
		this.year=yearPassed;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JButton btnUsunWydarzenia = new JButton("Usun wydarzenia do tej daty");

		contentPane.add(btnUsunWydarzenia, BorderLayout.SOUTH);
		
		JLabel lblUsunWydarzeniaDo = new JLabel("Usun wydarzenia do tej daty");
		contentPane.add(lblUsunWydarzeniaDo, BorderLayout.NORTH);
		
		JDayChooser dayChooser = new JDayChooser();
		dayChooser.setMonth(month);
		dayChooser.setYear(year);
		contentPane.add(dayChooser, BorderLayout.CENTER);

		btnUsunWydarzenia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				dService.removeEventsToDate()
//			Utils.pDebug(String.valueOf(dayChooser.getDay()));

				try {
					// Plus 1 bo Jcalendar numeruje od zera
					dService.removeEventsToDate(TimestampUtil.intsToTimestamp(dayChooser.getDay(),month+1,year));
//					Utils.pDebug(TimestampUtil.intsToTimestamp(dayChooser.getDay(),month+1,year).toString());
					JOptionPane.showMessageDialog(contentPane, new StringBuilder("Pomyslnie usunieto wszystkie wydarzenia do daty" +dayChooser.getDay()+"/"+(month+1)+"/"+year));
					dispose();
					Utils.pInfo("Pomyslnie usunieto wydarzenia");
					Utils.pInfo("Zamknieto okno DeleteEvents");
				} catch (Exception ex) {
//					ex.printStackTrace();
					JOptionPane.showMessageDialog(contentPane, new StringBuilder("Brak eventow do usuniecia"));
					System.err.println(ex.getMessage());
					dispose();
					Utils.pInfo("Zamknieto okno DeleteEvents");

				}

			}

		});
	}

	

}
