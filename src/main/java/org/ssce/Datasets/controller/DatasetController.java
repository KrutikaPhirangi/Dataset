package org.ssce.Datasets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.ssce.Datasets.advice.DatasetResponse;
import org.ssce.Datasets.model.Dataset;
import org.ssce.Datasets.service.DatasetService;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/dataset")
public class DatasetController {

    private final DatasetService datasetService;

    @Autowired
    public DatasetController(DatasetService service){
        this.datasetService=service;
    }

    @PostMapping("/create")
    public DatasetResponse createData(@RequestBody @Valid Dataset dataset) {
        if (dataset.getStatus() == null) {
            dataset.setStatus(Dataset.Status.DRAFT);
        }
            datasetService.createData(dataset);
            Map<String, Object> ok = new HashMap<>();
            ok.put("status", HttpStatus.OK.value());
            ok.put("errmsg", " ");

            Map<String, Object> success = new HashMap<>();
            success.put("id", dataset.getUuid());

            return new DatasetResponse("api.dataset.create", "v1", ok, HttpStatus.OK, success);
    }

    @GetMapping("/get/{uuid}")
    public DatasetResponse findByUuid(@PathVariable()UUID uuid) {
        Optional<Dataset> optional = Optional.ofNullable(datasetService.findByUuid(uuid));
        Map<String ,Object> ok = new HashMap<>();
        ok.put("status",HttpStatus.OK.value());
        ok.put("errmsg"," ");

        Map<String , Object> success =  new HashMap<>();
        success.put("dataset",optional.get());

        return new DatasetResponse("api.dataset.create", "v1", ok, HttpStatus.OK, success);
    }
}
