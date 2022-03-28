import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class IHMListe {
    private JTable table;
    private JPanel panel;

    public IHMListe() {

        Map<Integer, Map<String, String>> data = getEmployees();
        EmployeeTableModel model = new EmployeeTableModel();
        EmployeeTableColumnModel header = new EmployeeTableColumnModel();

        model.setModel(data);
        table.setModel(model);
        table.setTableHeader(new JTableHeader(header));

        JFrame frame = new JFrame("Mon IHM");


        Dimension d = new Dimension(300, 300);
        frame.setSize(d);

        frame.setContentPane(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int column = table.getSelectedRow();
                Map<String, String> data = model.getRowData(column);

                System.out.println(e.getButton());
                System.out.println(data);

                IHMEditEmployee ihmEditEmployee = new IHMEditEmployee(data);
                JFrame frame = new JFrame(
                        "Edition de l'employee " +
                        data.get("col_first_name") +
                        " " +
                        data.get("col_last_name")
                );

                frame.setContentPane(ihmEditEmployee.getMainPanel());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                frame.setSize(1000,900);

                frame.setVisible(true);
            }
        });
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
            rs = stmt.executeQuery("SELECT first_name, last_name, job_id FROM employees");

            // Méthode alternative
//            for (int j = 0; j < rs.getFetchSize(); j++)
//            {
//                if (rs.absolute(j)) {
//                    rs.getString("first_name");
//                }
//            }
            Map<String, String> item;

            while (rs.next()) {
                item = new HashMap<>();
                item.put("col_first_name", rs.getString("first_name"));
                item.put("col_last_name", rs.getString("last_name"));
                item.put("col_job_id", rs.getString("job_id"));
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
            conn = DriverManager.getConnection("jdbc:mysql://localhost:33066/employee?" +
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
