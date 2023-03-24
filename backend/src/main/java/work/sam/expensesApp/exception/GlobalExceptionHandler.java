package work.sam.expensesApp.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExpenseException.class)
    public ResponseEntity<String> handleExpenseException(ExpenseException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<String> handleUserException(UserException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }

    @ExceptionHandler(DescriptionException.class)
    public ResponseEntity<String> handleDescriptionException(UserException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }

    @ExceptionHandler(MappingException.class)
    public ResponseEntity<String> handleMappingException(UserException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }
}
