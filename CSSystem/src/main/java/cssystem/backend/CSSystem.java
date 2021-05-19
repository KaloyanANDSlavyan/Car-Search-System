package cssystem.backend;

import cssystem.Controllers.AbstractController;
import cssystem.backend.dao.DAO;
import cssystem.backend.dao.MainDAO;
import cssystem.backend.models.*;
import cssystem.backend.services.ValidationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;

public class CSSystem {

    private static CSSystem csSystem;
    private final Logger LOGGER = LogManager.getLogger("eventLogger");
    private DAO<Description, Long, String> descriptionDAO = new MainDAO<>();
    private DAO<Type, String, Long> typeDAO = new MainDAO<>();
    private DAO<Brand, String, Long> brandDAO = new MainDAO<>();
    private DAO<Owner, String, Long> ownerDAO = new MainDAO<>();
    private DAO<Color, String, Long> colorDAO = new MainDAO<>();
    private DAO<Auto, Long, Long> autoDAO = new MainDAO<>();

    private List<Type> typeList;
    private List<Brand> brandList;

    public CSSystem() {
    }

    public static CSSystem getInstance(){

        if(csSystem == null) {
            csSystem = new CSSystem();
        }
        return csSystem;
    }

    public void initializeDB(){
        initAdminTable("admin","Admin123*");
        initDescriptionTable();
        initColorTable();
    }

    private void initAdminTable(String username, String password){
        DAO<Admin, String, Long> adminDAO = new MainDAO<>();
        Admin admin = adminDAO.findByID(Admin.class, 1L);

        if(admin == null) {
            ValidationService validationService = ValidationService.getInstance();
            admin = new Admin(username, password);
            Map<String, Set<String>> cons = validationService.validate(admin);
            if(cons.isEmpty()) {
                adminDAO.save(admin);
            }
            else LOGGER.error("Error while validating - " + cons.toString());
        }
    }

    private void initDescriptionTable(){
        List<Description> typeList = descriptionDAO.selectAll(Description.class);
        DataSource dataSource = DataSource.getInstance();

        if(typeList.isEmpty()){
            Map<String, Map<String, Set<String>>> descriptions = dataSource.getDescription();
            Set<Map.Entry<String, Map<String, Set<String>>>> typeSet = descriptions.entrySet();

            for(Map.Entry<String, Map<String, Set<String>>> entry : typeSet){
                String type = entry.getKey();
                Type type1 = new Type(type);

                Map<String, Set<String>> brandMap = entry.getValue();
                Set<Map.Entry<String, Set<String>>> brandSet = brandMap.entrySet();

                for(Map.Entry<String, Set<String>> entry1 : brandSet){
                    String brand = entry1.getKey();

                    Set<String> models = entry1.getValue();
                    for(String model : models) {
                        Brand existingBrand = brandDAO.findBy1Value(Brand.class, "name", brand);
                        if(existingBrand != null){
                            Description description = new Description(type1, existingBrand, model);
                            descriptionDAO.save(description);
                        } else {
                            Brand newBrand = new Brand(brand);
                            Description description = new Description(type1, newBrand, model);
                            descriptionDAO.save(description);
                        }
                    }
                }
            }
        }
    }

    public void initColorTable(){
        DAO<Color, String, Long> colorDAO = new MainDAO<>();
        List<Color> typeList = colorDAO.selectAll(Color.class);

        DataSource dataSource = DataSource.getInstance();

        if(typeList.isEmpty()) {
            String[] colors = dataSource.getColors();
            for(int i = 0; i < colors.length; i++){
                Color color = new Color();
                color.setName(colors[i]);
                colorDAO.save(color);
            }
        }
    }

    public Set<String> findBrandsByType(String selectedType) {

        typeList = getAllTypes();
        brandList = getAllBrands();

        Type type = new Type();

        for(int i = 0; i < typeList.size(); i++){
            if(typeList.get(i).getName().equals(selectedType)) {
                type = typeList.get(i);
            }
        }
        List<Description> descriptions = descriptionDAO.findListBy1Value(Description.class, "type", type.getID());

        Set<String> brandNameSet = new HashSet<>();

        for(int i = 0; i < descriptions.size(); i++) {
            System.out.println(descriptions.get(i).getBrand().getName());
            brandNameSet.add(descriptions.get(i).getBrand().getName());
        }

        return brandNameSet;
    }

    public List<String> findModelsByTypeBrand(String selectedType, String selectedBrand){

        typeList = getAllTypes();
        brandList = getAllBrands();

        Type type = new Type();
        Brand brand = new Brand();

        for(int i = 0; i < typeList.size(); i++){
            if(typeList.get(i).getName().equals(selectedType)) {
                type = typeList.get(i);
            }
        }

        for(int i = 0; i < brandList.size(); i++){
            if(brandList.get(i).getName().equals(selectedBrand)) {
                brand = brandList.get(i);
            }
        }

        List<Description> descriptions = descriptionDAO.findListBy2Values(Description.class, "type", "brand", type.getID(), brand.getID());

        List<String> modelStringList = new ArrayList<>();

        for(int i = 0; i < descriptions.size(); i++)
            modelStringList.add(descriptions.get(i).getModel());

        return modelStringList;
    }

