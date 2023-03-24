package work.sam.expensesApp.exception;

import org.springframework.http.HttpStatus;


public class DescriptionException extends RuntimeException{

    private final HttpStatus httpStatus;

    public DescriptionException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {return httpStatus;}
}
