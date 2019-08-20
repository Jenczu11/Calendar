package GUITest;

import javax.swing.*;

class Wydarzenie {
    private JPanel root;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JCheckBox przypomnienieCheckBox;
    private JButton zapiszButton;
    private JComboBox comboBox3;
    private JComboBox comboBox4;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Wydarzenie");
        frame.setContentPane(new Wydarzenie().root);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
