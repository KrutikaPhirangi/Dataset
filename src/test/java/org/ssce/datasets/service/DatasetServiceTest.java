package org.ssce.datasets.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.ssce.datasets.model.Dataset;
import org.ssce.datasets.respository.DatasetRepository;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DatasetServiceTest {

    @Mock
    private DatasetRepository datasetRepository;
    @InjectMocks
    private DatasetService datasetService;

    Dataset dataset;

    @BeforeEach
    void setup(){
        dataset = new Dataset();
        dataset.setUuid(UUID.fromString("57099329-5fc7-40ef-8963-cd06412fe3e4"));
        dataset.setName("Sam");
        dataset.setDataSchema(new HashMap<>());
        dataset.setRouterConfig(new HashMap<>());
        dataset.setStatus(Dataset.Status.DRAFT);
        dataset.setCreatedBy("Oggy");
        dataset.setUpdatedBy("Olly");
        dataset.setCreatedDate(LocalDateTime.now());
        dataset.setUpdatedDate(LocalDateTime.now());
    }

    @Test
    void testCreateDataset(){
        dataset = new Dataset();
        dataset.setUuid(UUID.fromString("57099329-5fc7-40ef-8963-cd06412fe3e4"));
        dataset.setName("Surabhi");
        dataset.setDataSchema(new HashMap<>());
        dataset.setRouterConfig(new HashMap<>());
        dataset.setStatus(Dataset.Status.DRAFT);
        dataset.setCreatedBy("Surabhi");
        dataset.setUpdatedBy("Qwerty");
        dataset.setCreatedDate(LocalDateTime.now());
        dataset.setUpdatedDate(LocalDateTime.now());
        when(datasetRepository.save(any(Dataset.class))).thenReturn(dataset);
        assertThat(datasetService.createData(dataset)).isEqualTo(dataset);
    }

    @Test
    void testGetByUuid(){
        Dataset data = new Dataset();
        data.setUuid(UUID.fromString("57099329-5fc7-40ef-8963-cd06412fe3e4"));
        data.setName("Sam");
        data.setDataSchema(new HashMap<>());
        data.setRouterConfig(new HashMap<>());
        data.setStatus(Dataset.Status.DRAFT);
        data.setCreatedBy("Oggy");
        data.setUpdatedBy("Olly");
        data.setCreatedDate(LocalDateTime.now());
        data.setUpdatedDate(LocalDateTime.now());
        when(datasetRepository.findByUuid(UUID.fromString("57099329-5fc7-40ef-8963-cd06412fe3e4"))).thenReturn(data);
        assertThat(datasetService.findByUuid(UUID.fromString("57099329-5fc7-40ef-8963-cd06412fe3e4")).getName()).isEqualTo(data.getName());
    }

    @Test
    void getDatasetById_failure() {
       when(datasetService.findByUuid(UUID.fromString("692046b9-7134-4354-a7f6-0dca739b5b23"))).thenThrow(new NoSuchElementException("User Not found"));
       Exception exception=assertThrows(NoSuchElementException.class,() ->{
           datasetService.findByUuid(UUID.fromString("692046b9-7134-4354-a7f6-0dca739b5b23"));
       });
       assertTrue(exception.getMessage().contains("User Not found"));
    }
}
