package cssystem.backend.others;

import cssystem.backend.models.Auto;

import java.util.ArrayList;
import java.util.List;

public class FoundAutoRetriever {       // Singleton class
    private static FoundAutoRetriever foundAutoRetriever;
    private List<Auto> autoList = new ArrayList<>();
    private Auto auto;

    public static FoundAutoRetriever getInstance() {
        if(foundAutoRetriever == null)
            foundAutoRetriever = new FoundAutoRetriever();

        return foundAutoRetriever;
    }

    public void addAutoList(List<Auto> autos) {
        autoList.addAll(autos);
    }
    public void addAuto(Auto auto){
        autoList.add(auto);
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

    @Override
    public String toString() {
        return "FoundAutoRetriever{" +
                "autoList=" + autoList +
                '}';
    }
}
