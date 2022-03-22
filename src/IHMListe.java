import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class IHMListe {
    private JTable table;
    private JPanel panel;

    public IHMListe() {

        Map<Integer, Map<String, String>> data = getEmployees();
        EmployeeTableModel model = new EmployeeTableModel();
        model.setModel(data);
        table.setModel(model);

        JFrame frame = new JFrame("Mon IHM");


        Dimension d = new Dimension(300, 300);
        frame.setSize(d);

        frame.setContentPane(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


    public Map<Integer, Map<String, String>> getEmployees()
    {
        Connection conn = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        Map<Integer, Map<String, String>> data = new HashMap<>();
        int i = 0;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM employees");
            while (rs.next()) {
                Map<String, String> item = new HashMap<>();
                item.put("first_name", rs.getString("first_name"));
                item.put("last_name", rs.getString("last_name"));
                data.put(i, item);
                i++;
            }
            // Now do something with the ResultSet ....
        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return data;
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
        IHMListe ihmListe = new IHMListe();
    }
}
