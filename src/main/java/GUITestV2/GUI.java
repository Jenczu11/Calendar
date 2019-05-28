package GUITestV2;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;

import javax.swing.*;

public class GUI {
    private JPanel root;
    private JDateChooser JDateChooser1;
    private JMonthChooser Jmonth;
//    private JMenuBar JMenuBar;

    /*
        Jezeli cos z Jcalendar jest jako
        Error:forms: GUITestV2\GUI.form: Form contains components with Custom Create option but no createUIComponents() method
        nalezy stworzyc metode publiczna i wywolac konstruktory na elementy
         */
    public void createUIComponents()
    {
        JDateChooser1 = new JDateChooser();
//        JMenuBar = new JMenuBar();

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI");
        frame.setContentPane(new GUI().root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        JMenuBar JMenuBar = new JMenuBar();
        frame.setJMenuBar(JMenuBar);

        JMenu mnOptions = new JMenu("Menu");
        JMenuBar.add(mnOptions);

    }
}
