package cssystem.backend;

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
    private DAO<Auto, String, Long> autoDAO = new MainDAO<>();

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

    public void saveAutoInDB(Map<String, String> info, int kilometers, int horsePower, double price){
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

        Auto auto = new Auto(owner, description1, color, kilometers, horsePower, info.get("description"), price);

        autoDAO.save(auto);
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
