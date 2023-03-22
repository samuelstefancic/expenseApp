package work.sam.expensesApp.exception;

import org.springframework.http.HttpStatus;

public class ExpenseException extends RuntimeException {
    private final HttpStatus httpStatus;

    public ExpenseException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {return httpStatus;}
}
