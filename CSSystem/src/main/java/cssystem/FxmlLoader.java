package cssystem;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.net.URL;

public class FxmlLoader {
    private AnchorPane anchorPane;

    public AnchorPane getAnchorPane(String fxmlName) {

        try {
            URL fileUrl = getClass().getResource("/FXMLFiles/" + fxmlName + ".fxml");
            if (fileUrl == null)
                    throw new java.io.FileNotFoundException("FXML file can't be found!");

            anchorPane = new FXMLLoader().load(fileUrl);

        }catch (Exception e) {
            e.printStackTrace();
        }

        return anchorPane;
    }
}
