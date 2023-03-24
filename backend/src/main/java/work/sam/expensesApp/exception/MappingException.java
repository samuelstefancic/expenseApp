package work.sam.expensesApp.exception;

import org.springframework.http.HttpStatus;

public class MappingException extends RuntimeException{

    private final HttpStatus httpStatus;

    public MappingException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }


}
