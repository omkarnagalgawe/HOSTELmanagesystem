import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Fee extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4;
    JTextField tf1, tf2, tf3, tf4;
    JButton btnSave, btnCancel;

    // Database connection details
    String url = "jdbc:mysql://localhost/hostel";
    String username = "root";
    String password = "1234";

    public Fee() {
        super("Fee Details");
        setup();
        addWindowListener(new WindowEventHandler());
        setVisible(true);
    }

    public void setup() {
        setLayout(null);
        l1 = new JLabel("Student ID:");
        l1.setBounds(50, 100, 100, 25);

        tf1 = new JTextField();
        tf1.setBounds(150, 100, 150, 25);

        l2 = new JLabel("Total Amount:");
        l2.setBounds(50, 150, 100, 25);

        tf2 = new JTextField();
        tf2.setBounds(150, 150, 150, 25);

        l3 = new JLabel("Balance:");
        l3.setBounds(50, 200, 100, 25);

        tf3 = new JTextField();
        tf3.setBounds(150, 200, 150, 25);

        l4 = new JLabel("Date:");
        l4.setBounds(50, 250, 100, 25);

        tf4 = new JTextField();
        tf4.setBounds(150, 250, 150, 25);

        btnSave = new JButton("Save");
        btnSave.setBounds(100, 300, 100, 25);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(220, 300, 100, 25);

        add(l1);
        add(tf1);
        add(l2);
        add(tf2);
        add(l3);
        add(tf3);
        add(l4);
        add(tf4);
        add(btnSave);
        add(btnCancel);

        setBackground(Color.cyan);
        setForeground(Color.black);

        btnSave.addActionListener(this);
        btnCancel.addActionListener(this);

        setSize(400, 400);
    }

    public void actionPerformed(ActionEvent ae) {
        String sst = ae.getActionCommand();
        if (sst.equals("Save")) {
            String studentId = tf1.getText();
            String totalAmount = tf2.getText();
            String balance = tf3.getText();
            String date = tf4.getText();

            // Save the fee details using the provided information
            saveFeeDetails(studentId, totalAmount, balance, date);

            // Clear the text fields after saving
            tf1.setText("");
            tf2.setText("");
            tf3.setText("");
            tf4.setText("");
        } else if (sst.equals("Cancel")) {
            dispose();
            Hostel hostel = new Hostel();
        }
    }

    private void saveFeeDetails(String studentId, String totalAmount, String balance, String date) {
        try {
            // Establish the database connection
            Connection connection = DriverManager.getConnection(url, username, password);

            // Create the SQL query to insert fee details into the table
            String query = "INSERT INTO fee (student_id, total_amount, balance, date) VALUES (?, ?, ?, ?)";

            // Create the prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Set the parameter values
            preparedStatement.setString(1, studentId);
            preparedStatement.setString(2, totalAmount);
            preparedStatement.setString(3, balance);
            preparedStatement.setString(4, date);


            // Execute the query
            preparedStatement.executeUpdate();

            // Close the statement and connection
            preparedStatement.close();
            connection.close();

            System.out.println("Fee details saved successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    class WindowEventHandler extends WindowAdapter {
        public void windowClosing(WindowEvent we) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        Fee fee = new Fee();
    }
}