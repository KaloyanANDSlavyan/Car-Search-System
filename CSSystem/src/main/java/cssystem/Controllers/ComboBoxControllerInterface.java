package cssystem.Controllers;

import java.util.List;
import java.util.Set;

public interface ComboBoxControllerInterface {
    public void initLoader();
    public void isComboBoxEmpty(String indicator);
    public void fillBrandComboBox(Set<String> brandNameSet);
    public void fillModelComboBox(List<String> modelNameSet);
}
