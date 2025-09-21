package app.service;

import app.domain.Pat;
import app.exceptions.PatNotFoundException;
import app.exceptions.PatSaveException;
import app.exceptions.PatUpdateException;
import app.repository.PatRepository;

import java.io.IOException;
import java.util.List;

public class PatService {
    private final PatRepository repository;

    public PatService() throws IOException {
        repository = new PatRepository();
    }

    public Pat save(Pat pat) throws PatSaveException, IOException {
        if (pat == null) {
            throw new PatSaveException("Пациент не может быть null");
        }

        String title = pat.getTitle();
        if (title == null || title.trim().isEmpty()) {
            throw new PatSaveException("Наименование пациента не может быть пустым");
        }

        if (pat.getPrice() <= 0) {
            throw new PatSaveException("Цена Пациента не должна быть отрицательной");
        }

        pat.setActive(true);
        return repository.save(pat);
    }

    public List<Pat> getAllActivePats() throws IOException {
        return repository.findAll()
                .stream()
                .filter(Pat::isActive)
//                .filter(x -> x.isActive())
                .toList();
    }

    public Pat getActivePatById(int id) throws IOException, PatNotFoundException {
        Pat pat = repository.findById(id);

        if (pat == null || !pat.isActive()) {
            throw new PatNotFoundException(id);
        }
        return pat;
    }


    public void update(Pat pat) throws PatUpdateException, IOException {
        if (pat == null) {
            throw new PatUpdateException("Пациент не может быть null");
        }

        if (pat.getPrice() <= 0) {
            throw new PatUpdateException("Цена пациента должна быть положительной");
        }

        pat.setActive(true);
        repository.update(pat);
    }

    public void deleteById(int id) throws IOException, PatNotFoundException {
        Pat pat = getActivePatById(id);
        pat.setActive(false);
        repository.update(pat);
    }


    public void deleteByTitle(String title) throws IOException, PatNotFoundException {
        Pat pat = getAllActivePats()
                .stream()
                .filter(x -> x.getTitle().equals(title))
                .peek(x -> x.setActive(false))
                .findFirst()
                .orElseThrow(
                        () -> new PatNotFoundException(title)
                );
        repository.update(pat);
    }


    public void restoreById(int id) throws IOException, PatNotFoundException {
        Pat pat = repository.findById(id);

        if (pat != null) {
            pat.setActive(true);
            repository.update(pat);
        } else {
            throw new PatNotFoundException(id);
        }
    }


    public int getActivePatsCount() throws IOException {
        return getAllActivePats().size();
    }


    public double getActivePatsTotalCost() throws IOException {
        return getAllActivePats()
                .stream()
                .mapToDouble(Pat::getPrice)
                .sum();
    }


    public double getActivePatsAveragePrice() throws IOException {
        int patCount = getActivePatsCount();

        if (patCount == 0) {
            return 0.0;
        }

        return getActivePatsTotalCost() / patCount;
    }
}
