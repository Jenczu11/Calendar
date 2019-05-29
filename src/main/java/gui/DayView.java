package gui;

import data.Event;
import service.DataService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DayView {

	private Timer timer;
	private static final int TIMER_DELAY = 1000;
	private JFrame frame;
	private static JTable table;
	private static DataService dService;
	private static int day;
	private static int month;
	private static int year;
	private static Timestamp date;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DayView window = new DayView();
					System.out.println("DayView:"+day+" "+month+" "+year);
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
	public DayView(int day, int month, int year) {

		this.day=day;
		this.month=month;
		this.year=year;

	}
	public DayView()
	{
		dService=DataService.getInstance();
		String SDay = Integer.toString(DayView.day);
		if (DayView.day>=1 && DayView.day<=9) SDay = "0"+SDay;
		String SMonth = Integer.toString(DayView.month);
		if (DayView.month>=1 && DayView.month<=9) SMonth = "0"+SMonth;
		String query = SDay+"/"+SMonth+"/"+DayView.year;
		DayView.date=dService.StringToTimestamp(query);
		initialize();
		showEvents();
		//Aktualnie timer mozna wylaczyc
//		timer = new Timer(TIMER_DELAY, new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				showEvents();
//			}
//		});
//		timer.start();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//<editor-fold desc="frame INIT">
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				System.out.println("Okno DayView zamkniete");
			}
		});
		frame.setBounds(100, 100, 563, 417);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		//</editor-fold>

		//<editor-fold desc="JPanel ktory zawiera btnAddEvent">
		JPanel panel_1 = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel_1, 328, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel_1, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel_1, -9, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel_1, 547, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(panel_1);
		//</editor-fold>
		
		JButton btnAddEvent = new JButton("Dodaj Wydarzenie");
		panel_1.add(btnAddEvent);

		//<editor-fold desc="Table init">
		table = new JTable();
		springLayout.putConstraint(SpringLayout.NORTH, table, 40, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, table, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, table, -6, SpringLayout.NORTH, panel_1);
		springLayout.putConstraint(SpringLayout.EAST, table, -8, SpringLayout.EAST, frame.getContentPane());
		//</editor-fold>
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"ID", "Data", "Godzina roz.", "Godzina zak.", "Wydarzenie", "Opis"
				}
				));
		//<editor-fold desc="Table setWidthForColumns">
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(45);
		table.getColumnModel().getColumn(2).setPreferredWidth(45);
		table.getColumnModel().getColumn(3).setPreferredWidth(82);
		table.getColumnModel().getColumn(4).setPreferredWidth(77);
		table.getColumnModel().getColumn(5).setPreferredWidth(120);
		//</editor-fold>
		table.setFillsViewportHeight(true);
		frame.getContentPane().add(table);

		//<editor-fold desc="lblMain: Wydarzenia z dnia rrrr-mm-dd">
		JLabel lblMain = new JLabel("Wydarzenia z dnia: " + date.toString().substring(0, 10));
		springLayout.putConstraint(SpringLayout.NORTH, lblMain, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblMain, 0, SpringLayout.WEST, table);
		springLayout.putConstraint(SpringLayout.SOUTH, lblMain, -6, SpringLayout.NORTH, table);
		springLayout.putConstraint(SpringLayout.EAST, lblMain, 472, SpringLayout.WEST, frame.getContentPane());
		lblMain.setFont(new Font("Trebuchet MS", Font.PLAIN, 33));
		frame.getContentPane().add(lblMain);
		//</editor-fold>

		btnAddEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Events events = new Events(date,(DefaultTableModel) table.getModel(),DayView.this);
				Events.main(null);
			}
		});
	}
	public void showEvents() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
//		String SDay = Integer.toString(DayView.day);
//		if (DayView.day>=1 && DayView.day<=9) SDay = "0"+SDay;
//		String SMonth = Integer.toString(DayView.month);
//		if (DayView.month>=1 && DayView.month<=9) SMonth = "0"+SMonth;
//		String query = SDay+"/"+SMonth+"/"+DayView.year;
//		System.out.println(query);
////		dService.GetAllEventsForDate(Timestamp.valueOf(query));
////		System.out.println(dService.toString());
		for (Event event : dService.GetAllEventsForDate(date)) {
			String data0 = String.valueOf(event.getId());
			String data1 = new SimpleDateFormat("dd-MM-yyyy").format(event.getStartDate());
			String data2 = new SimpleDateFormat("HH:mm").format(event.getStartDate());
			String data3 = new SimpleDateFormat("HH:mm").format(event.getEndDate());
			String data4 = event.getTitle();
			String data5 = event.getPlace();
			Object[] row = {data0, data1, data2, data3, data4, data5 };
			model.addRow(row);
		}
	}
}
