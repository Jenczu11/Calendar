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

/**
 * 
 * Klasa pozwalajaca na usuniecie wydarzenia z danej daty 
 *
 */
public class DeleteEvents extends JFrame implements Runnable {

	private static int month;
	private static int year;
	private final JPanel contentPane;
	private final DataService dService;

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
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteEvents de = new DeleteEvents(month,year);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param monthPassed month to pass from JCalendar
	 * @param yearPassed year to pass from JCalendar
	 */
	public DeleteEvents(int monthPassed,int yearPassed) {
		dService = DataService.getInstance();
		month=monthPassed;
		year=yearPassed;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JButton btnUsunWydarzenia = new JButton("Usun wydarzenia do tej daty");
		btnUsunWydarzenia.setForeground(new Color(255, 215, 0));
		btnUsunWydarzenia.setBackground(new Color(25, 25, 112));

		contentPane.add(btnUsunWydarzenia, BorderLayout.SOUTH);
		
		JLabel lblUsunWydarzeniaDo = new JLabel("Usun wydarzenia do tej daty");
		lblUsunWydarzeniaDo.setForeground(new Color(139, 0, 0));
		lblUsunWydarzeniaDo.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsunWydarzeniaDo.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblUsunWydarzeniaDo, BorderLayout.NORTH);
		
		JDayChooser dayChooser = new JDayChooser();
		dayChooser.getDayPanel().setBackground(new Color(255, 255, 255));
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
