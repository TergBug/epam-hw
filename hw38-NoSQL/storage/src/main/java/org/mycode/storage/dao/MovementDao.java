package org.mycode.storage.dao;

import org.mycode.storage.model.Movement;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementDao extends PagingAndSortingRepository<Movement, Integer> {
}
