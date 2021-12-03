package com.marcohnp.votingsession.repository;

import com.marcohnp.votingsession.entity.VotoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VotoRepository extends MongoRepository<VotoEntity, String> {
}
