package app.exceptions;

public class PatNotFoundException extends Exception{
    public PatNotFoundException(int id) {
        super(String.format("Пациент с идентификатором %d не найден", id));
    }
    public PatNotFoundException(String title) {
        super(String.format("Продукт с именем %s не найден", title));
    }
}
