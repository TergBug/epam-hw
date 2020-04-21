package org.mycode.storage.dao;

import org.mycode.storage.model.Storage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageDao extends CrudRepository<Storage, Integer> {
}
