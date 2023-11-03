package jmauriciorlima.com.github.projecttesttask.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UpdateResourceNotFoundException extends RuntimeException {

    public UpdateResourceNotFoundException(String message) {
        super(message);
    }
}
