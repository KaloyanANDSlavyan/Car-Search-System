package cssystem.backend.services;

import cssystem.backend.dao.DAO;
import cssystem.backend.dao.MainDAO;
import cssystem.backend.models.Admin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuthenticationService {
    private final Logger LOGGER = LogManager.getLogger();
    private static AuthenticationService authenticationService;

    public AuthenticationService() {
    }

    public static AuthenticationService getInstance(){
        if(authenticationService == null)
            authenticationService = new AuthenticationService();

        return authenticationService;
    }

    public boolean authenticate(String username, String password){
//        CryptoService cryptoService = CryptoService.getInstance();
//        password = cryptoService.encrypt(password, cryptoService.getKey(), cryptoService.getCipher());
        try {
            DAO<Admin, String> dao = new MainDAO<>();
            Admin admin = dao.findBy2Values(Admin.class, "username", "password", username, password);
            return admin.getUsername().equals(username) && admin.getPassword().equals(password);
            // can be NoResultException
        } catch(Exception e){
            LOGGER.error("An exception occurred during the authorization of Admin: " + ", message: " + e.getMessage());
            return false;
        }
    }
}
