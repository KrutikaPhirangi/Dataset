package org.ssce.datasets.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.ssce.datasets.advice.DatasetResponse;
import org.ssce.datasets.model.Dataset;
import org.ssce.datasets.service.DatasetService;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/dataset")
public class DatasetController {
    @Autowired
    private DatasetService datasetService;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    public DatasetController(DatasetService service) {
        this.datasetService = service;
    }

    @PostMapping("/create")
    public DatasetResponse createData(@RequestBody @Valid Dataset dataset) {
        datasetService.createData(dataset);
        DatasetResponse response = new DatasetResponse();
        response.setId("api.dataset.create");
        response.setVer("v1");
        response.setParam(new HashMap<>());
        Map<String, Object> uuid = new HashMap<>();
        uuid.put("id", dataset.getUuid());
        response.setResult(uuid);
        return response;
    }

    @GetMapping("/get/{uuid}")
    public DatasetResponse findByUuid(@PathVariable() UUID uuid) {
        Optional<Dataset> optional = Optional.ofNullable(datasetService.findByUuid(uuid));
        DatasetResponse response = new DatasetResponse();
        response.setId("api.dataset.create");
        response.setVer("v1");
        response.setParam(new HashMap<>());
        Map<String, Object> dataset = new HashMap<>();
        dataset.put("dataset", optional.get());
        response.setResult(dataset);
        return response;
    }
}
