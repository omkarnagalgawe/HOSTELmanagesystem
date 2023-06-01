import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Refund extends JFrame implements ActionListener {
    // Attributes of the Refund class
    private JTextField studentIdField;
    private JTextField totalAmountField;
    private JTextField balanceField;
    private JTextField fineField;
    private JTextField dateField;
    private JButton saveButton;
    private JButton cancelButton;

    // Database connection properties
    private String url = "jdbc:mysql://localhost/hostel";
    private String username = "root";
    private String password = "1234";

    public Refund() {
        super("Refund");
        setup();
        addWindowListener(new WindowEventHandler());
        setVisible(true);
    }

    public void setup() {
        setLayout(null);

        // Create and position labels, text fields, buttons, etc.
        JLabel studentIdLabel = new JLabel("Student ID:");
        studentIdLabel.setBounds(70, 120, 100, 25);
        studentIdField = new JTextField();
        studentIdField.setBounds(240, 120, 180, 25);

        JLabel totalAmountLabel = new JLabel("Total Amount:");
        totalAmountLabel.setBounds(70, 160, 100, 25);
        totalAmountField = new JTextField();
        totalAmountField.setBounds(240, 160, 180, 25);

        JLabel balanceLabel = new JLabel("Balance:");
        balanceLabel.setBounds(70, 200, 100, 25);
        balanceField = new JTextField();
        balanceField.setBounds(240, 200, 180, 25);

        JLabel fineLabel = new JLabel("Fine:");
        fineLabel.setBounds(70, 240, 100, 25);
        fineField = new JTextField();
        fineField.setBounds(240, 240, 180, 25);

        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setBounds(70, 280, 100, 25);
        dateField = new JTextField();
        dateField.setBounds(240, 280, 180, 25);

        saveButton = new JButton("Save");
        saveButton.setBounds(200, 330, 100, 25);
        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(340, 330, 100, 25);

        // Add components to the frame
        add(studentIdLabel);
        add(studentIdField);
        add(totalAmountLabel);
        add(totalAmountField);
        add(balanceLabel);
        add(balanceField);
        add(fineLabel);
        add(fineField);
        add(dateLabel);
        add(dateField);
        add(saveButton);
        add(cancelButton);

        // Register action listeners for buttons
        saveButton.addActionListener(this);
        cancelButton.addActionListener(this);

        setSize(500, 400);
    }

    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
        if (command.equals("Save")) {
            // Retrieve input values from text fields
            String studentIdText = studentIdField.getText();
            String totalAmountText = totalAmountField.getText();
            String balanceText = balanceField.getText();
            String fineText = fineField.getText();
            String dateText = dateField.getText();

            // Perform validation and processing of input values
            // ...

            // Insert refund information into the database
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                String query = "INSERT INTO refund (student_id, total_amount, balance, fine, date) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, Integer.parseInt(studentIdText));
                statement.setDouble(2, Double.parseDouble(totalAmountText));
                statement.setDouble(3, Double.parseDouble(balanceText));
                statement.setDouble(4, Double.parseDouble(fineText));
                statement.setDate(5, Date.valueOf(dateText));
                statement.executeUpdate();
                statement.close();
                connection.close();

                JOptionPane.showMessageDialog(null, "Refund information saved successfully");
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error occurred while saving refund information");
            }
        } else if (command.equals("Cancel")) {
            dispose();
        }
    }

    // Other methods and functionality specific to the Refund class

    class WindowEventHandler extends WindowAdapter {
        public void windowClosing(WindowEvent we) {
            dispose();
        }
    }

    public static void main(String[] args) {
        new Refund();
    }
}
