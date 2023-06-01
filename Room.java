import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Room extends JFrame implements ActionListener {
    JLabel l1, l2, l3;
    JTextField t1, t2, t3;
    JButton b1, b2, b3;

    // Database connection details
    String url = "jdbc:mysql://localhost/hostel";
    String username = "root";
    String password = "1234";

    public Room() {
        super("Room Allotment");
        setup();
        addWindowListener(new WindowEventHandler());
        setVisible(true);
    }

    public void setup() {
        setLayout(null);

        l1 = new JLabel("Student ID");
        l1.setBounds(70, 120, 100, 25);
        t1 = new JTextField();
        t1.setBounds(240, 120, 180, 25);

        l2 = new JLabel("Room No");
        l2.setBounds(70, 160, 120, 25);
        t2 = new JTextField();
        t2.setBounds(240, 160, 200, 25);

        l3 = new JLabel("Floor Number");
        l3.setBounds(70, 200, 140, 25);
        t3 = new JTextField();
        t3.setBounds(240, 200, 200, 25);

        b1 = new JButton("Save");
        b1.setBounds(200, 250, 100, 25);
        b2 = new JButton("Cancel");
        b2.setBounds(340, 250, 100, 25);
        b3 = new JButton("Clear");
        b3.setBounds(480, 250, 100, 25);

        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(l3);
        add(t3);
        add(b1);
        add(b2);
        add(b3);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        setBackground(Color.cyan);
        setSize(800, 600);
    }

    public void actionPerformed(ActionEvent ae) {
        String sst = ae.getActionCommand();
        if (sst.equals("Save")) {
            String studentID = t1.getText();
            String roomNo = t2.getText();
            String floorNo = t3.getText();

            // Save the room details using the provided information
            saveRoomDetails(studentID, roomNo, floorNo);

            // Clear the text fields after saving
            t1.setText("");
            t2.setText("");
            t3.setText("");
        } else if (sst.equals("Cancel")) {
            dispose();
        } else if (sst.equals("Clear")) {
            t1.setText("");
            t2.setText("");
            t3.setText("");
        }
    }

    private void saveRoomDetails(String studentID, String roomNo, String floorNo) {
        try {
            // Establish the database connection
            Connection connection = DriverManager.getConnection(url, username, password);

            // Create the SQL query to insert room details into the table
            String query = "INSERT INTO room (student_id, room_no, floor_no) VALUES (?, ?, ?)";

            // Create the prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Set the parameter values
            preparedStatement.setString(1, studentID);
            preparedStatement.setString(2, roomNo);
            preparedStatement.setString(3, floorNo);

            // Execute the query
            preparedStatement.executeUpdate();

            // Close the statement and connection
            preparedStatement.close();
            connection.close();

            System.out.println("Room details saved successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Room room = new Room();
    }

    class WindowEventHandler extends WindowAdapter {
        public void windowClosing(WindowEvent we) {
            dispose();
        }
    }
}
