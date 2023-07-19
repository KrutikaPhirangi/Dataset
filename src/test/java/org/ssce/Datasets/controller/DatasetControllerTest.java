package org.ssce.Datasets.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.ssce.Datasets.model.Dataset;
import org.ssce.Datasets.service.DatasetService;

import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DatasetController.class)
@ExtendWith(MockitoExtension.class)
public class DatasetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DatasetService datasetService;

    @Test
    void testCreateDataset() throws Exception {
        Dataset data = new Dataset();
        data.setUuid(UUID.randomUUID());
        data.setName("Sam");
        data.setDataSchema(new HashMap<>());
        data.setRouterConfig(new HashMap<>());
        data.setStatus(Dataset.Status.DRAFT);
        data.setCreatedBy("Oggy");
        data.setUpdatedBy("Olly");
        data.setCreatedDate(LocalDateTime.now());
        data.setUpdatedDate(LocalDateTime.now());
        when(datasetService.createData(any(Dataset.class))).thenReturn(data);
        this.mockMvc.perform(post("/dataset/create").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(data)))
                        .andExpect(status().isOk())
                        .andDo(print());
        }

    @Test
    void testCreateDataError() throws Exception {
        Dataset dataset = new Dataset();
        dataset.setUuid(UUID.fromString("51ababe2-a1e7-48ac-9e0d-c8be18eeadea"));
        dataset.setName("Sam12");
        dataset.setDataSchema(new HashMap<>());
        dataset.setRouterConfig(new HashMap<>());
        dataset.setStatus(Dataset.Status.DRAFT);
        dataset.setCreatedBy("Oggy");
        dataset.setUpdatedBy("Olly");
        dataset.setCreatedDate(LocalDateTime.now());
        dataset.setUpdatedDate(LocalDateTime.now());
        when(datasetService.createData(any(Dataset.class))).thenReturn(dataset);

        this.mockMvc.perform(post("/dataset/create").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dataset)))
                        .andExpect(status().isBadRequest())
                        .andDo(print());
    }

    @Test
    void testGetDatasetError() throws Exception {
        Dataset dataset = new Dataset();
        dataset.setUuid(UUID.fromString("51ababe2-a1e7-48ac-9e0d-c8be18eeadea"));
        dataset.setName("Sam12");
        dataset.setDataSchema(new HashMap<>());
        dataset.setRouterConfig(new HashMap<>());
        dataset.setStatus(Dataset.Status.DRAFT);
        dataset.setCreatedBy("Oggy");
        dataset.setUpdatedBy("Olly");
        dataset.setCreatedDate(LocalDateTime.now());
        dataset.setUpdatedDate(LocalDateTime.now());
        when(datasetService.findByUuid(UUID.fromString("51ababe2-a1e7-48ac-9e0d-c8be18eeadea"))).thenReturn(dataset);
        this.mockMvc.perform(get("/dataset/get/51ababe2-a1e7-48ac-9e0d")).andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    void testGetDataset() throws Exception {
        Dataset dataset = new Dataset();
        dataset.setUuid(UUID.fromString("51ababe2-a1e7-48ac-9e0d-c8be18eeadea"));
        dataset.setName("Sam12");
        dataset.setDataSchema(new HashMap<>());
        dataset.setRouterConfig(new HashMap<>());
        dataset.setStatus(Dataset.Status.DRAFT);
        dataset.setCreatedBy("Oggy");
        dataset.setUpdatedBy("Olly");
        dataset.setCreatedDate(LocalDateTime.now());
        dataset.setUpdatedDate(LocalDateTime.now());
        when(datasetService.findByUuid(UUID.fromString("51ababe2-a1e7-48ac-9e0d-c8be18eeadea"))).thenReturn(dataset);
        this.mockMvc.perform(get("/dataset/get/51ababe2-a1e7-48ac-9e0d-c8be18eeadea")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testGetDatasetErrors() throws Exception {
        Dataset dataset = new Dataset();
        dataset.setUuid(UUID.fromString("51ababe2-a1e7-48ac-9e0d-c8be18eeadea"));
        dataset.setName("Sam12");
        dataset.setDataSchema(new HashMap<>());
        dataset.setRouterConfig(new HashMap<>());
        dataset.setStatus(Dataset.Status.DRAFT);
        dataset.setCreatedBy("Oggy");
        dataset.setUpdatedBy("Olly");
        dataset.setCreatedDate(LocalDateTime.now());
        dataset.setUpdatedDate(LocalDateTime.now());
        when(datasetService.findByUuid(UUID.fromString("51ababe2-a1e7-48ac-9e0d-c8be18eeadea"))).thenReturn(dataset);
        this.mockMvc.perform(get("/dataset/get")).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    void testGetDatasetNotFound() throws Exception {
        Dataset dataset = new Dataset();
        dataset.setUuid(UUID.fromString("51ababe2-a1e7-48ac-9e0d-c8be18eeadea"));
        dataset.setName("Sam12");
        dataset.setDataSchema(new HashMap<>());
        dataset.setRouterConfig(new HashMap<>());
        dataset.setStatus(Dataset.Status.DRAFT);
        dataset.setCreatedBy("Oggy");
        dataset.setUpdatedBy("Olly");
        dataset.setCreatedDate(LocalDateTime.now());
        dataset.setUpdatedDate(LocalDateTime.now());
        when(datasetService.findByUuid(UUID.fromString("51ababe2-a1e7-48ac-9e0d-c8be18eeadea"))).thenReturn(dataset);
        this.mockMvc.perform(get("/dataset/get/1")).andDo(print()).andExpect(status().isBadRequest());
    }

}
