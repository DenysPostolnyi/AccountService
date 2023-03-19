package account.controller;

import account.dto.PersonDTO;
import account.models.Person;
import account.urils.ErrorResponse;
import account.urils.IncorrectDataError;
import account.urils.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final PersonMapper personMapper;

    @Autowired
    public AuthController(PersonMapper personMapper) {
        this.personMapper = personMapper;
    }

    @PostMapping("/signup")
    public PersonDTO signup(@RequestBody @Valid Person person, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            bindingResult.getFieldErrors().forEach(System.out::println);
            throw new IncorrectDataError();
        }
        return personMapper.convertToPersonDTO(person);
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
