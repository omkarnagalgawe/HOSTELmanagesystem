import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Attend extends JFrame implements ActionListener {
    private JLabel l1, l2, l3, l4;
    private JTextField t1, t2, t3, t4;
    private JButton b1, b2;
    private Connection con;

    public Attend() {
        super("Mark Attendance");
        setup();
        addWindowListener(new WindowEventHandler());
        setVisible(true);
        establishConnection();
    }

    public void setup() {
        setLayout(null);

        l1 = new JLabel("Mark Attendance");
        l1.setFont(new Font("Monotype Corsiva", Font.ITALIC, 25));
        l1.setBounds(250, 100, 400, 25);
        l1.setForeground(Color.RED);

        l2 = new JLabel("Student ID");
        l2.setBounds(150, 150, 100, 25);
        t1 = new JTextField();
        t1.setBounds(250, 150, 150, 25);

        l3 = new JLabel("Total Days");
        l3.setBounds(150, 200, 100, 25);
        t2 = new JTextField();
        t2.setBounds(250, 200, 150, 25);

        l4 = new JLabel("Present Days");
        l4.setBounds(150, 250, 100, 25);
        t3 = new JTextField();
        t3.setBounds(250, 250, 150, 25);

        b1 = new JButton("Mark Attendance");
        b1.setBounds(200, 300, 150, 25);
        b2 = new JButton("Cancel");
        b2.setBounds(370, 300, 150, 25);

        add(l1);
        add(l2);
        add(t1);
        add(l3);
        add(t2);
        add(l4);
        add(t3);
        add(b1);
        add(b2);

        setBackground(Color.CYAN);
        setSize(800, 600);

        b1.addActionListener(this);
        b2.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();

        if (action.equals("Mark Attendance")) {
            markAttendance();
        } else if (action.equals("Cancel")) {
            dispose();
            new Hostel();
        }
    }

    private void establishConnection() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hostel", "root", "1234");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to connect to the database: " + e.getMessage());
        }
    }

    private void markAttendance() {
        String studentId = t1.getText();
        int totalDays = Integer.parseInt(t2.getText());
        int presentDays = Integer.parseInt(t3.getText());
        int absentDays = totalDays - presentDays;

        try {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO attend (student_id, total_days, present_days) VALUES ( ?, ?, ?)");
            stmt.setString(1, studentId);
            stmt.setInt(2, totalDays);
            stmt.setInt(3, presentDays);
           // stmt.setInt(4, absentDays);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Attendance marked for Student ID: " + studentId
                        + "\nTotal Days: " + totalDays
                        + "\nPresent Days: " + presentDays
                        + "\nAbsent Days: " + absentDays);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to mark attendance. No rows were affected.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to mark attendance: " + e.getMessage());
        }
    }

    class WindowEventHandler extends WindowAdapter {
        public void windowClosing(WindowEvent we) {
            dispose();
            new Hostel();
        }
    }
}
