import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class IHMJava {
    private JTextField txtName;
    private JPanel panel;
    private JButton btnSend;

    public IHMJava() {
        JFrame frame = new JFrame("Mon IHM");

        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(frame,
                        "Je m'appelle " + txtName.getText());
            }
        });

        Dimension minDim = new Dimension(200, 200);
        frame.setMinimumSize(minDim);

        Dimension maxDim = new Dimension(400, 400);
        frame.setMaximumSize(maxDim);

        Dimension d = new Dimension(300, 300);
        frame.setSize(d);
        frame.setPreferredSize(d);

        frame.setContentPane(panel);

        insertJobs();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void insertJobs()
    {
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement("INSERT INTO jobs(job_title, min_salary, max_salary) VALUES (?, ?, ?)");
            stmt.setString(1, "Développeur");
            stmt.setFloat(2, 65000.0f);
            stmt.setFloat(3, 85000.0f);

            stmt.execute();
            // Now do something with the ResultSet ....
        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public void getEmployeesFirstname()
    {
        Connection conn = getConnection();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM employees");
            while (rs.next()) {
                String firstName = rs.getString("first_name");
                System.out.println(firstName);
            }

            // Now do something with the ResultSet ....
        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public Connection getConnection()
    {
        Connection conn = null;
        try {
            conn =
                    DriverManager.getConnection("jdbc:mysql://localhost:33066/employee?" +
                            "user=iris&password=iris");
            // Do something with the Connection
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return conn;
    }

    public static void main(String[] args)
    {
        IHMJava ihm = new IHMJava();
    }
}
