package org.ssce.Datasets.advice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
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

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String,Object> responseBody = new LinkedHashMap<>();
        responseBody.put("id", "api.dataset.create");
        responseBody.put("ver", "v1");
        String msg =e.getLocalizedMessage();
        responseBody.put("param",msg);
        responseBody.put("responseCode",status.value());
        responseBody.put("result", new HashMap<>());

        return new ResponseEntity<>(responseBody,headers,status);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatchException( IllegalArgumentException e, HttpStatus status){
        Map<String,Object> responseBody = new LinkedHashMap<>();
        responseBody.put("id", "api.dataset.create");
        responseBody.put("ver", "v1");
        Map<String,Object> param = new LinkedHashMap<>();
        param.put("status",e.getMessage());
        responseBody.put("param",param);
        responseBody.put("responseCode",status.value());
        responseBody.put("result",new HashMap<>());

        return new ResponseEntity<>(responseBody,status);
    }
}
