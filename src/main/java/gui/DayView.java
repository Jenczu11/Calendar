package gui;

import data.Event;
import service.DataService;
import data.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 *
 * Okno wyswietlajace tabelke, w ktorej zawarte sa informacje o wydarzeniach,
 * ktore sa zaplanowane na dany dzien
 *
 */
public class DayView {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Timer timer;
	private static final int TIMER_DELAY = 1000;
	private JFrame frame;
	private static DataService dService;
	private static int day;
	private static int month;
	private static int year;
	private static Timestamp date;
	private DefaultTableModel model;
	private JTable table;
	/**
	 * Launch the application.
	 * @param args set to null
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(() -> {
			try {
				DayView window = new DayView();
				Utils.pInfo("DayView: "+day+" "+month+" "+year);
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the application
	 * @param day day of the Calendar to pass
	 * @param month month of the Calendar to pass
	 * @param year year of the Calendar to pass
	 *
	 */
	public DayView(int day, int month, int year) {

		DayView.day =day;
		DayView.month =month;
		DayView.year =year;

	}
	/**
	 * Launch the application.
	 * @wbp.parser.entryPoint
	 */
	private DayView()
	{
		dService=DataService.getInstance();
		String SDay = Integer.toString(DayView.day);
		if (DayView.day>=1 && DayView.day<=9) SDay = "0"+SDay;
		String SMonth = Integer.toString(DayView.month);
		if (DayView.month>=1 && DayView.month<=9) SMonth = "0"+SMonth;
		String query = SDay+"/"+SMonth+"/"+ DayView.year;
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
				Utils.pInfo("Okno DayView zamkniete");
			}

			@Override
			public void windowActivated(WindowEvent e) {
				super.windowActivated(e);
				Utils.pDebug(e.toString());
			}
		});
		frame.setBounds(100, 100, 563, 417);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		//</editor-fold>

		//<editor-fold desc="JPanel ktory zawiera btnAddEvent">
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(169, 169, 169));
		springLayout.putConstraint(SpringLayout.NORTH, panel_1, 328, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel_1, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel_1, -9, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel_1, 547, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(panel_1);
		//</editor-fold>

		JButton btnAddEvent = new JButton("Dodaj Wydarzenie");
		btnAddEvent.setForeground(new Color(255, 215, 0));
		btnAddEvent.setBackground(new Color(25, 25, 112));
		panel_1.add(btnAddEvent);

		//<editor-fold desc="lblMain: Wydarzenia z dnia rrrr-mm-dd">
		JLabel lblMain = new JLabel("Wydarzenia z dnia: " + date.toString().substring(0, 10));
		springLayout.putConstraint(SpringLayout.NORTH, lblMain, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblMain, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblMain, -294, SpringLayout.NORTH, panel_1);
		springLayout.putConstraint(SpringLayout.EAST, lblMain, -22, SpringLayout.EAST, frame.getContentPane());
		lblMain.setForeground(new Color(25, 25, 112));
		lblMain.setFont(new Font("Tahoma", Font.BOLD, 32));
		frame.getContentPane().add(lblMain);

		table = new JTable();
		final String[] column_names = {
				"ID", "Data", "Rozpoczecie", "Zakonczenie", "Wydarzenie", "Miejsce"
		};
		model = new DefaultTableModel(column_names ,0)
		{
			@Override
			public boolean isCellEditable(int row, int column) {
				//all cells false
				return false;
			}
		};
		table.setModel(model);


		//<editor-fold desc="Table setWidthForColumns">
		table.getColumnModel().getColumn(0).setPreferredWidth(11);
		table.getColumnModel().getColumn(1).setPreferredWidth(45);
		table.getColumnModel().getColumn(2).setPreferredWidth(25);
		table.getColumnModel().getColumn(3).setPreferredWidth(25);
		table.getColumnModel().getColumn(4).setPreferredWidth(77);
		table.getColumnModel().getColumn(5).setPreferredWidth(120);
		//</editor-fold>
		table.setFillsViewportHeight(true);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent mouseEvent) {
				super.mousePressed(mouseEvent);
				JTable target=(JTable)mouseEvent.getSource();
				int row = target.getSelectedRow();
				int column = target.getSelectedColumn();
//				System.out.println("row = " + row);
//				System.out.println("column = " + column);
//				System.out.println(target.getValueAt(row,0));
//				EditEvent editEvent = new EditEvent(Integer.parseInt(target.getValueAt(row,0).toString()),date,(DefaultTableModel) table.getModel(),DayView.this);
//				editEvent.main(null);
				EditEvents editEvents;
				if(target.getSelectedRow()>=0 && target.getSelectedColumn() >=0) {
					editEvents = new EditEvents(Integer.parseInt(target.getValueAt(row, 0).toString()), date, (DefaultTableModel) table.getModel(), DayView.this);
					EditEvents.main(null);
					showEvents();
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(table);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 6, SpringLayout.SOUTH, lblMain);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, lblMain);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -18, SpringLayout.NORTH, panel_1);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -21, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(scrollPane);



		btnAddEvent.addActionListener(e -> {
			addEvent addEvent = new addEvent(date,(DefaultTableModel) table.getModel(), DayView.this);
			addEvent.main(null);
			showEvents();
		});
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				System.out.println(e);
			}
		});

	}

	void showEvents() {
		model.setRowCount(0);
//		String SDay = Integere.toString(DayView.day);
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

