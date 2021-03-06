package cssystem.backend.others;

import cssystem.backend.models.Auto;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;

public class DataTransfer {       // Singleton class
    private static DataTransfer dataTransfer;
    private List<Auto> autoList = new ArrayList<>();
    private Auto auto;
    private boolean userIndicator;
    private AnchorPane loaderPane;

    public static DataTransfer getInstance() {
        if(dataTransfer == null)
            dataTransfer = new DataTransfer();

        return dataTransfer;
    }

    public void addAutoList(List<Auto> autos) {
        autoList.addAll(autos);
    }

    public List<Auto> getAutoList() {
        return autoList;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public boolean isUserIndicator() {
        return userIndicator;
    }

    public void setUserIndicator(boolean userIndicator) {
        this.userIndicator = userIndicator;
    }

    public AnchorPane getLoaderPane() {
        return loaderPane;
    }

    public void setLoaderPane(AnchorPane loaderPane) {
        this.loaderPane = loaderPane;
    }

    @Override
    public String toString() {
        return "FoundAutoRetriever{" +
                "autoList=" + autoList +
                '}';
    }
}
