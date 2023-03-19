package account.controller;

import account.dto.PersonDTO;
import account.models.Person;
import account.services.PeopleService;
import account.urils.ErrorResponse;
import account.urils.IncorrectDataError;
import account.urils.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final PersonMapper personMapper;
    private final PeopleService peopleService;

    @Autowired
    public AuthController(PersonMapper personMapper, PeopleService peopleService) {
        this.personMapper = personMapper;
        this.peopleService = peopleService;
    }

    @PostMapping("/signup")
    public PersonDTO signup(@RequestBody @Valid Person person, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            bindingResult.getFieldErrors().forEach(System.out::println);
            throw new IncorrectDataError();
        }
        return personMapper.convertToPersonDTO(peopleService.register(person));
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> exceptionHandler(IncorrectDataError error){
        ErrorResponse response = new ErrorResponse(
                System.currentTimeMillis(),
                400,
                "Bad Request",
                "/api/auth/signup"
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
