package work.sam.expensesApp.exception;

import org.springframework.http.HttpStatus;

public class UserException extends RuntimeException{
    private final HttpStatus httpStatus;

    public UserException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }



    public HttpStatus getHttpStatus() {return httpStatus;}
}
