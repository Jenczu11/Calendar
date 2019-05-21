package GUITest;

import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GUI extends JFrame {
    private JPanel rootPanel;
    private JCalendar calendar;
    private JButton aboutUSButton;

    public GUI() {
        initAction();
        aboutUSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AboutUs aboutUs = new AboutUs();
                aboutUs.main(null);
            }
        });
    }

    public void createUIComponents() {
        calendar = new JCalendar();
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI");
        frame.setContentPane(new GUI().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }

    private void initAction()
    {
        calendar.getDayChooser().setAlwaysFireDayProperty(true);
        calendar.getDayChooser().addPropertyChangeListener("day",new PropertyChangeListener() {
                    public void propertyChange(PropertyChangeEvent evt) {
                                Wydarzenie window = new Wydarzenie();
                                window.main(null);
                    };});
    }

    //    public GUI()
//    {
//        add(rootPanel);
//        setTitle("Title");
//        setSize(400,600);
//    }
}
