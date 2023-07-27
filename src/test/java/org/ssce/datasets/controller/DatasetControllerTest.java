package org.ssce.datasets.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.ssce.datasets.model.Dataset;
import org.ssce.datasets.service.DatasetService;

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
        data.setName("datasetEmployeeTwo");
        data.setDataSchema(new HashMap<>());
        data.setRouterConfig(new HashMap<>());
        data.setStatus(Dataset.Status.DRAFT);
        data.setCreatedBy("datasetEmployeeTwo");
        data.setUpdatedBy("datasetEmployeeTwo");
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
        dataset.setUuid(UUID.fromString("19a53566-6990-4a3f-8e82-585412df00b2"));
        dataset.setName("datasetEmployee2");
        dataset.setDataSchema(new HashMap<>());
        dataset.setRouterConfig(new HashMap<>());
        dataset.setStatus(Dataset.Status.DRAFT);
        dataset.setCreatedBy("datasetEmployeeTwo");
        dataset.setUpdatedBy("datasetEmployeeTwo");
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
        dataset.setUuid(UUID.fromString("19a53566-6990-4a3f-8e82-585412df00b2"));
        dataset.setName("datasetEmployee2");
        dataset.setDataSchema(new HashMap<>());
        dataset.setRouterConfig(new HashMap<>());
        dataset.setStatus(Dataset.Status.DRAFT);
        dataset.setCreatedBy("datasetEmployeeTwo");
        dataset.setUpdatedBy("datasetEmployeeTwo");
        dataset.setCreatedDate(LocalDateTime.now());
        dataset.setUpdatedDate(LocalDateTime.now());
        when(datasetService.findByUuid(UUID.fromString("19a53566-6990-4a3f-8e33-585412df00b2"))).thenReturn(dataset);
        this.mockMvc.perform(get("/dataset/get/19a53566-6990-4585412df00b2")).andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    void testGetDataset() throws Exception {
        Dataset dataset = new Dataset();
        dataset.setUuid(UUID.fromString("19a53566-6990-4a3f-8e82-585412df00b2"));
        dataset.setName("datasetEmployee2");
        dataset.setDataSchema(new HashMap<>());
        dataset.setRouterConfig(new HashMap<>());
        dataset.setStatus(Dataset.Status.DRAFT);
        dataset.setCreatedBy("datasetEmployeeTwo");
        dataset.setUpdatedBy("datasetEmployeeTwo");
        dataset.setCreatedDate(LocalDateTime.now());
        dataset.setUpdatedDate(LocalDateTime.now());
        when(datasetService.findByUuid(UUID.fromString("19a53566-6990-4a3f-8e82-585412df00b2"))).thenReturn(dataset);
        this.mockMvc.perform(get("/dataset/get/19a53566-6990-4a3f-8e82-585412df00b2")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testGetDatasetErrors() throws Exception {
        Dataset dataset = new Dataset();
        dataset.setUuid(UUID.fromString("51ababe2-a1e7-48ac-9e0d-c8be18eeadea"));
        dataset.setName("datasetEmployee2");
        dataset.setDataSchema(new HashMap<>());
        dataset.setRouterConfig(new HashMap<>());
        dataset.setStatus(Dataset.Status.DRAFT);
        dataset.setCreatedBy("datasetEmployeeTwo");
        dataset.setUpdatedBy("datasetEmployeeTwo");
        dataset.setCreatedDate(LocalDateTime.now());
        dataset.setUpdatedDate(LocalDateTime.now());
        when(datasetService.findByUuid(UUID.fromString("51ababe2-a1e7-48ac-9e0d-c8be18eeadea"))).thenReturn(dataset);
        this.mockMvc.perform(get("/dataset/get")).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    void testGetDatasetNotFound() throws Exception {
        Dataset dataset = new Dataset();
        dataset.setUuid(UUID.fromString("51ababe2-a1e7-48ac-9e0d-c8be18eeadea"));
        dataset.setName("dataset12");
        dataset.setDataSchema(new HashMap<>());
        dataset.setRouterConfig(new HashMap<>());
        dataset.setStatus(Dataset.Status.DRAFT);
        dataset.setCreatedBy("user");
        dataset.setUpdatedBy("user1");
        dataset.setCreatedDate(LocalDateTime.now());
        dataset.setUpdatedDate(LocalDateTime.now());
        when(datasetService.findByUuid(UUID.fromString("51ababe2-a1e7-48ac-9e0d-c8be18eeadea"))).thenReturn(dataset);
        this.mockMvc.perform(get("/dataset/get/1")).andDo(print()).andExpect(status().isBadRequest());
    }

}
