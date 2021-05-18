package cssystem.backend.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Owner implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long ID;
    private String name;
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
}
