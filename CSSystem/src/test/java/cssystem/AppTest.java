package cssystem;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import cssystem.backend.CSSystem;
import cssystem.backend.Configuration;
import cssystem.backend.models.Auto;
import cssystem.backend.services.ValidationService;
import org.junit.Ignore;
import org.junit.Test;

import java.util.*;


public class AppTest 
{
    @Ignore
    @Test
    public void addAutoTest(){
        Configuration configuration = Configuration.getInstance();
        configuration.configure();

        CSSystem csSystem = CSSystem.getInstance();
        Map<String, String> elements = new HashMap<>();

        elements.put("type", "Truck");
        elements.put("brand", "Nissan");
        elements.put("model", "Atleon");
        elements.put("color", "black");
        elements.put("ownerName", "Kaloyan");
        elements.put("phone", "0876200350");
        elements.put("price", "5000");
        elements.put("description", "sadf");
        elements.put("kilometers", "140300");
        elements.put("horsePower", "100");

        ValidationService validationService = ValidationService.getInstance();

        if(validationService.allDataFilled(elements)){
            if (validationService.isDataNumeric(elements)){
                Auto auto = csSystem.createAuto(elements);
                Map<String, Set<String>> cons = csSystem.validateAuto(auto);

                if (cons.isEmpty()) {
                    csSystem.saveAutoInDB(auto);
                } else {
                    Set<Map.Entry<String, Set<String>>> entries = cons.entrySet();

                    for (Map.Entry<String, Set<String>> entry : entries) {
                        for (String constraint : entry.getValue()) {
                            System.out.println(entry.getKey() + ": " + constraint);
                        }
                        System.out.println("\n");
                    }
                    fail();
                }
            } else{
                System.out.println("Invalid data input.");
                fail();
            }
        }else {
            System.out.println("Please fill all data");
            fail();
        }
    }

    @Test
    public void searchAutoTest(){
        Configuration configuration = Configuration.getInstance();
        configuration.configure();

        CSSystem csSystem = CSSystem.getInstance();
        Map<String, String> elements = new HashMap<>();

        elements.put("type", "");
        elements.put("brand", "");
        elements.put("model", "");
        elements.put("color", "");
        elements.put("minPower", "");
        elements.put("maxPower", "");
        elements.put("minPrice", "10000");
        elements.put("maxPrice", "");
        elements.put("minKilos", "");
        elements.put("maxKilos", "");

        List<Auto> foundAutos = csSystem.searchAutos(elements);

        if(!foundAutos.isEmpty()) {
            for (Auto auto : foundAutos) {
                Long id = auto.getID();
                String type = auto.getDescription().getType().getName();
                String brand = auto.getDescription().getBrand().getName();
                String model = auto.getDescription().getModel();
                String color = auto.getColor().getName();
                int power = auto.getHorsePower();
                double price = auto.getPrice();
                int kilometres = auto.getKilometres();

                System.out.println(id + ". " + type + " | " + brand
                        + " | " + model + " | " + color + " | " + "Power: "
                        + power + " | " + "Price: " + price + " | " + "Kilos: " + kilometres);
            }
        } else {
            System.out.println("\nAutos not found.");
            fail();
        }
    }
}
