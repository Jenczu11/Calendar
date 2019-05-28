package gui;

import data.Event;
import service.DataService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class DayView {

	private JFrame frame;
	private JTable table;
	private DataService dService;
	private static int day;
	private static int month;
	private static int year;
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
		initialize();
		showEvents();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 563, 417);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JPanel panel_1 = new JPanel();
		springLayout.putConstraint(SpringLayout.WEST, panel_1, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel_1, 547, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(panel_1);
		
		JButton btnAddEvent = new JButton("Dodaj Wydarzenie");
		btnAddEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Events events = new Events();
				Events.main(null);
			}
		});
		panel_1.add(btnAddEvent);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"ID", "Data", "Godzina roz.", "Godzina zak.", "Wydarzenie", "Opis"
				}
				));
		table.getColumnModel().getColumn(0).setPreferredWidth(45);
		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(2).setPreferredWidth(82);
		table.getColumnModel().getColumn(3).setPreferredWidth(82);
		table.getColumnModel().getColumn(4).setPreferredWidth(77);
		table.getColumnModel().getColumn(5).setPreferredWidth(120);
		table.setFillsViewportHeight(true);
		springLayout.putConstraint(SpringLayout.NORTH, table, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, table, -48, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, panel_1, 6, SpringLayout.SOUTH, table);
		springLayout.putConstraint(SpringLayout.SOUTH, panel_1, 39, SpringLayout.SOUTH, table);
		springLayout.putConstraint(SpringLayout.WEST, table, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, table, 537, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(table);
	}
	private void showEvents() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		String SDay = Integer.toString(DayView.day);
		if (DayView.day>=1 && DayView.day<=9) SDay = "0"+SDay;
		String SMonth = Integer.toString(DayView.month);
		if (DayView.month>=1 && DayView.month<=9) SMonth = "0"+SMonth;
		String query = SDay+"/"+SMonth+"/"+DayView.year;
		System.out.println(query);
//		dService.GetAllEventsForDate(Timestamp.valueOf(query));
//		System.out.println(dService.toString());
		for (Event event : dService.GetAllEventsForDate(dService.StringToTimestamp(query))) {
			String data0 = String.valueOf(event.getId());
			String data1 = new SimpleDateFormat("dd-MM-yyyy").format(event.getStartDate());
			String data2 = new SimpleDateFormat("HH:mm").format(event.getStartDate());
			String data3 = new SimpleDateFormat("HH:mm").format(event.getEndDate());
			String data4 = event.getTitle();
			String data5 = event.getDescription();
			Object[] row = {data0, data1, data2, data3, data4, data5 };
			model.addRow(row);
		}
	}
}
