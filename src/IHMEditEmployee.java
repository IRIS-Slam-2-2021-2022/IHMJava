import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class IHMEditEmployee {
    private Map<String, String> data;
    private JPanel mainPanel;

    public IHMEditEmployee()
    {
        data = new HashMap<String, String>();
    }

    public IHMEditEmployee(Map<String, String> pData)
    {
        data = pData;
    }

    public Map<String, String> getData()
    {
        return data;
    }

    public void setData(Map<String, String> pData)
    {
        data = pData;
    }

    public JPanel getMainPanel()
    {
        return mainPanel;
    }
}
