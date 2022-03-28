import javax.swing.table.AbstractTableModel;
import java.util.HashMap;
import java.util.Map;

public class EmployeeTableModel extends AbstractTableModel {

    private String[] columnNames = {"col_first_name", "col_last_name", "col_job_id"};

    private Map<Integer, Map<String, String>> data;

    public EmployeeTableModel() {
        data = new HashMap<Integer, Map<String, String>>();
    }

    public void setModel(Map<Integer, Map<String, String>> model)
    {
        data = model;
    }

    @Override
    public int getRowCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex).get(columnNames[columnIndex]);
    }

    public Map<String, String> getRowData(int rowIndex)
    {
        return data.get(rowIndex);
    }
}
