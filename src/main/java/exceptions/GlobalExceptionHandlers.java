package exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlers {

    @ExceptionHandler(BusinessException.class)
    public String handleBusinessException(BusinessException ex){
        return ex.getMessage();
    }
}
