package app.domain;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Doctor {
    private int id;
    private String name;
    private boolean active;
    private List<Pat> pats = new ArrayList<>();

    public Doctor() {
    }

    public Doctor(String name) {
        this.name = name;
    }

    public Doctor(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Pat> getProducts() {
        return pats;
    }

    public void setPats(List<Pat> pats) {
        this.pats = pats;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return id == doctor.id && active == doctor.active && Objects.equals(name, doctor.name) && Objects.equals(pats, doctor.pats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, active, pats);
    }


    @Override
    public String toString() {
        return String.format("Доктор: id - %d, имя - %s, активен - %b, лист покупок - %s.",
                id, name, active, pats);
    }
}
