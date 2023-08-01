package org.ssce.datasets.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssce.datasets.model.Dataset;
import org.ssce.datasets.respository.DatasetRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class DatasetService {
    @Autowired
    private DatasetRepository repository;

    @Transactional
    public Dataset createData( Dataset dataset) {
        dataset.setCreatedDate(System.currentTimeMillis());
        dataset.setUpdatedDate(System.currentTimeMillis());
        System.out.println(dataset);
        return repository.save(dataset);
    }

    @Transactional
    public Dataset findByUuid(UUID uuid){
        return repository.findByUuid(uuid);
    }
}
