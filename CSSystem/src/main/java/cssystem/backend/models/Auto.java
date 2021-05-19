package cssystem.backend.models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

@Entity
public class Auto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long ID;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "owner")
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "model_info")
    private Description description;

    @ManyToOne
    @JoinColumn(name = "color")
    private Color color;

    @Min(value = 1, message = "Kilometres must be atleast 1")
    private int kilometres;
    @Min(value = 1, message = "Horse power must be atleast 1")
    private int horsePower;
    @Min(value = 1, message = "Price must be atleast 1")
    private double price;
    private String details;
    @Transient
    private String imgSrc;

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

    public double getPrice() {
        return price;
    }

    public Owner getOwner() {
        return owner;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }
}
