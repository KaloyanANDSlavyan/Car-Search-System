package cssystem.backend.services;

import cssystem.backend.CSSystem;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

//This class will be used where validation is needed
public class ValidationService {
    private static ValidationService validationService;
    private ValidatorFactory factory;
    private Validator validator;
    private CSSystem csSystem = CSSystem.getInstance();

    public static ValidationService getInstance() {
        if (validationService == null)
            validationService = new ValidationService();
        return validationService;
    }

    public ValidationService() {
        createFactory();
        createValidator();
    }

    private void createFactory() {
        factory = Validation.buildDefaultValidatorFactory();
    }

    private void createValidator() {
        validator = factory.getValidator();
    }

    public Map<String, Set<String>> validate(Object object) {
        Set<ConstraintViolation<Object>> constraints = validator.validate(object);

        Map<String, Set<String>> cons = new LinkedHashMap<>();

        if (!constraints.isEmpty()) {
            cons.clear();
            for (ConstraintViolation<Object> con : constraints)
                cons.put(con.getPropertyPath().toString(), new LinkedHashSet<>());

            Set<Map.Entry<String, Set<String>>> entries = cons.entrySet();

            for (ConstraintViolation<Object> con : constraints) {
                for (Map.Entry<String, Set<String>> entry : entries)
                    if (con.getPropertyPath().toString().equals(entry.getKey()))
                        entry.getValue().add(con.getMessage());
            }
        }
        return cons;
    }

    public boolean allDataFilled(Map<String, String> data){
        if(data.get("color") != null
                && data.get("type") != null
                && data.get("brand") != null
                && data.get("model") != null
                && !data.get("ownerName").isEmpty()
                && !data.get("phone").isEmpty()
                && !data.get("description").isEmpty()
                && !data.get("price").isEmpty()
                && !data.get("kilometers").isEmpty()
                && !data.get("horsePower").isEmpty())
            return true;

        return false;
    }

    public boolean isDataNumeric(Map<String, String> data){
        if(csSystem.isNumeric(data.get("kilometers"))
                && csSystem.isNumeric(data.get("price"))
                && csSystem.isNumeric(data.get("horsePower"))
                && csSystem.isNumeric(data.get("phone")))
            return true;

        return false;
    }
}
