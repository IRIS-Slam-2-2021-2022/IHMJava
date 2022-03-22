import javax.swing.table.AbstractTableModel;
import java.util.HashMap;
import java.util.Map;

public class EmployeeTableModel extends AbstractTableModel {

    private String[] columnNames = {"first_name", "last_name"};

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
    public Object getValueAt(int i, int i1) {
        return data.get(i).get(columnNames[i1]);
    }
}
