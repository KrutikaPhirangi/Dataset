package org.ssce.datasets.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.ssce.datasets.advice.DatasetResponse;
import org.ssce.datasets.model.Dataset;
import org.ssce.datasets.service.DatasetService;

import java.io.InputStream;
import java.util.*;

@RestController
@RequestMapping("/dataset")
public class DatasetController {
    @Autowired
    private DatasetService datasetService;

    private ObjectMapper objectMapper = new ObjectMapper();

    public DatasetController(DatasetService service) {
        this.datasetService = service;
    }

    @PostMapping("/create")
    public DatasetResponse createData(@RequestBody String requestBody) throws Exception {
        InputStream schemaStream = DatasetController.class.getClassLoader().getResourceAsStream("model/dataset.schema.json");
        JsonSchema schema = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7).getSchema(schemaStream);
        JsonNode jsonNode = objectMapper.readTree(requestBody);

        Set<ValidationMessage> errors = schema.validate(jsonNode);
        List<String> listError = new ArrayList<>();
        for (ValidationMessage err : errors) {
           listError.add(err.toString());
        }
        if (errors.size() > 0) {
            throw new Exception(listError.toString());
        } else {
            Dataset dataset = objectMapper.readValue(requestBody, Dataset.class);

            Dataset saveDataset = datasetService.createData(dataset);
            Map<String, Object> result = new HashMap<>();
            result.put("id", saveDataset.getUuid());
            return new DatasetResponse("api.dataset.create", "v1", new HashMap<>(), result);
        }
    }

    @GetMapping("/get/{uuid}")
    public DatasetResponse findByUuid(@PathVariable() UUID uuid) {
        Optional<Dataset> optional = Optional.ofNullable(datasetService.findByUuid(uuid));
        Map<String, Object> result = new HashMap<>();
        result.put("dataset", optional.get());
        return new DatasetResponse("api.dataset.create", "v1", new HashMap<>(), result);
    }
}
