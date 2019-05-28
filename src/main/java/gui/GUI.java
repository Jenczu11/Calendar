package gui;

import com.toedter.calendar.JCalendar;
import data.Event;
import service.DataService;
import service.SQLHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;

public class GUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	private Timer timer;
	/**
	 * Liczba milisekund, po ktorych nastepuje sprawdzanie czy jakies zdarzenia wymagaja zaalarmowania
	 */
	private static final int TIMER_DELAY = 60000;
	public DataService dataService;
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
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
	public GUI() {
		initialize();
		dataService = DataService.getInstance();
		try {
			dataService.loadRepository(new SQLHandler());
		} catch (Exception e) {
			e.printStackTrace();
		}
		timer = new Timer(TIMER_DELAY, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkEvents();
			}
		});
		timer.start();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JCalendar calendar = new JCalendar();
		frame.getContentPane().add(calendar, BorderLayout.CENTER);

		        calendar.getDayChooser().setAlwaysFireDayProperty(true);
		        calendar.getDayChooser().addPropertyChangeListener("day",new PropertyChangeListener() {
		                    public void propertyChange(PropertyChangeEvent evt) {
								int day = (int) evt.getNewValue();
								int month =calendar.getMonthChooser().getMonth()+1;
								int year = calendar.getYearChooser().getYear();
		                                DayView window = new DayView(day,month,year);
		                                DayView.main(null);
//								System.out.println(calendar.getMonthChooser().getMonth()+1);
//								System.out.println(day);
//								System.out.println(year);
		                    }
		        });
		    
	}

	private void checkEvents() {
		ArrayList<Event> events=dataService.getAllEvents();;

		for (Event event : events) {
			long dif=event.getStartDate().getTime()-new Date().getTime();

			if(dif<1800000 && dif>0 && event.isAlarm()==false) {
				event.setAlarm(true);
				try {
					CloseEvent dialog = new CloseEvent(event);
					dialog.setTitle("Nadchodzace wydarzenie!");
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					dialog.addConfirmListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							dialog.timer.stop();
							dialog.dispose();
						}
					});
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Wystapil blad", JOptionPane.ERROR_MESSAGE);

				}
			}
		}
	}

}
