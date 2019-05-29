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

public class Events {

    private DayView dayView;
    private DataService dService;
    private static JFrame frame;
    private static Timestamp date;
    //	private DayView dayView = null;
    DefaultTableModel model = null;


    /**
     * Launch the application.
     * Nalezy odkomentowac konstruktor aby moc uruchomic okienko bezposrednio
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
//					Events window = new Events();
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
    private Events() {
        dService = DataService.getInstance();
        initialize();
    }


    public Events(Timestamp date, DefaultTableModel model, DayView dw) {

        Events.date = date;
        this.model = model;
        this.dayView=dw;
        dService = DataService.getInstance();

        initialize();
//		this.frame.setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

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
        Label label = new Label("Nowe wydarzenie:");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Dialog", Font.BOLD, 17));
        label.setBounds(65, 10, 155, 24);
        frame.getContentPane().add(label);
        //</editor-fold>

        //<editor-fold desc="Button Dodaj">
        Button button = new Button("DODAJ");

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

        JTextArea Place = new JTextArea();
        Place.setBounds(94, 83, 196, 16);
        frame.getContentPane().add(Place);

        Choice startDateHours = new Choice();
        startDateHours.setBounds(172, 117, 48, 22);
        for (int i = 0; i < 24; i++)
            if (i <= 9) {
                startDateHours.add("0" + String.format("%d", i));
            } else
                startDateHours.add(String.format("%d", i));

        frame.getContentPane().add(startDateHours);

        Choice endDateHours = new Choice();
        endDateHours.setBounds(172, 146, 48, 22);

        for (int i = 0; i < 24; i++)
            if (i <= 9) {
                endDateHours.add("0" + String.format("%d", i));
            } else
                endDateHours.add(String.format("%d", i));


        Choice startDateMinutes = new Choice();
        startDateMinutes.setBounds(242, 117, 48, 22);
        for (int i = 0; i < 60; i++)
            if (i <= 9) {
                startDateMinutes.add("0" + String.format("%d", i));
            } else
                startDateMinutes.add(String.format("%d", i));
//
        frame.getContentPane().add(startDateMinutes);
        frame.getContentPane().add(endDateHours);

        Choice endDateMinutes = new Choice();
        endDateMinutes.setBounds(242, 146, 48, 22);
        for (int i = 0; i < 60; i++)
            if (i <= 9) {
                endDateMinutes.add("0" + String.format("%d", i));
            } else
                endDateMinutes.add(String.format("%d", i));

//
        frame.getContentPane().add(endDateMinutes);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EventBuilder builder = new EventBuilder();
                builder.setId("");
                builder.setTitle(Title.getText());
                builder.setDescription(Place.getText());
                //builder.setStartDate(dService.StringToTimestampWithTime(scanner.nextLine()));
                //builder.setEndDate(dService.StringToTimestampWithTime(scanner.nextLine()));
//				System.out.println(startDateHours.getSelectedItem());
//				System.out.println(startDateMinutes.getSelectedItem());
//				System.out.println(endDateHours.getSelectedItem());
//				System.out.println(endDateMinutes.getSelectedItem());
                String time = startDateHours.getSelectedItem() + ":" + startDateMinutes.getSelectedItem() + ":00";
                builder.setStartDate(Timestamp.valueOf(date.toString().substring(0, 10) + " " + time));
                time = endDateHours.getSelectedItem() + ":" + endDateMinutes.getSelectedItem() + ":00";
                builder.setEndDate(Timestamp.valueOf(date.toString().substring(0, 10) + " " + time));
                try {
                    dService.addEvent(builder.createEvent());
                    dayView.showEvents();
                    frame.dispose();
                    System.out.println("Dodano wydarzenie: "+builder.toString());
                } catch (idException | dataException ex) {

                    System.err.println(ex.toString());
                    JOptionPane.showMessageDialog(null,ex.getMessage(),"Wystapil blad przy tworzeniu eventu",JOptionPane.ERROR_MESSAGE);
                }
//                dayView.showEvents();
//                frame.dispose();



            }
        });

    }

//    public void showEvents() {
//        System.out.println("setRowcount");
//        DefaultTableModel model = this.model;
//        System.out.println("setRowcount");
//        model.setRowCount(0);
//        System.out.println("setRowcount");
////		String SDay = Integer.toString(DayView.day);
////		if (DayView.day>=1 && DayView.day<=9) SDay = "0"+SDay;
////		String SMonth = Integer.toString(DayView.month);
////		if (DayView.month>=1 && DayView.month<=9) SMonth = "0"+SMonth;
////		String query = SDay+"/"+SMonth+"/"+DayView.year;
////		System.out.println(query);
//////		dService.GetAllEventsForDate(Timestamp.valueOf(query));
//////		System.out.println(dService.toString());
//
//        for (Event event : dService.GetAllEventsForDate(date)) {
//            String data0 = String.valueOf(event.getId());
//            String data1 = new SimpleDateFormat("dd-MM-yyyy").format(event.getStartDate());
//            String data2 = new SimpleDateFormat("HH:mm").format(event.getStartDate());
//            String data3 = new SimpleDateFormat("HH:mm").format(event.getEndDate());
//            String data4 = event.getTitle();
//            String data5 = event.getPlace();
//            Object[] row = {data0, data1, data2, data3, data4, data5};
//            model.addRow(row);
//        }
//    }
}
