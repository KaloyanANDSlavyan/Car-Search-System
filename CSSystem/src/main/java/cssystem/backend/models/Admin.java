package cssystem.backend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
public class Admin implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long ID;

    public Admin() {
    }

    @Size(min = 2, message = "Username - too short")
    @Size(max = 50, message = "Username - too long")
    @Pattern.List({
            @Pattern(regexp = "(?!.*[!@#$%\\^&\"\':|*+()\\=?,./]).*", message = "Username - special characters allowed are: -_"),
            @Pattern(regexp = "(?![0-9].*).*", message = "Username - cannot start with digit"),
            @Pattern(regexp = "(?![-].*).*", message = "Username - cannot start with hyphon"),
            @Pattern(regexp = "(?!.*[-]$).*", message = "Username - cannot end with hyphon"),
            @Pattern(regexp = "(?!.*[-].*[-]).*", message = "Username - hyphon is allowed only once"),
            @Pattern(regexp = "(?!.*[_].*[_]).*", message = "Username - underscore is allowed only once")
    })
    private String username;
    @Size(min = 8, message = "Password - too short")
    @Size(max = 30, message = "Password - too long")
    @Pattern.List({
            @Pattern(regexp = "(?=.*[0-9]).*", message = "Password - must contain at least one digit"),
            @Pattern(regexp = "(?=.*[A-Z]).*", message = "Password - must contain at least one capital letter"),
            @Pattern(regexp = "(?=.*[a-z]).*", message = "Password - must contain at least one lowercase letter"),
            @Pattern(regexp = "(?=.*[_@*#$%^&+=]).*", message = "Password - must contain at least one special character"),
            @Pattern(regexp = "(?=\\S+$).*", message = "Password - cannot contain spaces")
    })
    private String password;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
