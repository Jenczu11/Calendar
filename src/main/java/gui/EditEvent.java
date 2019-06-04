
package gui;

import data.EventBuilder;
import exceptions.dataException;
import exceptions.idException;
import service.DataService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;

//import net.miginfocom.swing.MigLayout;
/*
Ta klasa powinna byc dziediczna ale to kiedys sie poprawi
 */
@Deprecated
public class EditEvent {

	private static DayView dayView;
	private static DataService dService;
	public static JFrame frame;
	private static Timestamp date;
	public static Button button;
	public static Label label;
	private static int targetID;
	DefaultTableModel model = null;

	/**
	 * Launch the application.
	 * Nalezy odkomentowac konstruktor aby moc uruchomic okienko bezposrednio
	 * @param args set to null
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Events window = new Events();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	@Deprecated
	public EditEvent() {
		dService = DataService.getInstance();
		initialize();
	}


	public EditEvent(int targetID,Timestamp date, DefaultTableModel model, DayView dw) {

		EditEvent.date = date;
		this.targetID=targetID;
		this.model = model;
		this.dayView=dw;
		dService = DataService.getInstance();

		initialize();
//		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public static void initialize() {

		//<editor-fold desc="JFrame init+setup">
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 347, 275);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//</editor-fold>

		//<editor-fold desc="JLabel Nazwa setup">
		JLabel lblNazwa = new JLabel("Nazwa");
		lblNazwa.setForeground(Color.WHITE);
		lblNazwa.setBackground(Color.WHITE);
		lblNazwa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNazwa.setBounds(12, 50, 56, 16);
		frame.getContentPane().add(lblNazwa);
		//</editor-fold>

		//<editor-fold desc="JLabel miejsce setup">
		JLabel lblMiejsce = new JLabel("Miejsce");
		lblMiejsce.setForeground(Color.WHITE);
		lblMiejsce.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMiejsce.setBounds(12, 79, 56, 16);
		frame.getContentPane().add(lblMiejsce);
		//</editor-fold>

		//<editor-fold desc="JLabel godzina rozpoczecia setup">
		JLabel lblGodzinaRozpoczecia = new JLabel("Godzina rozpocz\u0119cia");
		lblGodzinaRozpoczecia.setForeground(Color.WHITE);
		lblGodzinaRozpoczecia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGodzinaRozpoczecia.setBounds(12, 123, 155, 16);
		frame.getContentPane().add(lblGodzinaRozpoczecia);
		//</editor-fold>

		//<editor-fold desc="JLabel Godzina zakonczenie setup">
		JLabel lblGodzinaZakonczenia = new JLabel("Godzina zako\u0144czenia");
		lblGodzinaZakonczenia.setForeground(Color.WHITE);
		lblGodzinaZakonczenia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGodzinaZakonczenia.setBounds(12, 152, 146, 16);
		frame.getContentPane().add(lblGodzinaZakonczenia);
		//</editor-fold>

		//<editor-fold desc="Checkbox Powiadomienie">
		Checkbox checkbox = new Checkbox("Powiadomienie");
		checkbox.setForeground(Color.WHITE);
		checkbox.setBounds(10, 184, 108, 24);
		frame.getContentPane().add(checkbox);
		//</editor-fold>

		//<editor-fold desc="Label wydarzenie">

		label = new Label("Edytuj wydarzenie:");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Dialog", Font.BOLD, 17));
		label.setBounds(65, 10, 155, 24);
		frame.getContentPane().add(label);
		//</editor-fold>

		//<editor-fold desc="Button Dodaj">
		button = new Button("Edytuj");

		button.setFont(new Font("Dialog", Font.BOLD, 14));
		button.setActionCommand("DODAJ");
		button.setBackground(new Color(178, 34, 34));
		button.setForeground(Color.BLACK);
		button.setBounds(170, 184, 120, 24);
		frame.getContentPane().add(button);
		//</editor-fold>

		//<editor-fold desc="JTextArea Tytul">
		JTextArea Title = new JTextArea();
		Title.setBounds(94, 53, 196, 16);
		frame.getContentPane().add(Title);
		//</editor-fold>

		//<editor-fold desc="JTextArea Place">
		JTextArea Place = new JTextArea();
		Place.setBounds(94, 83, 196, 16);
		frame.getContentPane().add(Place);
		//</editor-fold>

		//<editor-fold desc="Choice startDateHours">
		Choice startDateHours = new Choice();
		startDateHours.setBounds(172, 117, 48, 22);
		addToChoiceNumbers(startDateHours,0,24);
		frame.getContentPane().add(startDateHours);
		//</editor-fold>

		//<editor-fold desc="Choice endDateHours">
		Choice endDateHours = new Choice();
		endDateHours.setBounds(172, 146, 48, 22);
		addToChoiceNumbers(endDateHours,0,24);
		frame.getContentPane().add(endDateHours);
		//</editor-fold>

		//<editor-fold desc="Choice startDateMinutes">
		Choice startDateMinutes = new Choice();
		startDateMinutes.setBounds(242, 117, 48, 22);
		addToChoiceNumbers(startDateMinutes,0,60);
		frame.getContentPane().add(startDateMinutes);
		//</editor-fold>

		//<editor-fold desc="Choice endDateMinutes">
		Choice endDateMinutes = new Choice();
		endDateMinutes.setBounds(242, 146, 48, 22);
		addToChoiceNumbers(endDateMinutes,0,60);
		frame.getContentPane().add(endDateMinutes);
		//</editor-fold>

		/**
		 * Action listnery
		 */
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventBuilder builder = new EventBuilder();
				builder.setId(targetID);
				builder.setTitle(Title.getText());
				builder.setPlace(Place.getText());
				String time = startDateHours.getSelectedItem() + ":" + startDateMinutes.getSelectedItem() + ":00";
				builder.setStartDate(Timestamp.valueOf(date.toString().substring(0, 10) + " " + time));
				time = endDateHours.getSelectedItem() + ":" + endDateMinutes.getSelectedItem() + ":00";
				builder.setEndDate(Timestamp.valueOf(date.toString().substring(0, 10) + " " + time));
				try {
					dService.editEvent(builder.createEvent());
					dayView.showEvents();
					frame.dispose();
					System.out.println("Edytowano wydarzenie: "+builder.toString());
				} catch (idException | dataException ex) {

					System.err.println(ex.toString());
					JOptionPane.showMessageDialog(null,ex.getMessage(),"Wystapil blad przy tworzeniu eventu",JOptionPane.ERROR_MESSAGE);
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});
	}

	/**
	 * Dodaje pokolei liczby do choice w formacie %dd
	 * Zakres od 00-99
	 * @param where do jakiego componentu dodac
	 * @param start od jakiego numeru ma tworzyc
	 * @param end do jakiego numeru ma tworzyc
	 */
	static void addToChoiceNumbers(Choice where, int start, int end)
	{
		if(end>100) end=99;
		for (int i = start; i < end; i++)
			if (i <= 9) {
				where.add("0" + String.format("%d", i));
			} else
				where.add(String.format("%d", i));
	}
}
