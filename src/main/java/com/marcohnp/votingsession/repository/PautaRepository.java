package com.marcohnp.votingsession.repository;

import com.marcohnp.votingsession.entity.PautaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PautaRepository extends MongoRepository<PautaEntity, String> {
}
