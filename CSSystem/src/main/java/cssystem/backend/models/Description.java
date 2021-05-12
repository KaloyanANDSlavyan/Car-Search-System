package cssystem.backend.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Description implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long ID;
    private String model;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id")
    private Type type;

    public Description(Type type, Brand brand, String model) {
        this.model = model;
        this.brand = brand;
        this.type = type;
    }

    public Description() {
    }
}
