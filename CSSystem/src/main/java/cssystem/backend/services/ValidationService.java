package cssystem.backend.services;

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
    private Map<String, Set<String>> cons = new LinkedHashMap<>();

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
}
