package com.marcohnp.votingsession.repository;

import com.marcohnp.votingsession.entity.SessaoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SessaoRepository extends MongoRepository<SessaoEntity, String> {
}
