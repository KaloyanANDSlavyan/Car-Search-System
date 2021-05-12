package cssystem.backend;

import cssystem.backend.dao.DAO;
import cssystem.backend.dao.MainDAO;
import cssystem.backend.models.*;
import cssystem.backend.services.ValidationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CSSystem {

    private static CSSystem csSystem;
    private final Logger LOGGER = LogManager.getLogger("eventLogger");

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
        DAO<Admin, String> adminDAO = new MainDAO<>();
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
        DAO<Description, String> descriptionDAO = new MainDAO<>();
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
                    Brand brand1 = new Brand(brand);

                    Set<String> models = entry1.getValue();
                    for(String model : models) {
                        Description description = new Description(type1, brand1, model);
                        descriptionDAO.save(description);
                    }
                }
            }
        }
    }

    public void initColorTable(){
        DAO<Color, String> colorDAO = new MainDAO<>();
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

    public List<Type> getAllTypes(){
        DAO<Type, String> daoType = new MainDAO<>();
        return daoType.selectAll(Type.class);
    }

    public List<Color> getAllColors(){
        DAO<Color, String> daoColor = new MainDAO<>();
        return daoColor.selectAll(Color.class);
    }
}
