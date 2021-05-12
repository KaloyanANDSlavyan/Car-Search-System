package cssystem.backend;

import cssystem.backend.models.Color;
import cssystem.backend.models.Type;

import java.util.*;

public class DataSource {

    private Map<String, Map<String, Set<String>>> description = new HashMap<>();
    private static DataSource dataSource;

    private final String[] colors = {
            "red", "green", "blue", "white",
            "black", "orange", "yellow"
    };

    public DataSource() {
        // Adding types
        description.put("Car", new HashMap<>());
        description.put("Motorcycle", new HashMap<>());
        description.put("Truck", new HashMap<>());

        // Adding brands
        description.get("Car").put("Nissan", new HashSet<>());
        description.get("Car").put("Audi", new HashSet<>());
        description.get("Car").put("Lamborghini", new HashSet<>());
        description.get("Car").put("Honda", new HashSet<>());
        description.get("Car").put("Mitsubishi", new HashSet<>());

        description.get("Motorcycle").put("Yamaha", new HashSet<>());
        description.get("Motorcycle").put("BMW", new HashSet<>());

        description.get("Truck").put("Daf", new HashSet<>());
        description.get("Truck").put("Scania", new HashSet<>());

        // Adding models to every brand
        //Cars
        List<String> list = Arrays.asList("350z", "GT-R", "Micra", "Skyline");
        description.get("Car").get("Nissan").addAll(list);

        list = Arrays.asList("A3", "A4", "Q8", "S8");
        description.get("Car").get("Audi").addAll(list);

        list = Arrays.asList("Murcielago", "Aventador", "Gallardo", "Urus");
        description.get("Car").get("Lamborghini").addAll(list);

        list = Arrays.asList("Civic", "Accord", "CR-V", "Legend");
        description.get("Car").get("Honda").addAll(list);

        list = Arrays.asList("Colt", "Lancer", "Eclipse", "Galant");
        description.get("Car").get("Mitsubishi").addAll(list);

        //Motorcycles
        list = Arrays.asList("R", "C");
        description.get("Motorcycle").get("BMW").addAll(list);

        list = Arrays.asList("YZF", "FZ6");
        description.get("Motorcycle").get("Yamaha").addAll(list);

        //Trucks
        list = Arrays.asList("CF", "95");
        description.get("Truck").get("Daf").addAll(list);

        list = Arrays.asList("124", "112");
        description.get("Truck").get("Scania").addAll(list);
    }

    public static DataSource getInstance(){
        if(dataSource == null)
            dataSource = new DataSource();
        return dataSource;
    }

    public Map<String, Map<String, Set<String>>> getDescription() {
        return description;
    }

    public String[] getColors() {
        return colors;
    }
}
