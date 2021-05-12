package cssystem.backend.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Type implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long ID;
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "type", cascade=CascadeType.ALL)
    private Set<Description> descriptions = new HashSet<>();

    public Type(String name) {
        this.name = name;
    }

    public Type() {
    }

    public long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }
}
