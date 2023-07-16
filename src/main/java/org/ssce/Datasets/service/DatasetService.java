package org.ssce.Datasets.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssce.Datasets.model.Dataset;
import org.ssce.Datasets.respository.DatasetRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class DatasetService {
    @Autowired
    private DatasetRepository repository;

    @Transactional
    public Dataset createData(Dataset dataset) {

        dataset.setCreatedDate(LocalDateTime.now());
        dataset.setUpdatedDate(LocalDateTime.now());
        return repository.save(dataset);
    }

    @Transactional
    public Dataset findByUuid(UUID uuid){
        return repository.findByUuid(uuid);
    }
}
