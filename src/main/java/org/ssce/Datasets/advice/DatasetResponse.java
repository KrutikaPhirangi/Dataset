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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.ConnectException;
import java.util.*;

@Component
@RestControllerAdvice
@Data
@NoArgsConstructor
public class DatasetResponse extends ResponseEntityExceptionHandler {

    private String id;
    private String ver;
    private Map<String , Object> param ;
    private Map<String , Object> result ;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String,Object> responseBody = new LinkedHashMap<>();
        responseBody.put("id", "api.dataset.create");
        responseBody.put("ver", "v1");
        List<FieldError> fieldErrors= ex.getBindingResult().getFieldErrors();

        Map<String,Object> listErrors = new HashMap<>();

        for (FieldError fieldError : fieldErrors){
            String errorMessage = fieldError.getDefaultMessage();
            listErrors.put("errMsg",errorMessage);
            listErrors.put("err",HttpStatus.BAD_REQUEST);
        }
        responseBody.put("params",listErrors);
        responseBody.put("responseCode", "FAILED");
        responseBody.put("result",new HashMap<>());

        return new ResponseEntity<>(responseBody,headers,status);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String,Object> responseBody = new LinkedHashMap<>();
        responseBody.put("id", "api.dataset.create");
        responseBody.put("ver", "v1");
        Map<String,Object> param = new HashMap<>();
        param.put("errMsg","SET STATUS");
        param.put("err",HttpStatus.BAD_REQUEST);
        responseBody.put("param",param);
        responseBody.put("responseCode",status.value());
        responseBody.put("result", new HashMap<>());

        return new ResponseEntity<>(responseBody,headers,status);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex){
        Map<String,Object> responseBody = new LinkedHashMap<>();
        responseBody.put("id", "api.dataset.create");
        responseBody.put("ver", "v1");
        Map<String,Object> param = new HashMap<>();
        param.put("errMsg","Invalid UUId");
        param.put("err",HttpStatus.BAD_REQUEST);
        responseBody.put("param",param);
        responseBody.put("responseCode","FAILED");
        responseBody.put("result", new HashMap<>());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex){
        Map<String,Object> responseBody = new LinkedHashMap<>();
        responseBody.put("id", "api.dataset.create");
        responseBody.put("ver", "v1");
        Map<String,Object> param = new HashMap<>();
        param.put("errMsg","Dataset Not Found");
        responseBody.put("param",param);
        responseBody.put("result", new HashMap<>());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String,Object> responseBody = new LinkedHashMap<>();
        responseBody.put("id", "api.dataset.create");
        responseBody.put("ver", "v1");
        Map<String,Object> param = new HashMap<>();
        param.put("errMsg","Invalid URI need parameter");
        responseBody.put("param",param);
        responseBody.put("result", new HashMap<>());

        return new ResponseEntity<>(responseBody,headers,status);
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<Object> handleConnectException(ConnectException ex){
        Map<String,Object> responseBody = new LinkedHashMap<>();
        responseBody.put("id", "api.dataset.create");
        responseBody.put("ver", "v1");
        Map<String,Object> param = new HashMap<>();
        param.put("errMsg","Server is currently unavailable");
        responseBody.put("param",param);
        responseBody.put("result", new HashMap<>());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }
}
