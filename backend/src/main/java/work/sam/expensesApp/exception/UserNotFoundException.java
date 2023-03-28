package work.sam.expensesApp.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends UserException{
    public UserNotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus.NOT_FOUND);
    }
}
