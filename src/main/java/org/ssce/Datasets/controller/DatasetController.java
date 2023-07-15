package org.ssce.Datasets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.ssce.Datasets.advice.DatasetResponse;
import org.ssce.Datasets.model.Dataset;
import org.ssce.Datasets.service.DatasetService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/dataset")
public class DatasetController {

    private final DatasetService datasetService;

    private final DatasetResponse datasetResponse;

    @Autowired
    public DatasetController(DatasetService service,DatasetResponse datasetResponse){
        this.datasetService=service;
        this.datasetResponse=datasetResponse;
    }

    @PostMapping("/create")
    public DatasetResponse createData(@RequestBody @Valid Dataset dataset){

        if(Dataset.Status.values().equals(null)){

        }
        datasetService.createData(dataset);
        Map<String ,Object> ok = new HashMap<>();
        ok.put("err",HttpStatus.OK);
        ok.put("errmsg"," ");

        Map<String , Object> success =  new HashMap<>();
        success.put("id",dataset.getUuid());

        Map<String , Object> err = new HashMap<>();
        err.put("err" , HttpStatus.BAD_REQUEST);
        err.put("errmsg","ERROR");

        Map<String,Object> error = new HashMap<>();

        return new DatasetResponse("api.dataset.create", "v1", ok, HttpStatus.OK, success);
    }

    @GetMapping("/get/{uuid}")
    public List<Dataset> findByUuid(@PathVariable()UUID uuid){
        return datasetService.findByUuid(uuid);
    }
}
