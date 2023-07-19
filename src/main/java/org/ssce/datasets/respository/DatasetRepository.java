package org.ssce.datasets.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssce.datasets.model.Dataset;

import java.util.UUID;

@Repository
public interface DatasetRepository extends JpaRepository<Dataset, UUID> {
    public Dataset findByUuid(UUID uuid);
}
