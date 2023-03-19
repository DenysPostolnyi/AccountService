package account.urils;

import account.dto.PersonDTO;
import account.models.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {
    public PersonDTO convertToPersonDTO(Person person){
        return new PersonDTO(person.getName(), person.getLastname(), person.getEmail());
    }
}
