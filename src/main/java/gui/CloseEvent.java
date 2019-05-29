package gui;

import data.Event;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

/**
 *
 * Klasa reprezentujaca wydarzenia, kt�re musza zosta� zaalarmowane
 */
public class CloseEvent extends JDialog {

    private static final long serialVersionUID = -2327877112256512582L;
    private final JPanel contentPanel = new JPanel();
    private JButton okButton;
    private JTextField titleField;
    private JTextField desciptionField;
    private JTextField startTimeField;
    private JTextField endTimeField;
    public Timer timer;
    /**
     * Zmienna odpowiadajaca za czas po jakim ma wystapi� sygna� dzwi�kowy
     */
    private static final int TIMER_DELAY = 1000;

    /**
     * Tworzy okno dialogowe alarmujace o nadchodzacym wydarzeniu
     * @param event Wydarzenie
     */
    public CloseEvent(Event event) {
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        Toolkit.getDefaultToolkit().beep();
        timer = new Timer(TIMER_DELAY, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Toolkit.getDefaultToolkit().beep();
            }
        });
        timer.start();
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        SpringLayout sl_contentPanel = new SpringLayout();
        contentPanel.setLayout(sl_contentPanel);

        JLabel lblNadchodzceWydarzenie = new JLabel("Nadchodz\u0105ce wydarzenie!");
        sl_contentPanel.putConstraint(SpringLayout.NORTH, lblNadchodzceWydarzenie, 10, SpringLayout.NORTH, contentPanel);
        sl_contentPanel.putConstraint(SpringLayout.EAST, lblNadchodzceWydarzenie, -82, SpringLayout.EAST, contentPanel);
        lblNadchodzceWydarzenie.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        contentPanel.add(lblNadchodzceWydarzenie);

        JLabel lblTitle = new JLabel("Tytu\u0142:");
        sl_contentPanel.putConstraint(SpringLayout.NORTH, lblTitle, 18, SpringLayout.SOUTH, lblNadchodzceWydarzenie);
        sl_contentPanel.putConstraint(SpringLayout.WEST, lblTitle, 35, SpringLayout.WEST, contentPanel);
        contentPanel.add(lblTitle);

        JLabel lblDescription = new JLabel("Opis:");
        sl_contentPanel.putConstraint(SpringLayout.WEST, lblDescription, 0, SpringLayout.WEST, lblTitle);
        contentPanel.add(lblDescription);

        JLabel lblStartTime = new JLabel("Godzina rozpocz\u0119cia:");
        sl_contentPanel.putConstraint(SpringLayout.WEST, lblStartTime, 0, SpringLayout.WEST, lblTitle);
        contentPanel.add(lblStartTime);

        JLabel lblEndTime = new JLabel("Godzina zako\u0144czenia:");
        sl_contentPanel.putConstraint(SpringLayout.WEST, lblEndTime, 0, SpringLayout.WEST, lblTitle);
        contentPanel.add(lblEndTime);

        titleField = new JTextField();
        titleField.setEditable(false);
        sl_contentPanel.putConstraint(SpringLayout.SOUTH, titleField, 0, SpringLayout.SOUTH, lblTitle);
        sl_contentPanel.putConstraint(SpringLayout.EAST, titleField, -117, SpringLayout.EAST, contentPanel);
        titleField.setText(event.getTitle());
        contentPanel.add(titleField);
        titleField.setColumns(10);

        desciptionField = new JTextField();
        desciptionField.setEditable(false);
        sl_contentPanel.putConstraint(SpringLayout.NORTH, desciptionField, 5, SpringLayout.SOUTH, titleField);
        sl_contentPanel.putConstraint(SpringLayout.NORTH, lblDescription, 3, SpringLayout.NORTH, desciptionField);
        sl_contentPanel.putConstraint(SpringLayout.EAST, desciptionField, 0, SpringLayout.EAST, titleField);
        desciptionField.setText(event.getPlace());
        contentPanel.add(desciptionField);
        desciptionField.setColumns(10);

        startTimeField = new JTextField();
        startTimeField.setEditable(false);
        sl_contentPanel.putConstraint(SpringLayout.NORTH, lblStartTime, 3, SpringLayout.NORTH, startTimeField);
        sl_contentPanel.putConstraint(SpringLayout.NORTH, startTimeField, 6, SpringLayout.SOUTH, desciptionField);
        sl_contentPanel.putConstraint(SpringLayout.WEST, startTimeField, 0, SpringLayout.WEST, titleField);
        startTimeField.setText(new SimpleDateFormat("HH:mm").format(event.getStartDate()));
        contentPanel.add(startTimeField);
        startTimeField.setColumns(10);

        endTimeField = new JTextField();
        sl_contentPanel.putConstraint(SpringLayout.NORTH, endTimeField, 13, SpringLayout.SOUTH, startTimeField);
        sl_contentPanel.putConstraint(SpringLayout.NORTH, lblEndTime, 3, SpringLayout.NORTH, endTimeField);
        endTimeField.setEditable(false);
        sl_contentPanel.putConstraint(SpringLayout.WEST, endTimeField, 0, SpringLayout.WEST, titleField);
        endTimeField.setText(new SimpleDateFormat("HH:mm").format(event.getEndDate()));
        contentPanel.add(endTimeField);
        endTimeField.setColumns(10);
        {
            JPanel buttonPane = new JPanel();
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                okButton = new JButton("OK");
                okButton.setActionCommand("OK");
                getRootPane().setDefaultButton(okButton);
            }
            buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
            buttonPane.add(okButton);
        }
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                timer.stop();
                dispose();
            }
        });
    }

    public void addConfirmListener(ActionListener actionListener) {
        okButton.addActionListener(actionListener);
    }
}
