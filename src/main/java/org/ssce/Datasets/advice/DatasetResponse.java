package org.ssce.Datasets.advice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@Component
@RestControllerAdvice
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatasetResponse extends ResponseEntityExceptionHandler {

    private String id;
    private String ver;
    private Map<String , Object> param ;
    private HttpStatus responseCode;
    private Map<String , Object> result ;


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String,Object> responseBody = new LinkedHashMap<>();
        responseBody.put("id", "api.dataset.create");
        responseBody.put("ver", "v1");
        List<FieldError> fieldErrors= ex.getBindingResult().getFieldErrors();

        List<String> listErrors = new ArrayList<>();

        for (FieldError fieldError : fieldErrors){
            String errorMessage = fieldError.getDefaultMessage();
            listErrors.add(errorMessage);
        }
        responseBody.put("params",listErrors);
        responseBody.put("responseCode", status.value());
        responseBody.put("result",new HashMap<>());

        return new ResponseEntity<>(responseBody,headers,status);
    }
}
