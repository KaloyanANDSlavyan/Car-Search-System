package cssystem.backend.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Auto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long ID;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner")
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "model_info")
    private Description description;

    @ManyToOne
    @JoinColumn(name = "color")
    private Color color;

    private int kilometres;
    private int horsePower;
    private double price;
    private String details;

    public Auto() {
    }

    public Auto(Owner owner, Description description, Color color, int kilometres, int horsePower, String details, double price) {
        this.owner = owner;
        this.description = description;
        this.color = color;
        this.kilometres = kilometres;
        this.horsePower = horsePower;
        this.price = price;
        this.details = details;
    }

    public long getID() {
        return ID;
    }

    public Description getDescription() {
        return description;
    }

    public Color getColor() {
        return color;
    }

    public int getKilometres() {
        return kilometres;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public String getDetails() {
        return details;
    }
}
