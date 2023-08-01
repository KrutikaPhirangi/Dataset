package org.ssce.datasets.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.ssce.datasets.model.Dataset;
import org.ssce.datasets.respository.DatasetRepository;

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
        UUID uuid = UUID.fromString("74059a86-98c1-45ea-b7ce-1609b71c87f9");
        dataset = new Dataset(uuid, "datasetEmployee", new HashMap<>(), new HashMap<>(), Dataset.Status.DRAFT,
                "datasetEmployee", "datasetEmployee", System.currentTimeMillis(), System.currentTimeMillis());
    }

    @Test
    void testCreateDataset(){
        UUID uuid = UUID.fromString("74059a86-98c1-45ea-b7ce-1609b71c87f9");
        dataset = new Dataset(uuid, "datasetEmployee", new HashMap<>(), new HashMap<>(), Dataset.Status.DRAFT,
                "datasetEmployee", "datasetEmployee", System.currentTimeMillis(), System.currentTimeMillis());
        when(datasetRepository.save(any(Dataset.class))).thenReturn(dataset);
        assertThat(datasetService.createData(dataset)).isEqualTo(dataset);
    }

    @Test
    void testGetByUuid(){
        UUID uuid = UUID.fromString("74059a86-98c1-45ea-b7ce-1609b71c87f9");
        dataset = new Dataset(uuid, "datasetEmployee", new HashMap<>(), new HashMap<>(), Dataset.Status.DRAFT,
                "datasetEmployee", "datasetEmployee", System.currentTimeMillis(), System.currentTimeMillis());
        when(datasetRepository.findByUuid(UUID.fromString("3fe59817-6892-4cd9-832a-6f96d7a6cd1a"))).thenReturn(dataset);
        assertThat(datasetService.findByUuid(UUID.fromString("3fe59817-6892-4cd9-832a-6f96d7a6cd1a")).getName()).isEqualTo(dataset.getName());
    }

    @Test
    void getDatasetById_failure() {
       when(datasetService.findByUuid(UUID.fromString("692046b9-7134-4354-a7f6-0dca739b5b23"))).thenThrow(new NoSuchElementException("Data Not found"));
       Exception exception=assertThrows(NoSuchElementException.class,() ->{
           datasetService.findByUuid(UUID.fromString("692046b9-7134-4354-a7f6-0dca739b5b23"));
       });
       assertTrue(exception.getMessage().contains("Data Not found"));
    }
}
