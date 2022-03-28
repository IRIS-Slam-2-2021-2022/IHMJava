import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class EmployeeTableColumnModel extends DefaultTableColumnModel {
    private String[] headerColumnNames = {
            "Prénom",
            "Nom",
            "Job"
    };


    public EmployeeTableColumnModel()
    {
        for ( int i = 0; i < headerColumnNames.length; i++ ) {
            TableColumn col = new TableColumn();
            col.setHeaderValue(headerColumnNames[i]);
            this.addColumn(col);
        }
    }
}
