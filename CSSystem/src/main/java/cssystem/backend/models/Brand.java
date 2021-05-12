package cssystem.backend.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Brand implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long ID;
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "brand", cascade=CascadeType.ALL)
    private Set<Description> descriptions = new HashSet<>();

    public Brand(String name) {
        this.name = name;
    }

    public Brand() {
    }
}
