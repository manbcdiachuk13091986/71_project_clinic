package app.controller;

import app.domain.Pat;
import app.exceptions.PatNotFoundException;
import app.exceptions.PatSaveException;
import app.exceptions.PatUpdateException;
import app.service.PatService;

import java.util.List;
import java.io.IOException;

public class PatController {
    private final PatService service;

    public PatController() throws IOException {
        service = new PatService();
    }

    public Pat save(String title, double price) throws PatSaveException, IOException {
        Pat pat = new Pat(title, price);
        return service.save(pat);
    }

    public List<Pat> getAllActivePats() throws IOException {
        return service.getAllActivePats();
    }

    public Pat getActivePatById(int id) throws IOException, PatNotFoundException {
        return service.getActivePatById(id);
    }

    public void update(int id, double price) throws PatUpdateException, IOException {
        Pat pat = new Pat(id, price);
        service.update(pat);
    }

    public void deleteById(int id) throws IOException, PatNotFoundException {
        service.deleteById(id);
    }

    public void deleteByTitle(String title) throws IOException, PatNotFoundException {
        service.deleteByTitle(title);
    }

    public void restoreById(int id) throws IOException, PatNotFoundException {
        service.restoreById(id);
    }

    public int getActivePatsCount() throws IOException {
        return service.getActivePatsCount();
    }

    public double getActivePatsTotalCost() throws IOException {
        return service.getActivePatsTotalCost();
    }

    public double getActivePatsAveragePrice() throws IOException {
        return service.getActivePatsAveragePrice();
    }
}
