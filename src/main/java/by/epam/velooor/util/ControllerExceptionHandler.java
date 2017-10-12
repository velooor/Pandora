package by.epam.velooor.util;

import by.epam.velooor.exception.ControllerException;
import com.google.gson.Gson;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;


@org.springframework.web.bind.annotation.ControllerAdvice(basePackages = "by.epam.velooor.controller")
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {ControllerException.class})
    public ResponseEntity<String> apartmentsErrorHandler(Exception exception) {
        Gson gson = new Gson();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<String>(gson.toJson(exception.getCause().getMessage()), responseHeaders, HttpStatus.OK);
    }

}
