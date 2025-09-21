package app.domain;

import java.util.Objects;

public class Pat {
    private int id;
    private String title;
    private double price;
    private boolean active;

    public Pat() {
    }

    public Pat(String title, double price) {
        this.title = title;
        this.price = price;
    }

    public Pat(int id, double price) {
        this.id = id;
        this.price = price;
    }

    public Pat(int id, String title, double price, boolean active) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pat pat = (Pat) o;
        return id == pat.id && Double.compare(price, pat.price) == 0 && active == pat.active && Objects.equals(title, pat.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, active);
    }


    @Override
    public String toString() {
        return String.format("т: id Пациент- %d, наименование - %s, цена - %.2f, активен - %b.",
                id, title, price, active);
    }
}
