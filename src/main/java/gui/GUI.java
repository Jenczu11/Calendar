package gui;

import com.toedter.calendar.JCalendar;
import data.Event;
import service.DataService;
import service.SQLHandler;
import service.XMLHandler;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;

public class GUI {

    private JFrame frame;

    private Timer timer;
    /**
     * Liczba milisekund, po ktorych nastepuje sprawdzanie czy jakies zdarzenia wymagaja zaalarmowania
     */
    private static final int TIMER_DELAY = 60000;
    /**
     * Glowny DataService
     */
    public DataService dataService;

    /**
     * Launch the application.
     */
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
        //<editor-fold desc="Init frame with calendar panel">
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JCalendar calendar = new JCalendar();
        frame.getContentPane().add(calendar, BorderLayout.CENTER);
        calendar.getDayChooser().setAlwaysFireDayProperty(true);
        //</editor-fold>

        //<editor-fold desc="Add MenuBar">
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu mnFile = new JMenu("File");
        menuBar.add(mnFile);
        JMenu mnXml = new JMenu("XML");
        mnFile.add(mnXml);
        JMenu mnSql = new JMenu("SQL");
        mnFile.add(mnSql);
        //</editor-fold>

        //<editor-fold desc="SaveToXML button">
        JMenuItem mntmSaveToXml = new JMenuItem("Save to XML");
        mntmSaveToXml.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    dataService.saveRepository(new XMLHandler("XMLtest.xml"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println("Zapisano do XML");
            }
        });
        mnXml.add(mntmSaveToXml);
        //</editor-fold>

        //<editor-fold desc="LoadToXML button">
        JMenuItem mntmLoadFromXml = new JMenuItem("Load from XML");
        mntmLoadFromXml.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    dataService.loadRepository(new XMLHandler("XMLtest.xml"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println("Zaladowane z XML");
            }
        });
        mnXml.add(mntmLoadFromXml);
        //</editor-fold>

        //<editor-fold desc="SaveToSQL button">
        JMenuItem mntmSaveToSql = new JMenuItem("Save to SQL");
        mntmSaveToSql.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    dataService.saveRepository(new SQLHandler());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println("Zapisano do SQL");
            }
        });
        mnSql.add(mntmSaveToSql);
        //</editor-fold>

        //<editor-fold desc="LoadFromSQL button">
        JMenuItem mntmLoadFromSql = new JMenuItem("Load from SQL");
        mntmLoadFromSql.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    dataService.loadRepository(new SQLHandler());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println("Zaladowano z SQL");
            }
        });
        mnSql.add(mntmLoadFromSql);
        //</editor-fold>

        //<editor-fold desc="AboutUs Button">
        JMenu mnAboutus = new JMenu("About Us");
        mnAboutus.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AboutUs aboutUs = new AboutUs();
                //TODO: mozna zmienic konstruktor zeby odpalal
                aboutUs.main(null);
            }
        });
        menuBar.add(mnAboutus);
        //</editor-fold>

        //<editor-fold desc="Settings button">
        JMenu mnSettings = new JMenu("Settings");
        mnSettings.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {


            }
        });
        menuBar.add(mnSettings);
        //</editor-fold>

        //<editor-fold desc="ColorPicker Button">
        JMenuItem mntmColorpicker = new JMenuItem("ColorPicker");
        mntmColorpicker.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Another ColorPicker
                //Color choosenColor=JColorChooser.showDialog(null,"Wybor koloru",Color.GRAY);

                //<editor-fold desc="ColorPickerSetup only HSB">
                JColorChooser cc = new JColorChooser();
                AbstractColorChooserPanel[] panels = cc.getChooserPanels();
                JPanel p = new JPanel();
                panels[1].setBorder(
                        new TitledBorder(panels[1].getDisplayName()));
                p.add(panels[1]);
                JPanel gui = new JPanel(new BorderLayout(2, 2));
                gui.add(p, BorderLayout.CENTER);
                JOptionPane.showMessageDialog(null, gui);
                //</editor-fold>
                Color choosenColor = cc.getColor();
                calendar.getDayChooser().getDayPanel().setBackground(choosenColor.brighter());

            }
        });
        mnSettings.add(mntmColorpicker);
        //</editor-fold>

        //<editor-fold desc="When day pressed open DayView">
        calendar.getDayChooser().addPropertyChangeListener("day", new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                int day = (int) evt.getNewValue();
                int month = calendar.getMonthChooser().getMonth() + 1;
                int year = calendar.getYearChooser().getYear();
                DayView window = new DayView(day, month, year);
                DayView.main(null);
//				System.out.println(calendar.getMonthChooser().getMonth()+1);
//				System.out.println(day);
//				System.out.println(year);
            }
        });
        //</editor-fold>

    }

    private void checkEvents() {
        ArrayList<Event> events = dataService.getAllEvents();
        for (Event event : events) {
            long dif = event.getStartDate().getTime() - new Date().getTime();
            if (dif < 1800000 && dif > 0 && event.isAlarm() == false) {
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
