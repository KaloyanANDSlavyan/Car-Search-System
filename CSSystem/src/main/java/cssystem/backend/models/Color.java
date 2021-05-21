package cssystem.backend.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Color implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long ID;
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "color")
    private Set<Auto> autos = new HashSet<>();

    public Color(String name) {
    }

    public Color() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
