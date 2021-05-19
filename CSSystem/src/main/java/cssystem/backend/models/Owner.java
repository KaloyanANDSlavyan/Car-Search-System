package cssystem.backend.models;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Owner implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long ID;
    @Size(min = 2, message = "Name - too short")
    @Size(max = 100, message = "Name - too long")
    @Pattern.List({
            @Pattern(regexp = "(?!.*[0-9]).*", message = "First name - cannot contain digits."),
            @Pattern(regexp = "(?!.*[!@#$%^&*+=?_]).*", message = "First name - cannot contain special characters."),
            @Pattern(regexp = "(?![a-z]).*", message = "First name - should start with a capital letter."),
    })
    private String name;
    @Size(min = 10, message = "Phone number must contain atleast 10 digits")
    @Pattern(regexp = "(?!.*[a-z])(?!.*[A-Z])(?!.*[!@#$%^&*)(_=+'|<>~.,?]).*", message = "Phone number can contain only digits")
    private String phone;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    private Set<Auto> autos = new HashSet<>();

    public Owner() {
    }

    public Owner(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public long getID() {
        return ID;
    }
}
