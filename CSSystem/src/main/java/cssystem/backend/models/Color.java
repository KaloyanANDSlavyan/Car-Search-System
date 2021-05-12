package cssystem.backend.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Color implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long ID;
    private String name;

    public Color(String name) {
    }

    public Color() {
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
