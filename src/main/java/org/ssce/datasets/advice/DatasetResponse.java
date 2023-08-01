package org.ssce.datasets.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@Component
@RestControllerAdvice
@NoArgsConstructor
public class DatasetResponse extends ResponseEntityExceptionHandler {
    private String id;
    private String ver;
    private Map<String , Object> param ;
    private Map<String , Object> result ;

    public String getId() {
        return id;
    }

    public String getVer() {
        return ver;
    }

    public Map<String, Object> getParam() {
        return param;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public DatasetResponse(String id, String ver, Map<String, Object> param, Map<String, Object> result) {
        this.id = id;
        this.ver = ver;
        this.param = param;
        this.result = result;
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex){
        Map<String, Object> param = new HashMap<>();
        param.put("errMsg",  "Dataset Not Found");
        DatasetResponse response= new DatasetResponse("api.dataset.create", "v1", param, new HashMap<>());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleJsonProcessingException(Exception ex){
        Map<String, Object> param = new HashMap<>();
        param.put("errMsg",  ex.getMessage());
        DatasetResponse response= new DatasetResponse("api.dataset.create", "v1", param, new HashMap<>());
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> param = new HashMap<>();
        param.put("errMsg",  "Invalid id");
        DatasetResponse response= new DatasetResponse("api.dataset.create", "v1", param, new HashMap<>());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
