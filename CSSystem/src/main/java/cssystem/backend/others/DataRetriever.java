package cssystem.backend.others;

import cssystem.Controllers.AddAutomobileController;
import cssystem.Controllers.ControllerInterface;
import cssystem.Controllers.SearchAutomobileController;

import java.util.Map;

public class DataRetriever {

    private static DataRetriever dataRetriever;

    public static DataRetriever getInstance(){
        if(dataRetriever == null)
            dataRetriever = new DataRetriever();
        return dataRetriever;
    }

    public void gatherDataFromController(ControllerInterface controller, Map<String, String> elements){

        elements.put("type", controller.getTypeComboBox().getValue());
        elements.put("brand", controller.getBrandComboBox().getValue());
        elements.put("model", controller.getModelComboBox().getValue());
        elements.put("color", controller.getColorComboBox().getValue());

        if(controller instanceof SearchAutomobileController){
            SearchAutomobileController searchController = (SearchAutomobileController) controller;

            elements.put("minPower", searchController.getMinHorsepowerField().getText().trim());
            elements.put("maxPower", searchController.getMaxHorsepowerField().getText().trim());
            elements.put("minPrice", searchController.getMinPriceField().getText().trim());
            elements.put("maxPrice", searchController.getMaxPriceField().getText().trim());
            elements.put("minKilos", searchController.getMinKilometersField().getText().trim());
            elements.put("maxKilos", searchController.getMaxKilometersField().getText().trim());
        }
        else {
            AddAutomobileController addController = (AddAutomobileController) controller;

            elements.put("ownerName", addController.getNameField().getText().trim());
            elements.put("phone", addController.getPhoneField().getText().trim());
            elements.put("description", addController.getDescriptionArea().getText().trim());
            elements.put("price", addController.getPriceField().getText().trim());
            elements.put("kilometers", addController.getKilometersField().getText().trim());
            elements.put("horsePower", addController.getHorsepowerField().getText().trim());
        }
    }
}
