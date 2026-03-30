package fr.eni.tpenistore1.person;

import fr.eni.tpenistore1.dtos.PersonDTO;
import fr.eni.tpenistore1.exceptions.NotFoundException;
import fr.eni.tpenistore1.record.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Classe 'PersonService'
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 11/03/2026 16:27
 */
@Service
public class PersonService {
    private final IPersonDAO personDAO;
    private final PersonMapper personMapper;

    public PersonService(IPersonDAO personDAO, PersonMapper personMapper) {
        this.personDAO = personDAO;
        this.personMapper = personMapper;
    }

    public Page<PersonDTO> getAll(Pageable pageable) {
        return personDAO.getAll(pageable).map(personMapper::personToPersonDTO);
    }

    public PersonDTO getById(String id) {
        return personDAO.getById(id)
                .map(personMapper::personToPersonDTO)
                .orElseThrow(() -> new NotFoundException("Element non trouvé"));
    }

    public PersonDTO save(Person person) {
        personDAO.save(person);
        return personMapper.personToPersonDTO(person);
    }

    public PersonDTO deleteById(String id) {
        Person person = personDAO.getById(id).orElseThrow(() -> new NotFoundException("Element non trouvé"));
        personDAO.deleteById(id);
        return personMapper.personToPersonDTO(person);
    }

    public PersonDTO update(String id, Person person) {
        personDAO.getById(id).orElseThrow(() -> new NotFoundException("Element non trouvé"));
        personDAO.update(id, person);
        return personMapper.personToPersonDTO(person);
    }

    public PersonDTO findByEmail(String email) {
        return personDAO.findByEmail(email)
                .map(personMapper::personToPersonDTO)
                .orElseThrow(() -> new NotFoundException("Element non trouvé"));
    }
}