    public Auto createAuto(Map<String, String> info){
        Type type = typeDAO.findBy1Value(Type.class, "name", info.get("type"));
        Brand brand = brandDAO.findBy1Value(Brand.class, "name", info.get("brand"));

        Description description1 = descriptionDAO.findBy3Values(
                Description.class, "type", "brand", "model",
                type.getID(), brand.getID(), info.get("model"));

        Color color = colorDAO.findBy1Value(Color.class, "name", info.get("color"));
        Owner owner = ownerDAO.findBy1Value(Owner.class, "name", info.get("ownerName"));

        if(owner == null) {
            owner = new Owner(info.get("ownerName"), info.get("phone"));
        }

        Auto auto = new Auto(owner, description1, color, Integer.parseInt(info.get("kilometers")),
                Integer.parseInt(info.get("horsePower")), info.get("description"),
                Double.parseDouble(info.get("price")));

        return auto;
    }

    public Map<String, Set<String>> validateAuto(Auto auto){
        ValidationService validationService = ValidationService.getInstance();

        Map<String, Set<String>> cons = validationService.validate(auto.getOwner());
        Map<String, Set<String>> consAuto = validationService.validate(auto);

        Set<Map.Entry<String, Set<String>>> entries = consAuto.entrySet();
        for(Map.Entry<String, Set<String>> entry : entries)
            cons.put(entry.getKey(), entry.getValue());

        return cons;
    }

    public void saveAutoInDB(Auto auto){
        autoDAO.save(auto);
    }

    public void deleteAutoFromDB(Auto auto){
        autoDAO.delete(auto);

        List<Auto> autoList = autoDAO.findListBy1Value(Auto.class, "owner", auto.getOwner().getID());

        if(autoList.isEmpty()){
            ownerDAO.delete(auto.getOwner());
        }
    }

    public List<Auto> searchAutos(Map<String, String> elements){

        int intMinHorsePower;
        int intMaxHorsePower;
        int intMinPrice;
        int intMaxPrice;
        int intMinKilos;
        int intMaxKilos;

        Set<Map.Entry<String, String>> entries = elements.entrySet();
        List<Auto> autos = autoDAO.selectAll(Auto.class);
        List<Auto> foundAutos = new ArrayList<>();

        for(Auto auto: autos){

            int need = 0;
            int cnt = 0;

            String type = auto.getDescription().getType().getName();
            String brand = auto.getDescription().getBrand().getName();
            String model = auto.getDescription().getModel();
            String color = auto.getColor().getName();
            int horsePower = auto.getHorsePower();
            double price = auto.getPrice();
            int kilometres = auto.getKilometres();

            for(Map.Entry<String, String> element : entries){
                if(element != null){
                    String subElement = element.getValue();
                    if(subElement != null && !subElement.isEmpty()) {
                        need++;
                        if (type.equals(subElement) || brand.equals(subElement)
                                || model.equals(subElement) || color.equals(subElement))
                            cnt++;
                        switch (element.getKey()) {
                            case "minPower": {
                                if(!elements.get("minPower").isEmpty()) {
                                    intMinHorsePower = Integer.parseInt(elements.get("minPower"));
                                    if (horsePower >= intMinHorsePower)
                                        cnt++;
                                }
                            }
                            break;
                            case "maxPower": {
                                if(!elements.get("maxPower").isEmpty()) {
                                    intMaxHorsePower = Integer.parseInt(elements.get("maxPower"));
                                    if (horsePower <= intMaxHorsePower)
                                        cnt++;
                                }
                            }
                            break;
                            case "minKilos": {
                                if(!elements.get("minKilos").isEmpty()) {
                                    intMinKilos = Integer.parseInt(elements.get("minKilos"));
                                    if (kilometres >= intMinKilos)
                                        cnt++;
                                }
                            }
                            break;
                            case "maxKilos": {
                                if(!elements.get("maxKilos").isEmpty()) {
                                    intMaxKilos = Integer.parseInt(elements.get("maxKilos"));
                                    if (kilometres <= intMaxKilos)
                                        cnt++;
                                }
                            }
                            break;
                            case "minPrice": {
                                if(!elements.get("minPrice").isEmpty()) {
                                    intMinPrice = Integer.parseInt(elements.get("minPrice"));
                                    if (price >= intMinPrice)
                                        cnt++;
                                }
                            }
                            break;
                            case "maxPrice": {
                                if(!elements.get("maxPrice").isEmpty()) {
                                    intMaxPrice = Integer.parseInt(elements.get("maxPrice"));
                                    if (price <= intMaxPrice)
                                        cnt++;
                                }
                            }
                            break;
                        }
                    }
                }
            }
            System.out.println("Need: " + need);
            System.out.println("Cnt: " + cnt);
            if(need == cnt)
                foundAutos.add(auto);
        }

        return foundAutos;
    }

    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public List<Type> getAllTypes(){
        DAO<Type, String, Long> daoType = new MainDAO<>();
        return daoType.selectAll(Type.class);
    }

    public List<Color> getAllColors(){
        DAO<Color, String, Long> daoColor = new MainDAO<>();
        return daoColor.selectAll(Color.class);
    }

    public List<Brand> getAllBrands(){
        DAO<Brand, String, Long> daoColor = new MainDAO<>();
        return daoColor.selectAll(Brand.class);
    }
}
