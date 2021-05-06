package cssystem.backend;

import cssystem.backend.dao.DAO;
import cssystem.backend.dao.MainDAO;
import cssystem.backend.models.Admin;
import cssystem.backend.services.ValidationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        createAdmin("admin","Admin123*");
    }

    private void createAdmin(String username, String password){
        DAO<Admin> adminDAO = new MainDAO<>();
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
}
