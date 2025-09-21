package app.repository;

import app.domain.Pat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.io.IOException;

public class PatRepository {
    private final File database;


    private final ObjectMapper mapper;


    private int maxId;


    public PatRepository() throws IOException {
        database = new File("database/pat.txt");
        mapper = new ObjectMapper();

        mapper.enable(SerializationFeature.INDENT_OUTPUT);


        List<Pat> pats = findAll();

        if (!pats.isEmpty()) {
            Pat lastPat = pats.get(pats.size() - 1);
            maxId = lastPat.getId();
        }
    }


    public Pat save(Pat pat) throws IOException {
        pat.setId(++maxId);
        List<Pat> pats = findAll();
        pats.add(pat);
        mapper.writeValue(database, pats);
        return pat;
    }


    public List<Pat> findAll() throws IOException {
        try {
            Pat[] pats = mapper.readValue(database, Pat[].class);
            return new ArrayList<>(Arrays.asList(pats));
        } catch (MismatchedInputException e) {
            return new ArrayList<>();
        }
    }

    // Чтение одного продукта по id
    public Pat findById(int id) throws IOException {
        return findAll()
                .stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
    }


    public void update(Pat pat) throws IOException {
        int id = pat.getId();
        double newPrice = pat.getPrice();
        boolean active = pat.isActive();

        List<Pat> pats = findAll();
        pats
                .stream()
                .filter(x -> x.getId() == id)
                .forEach(
                        x -> {
                            x.setPrice(newPrice);
                            x.setActive(active);
                        }

                );

        mapper.writeValue(database, pats);
    }


    public void deleteById(int id) throws IOException {
        List<Pat> pats = findAll();
        pats.removeIf(x -> x.getId() == id);
        mapper.writeValue(database, pats);
    }
}
