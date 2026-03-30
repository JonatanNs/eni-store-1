package fr.eni.tpenistore1.person.sql;

import fr.eni.tpenistore1.person.IPersonDAO;
import fr.eni.tpenistore1.person.Person;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Classe 'UserSQLService'
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 27/02/2026 14:30
 */
@Service
@Profile("mysql")
public class PersonDAOSQL implements IPersonDAO {

    private final PersonSQLRepository repository;

    public PersonDAOSQL(PersonSQLRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Person> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<Person> getById(String id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public void save(Person entity) {
        repository.save(entity);
    }

    @Override
    public void update(String id, Person entity) {
        repository.save(entity);
    }

    @Override
    public Optional<Person> findByEmail(String email) {
        return repository.findByEmail(email);
    }
}

