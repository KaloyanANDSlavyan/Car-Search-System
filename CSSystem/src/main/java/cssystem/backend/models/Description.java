package cssystem.backend.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Model_info")
public class Description implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long ID;
    private String model;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "brand")
    private Brand brand;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type")
    private Type type;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "description")
    private Set<Auto> autos = new HashSet<>();

    public Description(Type type, Brand brand, String model) {
        this.model = model;
        this.brand = brand;
        this.type = type;
    }

    public Description() {
    }

    public String getModel() {
        return model;
    }

    public Brand getBrand() {
        return brand;
    }

    public Type getType() {
        return type;
    }

}
