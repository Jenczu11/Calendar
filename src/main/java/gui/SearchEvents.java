package gui;

import data.Event;
import service.DataService;
import service.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


/**
 * 
 * Klasa umozliwiajaca wyszukanie danego wydarzenia sposrod listy wydarzen
 *
 */

public class SearchEvents {

	private Timer timer;
	private static final int TIMER_DELAY = 1000;
	private JFrame frame;
	private static JTable table;
	private static DataService dService;
	private static int day;
	private static int month;
	private static int year;
	private static Timestamp date;
	private JTextField searchField;
	private ArrayList<Event> searched = new ArrayList<Event>();
	/**
	 * Launch the application.
	 * @param args set to null
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(() -> {
			try {
				SearchEvents window = new SearchEvents();
//					System.out.println("DayView:"+day+" "+month+" "+year);
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SearchEvents()
	{
		dService=DataService.getInstance();
		initialize();
		showEvents();
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
				Utils.pInfo("SearchEvents zamkniete");
			}
		});
		frame.setBounds(100, 100, 563, 417);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		//</editor-fold>

		//<editor-fold desc="JPanel ktory zawiera btnAddEvent">
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(192, 192, 192));
		springLayout.putConstraint(SpringLayout.NORTH, panel_1, 328, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel_1, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel_1, -9, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel_1, 547, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(panel_1);
		
		searchField = new JTextField();
		searchField.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void keyTyped(KeyEvent e) {

				if(searchField.getText().isEmpty()) {
//					System.out.println(searchField.getText().isEmpty());
					searched.removeAll(searched);
					showEvents();
				}
				if(!searchField.getText().isEmpty())
				{

				searched = (ArrayList) dService.search(searchField.getText());
				showEvents();
				}
			}
			@SuppressWarnings("unchecked")
			@Override
			public void keyReleased(KeyEvent e) {

				if(searchField.getText().isEmpty()) {
//					System.out.println(searchField.getText().isEmpty());
					searched.removeAll(searched);
					showEvents();
				}
				if(!searchField.getText().isEmpty())
				{
					Utils.pInfo("Szukam wedlug wzorca: "+searchField.getText());
//					System.out.println("szukam");
					searched = (ArrayList) dService.search(searchField.getText());
					showEvents();
				}
			}
		});

		panel_1.add(searchField);
		searchField.setColumns(40);

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
				)
					   {

						   @Override
						   public boolean isCellEditable(int row, int column) {
							   //all cells false
							   return false;
						   }
					   }
		);
		//<editor-fold desc="Table setWidthForColumns">
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(45);
		table.getColumnModel().getColumn(2).setPreferredWidth(45);
		table.getColumnModel().getColumn(3).setPreferredWidth(82);
		table.getColumnModel().getColumn(4).setPreferredWidth(77);
		table.getColumnModel().getColumn(5).setPreferredWidth(120);
		//</editor-fold>
		table.setFillsViewportHeight(true);
		table.setCellSelectionEnabled(false);
		frame.getContentPane().add(table);


		//<editor-fold desc="lblMain: Wydarzenia z dnia rrrr-mm-dd">
		JLabel lblMain = new JLabel("Szukaj wydarzeń:");
		lblMain.setForeground(new Color(25, 25, 112));
		lblMain.setHorizontalAlignment(SwingConstants.CENTER);
		springLayout.putConstraint(SpringLayout.NORTH, lblMain, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblMain, 0, SpringLayout.WEST, table);
		springLayout.putConstraint(SpringLayout.SOUTH, lblMain, -6, SpringLayout.NORTH, table);
		springLayout.putConstraint(SpringLayout.EAST, lblMain, 539, SpringLayout.WEST, frame.getContentPane());
		lblMain.setFont(new Font("Tahoma", Font.BOLD, 33));
		frame.getContentPane().add(lblMain);
		//</editor-fold>
//		table.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mousePressed(MouseEvent mouseEvent) {
//				super.mousePressed(mouseEvent);
//				JTable target=(JTable)mouseEvent.getSource();
//				int row = target.getSelectedRow();
//				int column = target.getSelectedColumn();
//				System.out.println("row = " + row);
//				System.out.println("column = " + column);
//				System.out.println(target.getValueAt(row,0));
////				EditEvent editEvent = new EditEvent(Integer.parseInt(target.getValueAt(row,0).toString()),date,(DefaultTableModel) table.getModel(),DayView.this);
////				editEvent.main(null);
//				EditEventsV2 editEventsV2 = new EditEventsV2(Integer.parseInt(target.getValueAt(row,0).toString()),date,(DefaultTableModel) table.getModel(),SearchEvents.this);
//				editEventsV2.main(null);
//			}
//		});

	
	}
	private void showEvents() {
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
		for (Event event : searched) {
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
